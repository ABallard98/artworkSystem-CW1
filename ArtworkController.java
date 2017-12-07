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

/**
 * 
 * @author Software Engineering Team Three
 * @version 1.0
 */

public class ArtworkController {

	@FXML
	private ImageView sellerAvatar; //image view used to display users avatar

	@FXML
	private Button addToFav; // button used to add to favourits

	@FXML
	private Label noOfBids; //label to display number of bids

	@FXML
	private Label categoryA; //label to display category

	@FXML
	private Label titleA; //label to display title

	@FXML
	private Label creatorA; //label to display creator

	@FXML
	private Label yearA; //label to display year

	@FXML
	private Label noOfBidsA; //label to display number of bids

	@FXML
	private Label bidsLimitA; //label to display bid limit

	@FXML
	private Label postcode1; //label to display postcode

	@FXML
	private Label postcode2; //label to display postcode

	@FXML
	private Label widthA; //label to display width

	@FXML
	private Label heightA; //label to display height

	@FXML
	private Label depthA; //label to display depth

	@FXML
	private Label materialA; //label to display matirial

	@FXML
	private Label currentPrice; //label to display current price

	@FXML
	private Label sellerA; //label to display seller

	@FXML
	private TextField bidAmount; //field for bid amount on artwork

	@FXML
	private Button placeBid; //button used to place bid

	@FXML
	private ImageView mainPic; //Imageview used to display main picture

	@FXML
	private ImageView pic1; //Imageview used to display pic1

	@FXML
	private ImageView pic2; //Imageview used to display pic2

	@FXML
	private ImageView pic3; //Imageview used to display pic3

	@FXML
	private ImageView pic4; //Imageview used to display pic4

	private static Painting currentPainting; //current painting used
	private static Sculpture currentSculpture; //current sculpture used.

	/**
	 * method to intailaize paintings and sculptures
	 */
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

	/**
	 * method to display user details
	 */
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

	/**
	 * Method to intialize painting.
	 */
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

	/**
	 * Method to intialize sculpture.
	 */
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

	/**
	 * Method Allows the setting of current painting.
	 */
	public static void setCurrentPainting(Painting painting) {
		currentPainting = painting;
		System.out.println("Res price: " + currentPainting.getReservePrice());
		currentSculpture = null;

	}

	/**
	 * Method used to add bid to artwork.
	 */
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

	/**
	 * Method Allows the setting of current sculpture.
	 */
	public static void setCurrentSculpture(Sculpture sculpture) {
		currentPainting = null;
		currentSculpture = sculpture;
	}

}
