/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;

public class Bid {

    private User bidder;
    private double price;
    private Artwork artwork;
    private Date bidDate;

    public Bid(User bidder, double price, Artwork artwork, Date bidDate){
        this.bidder = bidder;
        this.price = price;
        this.artwork = artwork;
        this.bidDate = bidDate;
    }

    public User getBidder(){
        return this.bidder;
    }

    public double getPrice(){
        return this.price;
    }

    public Artwork getArtwork(){
        return this.artwork;
    }

    public Date getBidDate(){
        return this.bidDate;
    }

    public void setBidder(User bidder){
        this.bidder = bidder;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setArtwork(Artwork artwork){
        this.artwork = artwork;
    }

    public void setBidDate(Date date){
        this.bidDate = date;
    }

    public String getTextFileOutput(){
        String output = this.getBidder().getUsername() + "," + this.artwork.getName()+ "," + this.getPrice() +
                "," + this.bidDate;
        return output;
    }

    public String toString(){
        String output = "Bid made by " + this.getBidder().getUsername() + " for the artwork " +
                this.getArtwork().getName() + " for " + this.getPrice() + " on " + this.getBidDate();
        return output;
    }
}
