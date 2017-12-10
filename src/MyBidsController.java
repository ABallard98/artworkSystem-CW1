import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
/**
 * This is the GUI class for displaying the bids a user has made
 * @author Thomas
 * Created on 4/12/2017
 */
public class MyBidsController {


    @FXML
    private RadioButton allAuctions; // Button to filter auctions

    @FXML
    private RadioButton active; // Button to filter auctions

    @FXML
    private RadioButton won; // Button to filter auctions

    @FXML
    private RadioButton finished; // Button to filter auctions

	@FXML
	private Button refreshButton; // Button to refresh the page

	@FXML
	private TableView<Bid> table; // Table for viewing

	@FXML
	private TableColumn<Bid, ImageView> picture; // Creating column for table

	@FXML
	private TableColumn<Bid, String> creatonNameColumn; // Creating column for table

	@FXML
	private TableColumn<Bid, Date> bidLimitColumn; // Creating column for table

	@FXML
	private TableColumn<Bid, String> titleColumn; // Creating column for table

	@FXML
	private TableColumn<Bid, Date> dateColumn; // Creating column for table

	@FXML
	private TableColumn<Bid, Double> amountColumn; // Creating column for table

	private ObservableList<Bid> bids; // List of bids

	/**
	 * Method to initialise the my bids display window
	 */
	public void initialize() {
		ToggleGroup tg = new ToggleGroup();
		allAuctions.setToggleGroup(tg);
		active.setToggleGroup(tg);
		won.setToggleGroup(tg);
		finished.setToggleGroup(tg);
		
		ArrayList<Bid> bids11 = FileReader.getBidsOfUser(LoginController.getUser());
		bids = FXCollections.observableArrayList(bids11);

		titleColumn.setCellValueFactory(new PropertyValueFactory<Bid, String>("title"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Bid, Date>("bidDate"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<Bid, Double>("amount"));
		picture.setCellValueFactory(new PropertyValueFactory<Bid, ImageView>("imgView"));
		
		picture.setMinWidth(100);
		table.setItems(bids);

		dateColumn.setMinWidth(200);
		dateColumn.setSortType(TableColumn.SortType.DESCENDING);
		table.getSortOrder().add(dateColumn);

		refreshButton.setOnAction(e-> refresh());		
	}
	
	/**
	 * Refreshes the list of bids
	 */
	public void refresh() {
		
		ArrayList<Bid> tempBids = new ArrayList<>();
		
		if(allAuctions.isSelected()) {
			tempBids = FileReader.getBidsOfUser(LoginController.getUser());
		} else if (won.isSelected()) {
			tempBids = LoginController.getUser().getWonBids();
		}
		bids = FXCollections.observableArrayList(tempBids);
		table.setItems(bids);
	} 
}
