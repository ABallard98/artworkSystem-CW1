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
    private TableColumn<Artwork, String>placedBidsColumn;

    @FXML
    private TableColumn<Artwork, String> bidLimitColumn;

    @FXML
    private TableColumn<Artwork, Double> currentPriceColumn;

    @FXML
    private TableColumn<Artwork, String> ongoingColumn;
    
    
    

    private ObservableList<Artwork> artworks;
    
    public void initialize() {
    	
    	ToggleGroup tg = new ToggleGroup();
    	allAuctions.setToggleGroup(tg);
    	finished.setToggleGroup(tg);
    	active.setToggleGroup(tg);
    	
    	artworks =  FXCollections.observableArrayList(LoginController.getUser().getArtForSale());

    	titleColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("title"));
    	creatonNameColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("creatorName"));
    	reservePriceColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("reservePrice"));
    	placedBidsColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("numberOfBids"));
    	bidLimitColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("bidsAllowed"));
    	//currentPriceColumn.setCellValueFactory(new PropertyValueFactory<Artwork,Double>("highestBid"));
    	ongoingColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("title"));
    	
    	System.out.println(artworks.size());
    	
		table.setItems(artworks);


    }







    
    
    
}