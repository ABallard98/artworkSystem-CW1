

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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * @author Thomas
 * @date 4/12/2017
 */
public class MyBidsController {


    @FXML
    private RadioButton allAuctions;

    @FXML
    private RadioButton active;

    @FXML
    private RadioButton won;

    @FXML
    private RadioButton finished;



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
	private TableColumn<Bid, String> titleColumn;

	@FXML
	private TableColumn<Bid, Date> dateColumn;

	@FXML
	private TableColumn<Bid, Double> amountColumn;

	private ObservableList<Bid> bids;

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
