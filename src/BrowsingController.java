import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    
    
    public void initialize() {
    
    	artworks =  FXCollections.observableArrayList(FileReader.getArtworks());

    	System.out.println("Artworks number: " + artworks.size());
    	image.setCellValueFactory(new PropertyValueFactory<Artwork,ImageView>("imageView"));
    	title.setCellValueFactory(new PropertyValueFactory<Artwork,String>("title"));
    	description.setCellValueFactory(new PropertyValueFactory<Artwork,String>("description"));
    	
    	table.setItems(artworks);
    	
    }
    
    
    
}
