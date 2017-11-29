import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class FavouriteUsersController {
 
	
    @FXML
    private Label favUsers;

    @FXML
    private TableView<?> table1;


    @FXML
    private Button button1;

	public void initalize() {
		

		
	}
	
	public void getNumberOfFavourites() {
		System.out.println("aaaa");

		favUsers.setText(LoginController.getUser().getFavouriteUsers().size()+ "");
		System.out.println((LoginController.getUser().getFavouriteUsers().size()));
	}
}
