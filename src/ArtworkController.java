import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ArtworkController {

	

    @FXML
    private Label categoryA;

    @FXML
    private Label titleA;

    @FXML
    private Label creatorA;

    @FXML
    private Label yearA;

    @FXML
    private Label noOfBidsA;

    @FXML
    private Label bidsLimitA;

    @FXML
    private Label postcode1;

    @FXML
    private Label postcode2;

    @FXML
    private Label widthA;

    @FXML
    private Label heightA;

    @FXML
    private Label depthA;

    @FXML
    private Label materialA;

    @FXML
    private Label currentPrice;

    @FXML
    private Label sellerA;

    @FXML
    private TextField bidAmount;

    @FXML
    private Button placeBid;
    
    private static Painting currentArtwork; 
    
    
    public void initialize() {
    	//Painting p = getPainting();
    	titleA.setText(currentArtwork.getTitle());
    	widthA.setText(currentArtwork.getWidth()+"");

    	heightA.setText(currentArtwork.getHeight()+"");

    	yearA.setText(currentArtwork.getCreationYear()+"");

    	creatorA.setText(currentArtwork.getCreator());
    	titleA.setText(currentArtwork.getTitle());
    	titleA.setText(currentArtwork.getTitle());

    }


	public Painting getCurrentArtwork() {
		return currentArtwork;
	}


	public static void setCurrentArtwork(Painting art) {
		currentArtwork = art;
	}
	
    
    
}
