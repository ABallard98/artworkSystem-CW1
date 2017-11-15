/**
 * Created by ayden on 10/11/2017.
 */

import javafx.beans.property.SimpleMapProperty;

import java.util.*;
import java.io.*;
import java.text.*;

public class FileReader {

    public static ArrayList<User> readUserFile() throws FileNotFoundException{
        ArrayList<User> users = new ArrayList<>();
        File[] listOfFiles = new File("userFiles//").listFiles();
        for(File e : listOfFiles) {
            users.add(constructUser(e.getName()));
        }
        return users;
 
     }

    public static ArrayList<Painting> readPaintingFile() throws FileNotFoundException{
        ArrayList<Painting> paintings = new ArrayList<>();
        File[] listOfFiles = new File("artworkFiles//paintings").listFiles();
        for(File e : listOfFiles){
           paintings.add(constructPainting(e.getName()));
        }
        return paintings;
    }

    //GOTTA CONSTRUCT THE BID, ADD THE BID TO THE ARTWORK AND ADD THE BID TO THE USER
    public static ArrayList<Bid> readBidFile(String filename){
        ArrayList<Bid> bids = new ArrayList<>();
        final String BID_FILE = filename;

        return bids;
    }

    public static Painting constructPainting(String filename){
        System.out.println(filename + " was found");
        String path = "artworkFiles//paintings//"+filename;
        try{
            Scanner in = new Scanner(new File(path));
            in.useDelimiter(",");
            String name = in.next();
            String username = in.next();
            String creator = in.next();
            int yearWasMade = in.nextInt();
            int numberOfBids = in.nextInt();
            double reservePrice = in.nextDouble();
            int width = in.nextInt();
            int height = in.nextInt();
            User seller = constructUser(username+".txt");

            Painting painting = new Painting(seller, new Date(),name,creator,yearWasMade,numberOfBids,reservePrice,
                    width,height);
            System.out.println("Painting " + painting.getName() + " was created");
            in.close();
            return painting;
        }
        catch(FileNotFoundException e){
            System.out.println("Error constructing painting. File " + filename + " was not found");
        }
        return null;
    }

    public static User constructUser(String filename){
        System.out.println(filename + " was found");
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy h:mm a");

        try{
            Scanner in = new Scanner(new File("userFiles//"+filename));
            in.useDelimiter(",");
            String username = in.next();
            String firstname = in.next();
            String lastname = in.next();
            String address = in.next();
            long phonenumber = in.nextLong();
            String postcode = in.next();
            User user = new User(username, firstname, lastname, address, postcode, phonenumber);
            in.close();
            return user;
        }
        catch(FileNotFoundException e){
            System.out.println("The system shat the bed and couldnt find the file");
        }
        return null;
    }




}
