/**
 * @author Thomas
 * @date 4/12/2017
 */

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

public class MyBidsController {

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
	private Button refreshButton;

	@FXML
	private TableView<Bid> table;

	@FXML
	private TableColumn<Bid, ImageView> picture;

	@FXML
	private TableColumn<Bid, String> creatonNameColumn;

	@FXML
	private TableColumn<Bid, Date> bidLimitColumn;

	@FXML
	private TableColumn<?, ?> currentPriceColumn;

	@FXML
	private TableColumn<?, ?> ongoingColumn;

	@FXML
	private TableColumn<Bid, String> titleColumn;

	@FXML
	private TableColumn<Bid, Date> dateColumn;

	@FXML
	private TableColumn<Bid, Double> amountColumn;

	private ObservableList<Bid> bids;

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
