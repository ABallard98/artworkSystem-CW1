/* Created by ayden on 10/11/2017.
 */

import java.io.*;
import java.io.FileReader;
import java.util.*;

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


    public static void addToFavourites(User user1, User user2) {
    	String path = "favourites.txt";
    	
		File file = new File("favourites.txt");
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(file, true));

		    writer.println(user1.getUsername() + "," +user2.getUsername());
		    writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static boolean removeFromFavourites(User user1, User user2){
        final String FAVOURITES_FILE = "favourites.txt";
        final File FAVOURITES = new File("favourites.txt");
        final File TEMP_FILE = new File("temp-favourites.txt");
        String toRemove = user1.getUsername()+","+user2.getUsername();

        try{
            BufferedReader reader = new BufferedReader(new FileReader(FAVOURITES));
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_FILE));
            String currentLine;


            while((currentLine = reader.readLine()) != null){
                if(null!=currentLine && !currentLine.equalsIgnoreCase(toRemove)){
                    writer.write(currentLine+ System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();
            FAVOURITES.delete();
            boolean successful = TEMP_FILE.renameTo(FAVOURITES);
            System.out.println(successful);
            return successful;
        }
        catch(Exception e){
            return false;
        }
    }

    


}
