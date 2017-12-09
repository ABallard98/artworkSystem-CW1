
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @author Daniel
 * @date 5/12/2017
 */

public class ArtworkController {
	


	@FXML
	private Button back;

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

	@FXML
	private BorderPane mainSection;

	private static Painting currentPainting;
	private static Sculpture currentSculpture;

	public void initialize() {

		if (currentPainting != null) {
			initializePainting();
			if (currentPainting.getNumberOfBids() > 0) {
				currentPrice.setText(currentPainting.getHighestBidAmount() + "");

			} else {
				currentPrice.setText(currentPainting.getReservePrice() + "");

			}
			categoryA.setText("Painting");

		}

		if (currentSculpture != null) {
			initializeSculpture();
			if (currentSculpture.getNumberOfBids() > 0) {
				currentPrice.setText(currentSculpture.getHighestBidAmount() + "");

			} else {
				currentPrice.setText(currentSculpture.getReservePrice() + "");

			}
			categoryA.setText("Sculpture");

		}

		placeBid.setOnAction(e -> addBid());

		System.out.println("Total no. of bids " + FileReader.getBids().size());
		System.out.println("Total no. of users " + FileReader.getUsers().size());

		addToFav.setOnAction(e -> showUser());

		back.setOnAction(e -> backToBrowsing());

	}

	public void backToBrowsing() {
		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("MainDashboard.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUser() {

		User user = null;

		if (currentSculpture != null) {
			if (currentSculpture.getOwner() != null) {
				user = currentSculpture.getOwner();
			} else if (currentPainting != null) {

			}

		} else if (currentPainting != null) {
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
		User owner = currentPainting.getOwner();

		Image image = owner.getImage();
		sellerAvatar.setImage(image);

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

		image = owner.getImage();
		sellerAvatar.setImage(image);

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
			try {
				double amount = Double.parseDouble(amountStr);
				Date date = new Date();
				bid = new Bid(type, LoginController.getUser(), amount, currentSculpture, date);

				if (currentSculpture.getBidsAllowed() > currentSculpture.getNumberOfPlacedBids()
						&& amount > currentSculpture.getReservePrice()
						&& amount > currentSculpture.getHighestBidAmount()
						&& !LoginController.getUser().getUsername().equals(currentSculpture.getCurrentHighestBidder())
						&& LoginController.getUser() != currentSculpture.getOwner()) {

					System.out.println("its okay");

					LoginController.getUser().addBid(bid);
					currentSculpture.addBidToItem(bid);
					FileReader.addBid(bid);

					try {
						Writer.writeBidFile(bid);
						System.out.println("Bid saved successfully");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("not working");
						e.printStackTrace();
					}

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setHeaderText("Bid has been placed");
					alert.setContentText("1");
					alert.showAndWait();

					
					if(bid.isWinningBid()) {
						Alert alert1 = new Alert(AlertType.INFORMATION);
						alert1.setTitle("You won");
						alert1.setHeaderText("You have won the auction");
						alert1.setContentText("Contact the seller to finish the auction");
						alert1.showAndWait();
					}
					
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Something went wrong");
					alert.setHeaderText("Bid could not be placed");
					alert.showAndWait();

				}

			} catch (Exception e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Wrong input");
				alert.setHeaderText("Bid could not be placed");
				alert.showAndWait();

			}

		} else if (currentPainting != null) {
			type = "painting";
			String amountStr = bidAmount.getText();
			double amount = Double.parseDouble(amountStr);
			Date date = new Date();
			
			if (currentPainting.getBidsAllowed() > currentPainting.getNumberOfPlacedBids()
					&& amount > currentPainting.getReservePrice()
					&& amount > currentPainting.getHighestBidAmount()
					&& !LoginController.getUser().getUsername().equals(currentPainting.getCurrentHighestBidder())
					&& LoginController.getUser() != currentPainting.getOwner()) {
				System.out.println("its okay");

				System.out.println("Bid placed.");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");

				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				
				bid = new Bid(type, LoginController.getUser(), amount, currentPainting, date);
				LoginController.getUser().addBid(bid);
				currentPainting.addBidToItem(bid);
				FileReader.addBid(bid);

				try {
					Writer.writeBidFile(bid);
					System.out.println("Bid saved successfully");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("not working");
					e.printStackTrace();
				}
				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Something went wrong");

				alert.setHeaderText("Bid has not been placed");
				alert.setContentText("Try again");

				alert.showAndWait();
				
			}

		}

		System.out.println("Number of bids of user " + LoginController.getUser() + " is "
				+ LoginController.getUser().getPlacedBids().size());
	}

	public static void setCurrentSculpture(Sculpture sculpture) {
		currentPainting = null;
		currentSculpture = sculpture;
	}
	
	public void refresh() {
		
	}

}
