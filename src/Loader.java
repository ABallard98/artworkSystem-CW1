import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {

	private ArrayList<File> userFiles;
	private ArrayList<File> artworkList;
	private static ArrayList<User> users;
	private static ArrayList<Artwork> artworks;

	
	public static void init() {
		
		//User user1 = new User();
		
	}
	
	public static void readUserFiles() {

		// to implement
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