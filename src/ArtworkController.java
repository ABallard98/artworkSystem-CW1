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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;

/**
 * This is the GUI class for displaying artwork
 * @author Daniel
 * Created on 5/12/2017
 */

public class ArtworkController {

	@FXML
	private Button back; // button to return to browsing

	@FXML
	private ImageView sellerAvatar; // avatar of the seller

	@FXML
	private Button addToFav; // button to add to favourites

	@FXML
	private Label noOfBids; // shows number of bids

	@FXML
	private Label categoryA; // shows the category (sculpture or painting)

	@FXML
	private Label titleA; // show title

	@FXML
	private Label creatorA; // shows creator

	@FXML
	private Label yearA; // shows year of creation

	@FXML
	private Label noOfBidsA; // shows number of placed bids

	@FXML
	private Label bidsLimitA; // shows a limit of bids

	@FXML
	private Label widthA; // shows the width

	@FXML
	private Label heightA; // shows the height

	@FXML
	private Label depthA; // shows the depth

	@FXML
	private Label materialA; // shows the material

	@FXML
	private Label currentPrice; // shows the current price

	@FXML
	private Label sellerA; // shows the seller

	@FXML
	private TextField bidAmount; // text field to take an amount of bid

	@FXML
	private Button placeBid; // button to place a bid

	@FXML
	private ImageView mainPic; // container to display main image

	@FXML
	private ImageView pic1; // container to display first image

	@FXML
	private ImageView pic2; // container to display second image

	@FXML
	private ImageView pic3; // container to display third image

	@FXML
	private ImageView pic4; // container to display fourth image

	@FXML
	private BorderPane mainSection; // central pane of the window

	private static Painting currentPainting; // displayed painting
	private static Sculpture currentSculpture; // displayed sculpture

	/**
	 * Initialises the view of an artwork
	 */
	public void initialize() {

		// If painting is displayed, update the page
		if (currentPainting != null) {
			initializePainting(); // initialises the painting display
			
			// If there are any bids on the painting, get the amount of the highest bid
			if (currentPainting.getNumberOfBids() > 0) {
				currentPrice.setText(currentPainting.getHighestBidAmount() + "");
			} else { // Otherwise, display the reserve price
				currentPrice.setText(currentPainting.getReservePrice() + "");
			}
			
			categoryA.setText("Painting"); // set the label for category to "Painting"

		}
		
		// If painting is displayed, update the page
		if (currentSculpture != null) {
			initializeSculpture(); // initialises the painting display
			
			// If there are any bids on the sculpture, get the amount of the highest bid
			if (currentSculpture.getNumberOfBids() > 0) {
				currentPrice.setText(currentSculpture.getHighestBidAmount() + "");
			} else { // Otherwise, display the reserve price
				currentPrice.setText(currentSculpture.getReservePrice() + "");

			}
			
			categoryA.setText("Sculpture"); // set the label for category to "Sculpture"

		}

		// Allows to place a bid
		placeBid.setOnAction(e -> addBid());

		// Displays a view of a seller
		addToFav.setOnAction(e -> showUser());

		// Return to browsing page
		back.setOnAction(e -> backToBrowsing());

	}

	/**
	 * Method to set the page back to browsing
	 */
	public void backToBrowsing() {
		BorderPane bp;
		
		// Try to load an FXML file with browsing 
		try {
			bp = FXMLLoader.load(getClass().getResource("MainDashboard.fxml"));
			mainSection.getChildren().setAll(bp);

			// catch error if FXML cannot be loaded
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shows a window of a user information
	 */
	public void showUser() {

		User user = null;

		// Gets the owner of either sculpture or painting
		if (currentSculpture != null) {
			if (currentSculpture.getOwner() != null) {
				user = currentSculpture.getOwner();
			}
		} else if (currentPainting != null) {
			if(currentPainting.getOwner() != null){
				user = currentPainting.getOwner();
			}	
		}
		
		// Passes the reference to the owner to the window displaying user
		UserDisplayController.setUser(user);

		// Loads the window for a user
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("/UserDisplay.fxml"));
		
		//  Try to display the user
		try {
			Parent root = fxmlL.load();

			Scene scene = new Scene(root, 450, 300);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.setTitle(user.getUsername());
			stage.show();

		} catch (IOException e) { // catch an exception if file cannot be loaded
			e.printStackTrace();
		}
	}

	/**
	 * Initialises a painting
	 */
	public void initializePainting() {
		// Looks for the image
		currentPainting.resolveImage();
		
		// Sets all information about the painting
		titleA.setText(currentPainting.getTitle());
		widthA.setText(currentPainting.getWidth() + "");
		heightA.setText(currentPainting.getHeight() + "");
		yearA.setText(currentPainting.getCreationYear() + "");
		creatorA.setText(currentPainting.getCreator());
		noOfBidsA.setText(currentPainting.getNumberOfPlacedBids() + "");
		bidsLimitA.setText(currentPainting.getBidsAllowed() + "");
		mainPic.setImage(currentPainting.getImage());
		sellerA.setText(currentPainting.getOwner().getUsername());
		
		// Gets the owner of the painting
		User owner = currentPainting.getOwner();

		// Get and display the image
		Image image = owner.getImage();
		sellerAvatar.setImage(image);

	}

	/**
	 * Initialises the sculpture
	 */
	public void initializeSculpture() {
		
		// Loads images of sculpture
		currentSculpture.resolveImage();
		currentSculpture.resolveAdditionalImages();

		// Adds images into an ArrayList
		ArrayList<Image> additionalImages = currentSculpture.getAdditionalImages();
		ArrayList<ImageView> imageViews = new ArrayList<>();

		// Adds the images for display
		imageViews.add(pic1);
		imageViews.add(pic2);
		imageViews.add(pic3);
		imageViews.add(pic4);

		// Get images from an ArrayList
		for (int i = 0; i < additionalImages.size(); i++) {
			imageViews.get(i).setImage((additionalImages.get(i)));
		}
		
		// Display information about sculpture
		mainPic.setImage(currentSculpture.getImage());
		titleA.setText(currentSculpture.getTitle());
		widthA.setText(currentSculpture.getWidth() + "");
		heightA.setText(currentSculpture.getHeight() + "");
		depthA.setText(currentSculpture.getDepth() + "");
		materialA.setText(currentSculpture.getMaterial());
		yearA.setText(currentSculpture.getCreationYear() + "");
		creatorA.setText(currentSculpture.getCreator());
		noOfBidsA.setText(currentSculpture.getNumberOfPlacedBids() + "");
		bidsLimitA.setText(currentSculpture.getBidsAllowed() + "");

		// Get the owner of sculpture
		User owner = currentSculpture.getOwner();
		Image image;

		// Display the owner avatar
		image = owner.getImage();
		sellerAvatar.setImage(image);

		// Display the owner's username
		sellerA.setText(currentSculpture.getOwner().getUsername());

	}

	/**
	 * Method to add bid to artwork
	 */
	public void addBid() {

		String type = "";
		Bid bid = null;

		//  Error checking for bid
		if (currentSculpture != null) {
			type = "sculpture";
			String amountStr = bidAmount.getText(); // gets the amount of bid
			try { // try to parse the amount to a double
				double amount = Double.parseDouble(amountStr);
				Date date = new Date();
				
				// Initialises a new bid  object
				bid = new Bid(type, LoginController.getUser(), amount, currentSculpture, date);

				// Checks if bid is acceptable
				if (currentSculpture.getBidsAllowed() > currentSculpture.getNumberOfPlacedBids()
						&& amount > currentSculpture.getReservePrice()
						&& amount > currentSculpture.getHighestBidAmount()
						&& !LoginController.getUser().getUsername().equals(currentSculpture.getCurrentHighestBidder())
						&& LoginController.getUser() != currentSculpture.getOwner()) {

					// Add the sculpture
					LoginController.getUser().addBid(bid);
					currentSculpture.addBidToItem(bid);
					FileReader.addBid(bid);

					// Save bid to memory
					try {
						Writer.writeBidFile(bid);
					} catch (IOException e) {
						e.printStackTrace();
					}

					// Alert box informing the user about the successful bid
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setHeaderText("Bid has been placed");
					alert.setContentText("Thank you!");
					alert.showAndWait();

					// Alert box which is displayed if the bid is a final one
					if (bid.isWinningBid()) {
						Alert alert1 = new Alert(AlertType.INFORMATION);
						alert1.setTitle("You won");
						alert1.setHeaderText("You have won the auction");
						alert1.setContentText("Contact the seller to finish the auction");
						alert1.showAndWait();
					}

				} else { // if bid could not be placed, display an error message
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Something went wrong");
					alert.setHeaderText("Bid could not be placed");
					alert.showAndWait();

				}

			} catch (NumberFormatException e) { // Inform user if the input format was wrong
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wrong input");
				alert.setHeaderText("Bid could not be placed");
				alert.showAndWait();

			}

			// If bid is placed on a painting, check whether it is acceptable
		} else if (currentPainting != null) {
			try {
				type = "painting";
				String amountStr = bidAmount.getText();
				double amount = Double.parseDouble(amountStr);
				Date date = new Date();

				// Check if bid can be allowed
				if (currentPainting.getBidsAllowed() > currentPainting.getNumberOfPlacedBids()
						&& amount > currentPainting.getReservePrice() && amount > currentPainting.getHighestBidAmount()
						&& !LoginController.getUser().getUsername().equals(currentPainting.getCurrentHighestBidder())
						&& LoginController.getUser() != currentPainting.getOwner()) {

					// Inform user about the successful bid
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setHeaderText("Bid has been placed");
					alert.setContentText("Thank you!");

					// Initialises a new bid and adds it to memory
					bid = new Bid(type, LoginController.getUser(), amount, currentPainting, date);
					LoginController.getUser().addBid(bid);
					currentPainting.addBidToItem(bid);
					FileReader.addBid(bid);

					// Save to memory
					try {
						Writer.writeBidFile(bid);
					} catch (IOException e) {
						e.printStackTrace();
					}
					alert.showAndWait();

				} else { // Error message if bid was not accepted
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Something went wrong");
					alert.setHeaderText("Bid has not been placed");
					alert.setContentText("Try again");
					alert.showAndWait();
				}
			} catch (NumberFormatException e) { // show an error message for wrong input
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wrong input");
				alert.setHeaderText("Bid could not be placed");
				alert.showAndWait();

			}

		}

	}

	/**
	 * Sets the sculpture
	 * @param sculpture sculpture to be displayed
	 */
	public static void setCurrentSculpture(Sculpture sculpture) {
		currentPainting = null;
		currentSculpture = sculpture;
	}
	
	/**
	 * Sets the painting
	 * @param painting painting to display
	 */
	public static void setCurrentPainting(Painting painting) {
		currentPainting = painting;
		currentSculpture = null;

	}

}
