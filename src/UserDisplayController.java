import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class UserDisplayController {


	private static User user;
	
	
    @FXML
    private ImageView avatar;

    @FXML
    private Label username;

    
    public void initialize() {
    	avatar.setImage(user.getImage());
    	username.setText(user.getUsername());
    }


	public  static User getUser() {
		return user;
	}


	public static void setUser(User user1) {
		user = user1;
	}
    
    
	
    
}
