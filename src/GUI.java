import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUI extends Application {

	private Stage stage;

	
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainGUI.fxml"));

		Scene scene = new Scene(root, 1280, 800);

		stage.setTitle("Artatawe");
		stage.setScene(scene);

		
		//loginButton.setOnAction(e-> handleLogin());
		
		stage.show();

	}

	

}
