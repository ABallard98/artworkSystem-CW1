import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * This class launches the overall application
 * @author Daniel
 * Created on 8/12/17
 */
public class Main extends Application {

	/**
	 * This method sets the GUI
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));

		Scene scene = new Scene(root, 600, 400);

		stage.setTitle("Log in");
		stage.setScene(scene);

		stage.show();

	}

	public static void main(String[] args) {
		FileReader.initialize();
		launch(args);
		System.out.println(FileReader.getUsers().size());
	}

}
