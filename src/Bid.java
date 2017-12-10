import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class constructs and contains information about bids on artwork
 * @author Thomas
 * Created on 20/11/2017
 */
public class Bid {

	private String typeOfArtwork; // Type artwork (painting or structure).
	private User bidder; // User that placed the bid.
	private double amount; // Price the user placed.
	private Artwork artwork; // The artwork that the bid is placed on.
	private Date bidDate; // The date the bid was placed.
	private ImageView imgView;// Container for an image
	private String title;// Title of the artwork bid is placed on
	private String bidDateString; // date when bid was placed
	private Image image;// image of the artwork bid was placed on
	private boolean winningBid; // Checks if your bid is the last bid

	/**
	 * Creates a new bid object.
	 * @param typeOfArtwork sculpture or painting
	 * @param bidder user who made the bid
	 * @param artwork which artwork bid was on
	 * @param bidDate date of bid
	 * @param amount how much the bid is worth in £'s
	 */
	public Bid(String typeOfArtwork, User bidder, double amount, Artwork artwork, Date bidDate) {

		this.typeOfArtwork = typeOfArtwork;
		this.bidder = bidder;
		this.artwork = artwork;
		this.bidDate = bidDate;
		this.amount = amount;
		this.imgView = new ImageView();
		this.image = artwork.getImage();
		this.title = artwork.getTitle();
	}



	/**
	 * This method returns an image of the art that the bid was 
	 * placed upon
	 * @return image - picture of the artwork
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * This method sets the image of the art that was bid on
	 * @param image - picture of the artwork
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Method to get the type of artwork.
	 * @return String - type of artwork.
	 */
	public String getTypeOfArtwork() {
		return this.typeOfArtwork;
	}

	/**
	 * Method to get the date of the bid in String form
	 * @return String - bidDateString
	 */
	public String getBidDateString() {
		return bidDateString;
	}

	/**
	 * Method to display an ImageView window containing the 
	 * image of the art
	 * @return imgView - a display of the art image
	 */
	public ImageView getImgView() {
		imgView.setFitWidth(100);
		imgView.setFitHeight(100);
		imgView.setImage(image);
		return imgView;
	}
		
	/**
	 * Method to return the title of the artwork being bid upon
	 * @return String - title of the artwork
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Method to get the bidder.
	 * @return User - bidder.
	 */
	public User getBidder() {
		return this.bidder;
	}

	/**
	 * Method to get the price.
	 * @return double - price.
	 */
	public double getAmount() {
		return this.amount;
	}

	/**
	 * Method to get the artwork.
	 * @return Artwork - artwork.
	 */
	public Artwork getArtwork() {
		return this.artwork;
	}

	/**
	 * Method to get the date the bid was placed.
	 * @return Date - bidDate.
	 */
	public Date getBidDate() {
		return this.bidDate;
	}
	
	/**
	 * Method to get see check if a bid is the 
	 * winning bid
	 * @return Boolean - winningBid, True if it is 
	 * false otherwise
	 */
	public boolean isWinningBid() {
		return winningBid;
	}

	/**
	 * Method to set the winningBid boolean
	 * @param winningBid the winning bid
	 */
	public void setWinningBid(boolean winningBid) {
		this.winningBid = winningBid;
	}

	/**
	 * Method to get the text file output which will be used to store the bid.
	 * @return String - data on the bid.
	 */
	public String getTextFileOutput() {
		String output = this.getTypeOfArtwork() + "," +
				this.getBidder().getUsername() + "," +
				this.artwork.getTitle() + "," + this.getAmount() +
				"," + this.bidDate;
		return output;
	}

	/**
	 * Method to get a string containing all of the information on the bid.
	 * @return String - all of the bid's attributes turned into a string.
	 */
	public String toString() {
		String output = "Bid made by " + this.getBidder().getUsername() +
				" for the artwork " + this.getArtwork().getTitle() + " for " +
				this.getAmount() + " on " + this.getBidDate();
		return output;
	}
} 