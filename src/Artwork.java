
/**
 * Created by ayden on 10/11/2017.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	private double highestBidAmount; // Current highest bid placed on the artwork.



	
	/**
	 * Creates a new general Artwork object without a description. Also
	 * creates the imageview.
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

		this.owner = owner;
		this.timeAdded = date;
		this.title = title;
		this.creatorName = creatorName;
		this.creationYear = creationYear;
		this.numberOfBids = numberOfBids;
		this.reservePrice = reservePrice;
		this.bidsOnItem = new ArrayList<>();
		this.bidIsOver = false;
		highestBidAmount = reservePrice;
		resolveImage();
		imageView = new ImageView();
		//imageView.setImage(image);

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
		this.owner = owner;
		this.timeAdded = date;
		this.title = title;
		this.creatorName = creatorName;
		this.creationYear = creationYear;
		this.numberOfBids = numberOfBids;
		this.reservePrice = reservePrice;
		this.bidsOnItem = new ArrayList<>();
		this.description = description;
		highestBidAmount = reservePrice;
		resolveImage();
		imageView = new ImageView();
	}
	

	public void resolveImage() {
		this.image = FileReader.retrieveImage(title);
		//System.out.println("image "+ image.toString());
	}
	

	/**
	 * Method to get the name of the artwork.
	 * @return String - name.
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Method to get the seller of the artwork.
	 * @return User - seller.
	 */
	public User getOwner() {
		return this.owner;
	}

	/**
	 * Method to get the date the artwork was added to the system.
	 * @return Date - dateAdded.
	 */
	public Date getDateAdded() {
		return this.timeAdded;
	}

	/**
	 * Method to get the description of the artwork. Returns
	 * either an empty string or a string that has been given.
	 * @return String - description
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
	 * @return int - year.
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
	 * Method to set the seller of the artwork.
	 * @param owner
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * Method to set the date added of the artwork.
	 * @param timeAdded
	 */
	public void setTimeAdded(Date timeAdded) {
		this.timeAdded = timeAdded;
	}

	/**
	 * Method to set the name of the artwork.
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method to set the description of the artwork.
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Method to set the creator of the artwork.
	 * @param creator
	 */
	public void setCreator(String creator) {
		this.creatorName = creator;
	}

	/**
	 * Method to set the year the artwork was made.
	 * @param year
	 */
	public void setCreationYear(int year) {
		this.creationYear = year;
	}

	/**
	 * Method to set the number of bids possible on the artwork.
	 * @param bids
	 */
	public void setNumberOfBids(int bids) {
		this.numberOfBids = bids;
	}

	/**
	 * Method to get the number of bid currently placed.
	 * @return int - number of bids placed.
	 */
	public int getNumberOfPlacedBids() {
		return bidsOnItem.size();
	} 
	
	/**
	 * Method to set the reservePrice of the artwork.
	 * @param price
	 */
	public void setReservePrice(float price) {
		this.reservePrice = price;
	}

	/**
	 * Method to place a bid on the artwork.
	 * @param bid
	 */
	public void addBidToItem(Bid bid) {
		String error1 = "Bidder cannot bid on their own artwork." + "\n";
		String error2 = "Maximum number of bids has been reached." + "\n";
		String error3 = "Bid must be higher than current bid." + "\n";
		switch (bid.checkBid()) {
			case 0: {
				bidsOnItem.add(bid);
				owner.addBid(bid);
				System.out.println("Bid placed.");
				break;
			}
			case 1: {
				System.out.println(error1);
				break;
			}
			case 2: {
				System.out.println(error2);
			}
			case 3: {
				System.out.println(error3);
			}
			case 4: {
				System.out.println(error1 + error2);
			}
			case 5: {
				System.out.println(error1 + error3);
			}
			case 6: {
				System.out.println(error2 + error3);
			}
			default: {
				System.out.println(error1 + error2 + error3);
			}
		}
	}

	/**
	 * Method returns value of the highest current bid.
	 * @return double - value of highest bid.
	 */
	public double getHighestBidAmount() {
		return getHighestBid().getAmount();
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
	 * Checks if the auction is over
	 * @return boolean - true or false depending on whether
	 * the auction is over or not.
	 */
	public boolean isBidIsOver() {
		return bidIsOver;
	}

	/**
	 * Allows the setting of a boolean, true or false, to determine
	 * if the auction is over.
	 * @param bidIsOver
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
		int allowed = bidsAllowed - bidsOnItem.size();
		return allowed;
	}

	public void saveChanges() {
		// to implement
	}

	/**
	 * Method to get the image assigned to an artwork
	 * @return image - the picture the user uploads for
	 * the artwork
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Method to display the image assigned to the artwork
	 * in an imageView window
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
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Method to return the name of the creater of the artwork.
	 * @return String - creator name.
	 */
	public String getCreatorName() {
		return creatorName;
	}

	/**
	 * Method to return the all of the bids on an Artwork.
	 * @return ArrayList<Bid> - arraylist of all bids on artwork.
	 */
	public ArrayList<Bid> getBidsOnItem() {
		return bidsOnItem;
	}

	/**
	 * Method to allow the setting of a new highest bid.
	 * @param highestBid
	 */
	public void setHighestBid(Bid highestBid) {
		this.highestBid = highestBidAmount;
	}

	public void setHighestBidAmount(double highestBidAmount) {
		this.highestBidAmount = highestBidAmount;
	}
}// end of class
