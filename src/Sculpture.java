import java.util.*;
import javafx.scene.image.Image;
/**
 * This is a subclass of Artwork which creates sculpture objects
 * and contains the information for them
 * @author Marwan
 * Created on 21/11/2017
 */
public class Sculpture extends Artwork {

	private int width; // Width of the sculpture in cm
	private int height; // Height of the sculpture in cm
	private int depth; // Depth of the sculpture in cm
	private String material; // What the sculpture is made of
	private ArrayList<Image> additionalImages; // Extra images of the sculpture

	/**
	* Constructor to create a sculpture object without a description.
	* @param seller - the user who is selling the sculpture
    * @param date - date when put up for sale
    * @param name - title of the sculpture
    * @param creatorName - name of who created the sculpture
    * @param yearWasMade - year of sculptures creation
    * @param numberOfBids - how many bids allowed
    * @param reservePrice - minimum price for bid on sculpture
    * @param width - width of the sculpture in cm
    * @param height - height of the sculpture in cm
    * @param depth - depth of the sculpture in cm
    * @param material - what the sculpture is made out of
	 */
	public Sculpture(User seller, Date date, String name, String creatorName, int yearWasMade, int numberOfBids,
			double reservePrice, int width, int height, int depth, String material) {
		super(seller, date, name, creatorName, yearWasMade, numberOfBids, reservePrice);
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.material = material;
		additionalImages = new ArrayList<>();
	}

	/**
	* Constructor to create a sculpture object with a description.
	* @param seller - the user who is selling the sculpture
    * @param date - date when put up for sale
    * @param name - title of the sculpture
    * @param creatorName - name of who created the sculpture
    * @param yearWasMade - year of sculptures creation
    * @param numberOfBids - how many bids allowed
    * @param reservePrice - minimum price for bid on sculpture
    * @param width - width of the sculpture in cm
    * @param height - height of the sculpture in cm
    * @param depth - depth of the sculpture in cm
    * @param material - what the sculpture is made out of
    * @param description - brief description of the sculpture
	 */
	public Sculpture(User seller, Date date, String name, String creatorName, int yearWasMade, int numberOfBids,
			double reservePrice, int width, int height, int depth, String material, String description) {
		super(seller, date, name, creatorName, yearWasMade, numberOfBids, reservePrice, description);
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.material = material;
		additionalImages = new ArrayList<>();

	}
	
	/**
	 * Gets the additional images of a sculpture
	 */
	public void resolveAdditionalImages() {
		additionalImages = FileReader.retrieveAdditionalImages(title);
	}
	
	/**
	 * Method to get the ArrayList of additional images of an artwork.
	 * @return the images of the artwork
	 */
	public ArrayList<Image> getAdditionalImages() {
		return additionalImages;
	}

	/**
	 * Method to return the width of the sculpture.
	 * @return int - width of the sculpture
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Method to return the height of the sculpture.
	 * @return int - height of the sculpture
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Method to return the depth of the sculpture.
	 * @return depth - depth of the sculpture
	 */
	public int getDepth() {
		return this.depth;
	}

	/**
	 * Method to return the material of the sculpture.
	 * @return String - material of the sculpture
	 */
	public String getMaterial() {
		return this.material;
	}

	/**
	 * Method to format the sculptures information to be put into
	 * a text file for easier reading.
	 * @return String - text file output for a sculpture
	 */
	public String getTextFileOutput() {
		String output = this.getTitle() + "#" + this.getOwner().getUsername()
				+ "#" + this.getCreator() + "#" + this.getCreationYear() + "#"
				+ this.getBidsAllowed() + "#" + this.getReservePrice() + "#"
				+ this.getWidth() + "#" + this.getHeight() + "#" +
				this.getDepth() + "#" + this.getMaterial() + "#" +this.getDescription();
		return output;
	}

	/**
	 * Prints out the sculptures information.
	 */
	public String toString() {
		String output = "\nSculpture Name: " + this.getTitle() + "\nCreator: " + this.getCreator() + "\nSeller: "
				+ this.getOwner() + "\nYear: " + this.getCreationYear() + "\nNumber of available bids: "
				+ this.getBidsAllowed() + "\nReserve price " + this.getReservePrice() + "\nWidth: " + this.getWidth()
				+ "\nHeight: " + this.getHeight() + "\nDepth: " + this.getDepth() + "\nMaterial: " + this.getMaterial()
				+ "\nDescription: " + this.getDescription();
		return output;
	}

	/**
	 * Method to add an image into the additionalImages.
	 * @param img - the addtional images of the sculpture
	 */
	public void addPhoto(Image img) {
		additionalImages.add(img);
	}
}