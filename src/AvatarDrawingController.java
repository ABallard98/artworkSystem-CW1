import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AvatarDrawingController {

    @FXML
    private Canvas canvas;

    
    public void initialize() {
    
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	drawCircle(gc);
    	
    	
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                gc.beginPath();
                gc.moveTo(event.getX(), event.getY());
                gc.stroke();
                
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
                new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
                gc.closePath();
                gc.beginPath();
                gc.moveTo(event.getX(), event.getY());
            }
        });
        
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, 
                new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
                gc.closePath();
            }
        });

    }
    
    
    
    
    public void drawCircle(GraphicsContext gc) {
    	Circle circle = new Circle();
    	gc.setFill(Color.YELLOW);
    	gc.setStroke(Color.YELLOW);
    	
    	gc.fill();
    	
    	
    	
    }
    
}
