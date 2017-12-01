/* Created by ayden on 10/11/2017.
 */

import java.io.*;

public class Writer {


    /**
     * Method to write a user text file containing all of the users information
     * @param user - user of the file
     * @throws IOException
     */
    public static void writeUserFile(User user) throws IOException{
        String path = "userFiles//"+user.getUsername()+".txt";
        try{
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.println(user.getTextFileOutput());
            writer.close();
        }
        catch(IOException e){
            throw new IOException("error writing to file of user " + user.getUsername());
        }
    }

    public static void writeBidFile(Bid bid) throws IOException{
        String path = "bids//"+bid.getBidder().getUsername() +".txt";
        System.out.println(path);
        try{
        	File file = new File("bids//"+bid.getBidder().getUsername() +".txt");
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
           
            writer.println(bid.getTextFileOutput());
            writer.close();
        }
        catch(IOException e){
            throw new IOException("Error creating bid file");
        }
    }
    
    public static void saveBid(Bid bid) {
    	
    	
    	
    }

    public static void writePaintingFile(Painting painting) throws IOException{
        String path = "artworkFiles//paintings//"+painting.getTitle()+".txt";
        try{
            PrintWriter writer = new PrintWriter(path,"UTF-8");
            writer.println(painting.getTextFileOutput());
            writer.close();
        }
        catch(IOException e){
            throw new IOException("Error creating painting file for " + painting.getTitle());
        }
    }

    public static void writeSculptureFile(Sculpture sculpture) throws IOException{
        String path = "artworkFiles//sculptures//"+sculpture.getTitle()+".txt";
        try{
            PrintWriter writer = new PrintWriter(path, "UTF-8");
            writer.println(sculpture.getTextFileOutput());
            writer.close();
        }
        catch(IOException e){
            throw new IOException("Error creating sculpture file for " + sculpture.getTitle());
        }
    }





}
