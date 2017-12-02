import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class AvatarDrawingController {

    @FXML
    private Canvas canvas;

    
    public void initialize() {
    
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	draw(gc);
    	
    	
    	canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
    	    @Override
            public void handle(MouseEvent event) {
                gc.beginPath();
                gc.moveTo(event.getX(), event.getY());
                gc.stroke();
            }
    	});

    }
    
    
    public void draw(GraphicsContext gc) {
    	
    }
    
}
