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
 * This is the main GUI class which links to all other GUI classes
 * @author Marcin
 * Created on 22/11/2017
 */
public class GUIController {

	@FXML
	private ListView<String> searchList; // A list of searched items

	@FXML
	private Hyperlink dashboardLink; // Hyperlink for navigation

	@FXML
	private Hyperlink myAuctionsLink; // Hyperlink for navigation

	@FXML
	private Hyperlink myBidsLink; // Hyperlink for navigation

	@FXML
	private Hyperlink userSettingsLink; // Hyperlink for navigation

	@FXML
	private Button createNewArtworkButton; // Button for creating new Artwork

	@FXML
	private Hyperlink favouriteUsers; // Hyperlink for navigation

	@FXML
	private Label username; // Shows a users username
	@FXML
	private Label firstName; // Shows a users first name

	@FXML
	private Label lastName; // Shows a users last name

	@FXML
	private Label fullName; // Shows a users full name

	@FXML
	private Label address; // Shows a users address

	@FXML
	private Label postcode; // Shows a users postcode

	@FXML
	private Label lastLogin; // Shows a users last log in time

	@FXML
	private Label phoneNumber; // Shows a users phone number

	@FXML
	private BorderPane mainSection; // The main window

	@FXML
	private RadioButton artworkSelect; // Button to filter artwork

	@FXML
	private RadioButton userSelect; // Button to filter user

	@FXML
	private CheckBox sculptureSelect; // Checkbox to filter sculptures

	@FXML
	private CheckBox paintingSelect; // Checkbox to filter paintings

	private ObservableList<String> observableList; // A list of strings

	@FXML
	private Button searchButton; // Button to implement a search/filter

	@FXML
	private TextField searching; // allows search input

	@FXML
	private Button display; // Displays an item

	@FXML
	private Label user1; // Shows user1

	@FXML
	private Label today; // Shows the date

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
		userSettingsLink.setOnAction(e -> userSettings());

		createNewArtworkButton.setOnAction(e -> createNewArtwork());
		favouriteUsers.setOnAction(e -> userSettings1());
		myAuctionsLink.setOnAction(e -> showMyAuctions());

		display.setOnAction(e -> getSearchSelection());

		myBidsLink.setOnAction(e-> displayMyBids());
		dashboardLink.setOnAction(e -> displayMainDashboard());

	}
	
	/**
	 * Method to display my bids
	 */
	public void displayMyBids() {
		BorderPane bp; // Border Pane to load the new BorderPane in

		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("/MyBids.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to filter by artwork or users
	 */
	public void handleSearch() {

		if (artworkSelect.isSelected()) {
			displayArtworks();
		} else if (userSelect.isSelected()) {
			displayUsers();
		}

	}

	/**
	 * Displays chosen artwork
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
	 * Displays selected object
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

					Scene scene = new Scene(root, 450, 300);

					Stage stage = new Stage();
					stage.setScene(scene);
					stage.initModality(Modality.APPLICATION_MODAL);

					stage.setTitle(user.getUsername());
					stage.show();

				} catch (IOException e) {
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
			e.printStackTrace();
		}

	}
	
	/**
	 * Displays a user
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

			Scene scene = new Scene(root, 1009, 750);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.setTitle("Add a new artwork");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Displays the users information
	 */
	public void userSettings() {

		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Displays the main page
	 */
	public void displayMainDashboard() {

		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("MainDashboard.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show the favourite users settings
	 */
	public void userSettings1() {

		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("FavouriteUsers.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Returns the main section
	 * @return mainSection - the main GUI
	 */
	public BorderPane getMainSection() {
		return mainSection;
	}

}