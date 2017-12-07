/**
 * @author Tom
 * @date 20/11/2017
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class BrowsingController {

	@FXML
	private TableView<Artwork> table;

	@FXML
	private TableColumn<Artwork, ImageView> image;

	@FXML
	private TableColumn<Artwork, String> title;

	@FXML
	private TableColumn<Artwork, String> description;

	private ObservableList<Artwork> artworks;

	@FXML
	private CheckBox paintingSelected;

	@FXML
	private CheckBox sculptureSelected;

	@FXML
	private Button refresh;

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
