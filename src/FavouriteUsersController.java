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
 * This is the GUI class that allows users to favourite eachother and displays those users
 * @author Joshua
 * Created on 05/12/2017
 */

public class FavouriteUsersController {

	@FXML
	private Label favUsers; // Shows favourite users

	@FXML
	private TableView<User> table1; // Displays a table

	@FXML
	private TableColumn<User, String> usernameColumn; // Creates column for table

	@FXML
	private TableColumn<User, ImageView> avatar; // Creates column for table
	
    @FXML
    private Button displayUser; // Button to display a favourite user

    @FXML
    private Button deleteUser; // Button to delete a favourite user
	
	private ObservableList<User> favourites; // List of favourited users

	/**
	 * Initialises the favourite users window
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
	 * Displays a specific user
	 */
	public void showUser() {
		User displayedUser = table1.getSelectionModel().getSelectedItem();
		
		
		if(displayedUser != null) {
			UserDisplayController.setUser(displayedUser);
			

			FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("/UserDisplay.fxml"));
			try {
				Parent root = fxmlL.load();
				Scene scene = new Scene(root, 450, 300);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);

				stage.setTitle(displayedUser.getUsername());
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

			
		}
	}
	
	/**
	 * Deletes a user from the favourites table
	 */
	public void deleteUser() {
		User user = table1.getSelectionModel().getSelectedItem();
		//Removes selected user from memory
		User loggedUser = LoginController.getUser();
		if (FileReader.checkIfInFavouriteList(loggedUser, user)) {
			loggedUser.removeUserFromFavourites(user);
			Writer.removeFromFavourites(loggedUser, user);
		}
		favourites = FXCollections.observableArrayList(LoginController.getUser().getFavouriteUsers());
		table1.setItems(favourites);

	}
	
}