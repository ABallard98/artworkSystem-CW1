import java.io.File;
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

/**
 * @author Ayden
 * Created on 6/12/2017
 */
public class AvatarDrawingController {

	@FXML
	private Canvas canvas;

	@FXML
	private RadioButton selectLine;

	@FXML
	private RadioButton selectCircle;

	@FXML
	private Button save;

	@FXML
	private Button clear;	

    @FXML
    private ColorPicker colorPicker;


	private double startX;
	private double startY;
	private double endX;
	private double endY;
	private GraphicsContext gc;

	private EventHandler<MouseEvent> pressed1;
	private EventHandler<MouseEvent> released1;

	private EventHandler<MouseEvent> pressed2;
	private EventHandler<MouseEvent> released2;
	private Color color;
	

    @FXML
    private Button confirmColor;

    /**
     * Method it initialise the custom drawing window
     */
	public void initialize() {

		colorPicker.setOnAction(e-> {
			
			color = colorPicker.getValue();
			
			gc.setFill(color);
			gc.setStroke(color);
					
		});
		
		clear.setOnAction(e -> clearCanvas());

		gc = canvas.getGraphicsContext2D();
		initDraw(gc);

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
	 * Method to draw lines on a canvas
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
	 * Method to draw circles on the canvas
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
	 * Method to save the custom drawn image
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
	 * Method to reset the canvas
	 */
	public void clearCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

	}
	
	/**
	 * Changes the colour of the drawing
	 * @param gc contents of the images
	 */

	public void initDraw(GraphicsContext gc) {
		
		gc.setFill(color);
		gc.setStroke(color);
		gc.fill();

	}

}
