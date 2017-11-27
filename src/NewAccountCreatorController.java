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
		
		String userdata = "";
		userdata += "\n Username: " + usernameField.getText();
		userdata += "\n First name: " + firstNameField.getText();
		userdata += "\n Last name: " + lastNameField.getText();
		userdata += "\n Address: " + addressField.getText();
		userdata += "\n Post code: " + postcodeField.getText();
		userdata += "\n Phone number: " + phoneNumberField.getText();


		System.out.println(userdata);
		
	}
	
	
	public void openCustomPictureCreator() {
		// for Ayden
	}
	
}
