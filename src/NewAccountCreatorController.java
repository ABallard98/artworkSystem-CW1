import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This is the GUI class that handles the creation of a new user into the system
 * @author Joshua
 * Created on 1/12/2017
 */
public class NewAccountCreatorController {

	@FXML
	private TextField usernameField; // Allows input for a username

	@FXML
	private TextField firstNameField; // Allows input for a first name

	@FXML
	private TextField lastNameField; // Allows input for a last name

	@FXML 
	private TextField addressField; // Allows input for an address

	@FXML
	private TextField postcodeField; // Allows input for a postcode

	@FXML
	private TextField phoneNumberField; // Allows input for a phone number

	@FXML
	private Button createAccountButton; // Button to create the new account

	@FXML
	private Button openGeneratorButton; // Button to create a custom image

	@FXML
	private Button avatar1; // Button to select an avatar

	@FXML
	private ImageView av1; // Container to display an avatar

	@FXML
	private Button avatar3; // Button to select an avatar

	@FXML
	private ImageView av3; // Container to display an avatar

	@FXML
	private Button avatar2; // Button to select an avatar

	@FXML
	private ImageView av2; // Container to display an avatar

	@FXML
	private Button avatar4; // Button to select an avatar

	@FXML
	private ImageView av4; // Container to display an avatar

	@FXML
	private Button avatar5; // Button to select an avatar

	@FXML
	private ImageView av5; // Container to display an avatar

	@FXML
	private Button avatar6; // Button to select an avatar

	@FXML
	private ImageView av6; // Container to display an avatar

	@FXML
	private ImageView mainAvatar; // Container to display the main avatar

	private static boolean custom; // Boolean to check if custom image is used

	private int avatarIndex; // Reference for which avatar was selected
	private Image image1 = null;
	private Image image2 = null;
	private Image image3 = null;
	private Image image4 = null;
	private Image image5 = null;
	private Image image6 = null;

	/**
	 * Method to initialise the account registration window
	 */
	public void initialize() {
		initialiseAvatars();
		createAccountButton.setOnAction(e -> createAccount());
		openGeneratorButton.setOnAction(e -> openCustomPictureCreator());

		avatar1.setOnAction(e -> updateAvatar(1));
		avatar2.setOnAction(e -> updateAvatar(2));
		avatar3.setOnAction(e -> updateAvatar(3));
		avatar4.setOnAction(e -> updateAvatar(4));
		avatar5.setOnAction(e -> updateAvatar(5));
		avatar6.setOnAction(e -> updateAvatar(6));

		avatarIndex = 1;
	}

	/**
	 * Method to set the users images
	 */
	public void setImg() {
		if (custom) {
			Image img1;
			try {
				img1 = new Image(new FileInputStream("tmpImg.png"));
				mainAvatar.setImage(img1); // Sets main user avatar as the custom image
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to change the avatar selected
	 * @param i - index of the avatar
	 */
	public void updateAvatar(int i) {
		// Sets the users avatar to one of the 6 avatars available
		if (i == 1) {
			mainAvatar.setImage(image1);
			avatarIndex = 1;
		} else if (i == 2) {
			mainAvatar.setImage(image2);
			avatarIndex = 2;
		} else if (i == 3) {
			mainAvatar.setImage(image3);
			avatarIndex = 3;
		} else if (i == 4) {
			mainAvatar.setImage(image4);
			avatarIndex = 4;
		} else if (i == 5) {
			mainAvatar.setImage(image5);
			avatarIndex = 5;
		} else if (i == 6) {
			mainAvatar.setImage(image6);
			avatarIndex = 6;
		}

	}

	/**
	 * Method to intialise the avatars for selection
	 */
	public void initialiseAvatars() {

		image1 = null;
		image2 = null;
		image3 = null;
		image4 = null;
		image5 = null;
		image6 = null;

		try {
			image1 = new Image(new FileInputStream("avatars/avatar1.png"));
			image2 = new Image(new FileInputStream("avatars/avatar2.png"));
			image3 = new Image(new FileInputStream("avatars/avatar3.png"));
			image4 = new Image(new FileInputStream("avatars/avatar4.png"));
			image5 = new Image(new FileInputStream("avatars/avatar5.png"));
			image6 = new Image(new FileInputStream("avatars/avatar6.png"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		av1.setImage(image1);
		av2.setImage(image2);
		av3.setImage(image3);
		av4.setImage(image4);
		av5.setImage(image5);
		av6.setImage(image6);

	}

	/**
	 * Method to create an account from GUI input
	 */
	public void createAccount() {
		// Assigns variables based on user input
		String username = usernameField.getText();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String address = addressField.getText();
		String postCode = postcodeField.getText();
		String phoneNumber = phoneNumberField.getText();
		long phoneNumberLong;
		if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || postCode.isEmpty()
				|| phoneNumber.isEmpty()) { // Checks if number is correct format

			Alert alert = new Alert(AlertType.ERROR); // Error message
			alert.setTitle("Error");

			alert.setHeaderText("Could not create an user");
			alert.setContentText("Make sure you fill all fields and press button again");
			alert.showAndWait();

			return;
		}

		else {
			try {
				phoneNumberLong = Long.parseLong(phoneNumber);
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");

				alert.setHeaderText("Wrong format of phone number");
				alert.setContentText("Please enter correct phone number");
				alert.showAndWait();
				return;
			}

			String userdata = "";
			userdata += "\n Username: " + username;
			userdata += "\n First name: " + firstName;
			userdata += "\n Last name: " + lastName;
			userdata += "\n Address: " + address;
			userdata += "\n Post code: " + postCode;
			userdata += "\n Phone number: " + phoneNumberLong;

			if(custom) {
				avatarIndex = 101;
				// Gives users the custom avatar
				File file1 = new File("artworkImages/" + username);
				file1.mkdir();
				Path path = Paths.get("customAvatars/" + username + ".png");
				String path1 = "tmpImg.png";
				File file = new File(path1);

				try {
					Files.copy(file.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
			
					e.printStackTrace();
				}
			}
			
			User user = new User(username, firstName, lastName, address, postCode, phoneNumberLong, avatarIndex);
			FileReader.addUser(user); // Creates user

			try {
				Writer.writeUserFile(user); // Adds user to memory
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			

			Alert alert = new Alert(AlertType.INFORMATION); // Success message
			alert.setTitle("Success");

			alert.setHeaderText("The user has been created");
			alert.setContentText("Close this window to return to login screen");
			alert.showAndWait();

			createAccountButton.getScene().getWindow().hide();
		}

	}

	/**
	 * Method to open the window which allows an avatar to be drawn
	 */
	public void openCustomPictureCreator() {

		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("AvatarDrawingTool.fxml"));
		try {
			BorderPane login = (BorderPane) fxmlL.load();

			Scene scene = new Scene(login, 600, 400);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.showAndWait();
			
			if(custom) {
				setImg();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * Method to get a users main avatar
	 * @return mainAvatar - picture chosen by user
	 */
	public ImageView getMainAvatar() {
		return mainAvatar;
	}

	/**
	 * Method to set a users main avatar
	 * @param mainAvatar - picture chosen by user
	 */
	public void setMainAvatar(ImageView mainAvatar) {
		String path = "tmpImg.png";

		try {
			Image img = new Image(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		custom = true;
		avatarIndex = 100;
	}

	public static void setCustom(boolean val) {
		custom = val;
	}
}