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

	private static Painting currentPainting;
	private static Sculpture currentSculpture;

	public void initialize() {
		if (currentPainting != null) {
			initializePainting();

		}

		if (currentSculpture != null) {
			initializeSculpture();
		}

	}

	public void initializePainting() {
		titleA.setText(currentPainting.getTitle());
		widthA.setText(currentPainting.getWidth() + "");
		heightA.setText(currentPainting.getHeight() + "");
		yearA.setText(currentPainting.getCreationYear() + "");
		creatorA.setText(currentPainting.getCreator());
		titleA.setText(currentPainting.getTitle());
		titleA.setText(currentPainting.getTitle());
		noOfBidsA.setText(currentPainting.getNumberOfBids() + "");
		bidsLimitA.setText(currentPainting.getBidsAllowed() + "");
	}

	public void initializeSculpture() {
		titleA.setText(currentSculpture.getTitle());
		widthA.setText(currentSculpture.getWidth() + "");
		heightA.setText(currentSculpture.getHeight() + "");
		depthA.setText(currentSculpture.getDepth() + "");
		materialA.setText(currentSculpture.getMaterial());
		yearA.setText(currentSculpture.getCreationYear() + "");
		creatorA.setText(currentSculpture.getCreator());
		titleA.setText(currentSculpture.getTitle());
		titleA.setText(currentSculpture.getTitle());
		noOfBidsA.setText(currentSculpture.getNumberOfBids() + "");
		bidsLimitA.setText(currentSculpture.getBidsAllowed() + "");
	}

	public static void setCurrentPainting(Painting painting) {
		currentPainting = painting;
		currentSculpture = null;

	}

	public static void setCurrentSculpture(Sculpture sculpture) {
		currentPainting = null;

		currentSculpture = sculpture;

	}

}
