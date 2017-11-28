import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddArtworkController {

	@FXML
	private TextField width;

	@FXML
	private TextField height;

	@FXML
	private TextField depth;

	@FXML
	private TextField material;

	@FXML
	private RadioButton selectSculpture;

	@FXML
	private RadioButton selectPainting;

	@FXML
	private TextField title;

	@FXML
	private TextField creator;

	@FXML
	private TextField creationYear;

	@FXML
	private TextArea description;

	@FXML
	private TextField bidLimit;

	@FXML
	private TextField reservePrice;

	@FXML
	private Button addArtwork;

	public void initialize() {
		addArtwork.setOnAction(e -> createArtwork());
		final ToggleGroup group = new ToggleGroup();

		selectSculpture.setToggleGroup(group);
		selectPainting.setToggleGroup(group);

	}

	public void createArtwork() {
		String widthA = width.getText();
		String heightA = height.getText();
		String depthA = depth.getText();

		int widthI = Integer.parseInt(widthA);
		int heightI = Integer.parseInt(heightA);
		int depthI = Integer.parseInt(depthA);

		String materialA = material.getText();
		String titleA = title.getText();
		String creatorA = creator.getText();
		String creationYearA = creationYear.getText();

		int creationYearI = Integer.parseInt(creationYearA);

		String descriptionA = description.getText();
		String reservePriceA = reservePrice.getText();
		String bidLimitA = bidLimit.getText();

		int bidLimitI = Integer.parseInt(bidLimitA);
		double reservePriceD = Double.parseDouble(reservePriceA);

		User user = LoginController.getUser();
		System.out.println(user);

		if (selectSculpture.isSelected()) {
			
			
			Sculpture sculpture = new Sculpture(user, null, titleA, creatorA, creationYearI, bidLimitI, reservePriceD,
					widthI, heightI, depthI, materialA, descriptionA);
			try {
				Writer.writeSculptureFile(sculpture);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (selectPainting.isSelected()) {
			Painting painting = new Painting(user, null, titleA, creatorA, creationYearI, bidLimitI, reservePriceD,
					widthI, heightI, descriptionA);
			try {
				Writer.writePaintingFile(painting);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			


		}
		
		Alert alert = new  Alert(AlertType.INFORMATION);
		alert.setTitle("Success");

		alert.setHeaderText("Artwork has been added to the database");
		alert.setContentText("Your new auction will appear immediately");
		try {
			FileReader.readPaintingFiles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		alert.showAndWait();
		
		closeWindow();
		

	}
	
	public void closeWindow() {
		addArtwork.getScene().getWindow().hide();
	}

}
