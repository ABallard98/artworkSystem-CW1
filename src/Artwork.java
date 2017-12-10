import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class constructs and contains information about artwork objects
 * @author Marwan
 * Created on 21/11/2017
 */
public class Artwork {

	protected String title; // Name of the artwork.
	protected String description; // Description of the artwork.
	protected Image image; // An image of the artwork.
	protected String creatorName; // Name of creator of the artwork.
	protected int creationYear; // Year the artwork was made.
	protected double reservePrice; // Reserve price for the artwork.
	protected int bidsAllowed; // Number of the bids possible for this artwork.
	protected Date timeAdded; // Date added to the system.
	protected User owner; // Owner of the artwork.
	protected ArrayList<Bid> bidsOnItem; // ArrayList of bids on this artwork.
	protected int numberOfBids; // Number of bids that have been placed.
	private boolean bidIsOver; //Boolean depending on whether the bid is over or not.
	private double highestBid; // Current highest bid placed on the artwork.
	private ImageView imageView; // image view of the artwork
	private double highestBidAmount; // Current highest bid placed on the artwork
	private String winnerName; // Name of who won the auction
	private String currentHighestBid; // Name of who has the current bid

	
	/**
	 * Creates a new general Artwork object without a description. Also
	 * creates the ImageView.
	 * @param owner - seller of the artwork.
	 * @param date - date added to the system.
	 * @param title - name of the artwork.
	 * @param creatorName - creator of the artwork.
	 * @param creationYear - year the artwork was made.
	 * @param numberOfBids - number of bids possible for the artwork.
	 * @param reservePrice - reserve price of the artwork.
	 */
	public Artwork(User owner, Date date, String title, String creatorName,
				   int creationYear, int numberOfBids, double reservePrice) {

		this.currentHighestBid = "";
		this.owner = owner;
		this.timeAdded = date;
		this.title = title;
		this.creatorName = creatorName;
		this.creationYear = creationYear;
		this.numberOfBids = 0;
		this.bidsAllowed = numberOfBids;
		this.reservePrice = reservePrice;
		this.bidsOnItem = new ArrayList<>();
		this.bidIsOver = false;
		resolveImage();
		this.imageView = new ImageView();
		
	}


	/**
	 * Creates a new general Artwork object with a description. Also
	 * creates the ImageView.
	 * @param owner - seller of the artwork.
	 * @param date - date added to the system.
	 * @param title - name of the artwork.
	 * @param creatorName - creator of the artwork.
	 * @param creationYear - year the artwork was made.
	 * @param numberOfBids - number of bids possible for the artwork.
	 * @param reservePrice - reserve price of the artwork.
	 * @param description - description of the artwork.
	 */
	public Artwork(User owner, Date date, String title, String creatorName,
				   int creationYear, int numberOfBids, double reservePrice,
				   String description) {
		this.currentHighestBid = "";
		this.owner = owner;
		this.timeAdded = date;
		this.title = title;
		this.creatorName = creatorName;
		this.creationYear = creationYear;
		this.numberOfBids = 0;
		this.reservePrice = reservePrice;
		this.bidsOnItem = new ArrayList<>();
		this.description = description;
		this.highestBidAmount = reservePrice;
		resolveImage();
		this.imageView = new ImageView();
		this.bidsAllowed = numberOfBids;
		this.bidIsOver = false;

	}
	
	/**
	 * Method to get the name of the highest bidder.
	 * @return String - currentHighestBidder
	 */
	public String getCurrentHighestBidder() {
		return this.currentHighestBid;
	}
	
	/**
	 * Method to get the image of the artwork.
	 */
	public void resolveImage() {
		this.image = FileReader.retrieveImage(title);
	}
	

	/**
	 * Method to get the name of the artwork.
	 * @return String - title of the artwork.
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Method to get the seller of the artwork.
	 * @return User - seller of the artwork.
	 */
	public User getOwner() {
		return this.owner;
	}

	/**
	 * Method to get the date the artwork was added to the system.
	 * @return Date - date and time when Artwork was added.
	 */
	public Date getDateAdded() {
		return this.timeAdded;
	}

	/**
	 * Method to get the description of the artwork. Returns
	 * either an empty string or a string that has been given.
	 * @return String - description of the artwork
	 */
	public String getDescription() {
		if (this.description == null) {
			return "";
		} else {
			return this.description;
		}
	}

	/**
	 * Method to get the creator of the artwork.
	 * @return String - creator.
	 */
	public String getCreator() {
		return this.creatorName;
	}

	/**
	 * Method to get the year the artwork was made.
	 * @return int - year when artwork was created.
	 */
	public int getCreationYear() {
		return this.creationYear;
	}

	/**
	 * Method to get the number of bids possible on the artwork.
	 * @return int - numberOfBids.
	 */
	public int getNumberOfBids() {
		return this.numberOfBids;
	}

	/**
	 * Method to get the reserve price of the artwork.
	 * @return double - reservePrice.
	 */
	public double getReservePrice() {
		return this.reservePrice;
	}

	
	/**
	 * Method to get the number of bid currently placed.
	 * @return int - number of bids placed.
	 */
	public int getNumberOfPlacedBids() {
		return numberOfBids;
	} 

	/**
	 * Method to place a bid on the artwork.
	 * @param bid bid to be added to an artwork
	 */
	public void addBidToItem(Bid bid) {
		numberOfBids++;
		highestBid = bid.getAmount();
		bidsOnItem.add(bid);
		currentHighestBid = bid.getBidder().getUsername();
		

		if(bidsOnItem.size() == bidsAllowed) {
			bidIsOver = true;
			bid.setWinningBid(true);
		}
		
	}

	/**
	 * Method returns value of the highest current bid.
	 * @return double - value of highest bid.
	 */
	public double getHighestBidAmount() {
		return highestBid;
	}

	/**
	 * Methods returns the highest bid.
	 * @return Bid - bid with the highest amount.
	 */
	public Bid getHighestBid() {
		Bid biggest = null;
		for (Bid b : bidsOnItem) {
			double i = 0;
			if (b.getAmount() > i) {
				i = b.getAmount();
				biggest = b;
			}
			else {

			}
		}
		return biggest;
	}

	/**
	 * Checks if the auction is over.
	 * @return boolean - true or false depending on whether
	 * the auction is over or not.
	 */
	public boolean isBidIsOver() {
		return bidIsOver;
	}

	/**
	 * Allows the setting of a boolean, true or false, to determine
	 * if the auction is over.
	 * @param bidIsOver true if bid is over, false otherwise
	 */
	public void setBidIsOver(boolean bidIsOver) {
		this.bidIsOver = bidIsOver;
	}

	/**
	 * Method to get the number of bid allowed for each specific
	 * auction.
	 * @return int - number of bids allowed.
	 */
	public int getBidsAllowed() {
		return bidsAllowed;
	}

	/**
	 * Method to get the image assigned to an artwork.
	 * @return image - the picture the user uploads for
	 * the artwork
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Method to display the image assigned to the artwork
	 * in an imageView window.
	 * @return imageView - display window containing the image
	 */
	public ImageView getImageView() {
		imageView.setImage(image);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);

		return imageView;
	}

	/**
	 * Allows the setting of an image to an artwork.
	 * @param image image of the artwork
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Method to return the name of the creator of the artwork.
	 * @return String - creator name.
	 */
	public String getCreatorName() {
		return creatorName;
	}

	/**
	 * Method to return the all of the bids on an Artwork.
	 * @return arraylist of all bids on artwork.
	 */
	public ArrayList<Bid> getBidsOnItem() {
		return bidsOnItem;
	}

	public String getWinnerName() {
		winnerName = "";
		if(bidIsOver) {
			if(this.getHighestBid() != null) {
				return this.getHighestBid().getBidder().getUsername();
			}
		}
		
		return winnerName;
	}	
}
