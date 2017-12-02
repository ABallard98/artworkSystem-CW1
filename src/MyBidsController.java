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
    private TableColumn<Bid,Date> bidLimitColumn;

    @FXML
    private TableColumn<?, ?> currentPriceColumn;

    @FXML
    private TableColumn<?, ?> ongoingColumn;

    @FXML
    private TableColumn<Bid,String> titleColumn;


	private ObservableList<Bid> bids;
    
    public void initialize() {
    	ArrayList<Artwork> artworks = new ArrayList<>();
    	
    	
    	bids =  FXCollections.observableArrayList(LoginController.getUser().getPlacedBids());
    	System.out.println("Number of placed bids "+LoginController.getUser().getPlacedBids().size());
    	//picture.setCellValueFactory(new PropertyValueFactory<Bid,ImageView>("imgView"));
    	titleColumn.setCellValueFactory(new PropertyValueFactory<Bid,String>("title"));
    	bidLimitColumn.setCellValueFactory(new PropertyValueFactory<Bid,Date>("bidDate"));

    	for(Bid bid: bids) {
        	System.out.println("come on"+ bid.getArtwork().getImage().getHeight());
        	System.out.println("opacity"+ bid.getArtwork().getImageView().getOpacity());


    	}
    	
    	
    	picture.setCellValueFactory(new PropertyValueFactory<Bid,ImageView>("imgView"));

    	table.setItems(bids);

    	
    }
}




