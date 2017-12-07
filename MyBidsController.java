import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * this class is used to control user bids
 * @author Software Engineering Team Three
 * @version 1.0
 */

public class MyBidsController {

	@FXML
	private RadioButton allAuctions; //radio button to select all auctions 

	@FXML
	private RadioButton active; //radio button to select active.

	@FXML
	private RadioButton finished; //radio button to select finished

	@FXML
	private CheckBox sculptures; //check box to determine if sculptures are selected 

	@FXML
	private CheckBox paintings; //check box to determine if paintings are selected 

	@FXML
	private Button refreshButton; //button used to refresh

	@FXML
	private TableView<Bid> table; // tableview of the table of artwork.

	@FXML
	private TableColumn<Bid, ImageView> picture; //table column for picture

	@FXML
	private TableColumn<Bid, String> creatonNameColumn; //table column for creaton name

	@FXML
	private TableColumn<Bid, Date> bidLimitColumn; //table column for bid limit

	@FXML
	private TableColumn<?, ?> currentPriceColumn; //table column for current price

	@FXML
	private TableColumn<?, ?> ongoingColumn; //table column for ongoing auctions

	@FXML
	private TableColumn<Bid, String> titleColumn; //table column for title

	@FXML
	private TableColumn<Bid, Date> dateColumn; //table column for date

	@FXML
	private TableColumn<Bid, Double> amountColumn; //table column for bid amount

	private ObservableList<Bid> bids; //bids list to track changes 

	/**
	 * method used to intailaize viewing user bids
	 */
	public void initialize() {
		
		ArrayList<Bid> bids11 = FileReader.getBidsOfUser(LoginController.getUser());

		bids = FXCollections.observableArrayList(bids11);

		System.out.println("Number of placed bids ----------" + LoginController.getUser().getPlacedBids().size());
		// picture.setCellValueFactory(new
		// PropertyValueFactory<Bid,ImageView>("imgView"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<Bid, String>("title"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<Bid, Date>("bidDate"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<Bid, Double>("amount"));
		picture.setCellValueFactory(new PropertyValueFactory<Bid, ImageView>("imgView"));

		picture.setMinWidth(100);
		table.setItems(bids);

		dateColumn.setMinWidth(200);
		dateColumn.setSortType(TableColumn.SortType.DESCENDING);
		table.getSortOrder().add(dateColumn);

	}
}
