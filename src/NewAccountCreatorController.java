import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class NewAccountCreatorController {

	

    @FXML
    private TextField usernameField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField postcodeField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button openGeneratorButton;

	
	
	public void initialize() {
		
		createAccountButton.setOnAction(e-> createAccount());
		openGeneratorButton.setOnAction(e-> openCustomPictureCreator());
		
		
	}
	
	
	public void createAccount() {
		
		String username = usernameField.getText();
		String firstName =  firstNameField.getText();
		String lastName =  lastNameField.getText();
		String address =  addressField.getText();
		String postCode =  postcodeField.getText();
		String phoneNumber =  phoneNumberField.getText();
		
		long phoneNumberLong = Long.parseLong(phoneNumber);
		
		String userdata = "";
		userdata += "\n Username: " + username;
		userdata += "\n First name: " + firstName;
		userdata += "\n Last name: " + lastName;
		userdata += "\n Address: " + address;
		userdata += "\n Post code: " + postCode;
		userdata += "\n Phone number: " + phoneNumber;

		User user = new User(username, firstName, lastName, address, postCode, phoneNumberLong);

		try {
			Writer.writeUserFile(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userdata);
		
	}
	
	
	public void openCustomPictureCreator() {
		// for Ayden
	}
	
}
