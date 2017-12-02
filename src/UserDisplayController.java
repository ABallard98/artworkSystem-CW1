import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserDisplayController {


	private static User user;
	
	
    @FXML
    private ImageView avatar;

    @FXML
    private Label username;

    
    public void initialize() {
    	username.setText(user.getUsername());
    	try {
    		System.out.println(user.getFullName() + "   "+ user.getAvatarIndex());
    		String path = "avatars/avatar"+user.getAvatarIndex()+".png";
    		System.out.println("Avatar path is " + path);
			Image image = new Image(new FileInputStream(path));
	    	avatar.setImage(image);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }


	public  static User getUser() {
		return user;
	}


	public static void setUser(User user1) {
		user = user1;
	}
    
    
	
    
}
