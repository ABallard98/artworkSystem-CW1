/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;

public class Bid {

    private User bidder; //user that placed the bid
    private double price; //price the user placed
    private Artwork artwork; //the artwork that the bid is placed on
    private Date bidDate; //the date the bid was placed

    /**
     * Constructor of Bid
     * @param bidder - user that placed the bid
     * @param price - price the user placed
     * @param artwork - the artwork that the bid is placed on
     * @param bidDate - the date the bid was placed
     */
    public Bid(User bidder, double price, Artwork artwork, Date bidDate){
        this.bidder = bidder;
        this.price = price;
        this.artwork = artwork;
        this.bidDate = bidDate;
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
    public double getPrice(){
        return this.price;
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
     * Method to set the bidder
     * @param bidder
     */
    public void setBidder(User bidder){
        this.bidder = bidder;
    }

    /**
     * Method to set the price
     * @param price
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Method to set the artwork
     * @param artwork
     */
    public void setArtwork(Artwork artwork){
        this.artwork = artwork;
    }

    /**
     * Method to set the bid date
     * @param date
     */
    public void setBidDate(Date date){
        this.bidDate = date;
    }

    /**
     * Method to get the text file output which will be used to store the bid
     * @return String - data on the bid
     */
    public String getTextFileOutput(){
        String output = this.getBidder().getUsername() + "," + this.artwork.getName()+ "," + this.getPrice() +
                "," + this.bidDate;
        return output;
    }

    /**
     * Method to get a string containing all of the information on the bid
     * @return
     */
    public String toString(){
        String output = "Bid made by " + this.getBidder().getUsername() + " for the artwork " +
                this.getArtwork().getName() + " for " + this.getPrice() + " on " + this.getBidDate();
        return output;
    }

} //end of class
