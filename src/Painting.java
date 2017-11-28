/**
* Created by ayden on 10/11/2017.
*/

import java.util.*;

public class Painting extends Artwork{

   private int width;
   private int height;

   public Painting(User seller, Date date, String name, String creator, int yearWasMade, int numberOfBids,
                    double reservePrice, int width, int height){
       super(seller,date,name,creator,yearWasMade,numberOfBids,reservePrice);
       setWidth(width);
       setHeight(height);
   }

   public Painting(User seller, Date date, String name, String creator, int yearWasMade, int numberOfBids,
                   double reservePrice, int width, int height, String description){
       super(seller,date,name,creator,yearWasMade,numberOfBids,reservePrice,description);
       setWidth(width);
       setHeight(height);
   }

   public int getWidth(){
       return this.width;
   }

   public int getHeight(){
       return this.height;
   }

   public void setWidth(int width){
       this.width = width;
   }

   public void setHeight(int height){
       this.height = height;
   }

   public String getTextFileOutput(){
       String output = this.getTitle()+","+this.getOwner().getUsername()+","+this.getCreator()+","+this.getCreationYear()+
               ","+this.getNumberOfBids()+","+this.getReservePrice()+","+this.getWidth()+","+this.getHeight()+",";
       return output;
   }


   public String toString(){
       String output = "\nPainting Name: " + this.getTitle() +
               "\nCreator: " + this.getCreator() +
               "\nSeller: " + this.getOwner() +
               "\nYear: " + this.getCreationYear() +
               "\nNumber of available bids: " + this.getNumberOfBids() +
               "\nReserve price " + this.getReservePrice() +
               "\nWidth: " + this.getWidth() +
               "\nHeight: " + this.getHeight() +
               "\nDescription: " + this.getDescription();
       return output;
   }

}
