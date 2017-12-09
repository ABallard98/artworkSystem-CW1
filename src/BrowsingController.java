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
 * @author Marcin
 * @date 04/12/2017
 */

public class BrowsingController {

	@FXML
	private RadioButton database;

	@FXML
	private RadioButton fav;

	@FXML
	private BorderPane mainSection; // central section of the display

	@FXML
	private Button displaySelected; // a button to display selected artwork

	@FXML
	private TableView<Artwork> table; // table containing a list of artworks

	@FXML
	private TableColumn<Artwork, ImageView> image; // column with artwork images

	@FXML
	private TableColumn<Artwork, String> title; // column with artwork titles

	@FXML
	private TableColumn<Artwork, String> description; // column with artwork descriptions

	private ObservableList<Artwork> artworks;

	@FXML
	private CheckBox paintingSelected;

	@FXML
	private CheckBox sculptureSelected;

	@FXML
	private Button refresh;

	public void initialize() {
		
		ToggleGroup tg = new ToggleGroup();
		database.setToggleGroup(tg);		
		fav.setToggleGroup(tg);


		sculptureSelected.setSelected(true);
		paintingSelected.setSelected(true);

		image.setMinWidth(100);

		artworks = FXCollections.observableArrayList(FileReader.getArtworks());

		System.out.println("Artworks number: " + artworks.size());
		image.setCellValueFactory(new PropertyValueFactory<Artwork, ImageView>("imageView"));
		title.setCellValueFactory(new PropertyValueFactory<Artwork, String>("title"));
		title.setPrefWidth(200);
		description.setCellValueFactory(new PropertyValueFactory<Artwork, String>("description"));
		description.setPrefWidth(300);

		table.setItems(artworks);
		refresh.setOnAction(e -> update());

		displaySelected.setOnAction(e -> displaySelection());

	}

	public void update() {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
