import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.event.ChangeListener;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * this class is used to contol gui
 * @author Software Engineering Team Three
 * @version 1.0
 *
 */
public class GUIController {

	@FXML
	private ListView<String> searchList; //list view for searchList

	@FXML
	private Hyperlink dashboardLink; //hyperlink button to dashboard

	@FXML
	private Hyperlink myAuctionsLink; //hyperlink button to my auctions

	@FXML
	private Hyperlink myBidsLink; //hyperlink button to my bids

	@FXML
	private Hyperlink userSettingsLink; //hyperlink button to user settings

	@FXML
	private Button createNewArtworkButton; //button used to create new artwork

	@FXML
	private Hyperlink favouriteUsers; //hyperlink button to favourite users

	@FXML
	private Label username; //label for user name 
	@FXML
	private Label firstName; //label for first name

	@FXML
	private Label lastName; //label for last name

	@FXML
	private Label fullName; //label for full name

	@FXML
	private Label address; //label for user address

	@FXML
	private Label postcode; //label for user postcode

	@FXML
	private Label lastLogin; //label for users last log in

	@FXML
	private Label phoneNumber; //label for user phone number

	@FXML
	private BorderPane mainSection; // main section border pane

	@FXML
	private RadioButton artworkSelect; //radio button used to select artwork

	@FXML
	private RadioButton userSelect; //radio button used to select user

	@FXML
	private CheckBox sculptureSelect; //checkbox to determine if sculpture is selected

	@FXML
	private CheckBox paintingSelect; //checkbox to determine if painting is selected

	private ObservableList<String> observableList; //observe list to track changes

	@FXML
	private Button searchButton; //button used to search.

	@FXML
	private TextField searching; //field for searching items or users

	@FXML
	private Button display; //button used to display

	@FXML
	private Label user1; //label for user 1

	@FXML
	private Label today; //label for todays date

	/**
	 * Initialises the main elements of GUI
	 */
	@SuppressWarnings("deprecation")
	public void initialize() {
		searching.setDisable(true);
		paintingSelect.setSelected(true);
		sculptureSelect.setSelected(true);
		artworkSelect.setSelected(true);

		searchButton.setOnAction(e -> handleSearch());

		ToggleGroup tg = new ToggleGroup();
		userSelect.setToggleGroup(tg);
		artworkSelect.setToggleGroup(tg);

		user1.setText("Nice to see you " + LoginController.getUser().getFullName());
		LocalDate date = LocalDate.now();
		today.setText("Today is " + date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear());
		// eelm
		userSettingsLink.setOnAction(e -> userSettings());

		createNewArtworkButton.setOnAction(e -> createNewArtwork());
		favouriteUsers.setOnAction(e -> userSettings1());
		myAuctionsLink.setOnAction(e -> showMyAuctions());

		display.setOnAction(e -> getSearchSelection());

		myBidsLink.setOnAction(e-> displayMyBids());
		dashboardLink.setOnAction(e -> displayMainDashboard());
		// names.add(stringSet);

	}

	/**
	 * Method used to display bids
	 */
	public void displayMyBids() {
		BorderPane bp; // Border Pane to load the new BorderPane in

		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("/MyBids.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to handle searching users and artworks
	 */
	public void handleSearch() {

		if (artworkSelect.isSelected()) {
			displayArtworks();
		} else if (userSelect.isSelected()) {
			displayUsers();
		}

	}
	
	/**
	 * Method to display artworks
	 */
	public void displayArtworks() {
		ArrayList<String> sculptures = new ArrayList<>();
		ArrayList<String> paintings = new ArrayList<>();
		ArrayList<String> artworks = new ArrayList<>();

		for (Sculpture sculptureA : FileReader.getSculptures()) {
			sculptures.add(sculptureA.getTitle());
		}

		for (Painting paintingA : FileReader.getPaintings()) {
			paintings.add(paintingA.getTitle());
		}

		if (sculptureSelect.isSelected() && !paintingSelect.isSelected()) {
			observableList = FXCollections.observableArrayList(sculptures);
		} else if (!sculptureSelect.isSelected() && paintingSelect.isSelected()) {
			observableList = FXCollections.observableArrayList(paintings);
		} else if (sculptureSelect.isSelected() && paintingSelect.isSelected()) {
			for (String paint : paintings) {
				artworks.add(paint);
			}
			for (String scul : sculptures) {
				artworks.add(scul);
			}

			observableList = FXCollections.observableArrayList(artworks);
		} else {
			observableList = FXCollections.observableArrayList(new ArrayList<String>());

		}

		searchList.setItems(observableList);

	}

	/**
	 * Method to get selected searched item
	 */
	public void getSearchSelection() {
		String s = searchList.getSelectionModel().getSelectedItem();

		System.out.println("Selected " + s);

		BorderPane bp; // Border Pane to load the new BorderPane in

		try {
			
			if(FileReader.getUser(s) != null) {
				User user = FileReader.getUser(s);
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

				
			} else {
				if (FileReader.getSculpture(s) != null) {
					ArtworkController.setCurrentSculpture(FileReader.getSculpture(s));
				}

				if (FileReader.getPainting(s) != null) {
					ArtworkController.setCurrentPainting(FileReader.getPainting(s));
				}

				bp = (BorderPane) FXMLLoader.load(getClass().getResource("/ArtworkView.fxml"));
				mainSection.getChildren().setAll(bp);
			}




		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
			e.printStackTrace();
		}

	}
	
	/**
	 * Method used to display users
	 */
	public void displayUsers() {
		ArrayList<String> users = new ArrayList<>();

		for (User user : FileReader.getUsers()) {
			users.add(user.getUsername());
		}

		observableList = FXCollections.observableArrayList(users);
		searchList.setItems(observableList);

	}

	/**
	 * Switches Scene into one that contains list of auctions made by the user
	 */
	public void showMyAuctions() {

		BorderPane bp; // Border Pane to load the new BorderPane in

		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("MyAuctions.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	/**
	 * Switches Scene into one that contains list of favourite users added by the
	 * user
	 */
	public void favouriteUsers() {
		BorderPane bp;
		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("FavouriteUsers.fxml"));
			mainSection.getChildren().setAll(bp);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	/**
	 * Opens a new window where user can input data for Artwork
	 */
	public void createNewArtwork() {
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("AddArtwork.fxml"));
		try {
			Parent root = fxmlL.load();
			// NewAccountCreatorController newAccountController =
			// fxmlL.<NewAccountCreatorController>getController();

			Scene scene = new Scene(root, 1009, 750);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.setTitle("Add a new artwork");
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Switches Scene into one that contains user settings
	 * 
	 */	
	public void userSettings() {

		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * method used to display main dashboard
	 */
	public void displayMainDashboard() {

		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("MainDashboard.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * method used to switch to scene containing user settings
	 */
	public void userSettings1() {

		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("FavouriteUsers.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
