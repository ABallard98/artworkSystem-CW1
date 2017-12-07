import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * this class is used control users favourite users. 
 * @author Software Engineering Team Three
 * @version 1.0
 */
public class FavouriteUsersController {

	@FXML
	private Label favUsers; //label for favourite users.

	@FXML
	private TableView<User> table1; //table view of the table


	private ObservableList<User> favourites; //observable list favourites to track changes

	@FXML
	private TableColumn<User, String> usernameColumn; //table column for username

	@FXML
	private TableColumn<User, ImageView> avatar; //table column for user avatar
	

    @FXML
    private Button displayUser; // button used to display user

    @FXML
    private Button deleteUser; //button used to delete user
	
	/**
	 * method used to intailaize user favourite users
	 */
	public void initialize() {

		favourites = FXCollections.observableArrayList(LoginController.getUser().getFavouriteUsers());
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));

		avatar.setCellValueFactory(new PropertyValueFactory<User, ImageView>("imgView"));
		table1.setItems(favourites);
		favUsers.setText(LoginController.getUser().getFavouriteUsers().size() + "");

		deleteUser.setOnAction(e-> deleteUser() );

		displayUser.setOnAction(e-> showUser());
	}

	/**
	 * method used to show user details
	 */
	public void showUser() {
		User displayedUser = table1.getSelectionModel().getSelectedItem();
		
		
		if(displayedUser != null) {
			UserDisplayController.setUser(displayedUser);
			

			FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("/UserDisplay.fxml"));
			try {
				Parent root = fxmlL.load();
				// NewAccountCreatorController newAccountController =
				// fxmlL.<NewAccountCreatorController>getController();

				Scene scene = new Scene(root, 450, 300);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);

				stage.setTitle(displayedUser.getUsername());
				stage.show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
	}
	
	/**
	 * method used to delete user from favourites list
	 */
	public void deleteUser() {
		User user = table1.getSelectionModel().getSelectedItem();

		User loggedUser = LoginController.getUser();
		if (FileReader.checkIfInFavouriteList(loggedUser, user)) {
			loggedUser.removeUserFromFavourites(user);
			Writer.removeFromFavourites(loggedUser, user);
		}
		favourites = FXCollections.observableArrayList(LoginController.getUser().getFavouriteUsers());
		table1.setItems(favourites);

	}
	
}
