import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

	public void initialize() {
		username.setText(user.getUsername());
		try {
			System.out.println(user.getFullName() + "   " + user.getAvatarIndex());
			String path = "avatars/avatar" + user.getAvatarIndex() + ".png";
			System.out.println("Avatar path is " + path);
			Image image = new Image(new FileInputStream(path));
			avatar.setImage(image);

			confirm.setOnAction(e -> handleFavourites());
			checkboxes();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

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
		if (favourite.isSelected()) {
			User loggedUser = LoginController.getUser();
			if (!FileReader.checkIfInFavouriteList(loggedUser, user)) {
				LoginController.getUser().addUserToFavourites(user);
				Writer.addToFavourites(loggedUser, user);
			}
		} else {

		}
	}

}
