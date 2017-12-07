import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class MyAuctionsController {

	@FXML
	private Button refreshButton;

	@FXML
	private RadioButton allAuctions;

	@FXML
	private RadioButton active;

	@FXML
	private RadioButton finished;

	@FXML
	private CheckBox sculptures;

	@FXML
	private CheckBox paintings;

	@FXML
	private TableView<Artwork> table;

	@FXML
	private TableColumn<Artwork, String> titleColumn;

	@FXML
	private TableColumn<Artwork, String> creatonNameColumn;

	@FXML
	private TableColumn<Artwork, String> reservePriceColumn;

	@FXML
	private TableColumn<Artwork, String> placedBidsColumn;

	@FXML
	private TableColumn<Artwork, String> bidLimitColumn;

	@FXML
	private TableColumn<Artwork, Double> currentPriceColumn;

	@FXML
	private TableColumn<Artwork, Boolean> ongoingColumn;

	private ObservableList<Artwork> artworks;

	public void initialize() {

		ToggleGroup tg = new ToggleGroup();
		allAuctions.setToggleGroup(tg);
		finished.setToggleGroup(tg);
		active.setToggleGroup(tg);

		for (Artwork a : LoginController.getUser().getArtForSale()) {
			System.out.println(a.getClass());
		}

		artworks = FXCollections.observableArrayList(LoginController.getUser().getArtForSale());

		titleColumn.setCellValueFactory(new PropertyValueFactory<Artwork, String>("title"));
		creatonNameColumn.setCellValueFactory(new PropertyValueFactory<Artwork, String>("creatorName"));
		reservePriceColumn.setCellValueFactory(new PropertyValueFactory<Artwork, String>("reservePrice"));
		placedBidsColumn.setCellValueFactory(new PropertyValueFactory<Artwork, String>("numberOfBids"));
		bidLimitColumn.setCellValueFactory(new PropertyValueFactory<Artwork, String>("bidsAllowed"));
		currentPriceColumn.setCellValueFactory(new PropertyValueFactory<Artwork, Double>("highestBidAmount"));
		ongoingColumn.setCellValueFactory(new PropertyValueFactory<Artwork, Boolean>("bidIsOver"));

		System.out.println("No of my artworks is" + artworks.size());

		table.setItems(artworks);

		paintings.setSelected(true);
		sculptures.setSelected(true);

		active.setSelected(true);
		refreshButton.setOnAction(e -> filter());

	}

	public void filter() {

		ArrayList<Artwork> arts = LoginController.getUser().getArtForSale();
		ArrayList<Artwork> newArts = new ArrayList<>();

		ArrayList<Sculpture> sculpturesA = new ArrayList<>();
		ArrayList<Painting> paintingsA = new ArrayList<>();

		if (active.isSelected()) {

			for (Artwork a : arts) {

				if (!a.isBidIsOver()) {

					if (paintings.isSelected()) {
						if (a instanceof Painting) {
							newArts.add(a);
							System.out.println("yess");

						}

					}
					if (sculptures.isSelected()) {
						if (a instanceof Sculpture) {
							newArts.add(a);
						}

					}

				}
			}
			ObservableList<Artwork> artworksA = FXCollections.observableArrayList(newArts);

			table.setItems(artworksA);

		} else if (finished.isSelected()) {
			for (Artwork a : arts) {
				if (a.isBidIsOver()) {
					newArts.add(a);
				}
			}
		} else if (allAuctions.isSelected()) {
			newArts = arts;
		}

		if (sculptures.isSelected() && paintings.isSelected()) {
			ObservableList<Artwork> artworksA = FXCollections.observableArrayList(newArts);

			table.setItems(artworksA);

		} else if (sculptures.isSelected() && !paintings.isSelected()) {

		}

	}

}
