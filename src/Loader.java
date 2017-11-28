import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

	private ArrayList<File> userFiles;
	private ArrayList<File> artworkList;
	private static ArrayList<User> users;
	private static ArrayList<Artwork> artworks;

	public static void init() {

		// User user1 = new User();

	}

	public static void readUserFiles() {

		users = new ArrayList<>();
		File[] listOfFiles = new File("userFiles//").listFiles();
		for (File e : listOfFiles) {
			users.add(constructUser(e.getName()));
		}

	}

	public void readUserFile(String filename) {
		// to implement
	}

	public ArrayList<User> getSelectedUsers(String name) {
		// to implement
		return null;
	}

	public ArrayList<Artwork> getSelectedArtworks(String name) {
		return null;

	}

	public ArrayList<User> getNewUsers() {
		return null;

	}

	public ArrayList<Artwork> getFavouriteArtworks(String username) {
		return null;

	}

	public ArrayList<Artwork> getFavouriteUsers(String username) {
		return null;

	}

	public ArrayList<User> readDataFromUserFile(Scanner in) {
		return null;

	}

	public void readArtworkFile(String filename) {
		// to implement
	}

	public void readBidFile(String filename) {

	}

	public void readDataFromArtworkFile(String filename) {

	}

	public ArrayList<Bid> getUserBidHistory(String filename, String username) {
		return null;

	}
	
	
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

	/*
	 * 
	 * 
	 * 
	 * readDataFromUserFile( in : Scanner) : ArrayList<User> + readArtworkFile(
	 * filename : String) : void - readDataFromArtworkFile( in : Scanner) :
	 * ArrayList<Artwork> + readBidFile(filename : String) : void -
	 * readFromBidFile(in : Scanner) : ArrayList<Bid> + getUserBidHistory(filename :
	 * String, username : String) : ArrayList<Bid>
	 * 
	 * 
	 */

}
