/**
 * Created by ayden on 10/11/2017.
 */


import java.util.*;
import java.io.*;
import java.text.*;

public class FileReader {

	
	private ArrayList<File> userFiles;
	private ArrayList<File> artworkList;
	private static ArrayList<User> users;
	private static ArrayList<Artwork> artworks;
	private static ArrayList<Sculpture> sculptures;
	private static ArrayList<Painting> paintings;

	public static void initialize() {
		try {
			readUserFiles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			readPaintingFiles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			readSculptureFiles();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    /**
     * Method to return an arrayList containing all of the users in the system
     * @return ArrayList<User> - ArrayList of users
     * @throws FileNotFoundException
     */
    public static ArrayList<User> readUserFile() throws FileNotFoundException{
        ArrayList<User> users1 = new ArrayList<>();
        File[] listOfFiles = new File("userFiles//").listFiles();
        for(File e : listOfFiles) {
            users1.add(constructUser(e.getName()));
        }
        return users1;
 
     }

    
    public static void readUserFiles() throws FileNotFoundException{
        users = new ArrayList<>();
        File[] listOfFiles = new File("userFiles//").listFiles();
        for(File e : listOfFiles) {
            users.add(constructUser(e.getName()));
        }
 
     }
    /**
     * Method to return an arrayList containing all of the paintings in the file
     * @return ArrayList<Painting> - ArrayList of paintings
     * @throws FileNotFoundException
     */
    public static ArrayList<Painting> readPaintingFile() throws FileNotFoundException{
        ArrayList<Painting> paintings1 = new ArrayList<>();
        File[] listOfFiles = new File("artworkFiles//paintings").listFiles();
        for(File e : listOfFiles){
           paintings1.add(constructPainting(e.getName()));
        }
        return paintings1;
    }
    
    
    public static void readPaintingFiles() throws FileNotFoundException{
         paintings = new ArrayList<>();
        File[] listOfFiles = new File("artworkFiles//paintings").listFiles();
        for(File e : listOfFiles){
           paintings.add(constructPainting(e.getName()));
        }
    }
    

    public static ArrayList<Sculpture> readSculptureFile() throws FileNotFoundException{
        ArrayList<Sculpture> sculptures1 = new ArrayList<>();
        File[] listOfFiles = new File("artworkFiles//sculptures").listFiles();
        for(File e : listOfFiles){
            sculptures1.add(constructSculptures(e.getName()));
        }
        return sculptures1;
    }
    
    public static ArrayList<Sculpture> readSculptureFiles() throws FileNotFoundException{
      sculptures = new ArrayList<>();
        File[] listOfFiles = new File("artworkFiles//sculptures").listFiles();
        for(File e : listOfFiles){
            sculptures.add(constructSculptures(e.getName()));
        }
        return sculptures;
    }

    //TODO GOTTA CONSTRUCT THE BID, ADD THE BID TO THE ARTWORK AND ADD THE BID TO THE USER
    public static ArrayList<Bid> readBidFile(String filename){
        ArrayList<Bid> bids = new ArrayList<>();
        final String BID_FILE = filename;
        return bids;
    }

    /**
     * Method to construct an return a painting from a text file
     * @param filename - file name of the painting
     * @return Painting
     */
    private static Painting constructPainting(String filename){
        final String PATH = "artworkFiles//paintings//"+filename;
        try{
            Scanner in = new Scanner(new File(PATH));
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
            System.out.println("Painting " + painting.getTitle() + " was created");
            in.close();
            return painting;
        }
        catch(FileNotFoundException e){
            System.out.println("Error constructing painting. File " + filename + " was not found");
        }
        return null;
    }

    private static Sculpture constructSculptures(String filename){
        final String PATH = "artworkFiles//sculptures//"+filename;
        try{
            Scanner in = new Scanner(new File(PATH));
            in.useDelimiter(",");
            String name = in.next();
            String username = in.next();
            String creator = in.next();
            int yearWasMade = in.nextInt();
            int numberOfBids = in.nextInt();
            double reservePrice = in.nextDouble();
            int width = in.nextInt();
            int height = in.nextInt();
            int depth = in.nextInt();
            String material = in.next();

            User seller = constructUser(username+".txt");
            Sculpture sculpture = new Sculpture(seller,new Date(),name,creator,yearWasMade,
                    numberOfBids,reservePrice,width,height,depth,material);
            System.out.println("Sculpture " + sculpture.getTitle() + " was created");
            in.close();
            return sculpture;

        }
        catch(FileNotFoundException e){
            System.out.println("Error constructing sculpture. File " + filename + " was not found");
        }
        return null;
    }

    /**
     * Method to construct and return a user from a text file
     * @param filename - file name of the user
     * @return User
     */
    public static User constructUser(String filename){
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
            //TODO WHY DOES THIS ALWAYS CATCH EVEN WHEN IT WORKS
        }
        return null;
    }


	public static ArrayList<User> getUsers() {
		return users;
	}


	public static void setUsers(ArrayList<User> users) {
		FileReader.users = users;
	}


	public static ArrayList<Artwork> getArtworks() {
		return artworks;
	}


	public static ArrayList<Sculpture> getSculptures() {
		return sculptures;
	}


	public static ArrayList<Painting> getPaintings() {
		return paintings;
	}
    
    
    
    
}
