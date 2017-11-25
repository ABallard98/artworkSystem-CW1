import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class LoginWindow extends Application {

	

    @FXML
    private TextField loginField;

    @FXML
    private Button loginButton;

	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));

		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("Log in");
		stage.setScene(scene);

		
		//loginButton.setOnAction(e-> handleLogin());
		
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	
	public void handleLogin() {
		System.out.println(1);
		
	}
	
}
