
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This is the GUI class for handling the adding of artwork
 * @author Daniel
 * Created on 5/12/2017
 */

public class AddArtworkController {

	@FXML
	private TextField width; // Field for width of the artwork

	@FXML
	private TextField height; // Field for height of the artwork

	@FXML
	private TextField depth; // Field for depth of the artwork

	@FXML
	private TextField material; // Field for material of the artwork

	@FXML
	private RadioButton selectSculpture; // Radio button to choose a sculpture type

	@FXML
	private RadioButton selectPainting; // Radio button to choose a painting type

	@FXML
	private TextField title; // Text field that takes a title of the artwork

	@FXML
	private TextField creator; // Text field that takes a creator of the artwork

	@FXML
	private TextField creationYear; // Text field that takes a creation year

	@FXML
	private TextArea description; // Text field that takes a title

	@FXML
	private TextField bidLimit; // Text field that takes a limit of bids

	@FXML
	private TextField reservePrice; // Text field that takes a reserve price

	@FXML
	private Button addArtwork; // Button that creates an artwork

	@FXML
	private Button addImages; //  Button to choose images

	@FXML
	private ImageView pic1; // View of the first image

	@FXML
	private ImageView pic2; // View of the second image

	@FXML
	private ImageView pic3; // View of the third image

	@FXML
	private ImageView pic4; // View of the fourth image


	// Images for artworks
	private Image img1;
	private Image img2;
	private Image img3;
	private Image img4;

	// ArrayList of images
	private ArrayList<Image> images1;

	// List of files for images
	private List<File> list;
	
	// ArrayList of Image Views for images
	private ArrayList<ImageView> imagesView;

	/**
	 * Initialises the GUI for adding an artwork
	 */
	public void initialize() {
		
		// Sets the button to add an artwork on action
		addArtwork.setOnAction(e -> createArtwork());
		
		// Toggle Group for radio buttons 
		final ToggleGroup group = new ToggleGroup();

		// Adds radio button to a toggle group
		selectSculpture.setToggleGroup(group);
		selectPainting.setToggleGroup(group);
		imagesView = new ArrayList<ImageView>();
		images1 = new ArrayList<Image>();

		// Adds images for viewing
		imagesView.add(pic1);
		imagesView.add(pic2);
		imagesView.add(pic3);
		imagesView.add(pic4);

		// Adds images to an Array List
		images1.add(img1);
		images1.add(img2);
		images1.add(img3);
		images1.add(img4);

		// If painting is pressed, disable depth and material
		selectPainting.setOnAction(e -> {
			depth.setDisable(true);
			material.setDisable(true);
		});

		// If sculpture is pressed, ensure that
		// fields for depth and material are enabled
		selectSculpture.setOnAction(e -> {
			depth.setDisable(false);
			material.setDisable(false);
		});
		
		// If button for adding images is pressed,
		// open a new window
		addImages.setOnAction(e -> addPictures());

	}

	/**
	 * Opens a window for selecting images
	 */
	public void addPictures() {

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Open Resource File");
		Stage stage = new Stage();
		list = fileChooser.showOpenMultipleDialog(stage);

		for (int i = 0; i < list.size() && i < 4; i++) {

			Image imgA = images1.get(i);
			ImageView picA = imagesView.get(i);
			try {
				imgA = new Image(new FileInputStream(list.get(i).getPath()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			picA.setImage(imgA);

		}

	}
	
	/**
	 * Method to compy and image to a new directory
	 * @param name - name of the artwork image
	 */
	public void copyPictures(String name) {

		int counter = 0;
		for (File file : list) {
			File file1 = new File("artworkImages/" + name);
			file1.mkdir();
			Path path = Paths.get("artworkImages/" + name + "/" + counter + ".png");
			try {
				Files.copy(file.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			System.out.println(file.getPath());
			counter++;
		}
	}

	
	
	/**
	 * Method to create a new artwork from GUI input
	 */
	public void createArtwork() {
		try{			
		Painting painting = null;
		Sculpture sculpture = null;

		
		String widthA = width.getText();
		String heightA = height.getText();

		int widthI = Integer.parseInt(widthA);
		int heightI = Integer.parseInt(heightA);

		String titleA = title.getText();
		String creatorA = creator.getText();
		String creationYearA = creationYear.getText();

		int creationYearI = Integer.parseInt(creationYearA);
		
		String descriptionA = "";
			if(description.getText().isEmpty()){
				descriptionA = "";
			} else{
				descriptionA = description.getText();
		}
		String reservePriceA = reservePrice.getText();
		String bidLimitA = bidLimit.getText();

		int bidLimitI = Integer.parseInt(bidLimitA);
		double reservePriceD = Double.parseDouble(reservePriceA);

		User user = LoginController.getUser();
		System.out.println(user);
			
		
		if (selectSculpture.isSelected()) {
			String depthA = depth.getText();
			int depthI = Integer.parseInt(depthA);
			String materialA = material.getText();
			if(depthA.isEmpty() || materialA.isEmpty()){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");

				alert.setHeaderText("Could not create an artwork");
				alert.setContentText("Make sure you fill all fields and press button again");
				alert.showAndWait();

				return;
			} else{
			sculpture = new Sculpture(user, null, titleA, creatorA, creationYearI, bidLimitI, reservePriceD, widthI,
					heightI, depthI, materialA, descriptionA);
			user.addArtwork(sculpture);
			}
			try {
				Writer.writeSculptureFile(sculpture);
				copyPictures(titleA);
				sculpture.resolveImage();
				FileReader.addSculpture(sculpture);

			} catch (IOException e) {
				
				e.printStackTrace();
			}

		} else if (selectPainting.isSelected()) {

			painting = new Painting(user, null, titleA, creatorA, creationYearI, bidLimitI, reservePriceD, widthI,
					heightI, descriptionA);
			user.addArtwork(painting);
			try {
				Writer.writePaintingFile(painting);
				copyPictures(titleA);
				painting.resolveImage();
				FileReader.addPainting(painting);
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
		
		//Message to let user know creation was successful
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");

		alert.setHeaderText("Artwork has been added to the database");
		alert.setContentText("Your new auction will appear immediately");

		alert.showAndWait();

		closeWindow();
		} catch(NumberFormatException e){ //Error checking for input
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");

			alert.setHeaderText("Could not create an artwork");
			alert.setContentText("Make sure you fill all fields and press button again");
			alert.showAndWait();

			return;
		}

		
		
	}

	/**
	 * Closes the window for adding artworks
	 */
	public void closeWindow() {
		addArtwork.getScene().getWindow().hide();
	}

}
