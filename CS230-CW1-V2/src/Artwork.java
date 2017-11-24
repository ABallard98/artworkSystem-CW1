/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;

public class Artwork {

    protected User seller; //seller of the artwork
    protected Date dateAdded; //date added to the system
    protected String name; //name of the artwork
    protected String description; //description of the artwork
    protected String creator; //name of creator of the artwork
    protected int yearWasMade; //year the artwork was made
    protected int numberOfBids; //number of the bids possible for this artwork
    protected double reservePrice; //reserve price for the artwork
    protected ArrayList<Bid> bids; //ArrayList of bids on this artwork

    /**
     * Constructor of artwork without a description
     * @param seller - seller of the artwork
     * @param date - date added to the system
     * @param name - name of the artwork
     * @param creator - creator of the artwork
     * @param yearWasMade - year the artwork was made
     * @param numberOfBids - number of bids possible for the artwork
     * @param reservePrice - reserve price of the artwork
     */
    public Artwork(User seller, Date date, String name, String creator, int yearWasMade, int numberOfBids,
                   double reservePrice){
        setSeller(seller);
        setDateAdded(date);
        setName(name);
        setCreator(creator);
        setYearWasMade(yearWasMade);
        setNumberOfBids(numberOfBids);
        setReservePrice(reservePrice);
        this.bids = new ArrayList<>();
    }

    /**
     * Constructor of artwork with a  a description
     * @param seller - seller of the artwork
     * @param date - date added to the system
     * @param name - name of the artwork
     * @param creator - creator of the artwork
     * @param yearWasMade - year the artwork was made
     * @param numberOfBids - number of bids possible for the artwork
     * @param reservePrice - reserve price of the artwork
     * @param description  - description of the artwork
     */
    public Artwork(User seller, Date date, String name, String creator, int yearWasMade, int numberOfBids,
                   double reservePrice, String description){
    	setSeller(seller);
        setDateAdded(date);
        setName(name);
        setCreator(creator);
        setYearWasMade(yearWasMade);
        setNumberOfBids(numberOfBids);
        setReservePrice(reservePrice);
        this.bids = new ArrayList<>();
        setDescription(description);
    }

    /**
     * Method to get the seller of the artwork
     * @return User - seller
     */
    public User getSeller(){
        return this.seller;
    }

    /**
     * Method to get the date the artwork was added to the system
     * @return Date - dateAdded
     */
    public Date getDateAdded(){
        return this.dateAdded;
    }

    /**
     * Method to get the name of the artwork
     * @return String - name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Method to get the description of the artwork
     * @return String - description
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Method to get the creator of the artwork
     * @return String - creator
     */
    public String getCreator(){
        return this.creator;
    }

    /**
     * Method to get the year the artwork was made
     * @return int - year
     */
    public int getYearWasMade(){
        return this.yearWasMade;
    }

    /**
     * Method to get the number of bids possible on the artwork
     * @return int - numberOfBids
     */
    public int getNumberOfBids(){
        return this.numberOfBids;
    }

    /**
     * Method to get the reserve price of the artwork
     * @return double - reservePrice
     */
    public double getReservePrice(){
        return this.reservePrice;
    }

    /**
     * Method to set the seller of the artwork
     * @param seller
     */
    public void setSeller(User seller){
        this.seller = seller;
    }

    /**
     * Method to set the date added of the artwork
     * @param date
     */
    public void setDateAdded(Date date){
        this.dateAdded = date;
    }

    /**
     * Method to set the name of the artwork
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Method to set the description of the artwork
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * Method to set the creator of the artwork
     * @param creator
     */
    public void setCreator(String creator){
        this.creator = creator;
    }

    /**
     * Method to set the year the artwork was made
     * @param year
     */
    public void setYearWasMade(int year){
        this.yearWasMade = year;
    }

    /**
     * Method to set the number of bids possible on the artwork
     * @param bids
     */
    public void setNumberOfBids(int bids){
        this.numberOfBids = bids;
    }

    /**
     * Method to set the reservePrice of the artwork
     * @param price
     */
    public void setReservePrice(double price){
        this.reservePrice = price;
    }

    /**
     * Method to place a bid on the artwork
     * @param bid
     */
    public void placeBid(Bid bid){
        if(bids.size() < numberOfBids && bid.getPrice() > bids.get(bids.size()-1).getPrice()){
            bids.add(bid);
        }
        else{
            System.out.println("Error placing bid on artwork. Either price is lower or max bid aciheved.");
        }
    }

}//end of class
