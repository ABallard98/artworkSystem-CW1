import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends Application {

	
    @FXML
    private TextField loginField;

    @FXML
    private Button loginButton;

	
	
	@Override
	public void start(Stage arg0) throws Exception {
		
		
		//loginButton.setOnAction(e-> handleLogin());
		
	}

	public void handleLogin() {
		System.out.println(1);
		
	}
	
}
