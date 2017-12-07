import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * this class is used control user dashboard. 
 * @author Software Engineering Team Three
 * @version 1.0
 */

public class DashboardSettingsController {

	@FXML
	private Label username; //label for username.

	@FXML
	private Label firstName; //label for  user first name 
	
	@FXML
	private Label lastName; //label for user last name 

	@FXML
	private Label fullName; //label for user full name 

	@FXML
	private Label address; //label for user adress

	@FXML
	private Label postcode; //label for user postcode

	@FXML
	private Label lastLogin; //label for last login

	@FXML
	private Label phoneNumber; //label for user phone number
	
	
    @FXML
    private ImageView avatar; //Imageview used to user avatar


	private User user; //system user object
	
	
	/**
	 * method used to intailaize user dashboard
	 */
	public void initialize() {
		
		user = LoginController.getUser();

		username.setText(user.getUsername());
		firstName.setText(user.getFirstName());

		lastName.setText(user.getLastName());

		fullName.setText(user.getFullName());

		address.setText(user.getAddress());
		postcode.setText(user.getPostcode());

		//lastLogin.setText(user.getLastLogin());
		phoneNumber.setText(""+user.getPhonenumber());
		avatar.setImage(user.getImage());
		
	}

}
