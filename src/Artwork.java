
/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;

import javafx.scene.image.Image;

public class Artwork {

	protected String title; // name of the artwork
	protected String description; // description of the artwork
	protected Image image;
	protected String creatorName; // name of creator of the artwork
	protected int creationYear; // year the artwork was made
	protected double reservePrice; // reserve price for the artwork
	protected int bidsAllowed; // number of the bids possible for this artwork
	protected Date timeAdded; // date added to the system
	protected User owner; // owner of the artwork1
	protected ArrayList<Bid> bidsOnItem; // ArrayList of bids on this artwork
	protected int numberOfBids;
	private boolean bidIsOver;

	/**
	 * Constructor of artwork without a description
	 * 
	 * @param owner
	 *            - seller of the artwork
	 * @param date
	 *            - date added to the system
	 * @param title
	 *            - name of the artwork
	 * @param creatorName
	 *            - creator of the artwork
	 * @param creationYear
	 *            - year the artwork was made
	 * @param numberOfBids
	 *            - number of bids possible for the artwork
	 * @param reservePrice
	 *            - reserve price of the artwork
	 */
	public Artwork(User owner, Date date, String title, String creatorName, int creationYear, int numberOfBids,
			double reservePrice) {

		this.owner = owner;
		this.timeAdded = date;
		this.title = title;
		this.creatorName = creatorName;
		this.creationYear = creationYear;
		this.numberOfBids = 0;
		this.reservePrice = reservePrice;
		this.bidsOnItem = new ArrayList<>();
		this.bidIsOver = false;
		
	}

	
	public void resolveImage() {
		this.image = FileReader.retrieveImage(title);
		//System.out.println("image "+ image.toString());
	}
	

	
	/**
	 * Constructor of artwork with a a description
	 * 
	 * @param owner
	 *            - seller of the artwork
	 * @param date
	 *            - date added to the system
	 * @param title
	 *            - name of the artwork
	 * @param creatorName
	 *            - creator of the artwork
	 * @param creationYear
	 *            - year the artwork was made
	 * @param numberOfBids
	 *            - number of bids possible for the artwork
	 * @param reservePrice
	 *            - reserve price of the artwork
	 * @param description
	 *            - description of the artwork
	 */
	public Artwork(User owner, Date date, String title, String creatorName, int creationYear, int numberOfBids,
			double reservePrice, String description) {
		this.owner = owner;
		this.timeAdded = date;
		this.title = title;
		this.creatorName = creatorName;
		this.creationYear = creationYear;
		this.numberOfBids = numberOfBids;
		this.reservePrice = reservePrice;
		this.bidsOnItem = new ArrayList<>();
		this.description = description;
	}

	public void readPhoto(String imageFileName) {
		// to implement
	}

	/**
	 * Method to get the name of the artwork
	 * 
	 * @return String - name
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Method to get the seller of the artwork
	 * 
	 * @return User - seller
	 */
	public User getOwner() {
		return this.owner;
	}

	/**
	 * Method to get the date the artwork was added to the system
	 * 
	 * @return Date - dateAdded
	 */
	public Date getDateAdded() {
		return this.timeAdded;
	}

	/**
	 * Method to get the description of the artwork
	 * 
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
	 * Method to get the creator of the artwork
	 * 
	 * @return String - creator
	 */
	public String getCreator() {
		return this.creatorName;
	}

	/**
	 * Method to get the year the artwork was made
	 * 
	 * @return int - year
	 */
	public int getCreationYear() {
		return this.creationYear;
	}

	/**
	 * Method to get the number of bids possible on the artwork
	 * 
	 * @return int - numberOfBids
	 */
	public int getNumberOfBids() {
		return this.numberOfBids;
	}

	/**
	 * Method to get the reserve price of the artwork
	 * 
	 * @return double - reservePrice
	 */
	public double getReservePrice() {
		return this.reservePrice;
	}

	/**
	 * Method to set the seller of the artwork
	 * 
	 * @param owner
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * Method to set the date added of the artwork
	 * 
	 * @param timeAdded
	 */
	public void setTimeAdded(Date timeAdded) {
		this.timeAdded = timeAdded;
	}

	/**
	 * Method to set the name of the artwork
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method to set the description of the artwork
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Method to set the creator of the artwork
	 * 
	 * @param creator
	 */
	public void setCreator(String creator) {
		this.creatorName = creator;
	}

	/**
	 * Method to set the year the artwork was made
	 * 
	 * @param year
	 */
	public void setCreationYear(int year) {
		this.creationYear = year;
	}

	/**
	 * Method to set the number of bids possible on the artwork
	 * 
	 * @param bids
	 */
	public void setNumberOfBids(int bids) {
		this.numberOfBids = bids;
	}

	/**
	 * Method to set the reservePrice of the artwork
	 * 
	 * @param price
	 */
	public void setReservePrice(float price) {
		this.reservePrice = price;
	}

	/**
	 * Method to place a bid on the artwork
	 * 
	 * @param bid
	 */
	public void addBidToItem(Bid bid) {
		if (bidsOnItem.size() < numberOfBids && bid.getPrice() > bidsOnItem.get(bidsOnItem.size() - 1).getPrice()) {
			bidsOnItem.add(bid);
		} else {
			System.out.println("Error placing bid on artwork. Either price is lower or max bid aciheved.");
		}
	}

	public double getValueOfHighestBid() {
		return bidsOnItem.get(bidsOnItem.size() - 1).getPrice();
	}

	public Bid getHighestBid() {
		Bid bid = bidsOnItem.get(bidsOnItem.size() - 1);
		return bid;
	}

	public boolean isBidIsOver() {
		return bidIsOver;
	}

	public void setBidIsOver(boolean bidIsOver) {
		this.bidIsOver = bidIsOver;
	}

	public int getBidsAllowed() {
		int allowed = bidsAllowed - bidsOnItem.size();
		return allowed;

	}

	public void saveChanges() {
		// to implement
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}// end of class
