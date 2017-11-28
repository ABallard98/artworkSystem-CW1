import java.io.IOException;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIController {

    @FXML
    private ListView<String> searchList;
	
	
	@FXML
	private Hyperlink dashboardLink;

	@FXML
	private Hyperlink myAuctionsLink;

	@FXML
	private Hyperlink myBidsLink;

	@FXML
	private Hyperlink userSettingsLink;

	@FXML
	private Button createNewArtworkButton;

	@FXML
	private Hyperlink favouriteUsers;

	@FXML
	private Label username;

	@FXML
	private Label firstName;

	@FXML
	private Label lastName;

	@FXML
	private Label fullName;

	@FXML
	private Label address;

	@FXML
	private Label postcode;

	@FXML
	private Label lastLogin;

	@FXML
	private Label phoneNumber;

	@FXML
	private BorderPane mainSection;

	
    @FXML
    private RadioButton artworkSelect;

    @FXML
    private RadioButton userSelect;

    @FXML
    private CheckBox sculptureSelect;

    @FXML
    private CheckBox paintingSelect;
	
	
    private ObservableList<String> observableList;

    @FXML
    private Button searchButton;

    @FXML
    private Button display;

    
	
	/**
	 * Initialises the main elements of GUI
	 */
	public void initialize() {
		
		searchButton.setOnAction(e-> handleSearch());

		ToggleGroup tg = new ToggleGroup();
		userSelect.setToggleGroup(tg);
		artworkSelect.setToggleGroup(tg);


		userSettingsLink.setOnAction(e -> userSettings());

		createNewArtworkButton.setOnAction(e -> createNewArtwork());
		favouriteUsers.setOnAction(e -> userSettings1());
		myAuctionsLink.setOnAction(e -> showMyAuctions());
		

		display.setOnAction(e-> getSearchSelection());
		
		

		//names.add(stringSet);
		

		
	}
	
	
	public void handleSearch() {
		
		if(artworkSelect.isSelected()) {
			displayArtworks();
		} else if (userSelect.isSelected()) {
			displayUsers();
		}
		
	}
	
	public void displayArtworks() {
		ArrayList<String> sculptures = new ArrayList<>();
		ArrayList<String> paintings = new ArrayList<>();
		ArrayList<String> artworks = new ArrayList<>();

		

		for(Sculpture sculptureA : FileReader.getSculptures()) {
			sculptures.add(sculptureA.getTitle());
		}
		

		for(Painting paintingA : FileReader.getPaintings()) {
			paintings.add(paintingA.getTitle());
		}
		
		
		if(sculptureSelect.isSelected() && !paintingSelect.isSelected()) {
			observableList = FXCollections.observableArrayList(sculptures);
		} else if (!sculptureSelect.isSelected() && paintingSelect.isSelected()) {
			observableList = FXCollections.observableArrayList(paintings);
		} else if (sculptureSelect.isSelected() && paintingSelect.isSelected()) {
			for(String paint: paintings ) {
				artworks.add(paint);
			}
			for(String scul: sculptures ) {
				artworks.add(scul);
			}

			observableList = FXCollections.observableArrayList(artworks);
		} else {
			observableList = FXCollections.observableArrayList(new ArrayList<String>());

		}
		
		searchList.setItems(observableList);

	}
	
	
	public void getSearchSelection() {
		String s = searchList.getSelectionModel().getSelectedItem();

		System.out.println("Selected " + s);
	}
	
	
	public void displayUsers() {
		ArrayList<String> users = new ArrayList<>();
		
		for(User user : FileReader.getUsers()) {
			users.add(user.getUsername()+ " "+ user.getFirstName() + " "+ user.getLastName());
		}
		
		
		observableList = FXCollections.observableArrayList(users);
		searchList.setItems(observableList);

	}
	

	/**
	 * Switches Scene into one that  contains list of auctions
	 * made by the user
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
	 * Switches Scene into one that  contains list of favourite users
	 * added by the user
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

			Scene scene = new Scene(root, 1009, 867);

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
