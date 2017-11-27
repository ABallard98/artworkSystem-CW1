import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController  {

	
    @FXML
    private TextField loginField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;
	
	
	public void initialize()  {
		
		
		loginButton.setOnAction(e-> handleLogin());
		registerButton.setOnAction(e-> handleRegistration());
	}

	public void handleLogin() {
		System.out.println("User of name " + loginField.getText() + " is logging in");
		//System.exit(1);
		
		
	}
	
	public void handleRegistration() {
		
	}
	
}
