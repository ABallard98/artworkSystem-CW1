/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;

public class Sculpture extends Artwork{

    private int width;
    private int height;
    private int depth;
    private String material;

    public Sculpture(User seller, Date date, String name, String creator, int yearWasMade, int numberOfBids,
                     double reservePrice, int width, int height, int depth, String material){
        super(seller,date,name,creator,yearWasMade,numberOfBids,reservePrice);
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.material = material;
    }

    public Sculpture(User seller, Date date, String name, String creator, int yearWasMade, int numberOfBids,
                     double reservePrice, int width, int height, int depth, String material, String description){
        super(seller,date,name,creator,yearWasMade,numberOfBids,reservePrice,description);
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.material = material;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getDepth(){
        return this.depth;
    }

    public String getMaterial(){
        return this.material;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }

    public void setMaterial(String material){
        this.material = material;
    }

    public String getTextFileOutput(){
        String output = this.getName()+","+this.getSeller().getUsername()+","+this.getCreator()+","+
                this.getYearWasMade()+","+this.getNumberOfBids()+","+this.getReservePrice()+","+
                this.getWidth()+","+this.getHeight()+","+this.getDepth()+","+this.getMaterial()+",";
        return output;
    }

    public String toString(){
        String output = "\nSculpture Name: " + this.getName() +
                "\nCreator: " + this.getCreator() +
                "\nSeller: " + this.getSeller() +
                "\nYear: " + this.getYearWasMade() +
                "\nNumber of available bids: " + this.getNumberOfBids() +
                "\nReserve price " + this.getReservePrice() +
                "\nWidth: " + this.getWidth() +
                "\nHeight: " + this.getHeight() +
                "\nDepth: " + this.getDepth() +
                "\nMaterial: " + this.getMaterial();
        return output;
    }


}
