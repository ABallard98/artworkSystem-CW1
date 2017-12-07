import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

/**
 * this class is used to control user avatar drawing. 
 * @author Software Engineering Team Three
 * @version 1.0
 */

public class AvatarDrawingController {

	@FXML
	private Canvas canvas; //canvas for drawing on.

	@FXML
	private RadioButton selectLine; //radiobutton to select line 

	@FXML
	private RadioButton selectCircle; //radiobutton to select circle

	@FXML
	private Button save; //button used to save

	@FXML
	private Button clear; //button used to clear	

    @FXML
    private ColorPicker colorPicker; //color picker used to pick colour

    //variables used to drawlines
	private double startX; 
	private double startY;
	private double endX;
	private double endY;
	
	private GraphicsContext gc; //draw call gc to the canvas

	private EventHandler<MouseEvent> pressed1; 
	private EventHandler<MouseEvent> released1;

	private EventHandler<MouseEvent> pressed2;
	private EventHandler<MouseEvent> released2;
	private Color color;
	

    @FXML
    private Button confirmColor; //button used to confirm colour


	/**
	 * method used to intailaize drawing user avatar
	 */
	public void initialize() {
		//color = Color.PURPLE;

		colorPicker.setOnAction(e-> {
			
			color = colorPicker.getValue();
			
			gc.setFill(color);
			gc.setStroke(color);
					
		});
		
		// canvas.setStyle("border-color: red; border-width: 5px");

		clear.setOnAction(e -> clearCanvas());

		gc = canvas.getGraphicsContext2D();
		initDraw(gc);

		// gc.lineTo(startX, startY);
		// gc.stroke();
		gc.closePath();
		gc.beginPath();

		ToggleGroup tg = new ToggleGroup();
		selectCircle.setToggleGroup(tg);
		selectLine.setToggleGroup(tg);

		selectLine.setOnAction(e -> generateLines());
		selectCircle.setOnAction(e -> generateCircles());

		save.setOnAction(e -> saveImg());

	}

	/**
	 * method used to generate lines drawn.
	 */
	public void generateLines() {

		if (pressed2 != null && released2 != null) {
			canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED, pressed2);
			canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED, released2);

		}

		pressed1 = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				gc.beginPath();
				gc.moveTo(event.getX(), event.getY());
				startX = event.getX();
				startY = event.getY();
			}
		};

		released1 = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				gc.lineTo(event.getX(), event.getY());

				endX = event.getX();
				endY = event.getY();
				gc.strokeLine(startX, startY, endX, endY);
				gc.closePath();
			}
		};

		if (selectLine.isSelected()) {

			canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, pressed1);

			canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, released1);
		}

	}

	/**
	 * method used to generate circles drawn.
	 */
	public void generateCircles() {

		if (pressed1 != null && released1 != null) {
			canvas.removeEventHandler(MouseEvent.MOUSE_PRESSED, pressed1);
			canvas.removeEventHandler(MouseEvent.MOUSE_RELEASED, released1);
		}

		startX = 0;
		startY = 0;
		endX = 0;
		endY = 0;

		pressed2 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gc.beginPath();
				gc.moveTo(event.getX(), event.getY());
				startX = event.getX();
				startY = event.getY();
			}
		};

		released2 = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				gc.lineTo(event.getX(), event.getY());

				endX = event.getX();
				endY = event.getY();
				gc.fillOval(startX, startY, endX - startX, endY - startY);
				gc.closePath();
			}
		};

		if (selectCircle.isSelected()) {

			canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, pressed2);

			canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, released2);
		}

	}

	/**
	 * method to save image drawn
	 */
	public void saveImg() {

		WritableImage writableImg = new WritableImage(200, 200);

		File file = new File("tmpImg.png");

		canvas.snapshot(null, writableImg);

		try {
			ImageIO.write(SwingFXUtils.fromFXImage(writableImg, null), "png", file);
		} catch (Exception s) {
		}

		NewAccountCreatorController.setCustom(true);

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Success");

		alert.setHeaderText("The image has been created");
		alert.setContentText("Now you can use it as your avatar");
		alert.showAndWait();

		save.getScene().getWindow().hide();

	}

	/**
	 * method used to clear drawing canvas.
	 */
	public void clearCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

	}
	
	
	/**
	 * method used to draw in it.
	 * @param gc
	 */
	public void initDraw(GraphicsContext gc) {
		
		//color = colorPicker.getValue();
		
		gc.setFill(color);
		gc.setStroke(color);

		gc.fill();

	}

}
