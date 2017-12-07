import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * this class is used control browsing. 
 * @author Software Engineering Team Three
 * @version 1.0
 */

public class BrowsingController {

	@FXML
	private TableView<Artwork> table; // tableview of the table of artwork.

	@FXML
	private TableColumn<Artwork, ImageView> image; //table column for image.

	@FXML
	private TableColumn<Artwork, String> title; //table column for title

	@FXML
	private TableColumn<Artwork, String> description;  //table column for description

	private ObservableList<Artwork> artworks; //artworks list to track changes 

	@FXML
	private CheckBox paintingSelected; //checkbox to determine if painting is selected

	@FXML
	private CheckBox sculptureSelected;//checkbox to determine if sculpture is selected

	@FXML
	private Button refresh; //button used to refresh

	/**
	 * method used to intailaize browsing through artwork
	 */
	public void initialize() {

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

	}

	/**
	 * method used to update artword records
	 */
	public void update() {
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

	}

}
