import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    
    public void initialize() {
    	
    }
    
    
    
}
