import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This is the GUI class for logging in to the system
 * @author Marcin
 * Created on 22/11/2017
 */
public class LoginController {

	
	private static User user; // A user object
	
	@FXML
	private TextField loginField; // Text field for a username to log in

	@FXML
	private Button loginButton; // Button to log a user in

	@FXML
	private Button registerButton; // Button to register a new user

	/**
	 * Initialise the login screen
	 */
	public void initialize() {

		loginButton.setOnAction(e -> handleLogin());
		registerButton.setOnAction(e -> handleRegistration());
	}

	/**
	 * Allows a user to log in to the system
	 */
	public void handleLogin() {

		String username = loginField.getText();
		System.out.println("User of name " + username + " is logging in");

		User userA = FileReader.getUser(username);
		// Checks if user is in memory then logs in if it is
		if (userA != null) {
			System.out.println("Logging in was successful");
			user = userA;
			FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("MainGUI.fxml"));
			try {
				BorderPane login = (BorderPane) fxmlL.load();

				Scene scene = new Scene(login, 1360, 705);
				

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);

				closeWindow();
				stage.setTitle("Artatawe");
				stage.show();
				

			} catch (IOException e) {
				e.printStackTrace();
			}			
			
		} else { // Error message if user does not exist


			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Could not log in");

			alert.setHeaderText("The account with a given username does not exist");
			alert.setContentText("Try again or create a new account");
			try {
				FileReader.readPaintingFiles();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			alert.showAndWait();
		}
	}
	
	/**
	 * Method to close the log in window
	 */
	public void closeWindow() {
		loginButton.getScene().getWindow().hide();
	}
	
	/**
	 * Method to let a new user register
	 */
	public void handleRegistration() {
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("NewAccountCreator.fxml"));
		try {
			BorderPane login = (BorderPane) fxmlL.load();

			Scene scene = new Scene(login, 728, 728);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to return a user
	 * @return user - desired user
	 */
	public static User getUser() {
		return user;
	}	
}