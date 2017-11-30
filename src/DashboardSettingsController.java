import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardSettingsController {

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

	private User user;

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
	}

}
