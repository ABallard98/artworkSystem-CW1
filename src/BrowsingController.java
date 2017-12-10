import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * This is the GUI class for browsing all artworks for sale
 * @author Marcin
 * Created on 04/12/2017
 */

public class BrowsingController {

	@FXML
	private RadioButton database; // Button to select database filter

	@FXML
	private RadioButton fav; // Button to select favourites filter

	@FXML
	private BorderPane mainSection; // Central section of the display

	@FXML
	private Button displaySelected; // A button to display selected artwork

	@FXML
	private TableView<Artwork> table; // Table containing a list of artworks

	@FXML
	private TableColumn<Artwork, ImageView> image; // Column with artwork images

	@FXML
	private TableColumn<Artwork, String> title; // Column with artwork titles

	@FXML
	private TableColumn<Artwork, String> description; // Column with artwork descriptions

	private ObservableList<Artwork> artworks; // List of artwork

	@FXML
	private CheckBox paintingSelected; // Checkbox to filter paintings

	@FXML
	private CheckBox sculptureSelected; // Checkbox to filter sculptures

	@FXML
	private Button refresh; // Button to refresh the browser
	
	/**
	 * Method to intialise the browsing window
	 */
	public void initialize() {
		
		ToggleGroup tg = new ToggleGroup();
		database.setToggleGroup(tg);		
		fav.setToggleGroup(tg);

		sculptureSelected.setSelected(true);
		paintingSelected.setSelected(true);

		image.setMinWidth(100);

		artworks = FXCollections.observableArrayList(FileReader.getArtworks());

		image.setCellValueFactory(new PropertyValueFactory<Artwork, ImageView>("imageView"));
		title.setCellValueFactory(new PropertyValueFactory<Artwork, String>("title"));
		title.setPrefWidth(200);
		description.setCellValueFactory(new PropertyValueFactory<Artwork, String>("description"));
		description.setPrefWidth(300);

		table.setItems(artworks);
		refresh.setOnAction(e -> update());

		displaySelected.setOnAction(e -> displaySelection());

	}

	/**
	 * Method to update the artworks shown
	 */
	public void update() {
			// Displays either sculpture or paintings or both
		if (database.isSelected()) {
			if (sculptureSelected.isSelected() && paintingSelected.isSelected()) {
				artworks = FXCollections.observableArrayList(FileReader.getArtworks());
			} else if (!sculptureSelected.isSelected() && paintingSelected.isSelected()) {
				artworks = FXCollections.observableArrayList(FileReader.getSculptures());
			} else if (sculptureSelected.isSelected() && !paintingSelected.isSelected()) {
				artworks = FXCollections.observableArrayList(FileReader.getPaintings());
			} else {
				artworks = null;
			}

			table.setItems(artworks);
			// Displays favourites
		} else if (fav.isSelected()) {
			ArrayList<Artwork> artworks1 = new ArrayList<>();
			ArrayList<User> favs = LoginController.getUser().getFavouriteUsers();

			for (User u : favs) {

				for (Artwork art : u.getArtForSale()) {
					artworks1.add(art);
				}
			}
			artworks = FXCollections.observableArrayList(artworks1);
			table.setItems(artworks);

		}

	}
	
	/**
	 * Filters browser window by painting or sculpture
	 */
	public void displaySelection() {
		Artwork artwork = table.getSelectionModel().getSelectedItem();

		if (artwork instanceof Sculpture) {
			Sculpture sculpture = (Sculpture) artwork;
			ArtworkController.setCurrentSculpture(sculpture);
		} else if (artwork instanceof Painting) {
			Painting painting = (Painting) artwork;
			ArtworkController.setCurrentPainting(painting);
		}

		BorderPane bp;

		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("/ArtworkView.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
