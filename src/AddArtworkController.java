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

	@FXML
	private Button addImages;

	@FXML
	private ImageView pic1;

	@FXML
	private ImageView pic2;

	@FXML
	private ImageView pic3;

	@FXML
	private ImageView pic4;
	
	private Image img1;
	private Image img2;
	private Image img3;
	private Image img4;
	private ArrayList<Image> images1;

	

	private List<File> list;
	private ArrayList<ImageView> imagesView;

	public void initialize() {
		addArtwork.setOnAction(e -> createArtwork());
		final ToggleGroup group = new ToggleGroup();

		selectSculpture.setToggleGroup(group);
		selectPainting.setToggleGroup(group);
		imagesView = new ArrayList<ImageView>();
		images1 =  new ArrayList<Image>();
		
		imagesView.add(pic1);
		imagesView.add(pic2);
		imagesView.add(pic3);
		imagesView.add(pic4);

		images1.add(img1);
		images1.add(img2);
		images1.add(img3);
		images1.add(img4);

		addImages.setOnAction(e -> addPictures());

	}

	public void addPictures() {

		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Open Resource File");
		Stage stage = new Stage();
		list = fileChooser.showOpenMultipleDialog(stage);
		// stage.show();
		
		//for(int i = 0; i< list.size(); i++) {
		//	Image imgA = images1.get(i);
		//	System.out.println(list.get(i).getPath());
		//	try {
		//		imgA = new Image(new FileInputStream(list.get(i).getPath()));
		//	} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
		//		e.printStackTrace();
		//	}
		//	imagesView.get(i).setImage(img1);
		//}

		try {
			img1 = new Image(new FileInputStream(list.get(0).getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pic1.setImage(img1);

	}
	
	public void copyPictures(String name) {
		for (File file : list) {
			Path path = Paths.get("artworkImages/"+name+".png");
			try {
				Files.copy(file.toPath(), path, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(file.getPath());
		}
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

		copyPictures(titleA);
		Alert alert = new Alert(AlertType.INFORMATION);
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
