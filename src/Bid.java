
/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bid {

	private String typeOfArtwork; // Type artwork (painting or structure).
	private User bidder; // User that placed the bid.
	private double amount; // Price the user placed.
	private Artwork artwork; // The artwork that the bid is placed on.
	private Date bidDate; // The date the bid was placed.

	private ImageView imgView;//?
	private String title;//?
	private String bidDateString; //?
	private Image image;//?

	/**
	 * Creates a new bid object.
	 * @param typeOfArtwork
	 * @param bidder
	 * @param artwork
	 * @param bidDate
	 */
	public Bid(String typeOfArtwork, User bidder, double amount, Artwork artwork, Date bidDate) {

		this.typeOfArtwork = typeOfArtwork;
		this.bidder = bidder;
		this.artwork = artwork;
		this.bidDate = bidDate;
		this.amount = amount;
		System.out.println( artwork.getImage().impl_getUrl());
		imgView = new ImageView();
		image = artwork.getImage();
		this.title = artwork.getTitle();
	}

	public Bid(String typeOfArtwork, User bidder,double amount, Artwork artwork, String bid) {

		this.typeOfArtwork = typeOfArtwork;
		this.bidder = bidder;
		this.artwork = artwork;
		this.amount = amount;
		imgView = new ImageView();


		image = artwork.getImage();
		this.title = artwork.getTitle();
	}

	public Image getImage() {
		return image;
	}

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

	public String getBidDateString() {
		return bidDateString;
	}

	public void setBidDateString(String bidDateString) {
		this.bidDateString = bidDateString;
	}

	public ImageView getImgView() {
		imgView.setFitWidth(100);
		imgView.setFitHeight(100);
		imgView.setImage(image);
		return imgView;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public boolean checkBid() {
		if ((amount > artwork.getReservePrice()) &&
				(artwork.getBidsAllowed() > artwork.getNumberOfBids())) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getTitle() {
		return title;
	}

	/**
	 *
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
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

} // end of class
