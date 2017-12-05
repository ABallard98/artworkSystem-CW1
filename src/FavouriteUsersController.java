import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class FavouriteUsersController {

	@FXML
	private Label favUsers;

	@FXML
	private TableView<User> table1;


	private ObservableList<User> favourites;

	@FXML
	private TableColumn<User, String> usernameColumn;

	@FXML
	private TableColumn<User, ImageView> avatar;

	public void initialize() {

		favourites = FXCollections.observableArrayList(LoginController.getUser().getFavouriteUsers());
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));

		avatar.setCellValueFactory(new PropertyValueFactory<User, ImageView>("imgView"));
		table1.setItems(favourites);
		favUsers.setText(LoginController.getUser().getFavouriteUsers().size() + "");


	}

}
