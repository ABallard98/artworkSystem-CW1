import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	@FXML
	private Button avatar1;

	@FXML
	private ImageView av1;

	@FXML
	private Button avatar3;

	@FXML
	private ImageView av3;

	@FXML
	private Button avatar2;

	@FXML
	private ImageView av2;

	@FXML
	private Button avatar4;

	@FXML
	private ImageView av4;

	@FXML
	private Button avatar5;

	@FXML
	private ImageView av5;

	@FXML
	private Button avatar6;

	@FXML
	private ImageView av6;

	@FXML
	private ImageView mainAvatar;


	private int avatarIndex;
	private Image image1 = null;
	private Image image2 = null;
	private Image image3 = null;
	private Image image4 = null;
	private Image image5 = null;
	private Image image6 = null;

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

	}

	public void updateAvatar(int i) {

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

	public void createAccount() {


		
		
		String username = usernameField.getText();
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		String address = addressField.getText();
		String postCode = postcodeField.getText();
		String phoneNumber = phoneNumberField.getText();
		long phoneNumberLong = 0;
		if(username.isEmpty() 	|| firstName.isEmpty() 	|| lastName.isEmpty()
				|| address.isEmpty() 	|| postCode.isEmpty() 	|| phoneNumber.isEmpty()) {

			
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
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");

			alert.setHeaderText("Could not create an user");
			alert.setContentText("Make sure you fill all fields and press button again");
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

		User user = new User(username, firstName, lastName, address, postCode, phoneNumberLong, avatarIndex);
		//user.setAvatarIndex(avatarIndex);
		//user.resolvePicture();
		System.out.println(user.getTextFileOutput());
		FileReader.addUser(user);
		

		
		//user
		
		try {
			Writer.writeUserFile(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(userdata);
		
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");

		alert.setHeaderText("The user has been created");
		alert.setContentText("Close this window to return to login screen");
		alert.showAndWait();
		
		
		createAccountButton.getScene().getWindow().hide();

	}

	public void openCustomPictureCreator() {
		// for Ayden
	}

}
