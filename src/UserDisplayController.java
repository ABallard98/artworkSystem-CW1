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
 * @author Joshua and Ayden
 * Created on 04/12/2017
 */
public class UserDisplayController {

	private static User user;

	@FXML
	private ImageView avatar;

	@FXML
	private Label username;

	@FXML
	private CheckBox favourite;

	@FXML
	private Button confirm;

	/**
	 * Method to initialise the user display window
	 */
	public void initialize() {
		username.setText(user.getUsername());
		System.out.println(user.getFullName() + "   " + user.getAvatarIndex());
		String path = "avatars/avatar" + user.getAvatarIndex() + ".png";
		System.out.println("Avatar path is " + path);
		Image image = user.getImage();
		avatar.setImage(user.getImage());

		confirm.setOnAction(e -> handleFavourites());
		checkboxes();

	}

	/**
	 * Method to allow a user to check another user as a favourite
	 */
	public void checkboxes() {
		User loggedUser = LoginController.getUser();

		if (FileReader.checkIfInFavouriteList(loggedUser, user)) {
			favourite.setSelected(true);
		} else {
			favourite.setSelected(false);
		}

	}

	/**
	 * Method to return a user
	 * @return user - a displayed user
	 */
	public static User getUser() {
		return user;
	}

	/**
	 * Method to set a user
	 * @param user1 - a user object
	 */
	public static void setUser(User user1) {
		user = user1;
	}

	/**
	 * Method to add a favourite user
	 */
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
