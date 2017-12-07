import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * this class is used to control user display
 * @author Software Engineering Team Three
 * @version 1.0
 */

public class UserDisplayController {

	private static User user; //user object called user

	@FXML
	private ImageView avatar; //Imageview used to user avatar

	@FXML
	private Label username; //label for user name

	@FXML
	private CheckBox favourite; //checkbox to determine if favourite is selected

	@FXML
	private Button confirm; // button used to confirm

	/**
	 * method used to display user information
	 */
	public void initialize() {
		username.setText(user.getUsername());
		try {
			System.out.println(user.getFullName() + "   " + user.getAvatarIndex());
			String path = "avatars/avatar" + user.getAvatarIndex() + ".png";
			System.out.println("Avatar path is " + path);
			Image image = new Image(new FileInputStream(path));
			avatar.setImage(user.getImage());

			confirm.setOnAction(e -> handleFavourites());
			checkboxes();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * method used to intailaize user login.
	 */
	public void checkboxes() {
		User loggedUser = LoginController.getUser();

		if (FileReader.checkIfInFavouriteList(loggedUser, user)) {
			favourite.setSelected(true);
		} else {
			favourite.setSelected(false);
		}

	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user1) {
		user = user1;
	}

	public void handleFavourites() {
		User loggedUser = LoginController.getUser();
		
		

		if (favourite.isSelected()) {
			if (!FileReader.checkIfInFavouriteList(loggedUser, user)) {
				LoginController.getUser().addUserToFavourites(user);
				Writer.addToFavourites(loggedUser, user);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success ");

				alert.setHeaderText("User has been added to your list");
				alert.showAndWait();
				
				favourite.getScene().getWindow().hide();
				
			} else {

			}
		} else {
			if (FileReader.checkIfInFavouriteList(loggedUser, user)) {
				loggedUser.removeUserFromFavourites(user);
				Writer.removeFromFavourites(loggedUser, user);
			}

		}
	}

}
