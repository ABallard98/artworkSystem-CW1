import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * this class is used to contol the log in process
 * @author Software Engineering Team Three
 * @version 1.0
 *
 */
public class LoginController {

	
	private static User user; //user object
	
	@FXML
	private TextField loginField; //field for login

	@FXML
	private Button loginButton; //button used to login

	@FXML
	private Button registerButton; //button used to register new user

	/**
	 * method used to intailaize user login.
	 */
	public void initialize() {

		loginButton.setOnAction(e -> handleLogin());
		registerButton.setOnAction(e -> handleRegistration());
	}

	/**
	 * method used to handle user login and possible errors
	 */
	public void handleLogin() {

		String username = loginField.getText();
		System.out.println("User of name " + username + " is logging in");

		User userA = FileReader.getUser(username);

		if (userA != null) {
			System.out.println("Logging in was successful");
			user = userA;
			FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("MainGUI.fxml"));
			try {
				BorderPane login = (BorderPane) fxmlL.load();
				// NewAccountCreatorController newAccountController =
				// fxmlL.<NewAccountCreatorController>getController();

				Scene scene = new Scene(login, 1360, 705);
				

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);

				closeWindow();
				stage.setTitle("Artatawe");
				stage.show();
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Access the controller that was created by the FXML loader

			
			
		} else {


			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Could not log in");

			alert.setHeaderText("The account with a given username does not exist");
			alert.setContentText("Try again or create a new account");
			try {
				FileReader.readPaintingFiles();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			alert.showAndWait();
		}

	}
	
	/**
	 * method used to close an open window
	 */
	public void closeWindow() {
		loginButton.getScene().getWindow().hide();
	}
	

	/**
	 * method used handle user registration 
	 */
	public void handleRegistration() {
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("NewAccountCreator.fxml"));
		try {
			BorderPane login = (BorderPane) fxmlL.load();
			// NewAccountCreatorController newAccountController =
			// fxmlL.<NewAccountCreatorController>getController();

			Scene scene = new Scene(login, 728, 728);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * method used to retrive user
	 * @return - user
	 */
	public static User getUser() {
		return user;
	}
	
	
	
	

}
