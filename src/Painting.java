

import java.util.*;
/**
 * @author Marwan
 * @date 21/11/2017
 */
public class Painting extends Artwork{

   private int width; //width of painting in cm
   private int height; //height of painting in cm

   /**
    * Constructor for a new painting object without a description
    * @param seller - the user who is selling the painting
    * @param date - date when put up for sale
    * @param name - title of the painting
    * @param creatorName - name of who created the painting
    * @param yearWasMade - year of paintings creation
    * @param numberOfBids - how many bids allowed
    * @param reservePrice - minimum price for bids on painting
    * @param width - width of the painting in cm
    * @param height - height of the painting in cm
    */
   public Painting(User seller, Date date, String name, String creatorName, int yearWasMade, int numberOfBids,
                    double reservePrice, int width, int height){
       super(seller,date,name,creatorName,yearWasMade,numberOfBids,reservePrice);
       setWidth(width);
       setHeight(height);
   }

   /**
    * Constructor for a new painting object a description
    * @param seller - the user who is selling the painting
    * @param date - date when put up for sale
    * @param name - title of the painting
    * @param creatorName - name of who created the painting
    * @param yearWasMade - year of paintings creation
    * @param numberOfBids - how many bids allowed
    * @param reservePrice - minimum price for bids on painting
    * @param width - width of the painting in cm
    * @param height - height of the painting in cm
    * @param description - brief description of painting
    */
   public Painting(User seller, Date date, String name, String creatorName, int yearWasMade, int numberOfBids,
                   double reservePrice, int width, int height, String description){
       super(seller,date,name,creatorName,yearWasMade,numberOfBids,reservePrice,description);
       setWidth(width);
       setHeight(height);
   }

   /**
    * Method to return width of painting
    * @return int - width of painting
    */
   public int getWidth(){
       return this.width;
   }

   /**
    * Method to return height of painting
    * @return int - height of painting
    */
   public int getHeight(){
       return this.height;
   }

   /**
    * Method to set the width of the painting
    * @param width - width of the painting
    */
   public void setWidth(int width){
       this.width = width;
   }

   /**
    * Method to set the height of the painting
    * @param height = height of the painting
    */
   public void setHeight(int height){
       this.height = height;
   }

   /**
    * Method to format how the painting information
    * is written to a text file for easier reading
    * @return String - text file format of the painting info
    */
   public String getTextFileOutput(){
       String output = this.getTitle() + "#" + this.getOwner().getUsername()
               + "#" + this.getCreator() + "#" + this.getCreationYear() +
               "#" + this.getNumberOfBids() + "#" + this.getReservePrice() +
               "#" + this.getWidth() + "#" + this.getHeight() + "#" +this.getDescription();;
       return output;
   }
   
   
   
   

   /**
    * Method to print out the information of a painting
    * @return String - the paintings information
    */
   public String toString(){
       String output = "\nPainting Name: " + this.getTitle() +
               "\nCreator: " + this.getCreator() +
               "\nSeller: " + this.getOwner().getFullName() +
               "\nYear: " + this.getCreationYear() +
               "\nNumber of available bids: " + this.getNumberOfBids() +
               "\nReserve price " + this.getReservePrice() +
               "\nWidth: " + this.getWidth() +
               "\nHeight: " + this.getHeight() +
               "\nDescription: " + this.getDescription();
       return output;
   }

}
