import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FavouriteUsersController {

	@FXML
	private Label favUsers;

	@FXML
	private TableView<User> table1;

	@FXML
	private Button button1;

	private ObservableList<User> favourites;

	@FXML
	private TableColumn<User, String> usernameColumn;

	@FXML
	private TableColumn<User, String>firstNameColumn;

	@FXML
	private TableColumn<User, String> lastNameColumn;

	@FXML
	private TableColumn<User, Integer> itemsColumn;
	
	
	

	public void initalize() {


	}

	public void getNumberOfFavourites() {
		System.out.println("aaaa");

		favUsers.setText(LoginController.getUser().getFavouriteUsers().size() + "");
		System.out.println((LoginController.getUser().getFavouriteUsers().size()));
		
		
		
		favourites =  FXCollections.observableArrayList(LoginController.getUser().getFavouriteUsers());
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("firstName"));
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<User,String>("lastName"));
		itemsColumn.setCellValueFactory(new PropertyValueFactory<User,Integer>("sellingArtworks"));
		table1.setItems(favourites);
	}
}
