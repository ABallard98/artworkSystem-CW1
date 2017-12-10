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

/**
 * This is the GUI class to display the artwork a user has uploaded for auction
 * @author Thomas
 * Created on 20/11/2017
 */
public class MyAuctionsController {

	@FXML
	private Button refreshButton; // Button to refresh the page

	@FXML
	private RadioButton allAuctions; // Button to filter auctions

	@FXML
	private RadioButton active; // Button to filter auctions

	@FXML
	private RadioButton finished; // Button to filter auctions

	@FXML
	private CheckBox sculptures; // Checkbox to filter auctions

	@FXML
	private CheckBox paintings; // Checkbox to filter auctions

	@FXML
	private TableView<Artwork> table; // Table for viewing

	@FXML
	private TableColumn<Artwork, String> titleColumn; // Creating a column for the table

	@FXML
	private TableColumn<Artwork, String> creatonNameColumn; // Creating a column for the table

	@FXML
	private TableColumn<Artwork, String> reservePriceColumn; // Creating a column for the table

	@FXML
	private TableColumn<Artwork, String> placedBidsColumn; // Creating a column for the table

	@FXML
	private TableColumn<Artwork, String> bidLimitColumn; // Creating a column for the table

	@FXML
	private TableColumn<Artwork, Double> currentPriceColumn; // Creating a column for the table

	@FXML
	private TableColumn<Artwork, Boolean> ongoingColumn; // Creating a column for the table

	private ObservableList<Artwork> artworks; // List of artworks in auction

	/**
	 * Method to initialise my auction view
	 */
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
		creatonNameColumn.setCellValueFactory(new PropertyValueFactory<Artwork, String>("winnerName"));
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
	
	/**
	 * Method to filter artwork
	 */
	public void filter() {

		ArrayList<Artwork> arts = LoginController.getUser().getArtForSale();
		ArrayList<Artwork> newArts = new ArrayList<>();

		if (active.isSelected()) {

			for (Artwork a : arts) {

				if (!a.isBidIsOver()) {

					if (paintings.isSelected()) {
						if (a instanceof Painting) {
							newArts.add(a);
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
			newArts = new ArrayList<>();
			for (Artwork a : arts) {
				if (a.isBidIsOver()) {
					newArts.add(a);
				}
			}
			
			ObservableList<Artwork> artworksA = FXCollections.observableArrayList(newArts);

			table.setItems(artworksA);
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