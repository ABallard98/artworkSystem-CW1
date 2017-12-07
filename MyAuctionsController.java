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
 * this class is to controll user auctions
 * @author Software Engineering Team Three
 * @version 1.0
 */

public class MyAuctionsController {

    @FXML
    private Button refreshButton; //button used to refresh
	
    @FXML
    private RadioButton allAuctions; //radio button to select all auctions

    @FXML
    private RadioButton active; //radio button to select active

    @FXML
    private RadioButton finished; //radio button to select finished

    @FXML
    private CheckBox sculptures; //check box to determine if sculptures are selected 

    @FXML
    private CheckBox paintings; //check box to determine if paintings are selected

    @FXML
    private TableView<Artwork> table; // tableview of the table of artwork. 

    @FXML
    private TableColumn<Artwork, String> titleColumn; //table column for the title 

    @FXML
    private TableColumn<Artwork, String> creatonNameColumn; //table column for the creaton name

    @FXML
    private TableColumn<Artwork, String> reservePriceColumn; //table column for the reserve price

    @FXML
    private TableColumn<Artwork, String>placedBidsColumn; //table column for the placed bids 

    @FXML
    private TableColumn<Artwork, String> bidLimitColumn; //table column for the bid limit

    @FXML
    private TableColumn<Artwork, Double> currentPriceColumn; //table column for the current price

    @FXML
<<<<<<< HEAD
    private TableColumn<Artwork, String> ongoingColumn; //table column for the ongoing auctions
=======
    private TableColumn<Artwork, Boolean> ongoingColumn;
    
    
>>>>>>> e056db377b3bed032f7d89f50f1af9d2fd92ef40
    

    private ObservableList<Artwork> artworks; //artworks list to track changes. 
    
	/**
	 * method used to intailaize auctions for artwork
	 */
    public void initialize() {
    	
    	ToggleGroup tg = new ToggleGroup();
    	allAuctions.setToggleGroup(tg);
    	finished.setToggleGroup(tg);
    	active.setToggleGroup(tg);
    	
    	
    	for(Artwork a: LoginController.getUser().getArtForSale()) {
    		System.out.println(a.getClass());
    	}
    	
    	
    	
    	
    	
    	artworks =  FXCollections.observableArrayList(LoginController.getUser().getArtForSale());

    	titleColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("title"));
    	creatonNameColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("creatorName"));
    	reservePriceColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("reservePrice"));
    	placedBidsColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("numberOfBids"));
    	bidLimitColumn.setCellValueFactory(new PropertyValueFactory<Artwork,String>("bidsAllowed"));
    	currentPriceColumn.setCellValueFactory(new PropertyValueFactory<Artwork,Double>("highestBidAmount"));
    	ongoingColumn.setCellValueFactory(new PropertyValueFactory<Artwork,Boolean>("bidIsOver"));
    	
    	System.out.println("No of my artworks is" + artworks.size());
    	
		table.setItems(artworks);

		paintings.setSelected(true);
		sculptures.setSelected(true);

		active.setSelected(true);
		refreshButton.setOnAction(e-> filter());

    
    }


    public void filter() {
    	

    	ArrayList<Artwork> arts = LoginController.getUser().getArtForSale();
    	ArrayList<Artwork> newArts = new ArrayList<>();

    	ArrayList<Sculpture> sculpturesA = new ArrayList<>();
    	ArrayList<Painting> paintingsA = new ArrayList<>();

    	
    	if(active.isSelected()) {
    		
    		for(Artwork a: arts) {
    			if(!a.isBidIsOver()) {
    				newArts.add(a);
    			}
    		}
    		
    	} else if (finished.isSelected()) {
    		for(Artwork a: arts) {
    			if(a.isBidIsOver()) {
    				newArts.add(a);
    			}
    		}
    	} else if(allAuctions.isSelected()) {
    		newArts = arts;
    	}
    	
    	
    	if(sculptures.isSelected() && paintings.isSelected()) {
            ObservableList<Artwork> artworksA =  FXCollections.observableArrayList(newArts);

            table.setItems(artworksA);

    	} else if (sculptures.isSelected() && !paintings.isSelected()) {
    		
    	
    	}
    	

    	
    }





    
    
    
}
