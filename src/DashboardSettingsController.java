import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/** 
 * This is the GUI class for displaying user information
 * @author Marcin
 * Created on 04/12/2017
 */
public class DashboardSettingsController {

	@FXML
	private Label username; // Shows username of user

	@FXML
	private Label firstName; // Shows first name of user

	@FXML
	private Label lastName; // Shows last name of user

	@FXML
	private Label fullName; // Shows full name of user

	@FXML
	private Label address; // Shows address of user

	@FXML
	private Label postcode; // Shows postcode of user

	@FXML
	private Label lastLogin; // Shows last time logged in

	@FXML
	private Label phoneNumber; // Shows phone number of user
	
    @FXML
    private ImageView avatar; // Container for the user's avatar image

	private User user; // A user object

	/**
	 * Method to intialise the dashboard window
	 */
	public void initialize() {
		
		user = LoginController.getUser();

		username.setText(user.getUsername());
		firstName.setText(user.getFirstName());
		lastName.setText(user.getLastName());
		fullName.setText(user.getFullName());
		address.setText(user.getAddress());
		postcode.setText(user.getPostcode());
		phoneNumber.setText(user.getPhonenumber()+"");
		avatar.setImage(user.getImage());
		
	}

}
