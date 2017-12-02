/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bid {
	
	private String typeOfArtwork;
    private User bidder; //user that placed the bid
    private double reservePrice; //price the user placed
    private Artwork artwork; //the artwork that the bid is placed on
    private Date bidDate; //the date the bid was placed
    private ImageView imgView;
    private String title;
    private String bidDateString;

    /**
     * Constructor of Bid
     * @param bidder - user that placed the bid
     * @param reservePrice - price the user placed
     * @param artwork - the artwork that the bid is placed on
     * @param bidDate - the date the bid was placed
     */
    public Bid(String typeOfArtwork,User bidder, double reservePrice, Artwork artwork, Date bidDate){
        this.typeOfArtwork = typeOfArtwork;
    	this.bidder = bidder;
        this.reservePrice = reservePrice;
        this.artwork = artwork;
        this.bidDate = bidDate;
        Image img = artwork.getImage();
        this.title = artwork.getTitle();
    }

    
    public Bid(String typeOfArtwork,User bidder, double reservePrice, Artwork artwork, String bid){
        this.typeOfArtwork = typeOfArtwork;
    	this.bidder = bidder;
        this.reservePrice = reservePrice;
        this.artwork = artwork;
        this.bidDateString = bid;
        Image img = artwork.getImage();
        this.title = artwork.getTitle();
    }
    
    
    /**
     * Method to get the type of artwork
     * @return String - type of artwork
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
 
    	
    	return imgView;
    }
    
    
    

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	/**
     * Method to get the bidder
     * @return User - bidder
     */
    public User getBidder(){
        return this.bidder;
    }

    /**
     * Method to get the price
     * @return double - price
     */
    public double getReservePrice(){
        return this.reservePrice;
    }

    /**
     * Method to get the artwork
     * @return Artwork - artwork
     */
    public Artwork getArtwork(){
        return this.artwork;
    }

    /**
     * Method to get the date the bid was placed
     * @return Date - bidDate
     */
    public Date getBidDate(){
        return this.bidDate;
    }


    /**
     * Method to get the text file output which will be used to store the bid
     * @return String - data on the bid
     */
    public String getTextFileOutput(){
        String output = this.getTypeOfArtwork() + "," + this.getBidder().getUsername()
                + "," + this.artwork.getTitle()+ "," + this.getReservePrice() +
                "," + this.bidDate;
        return output;
    }

    /**
     * Method to get a string containing all of the information on the bid
     * @return
     */
    public String toString(){
        String output = "Bid made by " + this.getBidder().getUsername() + " for the artwork " +
                this.getArtwork().getTitle() + " for " + this.getReservePrice() + " on " + this.getBidDate();
        return output;
    }

} //end of class
