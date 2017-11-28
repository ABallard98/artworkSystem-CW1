import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIController {

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

	public void initialize() {

		userSettingsLink.setOnAction(e -> userSettings());

		createNewArtworkButton.setOnAction(e -> createNewArtwork());
		favouriteUsers.setOnAction(e-> userSettings1());
	}
	
	
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
