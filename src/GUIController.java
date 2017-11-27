import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GUIController {
 
	
    @FXML
    private Hyperlink dashboardLink;

    @FXML
    private Hyperlink myAuctionsLink;

    @FXML
    private Hyperlink myBidsLink;

    @FXML
    private Hyperlink userSettingsLink;

    @FXML
    private Button createNewArtworkButton;
    
    
    @FXML
    private Label username;

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    @FXML
    private Label fullName;

    @FXML
    private Label address;

    @FXML
    private Label postcode;

    @FXML
    private Label lastLogin;

    @FXML
    private Label phoneNumber;


	

    @FXML
    private BorderPane mainSection;
    
	public void initialize() {
		
		userSettingsLink.setOnAction(e-> userSettings());
	
		
		
	}
	
	
	public void userSettings() {
		
		
		
		
		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
}
