import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class ArtworkController {

	@FXML
	private ImageView sellerAvatar;

	@FXML
	private Button addToFav;

	@FXML
	private Label noOfBids;

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
			currentPrice.setText(currentPainting.getHighestBidAmount() + "");

		}

		if (currentSculpture != null) {
			initializeSculpture();
			currentPrice.setText(currentSculpture.getHighestBid() + "");

		}

		placeBid.setOnAction(e -> addBid());
	
		System.out.println("Total no. of bids " + FileReader.getBids().size());
		System.out.println("Total no. of users " + FileReader.getUsers().size());

		addToFav.setOnAction(e -> showUser());

	}

	public void showUser() {

		User user = null;

		if (currentSculpture != null) {
			if (currentSculpture.getOwner() != null) {
				user = currentSculpture.getOwner();
			} else if (currentPainting != null) {
			
			}

		}else if (currentPainting!= null) {
			user = currentPainting.getOwner();
		}
		UserDisplayController.setUser(user);

		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("/UserDisplay.fxml"));
		try {
			Parent root = fxmlL.load();
			// NewAccountCreatorController newAccountController =
			// fxmlL.<NewAccountCreatorController>getController();

			Scene scene = new Scene(root, 450, 300);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.setTitle(user.getUsername());
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		noOfBidsA.setText(currentPainting.getNumberOfPlacedBids() + "");
		bidsLimitA.setText(currentPainting.getBidsAllowed() + "");
		mainPic.setImage(currentPainting.getImage());
		// sellerAvatar.setImage(currentPainting.getOwner().getImage());
		sellerA.setText(currentPainting.getOwner().getUsername());
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

		for (int i = 0; i < additionalImages.size(); i++) {
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
		noOfBidsA.setText(currentSculpture.getNumberOfPlacedBids() + "");
		System.out.println("Number of bids----------------------- is " + currentSculpture.getNumberOfPlacedBids());
		bidsLimitA.setText(currentSculpture.getBidsAllowed() + "");

		User owner = currentSculpture.getOwner();
		String path = "avatars/avatar" + owner.getAvatarIndex() + ".png";
		System.out.println("Avatar path is " + path);
		Image image;
		try {
			image = new Image(new FileInputStream(path));
			sellerAvatar.setImage(image);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// sellerAvatar.setImage(currentSculpture.getOwner().getImage());
		sellerA.setText(currentSculpture.getOwner().getUsername());

	}

	public static void setCurrentPainting(Painting painting) {
		currentPainting = painting;
		System.out.println("Res price: " + currentPainting.getReservePrice());
		currentSculpture = null;

	}

	public void addBid() {

		String type = "";
		Bid bid = null;

		if (currentSculpture != null) {
			type = "sculpture";
			String amountStr = bidAmount.getText();
			double amount = Double.parseDouble(amountStr);
			Date date = new Date();
			bid = new Bid(type, LoginController.getUser(), amount, currentSculpture, date);

			LoginController.getUser().addBid(bid);
			currentSculpture.addBidToItem(bid);
			FileReader.addBid(bid);
			if (bid.checkBid() == 0) {
				
				System.out.println("its okay");

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("1");

				alert.showAndWait();

			} else if (bid.checkBid() == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("1");

				alert.showAndWait();
			} else if (bid.checkBid() == 2) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("2");

				alert.showAndWait();
			} else if (bid.checkBid() == 3) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("3");

				alert.showAndWait();
			} else if (bid.checkBid() == 4) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("4");

				alert.showAndWait();
			} else if (bid.checkBid() == 5) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("5");

				alert.showAndWait();
			} else if (bid.checkBid() == 6) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("6");

				alert.showAndWait();
			} else if (bid.checkBid() == 7) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("7");

				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Doesnt work");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				alert.showAndWait();
			}

		} else if (currentPainting != null) {
			type = "painting";
			String amountStr = bidAmount.getText();
			double amount = Double.parseDouble(amountStr);
			Date date = new Date();
			bid = new Bid(type, LoginController.getUser(), amount, currentPainting, date);
			LoginController.getUser().addBid(bid);
			currentPainting.addBidToItem(bid);
			FileReader.addBid(bid);

			if (bid.checkBid() == 0) {
				System.out.println("its okay");

				System.out.println("Bid placed.");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				alert.showAndWait();

			} else if (bid.checkBid() == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				alert.showAndWait();
			} else if (bid.checkBid() == 2) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				alert.showAndWait();
			} else if (bid.checkBid() == 3) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				alert.showAndWait();
			} else if (bid.checkBid() == 4) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				alert.showAndWait();
			} else if (bid.checkBid() == 5) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				alert.showAndWait();
			} else if (bid.checkBid() == 6) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				alert.showAndWait();
			} else if (bid.checkBid() == 7) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				alert.showAndWait();
			}

		}

		try

		{
			Writer.writeBidFile(bid);
			System.out.println("Bid saved successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Number of bids of user " + LoginController.getUser() + " is "
				+ LoginController.getUser().getPlacedBids().size());
	}

	public static void setCurrentSculpture(Sculpture sculpture) {
		currentPainting = null;
		currentSculpture = sculpture;
	}

}
