import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class ArtworkController {

	@FXML
	private Label categoryA;

	@FXML
	private Label titleA;

	@FXML
	private Label creatorA;

	@FXML
	private Label yearA;

	@FXML
	private Label noOfBidsA;

	@FXML
	private Label bidsLimitA;

	@FXML
	private Label postcode1;

	@FXML
	private Label postcode2;

	@FXML
	private Label widthA;

	@FXML
	private Label heightA;

	@FXML
	private Label depthA;

	@FXML
	private Label materialA;

	@FXML
	private Label currentPrice;

	@FXML
	private Label sellerA;

	@FXML
	private TextField bidAmount;

	@FXML
	private Button placeBid;
	
    @FXML
    private ImageView mainPic;

    @FXML
    private ImageView pic1;

    @FXML
    private ImageView pic2;

    @FXML
    private ImageView pic3;

    @FXML
    private ImageView pic4;

	private static Painting currentPainting;
	private static Sculpture currentSculpture;

	public void initialize() {
		if (currentPainting != null) {
			initializePainting();

		}

		if (currentSculpture != null) {
			initializeSculpture();
		}
		
		placeBid.setOnAction(e-> addBid());
		try {
			FileReader.constructBid(LoginController.getUser().getUsername()+ ".txt");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Total no. of bids "+ FileReader.getBids().size());
	}

	public void initializePainting() {
		currentPainting.resolveImage();
		titleA.setText(currentPainting.getTitle());
		widthA.setText(currentPainting.getWidth() + "");
		heightA.setText(currentPainting.getHeight() + "");
		yearA.setText(currentPainting.getCreationYear() + "");
		creatorA.setText(currentPainting.getCreator());
		titleA.setText(currentPainting.getTitle());
		titleA.setText(currentPainting.getTitle());
		noOfBidsA.setText(currentPainting.getNumberOfBids() + "");
		bidsLimitA.setText(currentPainting.getBidsAllowed() + "");
		mainPic.setImage(currentPainting.getImage());

	}

	public void initializeSculpture() {
		currentSculpture.resolveImage();
		currentSculpture.resolveAdditionalImages();

		ArrayList<Image> additionalImages = currentSculpture.getAdditionalImages();
		ArrayList<ImageView> imageViews = new ArrayList<>();
		
		imageViews.add(pic1);
		imageViews.add(pic2);
		imageViews.add(pic3);
		imageViews.add(pic4);

		
		for(int i = 0; i< additionalImages.size(); i++) {
			imageViews.get(i).setImage((additionalImages.get(i)));
		}
		mainPic.setImage(currentSculpture.getImage());

		titleA.setText(currentSculpture.getTitle());
		widthA.setText(currentSculpture.getWidth() + "");
		heightA.setText(currentSculpture.getHeight() + "");
		depthA.setText(currentSculpture.getDepth() + "");
		materialA.setText(currentSculpture.getMaterial());
		yearA.setText(currentSculpture.getCreationYear() + "");
		creatorA.setText(currentSculpture.getCreator());
		titleA.setText(currentSculpture.getTitle());
		titleA.setText(currentSculpture.getTitle());
		noOfBidsA.setText(currentSculpture.getNumberOfBids() + "");
		bidsLimitA.setText(currentSculpture.getBidsAllowed() + "");

	}

	public static void setCurrentPainting(Painting painting) {
		currentPainting = painting;
		currentSculpture = null;

	}
	
	
	public void addBid() {
		
		String type = "";
		Bid bid = null;
		
		if(currentSculpture != null) {
			type = "sculpture";
			String amountStr = bidAmount.getText();
			double amount = Double.parseDouble(amountStr);
			Date date = new Date();
			bid = new Bid(type, LoginController.getUser(), amount, currentSculpture, date);
			LoginController.getUser().addBid(bid);

		} else if (currentPainting != null) {
			type = "painting";
			String amountStr = bidAmount.getText();
			double amount = Double.parseDouble(amountStr);
			Date date = new Date();
			bid = new Bid(type, LoginController.getUser(), amount, currentPainting, date);
			LoginController.getUser().addBid(bid);
		}
		

		try {
			Writer.writeBidFile(bid);
			System.out.println("Bid saved successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Number of bids of user "+ LoginController.getUser() +" is "+ LoginController.getUser().getPlacedBids().size());
	}
	

	public static void setCurrentSculpture(Sculpture sculpture) {
		currentPainting = null;

		currentSculpture = sculpture;

	}

}
