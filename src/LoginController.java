import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("NewAccountCreator.fxml"));    
		try {
			BorderPane login = (BorderPane)fxmlL.load();
			//NewAccountCreatorController newAccountController = fxmlL.<NewAccountCreatorController>getController();

			Scene scene = new Scene(login,600 , 832);
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			
			stage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}          
		// Access the controller that was created by the FXML loader


	}
	
}
