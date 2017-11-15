/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;

public class Artwork {

    private User seller;
    private Date dateAdded;
    private String name;
    private String description;
    private String creator;
    private int yearWasMade;
    private int numberOfBids;
    private double reservePrice;

    private ArrayList<Bid> bids;

    public Artwork(User seller, Date date, String name, String creator, int yearWasMade, int numberOfBids,
                   double reservePrice){
        this.seller = seller;
        this.dateAdded = date;
        this.name = name;
        this.creator = creator;
        this.yearWasMade = yearWasMade;
        this.numberOfBids = numberOfBids;
        this.reservePrice = reservePrice;
        this.bids = new ArrayList<>();
    }

    public Artwork(User seller, Date date, String name, String creator, int yearWasMade, int numberOfBids,
                   float reservePrice, String description){
        this.seller = seller;
        this.dateAdded = date;
        this.name = name;
        this.creator = creator;
        this.yearWasMade = yearWasMade;
        this.numberOfBids = numberOfBids;
        this.reservePrice = reservePrice;
        this.bids = new ArrayList<>();
        this.description = description;
    }

    public User getSeller(){
        return this.seller;
    }

    public Date getDateAdded(){
        return this.dateAdded;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getCreator(){
        return this.creator;
    }

    public int getYearWasMade(){
        return this.yearWasMade;
    }

    public int getNumberOfBids(){
        return this.numberOfBids;
    }

    public double getReservePrice(){
        return this.reservePrice;
    }

    public void setSeller(User seller){
        this.seller = seller;
    }

    public void setDateAdded(Date date){
        this.dateAdded = date;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setCreator(String creator){
        this.creator = creator;
    }

    public void setYearWasMade(int year){
        this.yearWasMade = year;
    }

    public void setNumberOfBids(int bids){
        this.numberOfBids = bids;
    }

    public void setReservePrice(double price){
        this.reservePrice = price;
    }

    public void placeBid(Bid bid){
        if(bids.size() < numberOfBids && bid.getPrice() > bids.get(bids.size()-1).getPrice()){
            bids.add(bid);
        }
        else{
            System.out.println("Error placing bid on artwork. Either price is lower or max bid aciheved.");
        }
    }
}
