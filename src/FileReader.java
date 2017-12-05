
/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;

import javafx.scene.image.Image;

import java.io.*;
import java.text.*;

/**
 * 
 * @author Software Engineering Team Three
 * @version 1.0
 * 
 */
public class FileReader {

	private static ArrayList<User> users; // arrayList of User objects
	private static ArrayList<Artwork> artworks; // arrayList of Artwork objects
	private static ArrayList<Sculpture> sculptures; // arrayList of Sculpture objects
	private static ArrayList<Painting> paintings; // arrayList of Painting objects

	private static ArrayList<Bid> bids = new ArrayList<Bid>(); // arrayList of Bid objects

	/**
	 * Method to return a specific painting object
	 * @param str - the title of the required painting
	 * @return Painting - the painting with the required title
	 */
	public static Painting getPainting(String str) {

		for (Painting paint : paintings) { //searches the arrayList
			if (paint.getTitle().equalsIgnoreCase(str)) {
				return paint;
			}
		}

		return null;

	}

	/**
	 * Method to add a User object to the User ArrayList
	 * @param user - A User object
	 */
	public static void addUser(User user) {
		users.add(user);
	}

	/**
	 * Method to return a specific User object
	 * @param str - username of the required User
	 * @return user - the User object with the specific username
	 */
	public static User getUser(String str) {

		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(str)) {
				return user;
			}
		}

		return null;

	}

	/**
	 * Method to return a specific Sculpture object
	 * @param str - title of the required Sculpture
	 * @return user - the Sculpture object with the specific title
	 */
	public static Sculpture getSculpture(String str) {

		for (Sculpture sculpture : sculptures) {
			if (sculpture.getTitle().equalsIgnoreCase(str)) {
				return sculpture;
			}
		}

		return null;

	}

	/**
	 * Method to return a specific Artwork object
	 * @param str - title of the required Artwork
	 * @return user - the Artwork object with the specific title
	 */
	public static Artwork getArtwork(String str) {

		for (Artwork art : artworks) {
			if (art.getTitle().equalsIgnoreCase(str)) {
				return art;
			}
		}

		return null;

	}

	/**
	 * Initializes all the file readers ready for use
	 */
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

		try {
			readFavouritesFiles();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		artworks = new ArrayList<Artwork>();
		artworks.addAll(sculptures);
		artworks.addAll(paintings);

		try {
			readBidFiles();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Method to return an arrayList containing all of the users in the system
	 * 
	 * @return ArrayList<User> - ArrayList of users
	 * @throws FileNotFoundException
	 */
	public static ArrayList<User> readUserFile() throws FileNotFoundException {
		ArrayList<User> users = new ArrayList<>();
		File[] listOfFiles = new File("userFiles//").listFiles();
		for (File e : listOfFiles) {
			users.add(constructUser(e.getName()));
		}
		// favourites must be added here

		users = readFavouritesFile(users);

		return users;

	}

	/**
	 * THIS WRONG, DON'T REMOVE YET THOUGH PLEASE
	 */
	public static ArrayList<User> readFavouritesFile(ArrayList<User> users) throws FileNotFoundException {
		final String FAVOURITES_PATH = "favourites.txt";
		Scanner in = new Scanner(new File(FAVOURITES_PATH));
		in.useDelimiter(",");
		while (in.hasNext()) {
			User user1 = null;
			User user2 = null;

			String firstUser = in.next();
			String secondUser = in.next();

			// for loop to find the users
			for (User u : users) {
				if (u.getUsername().equals(firstUser)) {
					user1 = u;
				}
				if (u.getUsername().equals(secondUser)) {
					user2 = u;
				}
			}

			user1.addUserToFavourites(user2);
			if (in.hasNext()) {
				in.nextLine();
			}
		}
		in.close();
		return users;
	}
	
	/**
	 * Method to read the favourite user text file and 
	 * adds 2 users to an ArrayList of favourite users
	 * @throws FileNotFoundException - if file doesn't exist
	 */
	public static void readFavouritesFiles() throws FileNotFoundException {
		final String FAVOURITES_PATH = "favourites.txt";
		Scanner fileScanner = new Scanner(new File(FAVOURITES_PATH));

		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();

			Scanner in = new Scanner(line);
			in.useDelimiter(",");
			User user1 = null;
			User user2 = null;

			String firstUser = in.next();
			String secondUser = in.next();

			
			
			// for loop to find the users1
			for (User u : users) {
				System.out.println("user" + u.getAddress());
				
				if (u.getUsername().equals(firstUser)) {
					user1 = u;
				}
				if (u.getUsername().equals(secondUser)) {
					user2 = u;
				}
			}

			user1.addUserToFavourites(user2);
			if (in.hasNext()) {
				in.nextLine();
			}
		}
		fileScanner.close();
	}

	/**
	 * Method that creates a list of the user files
	 * and then calls the constructUser method to 
	 * add all users in the system into an ArrayList of Users
	 * @throws FileNotFoundException - if file doesn't exist
	 */
	public static void readUserFiles() throws FileNotFoundException {
		users = new ArrayList<>();
		File[] listOfFiles = new File("userFiles//").listFiles();
		for (File e : listOfFiles) {
			users.add(constructUser(e.getName()));
		}

	}

	/**
	 * Method to return an arrayList containing all of the paintings in the file
	 * 
	 * @return ArrayList<Painting> - ArrayList of paintings
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Painting> readPaintingFile() throws FileNotFoundException {
		ArrayList<Painting> paintings1 = new ArrayList<>();
		File[] listOfFiles = new File("artworkFiles//paintings").listFiles();
		for (File e : listOfFiles) {
			paintings1.add(constructPainting(e.getName()));
		}
		return paintings1;
	}
	//WHY ARE THERE TWO OF THESE METHODS??
	public static void readPaintingFiles() throws FileNotFoundException {
		paintings = new ArrayList<>();
		File[] listOfFiles = new File("artworkFiles//paintings").listFiles();
		for (File e : listOfFiles) {
			paintings.add(constructPainting(e.getName()));
		}
	}

	public static ArrayList<Sculpture> readSculptureFile() throws FileNotFoundException {
		ArrayList<Sculpture> sculptures1 = new ArrayList<>();
		File[] listOfFiles = new File("artworkFiles//sculptures").listFiles();
		for (File e : listOfFiles) {
			sculptures1.add(constructSculptures(e.getName()));
		}
		return sculptures1;
	}

	/**
	 * Method to return an arrayList containing all of the sculptures in the list
	 * of files
	 * 
	 * @return ArrayList<Sculpture> - ArrayList of sculptures
	 * @throws FileNotFoundException - if the file doesn't exist
	 */
	public static ArrayList<Sculpture> readSculptureFiles() throws FileNotFoundException {
		sculptures = new ArrayList<>();
		File[] listOfFiles = new File("artworkFiles//sculptures").listFiles();
		for (File e : listOfFiles) {
			sculptures.add(constructSculptures(e.getName()));
		}
		return sculptures;
	}

	/**
	 * Method to return an ArrayList of Bids using the constructBid() method
	 * @param filename
	 * @return ArrayList<Bid> - an arrayList of all bids made by a user
	 * @throws FileNotFoundException - if file doesn't exist
	 */
	public static ArrayList<Bid> readBidFile(String filename) throws FileNotFoundException {

		constructBid(filename);

		return bids;
	}
	/**
	 * Method to read the user's bid files and create bid objects which
	 * are then added to an ArrayList
	 * @param filename - name of the user whose bids you wants
	 */
	public static void constructBid(String filename) {
		String BID_FILE = "bids//" + filename;

		try {
			Scanner in = new Scanner(new File(BID_FILE));
			while (in.hasNext()) {
				in.useDelimiter(",");
				String typeOfArtwork = in.next();

				String username = in.next();
				String artwork = in.next();
				Double bidAmount = in.nextDouble();
				String dateString = in.nextLine();
				Date date = new Date(dateString);

				String error1 = "Bidder cannot bid on their own artwork." + "\n";
				String error2 = "Maximum number of bids has been reached." + "\n";
				String error3 = "Bid must be higher than current bid." + "\n";

				//If the bid was on a painting this block of code is used
				if (typeOfArtwork.equalsIgnoreCase("painting")) {
					Painting art = constructPainting(artwork + ".txt");
					User seller = getUser(username);
					Bid bid = new Bid(typeOfArtwork, seller, bidAmount, art, date);

							bids.add(bid);
							seller.addBid(bid);

				}
				//If the bid was on a sculpture then this block of code is used
				else {
					Sculpture art = constructSculptures(artwork + ".txt");
					User seller = getUser(username);
					Bid bid = new Bid(typeOfArtwork, seller, bidAmount, art, date);
					bids.add(bid);
					seller.addBid(bid);
				}
				in.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

		//ANOTHER DUPLICATE METHOD??
	public static void readBidFiles() throws FileNotFoundException {

		File[] listOfFiles = new File("bids/").listFiles();
		for (File e : listOfFiles) {

			Scanner sc = new Scanner(e);

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				Scanner linear = new Scanner(line);
				linear.useDelimiter(",");
				String type = linear.next();
				String username = linear.next();
				String artwork = linear.next();
				String amount = linear.next();
				String dateString = linear.next();
				Date date = new Date(dateString);
				Double amount1 = Double.parseDouble(amount);

				User user = FileReader.getUser(username);

				if (type.equalsIgnoreCase("sculpture")) {
					Sculpture sculpture = FileReader.getSculpture(artwork);
					//Date date = new Date();
					Bid bid = new Bid(type, user, 0, sculpture, date);
					user.addBid(bid);
					sculpture.addBidToItem(bid);
					// System.out.println(bid.toString());
					bids.add(bid);

				} else if (type.equalsIgnoreCase("painting")) {
					Painting painting = FileReader.getPainting(artwork);
					//Date date = new Date();
					Bid bid = new Bid(type, user, amount1, painting, date);
					user.addBid(bid);
					painting.addBidToItem(bid);
					// System.out.println(bid.toString());
					bids.add(bid);

				}
			}
		}
	}

	/**
	 * Method to construct an return a painting from a text file
	 * 
	 * @param filename
	 *            - file name of the painting
	 * @return Painting
	 */
	public static Painting constructPainting(String filename) {
		final String PATH = "artworkFiles//paintings//" + filename;
		try {
			Scanner in = new Scanner(new File(PATH));
			in.useDelimiter("#");
			String name = in.next();
			String username = in.next();
			String creator = in.next();
			int yearWasMade = in.nextInt();
			int numberOfBids = in.nextInt();
			double reservePrice = in.nextDouble();
			int width = in.nextInt();
			int height = in.nextInt();
			User seller = getUser(username);

			// checking if there is a description
			String description = "";
			if (in.hasNext()) {
				description = in.next();
				Painting painting = new Painting(seller, new Date(), name, creator, yearWasMade, numberOfBids,
						reservePrice, width, height, description);
				seller.addArtwork(painting);
				in.close();
				return painting;
			} else {
				Painting painting = new Painting(seller, new Date(), name, creator, yearWasMade, numberOfBids,
						reservePrice, width, height);

				if (seller != null) {
					seller.addArtwork(painting);
				}
				System.out.println("Painting " + painting.getTitle() + " was created");
				in.close();
				return painting;
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("Error constructing painting. File " + filename + " was not found");
		}

		return null;
	}

	/**
	 * Method to read a text file and create a Sculpture object
	 * @param filename - name of the sculpture file
	 * @return Sculpture - Sculpture object made from the text file info
	 */
	private static Sculpture constructSculptures(String filename) {
		final String PATH = "artworkFiles//sculptures//" + filename;
		try {
			Scanner in = new Scanner(new File(PATH));
			in.useDelimiter("#");
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

			User seller = constructUser(username + ".txt");

			String description = "";
			if (in.hasNext()) {
				description = in.next();
				Sculpture sculpture = new Sculpture(seller, new Date(), name, creator, yearWasMade, numberOfBids,
						reservePrice, width, height, depth, material, description);
				seller.addArtwork(sculpture);
				in.close();
				return sculpture;
			}

			Sculpture sculpture = new Sculpture(seller, new Date(), name, creator, yearWasMade, numberOfBids,
					reservePrice, width, height, depth, material);
			seller.addArtwork(sculpture);

			System.out.println("Sculpture " + sculpture.getTitle() + " was created");
			in.close();
			return sculpture;

		} catch (FileNotFoundException e) {
			System.out.println("Error constructing sculpture. File " + filename + " was not found");
		}
		return null;
	}

	/**
	 * Method to construct and return a user from a text file
	 * 
	 * @param filename
	 *            - file name of the user
	 * @return User
	 */
	public static User constructUser(String filename) {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yy h:mm a");
		try {
			Scanner in = new Scanner(new File("userFiles//" + filename));
			in.useDelimiter(",");
			String username = in.next();
			String firstname = in.next();
			String lastname = in.next();
			String address = in.next();
			long phonenumber = in.nextLong();
			int avatarIndex = 0;
			avatarIndex = in.nextInt();
			String postcode = in.next();

			User user = new User(username, firstname, lastname, address, postcode, phonenumber, avatarIndex);
			System.out.println(user.getUsername() + "," + avatarIndex);
			in.close();
			return user;
		} catch (FileNotFoundException e) {
			// TODO WHY DOES THIS ALWAYS CATCH EVEN WHEN IT WORKS
		}
		return null;
	}

	/**
	 * Method to return the users ArrayList
	 * @return ArrayList<User> - all users in the system
	 */
	public static ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Method to set the users ArrayList
	 * @param users - an arraylist of users in the system
	 */
	public static void setUsers(ArrayList<User> users) {
		FileReader.users = users;
	}

	/**
	 * Method to return the artwork ArrayList
	 * @return ArrayList<Artwork> - all artworks in the system
	 */
	public static ArrayList<Artwork> getArtworks() {
		return artworks;
	}

	/**
	 * Method to return the sculptures ArrayList
	 * @return ArrayList<Sculpture> - all sculptures in the system
	 */
	public static ArrayList<Sculpture> getSculptures() {
		return sculptures;
	}

	/**
	 * Method to return the painting ArrayList
	 * @return ArrayList<Painting> - all paintings in the system
	 */
	public static ArrayList<Painting> getPaintings() {
		return paintings;
	}

	/**
	 * Method to get the image of an artwork
	 * @param name - name of the artwork 
	 * @return Image - the picture associated with the selected
	 * artwork
	 */
	public static Image retrieveImage(String name) {
		Image image = null;
		try {
			image = new Image(new FileInputStream("artworkImages/" + name + "/0.png"));
			return image;

		} catch (FileNotFoundException e) {
			try {
				image = new Image(new FileInputStream("artworkImages/notfound.png"));
				return image;
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		}
		return image;
	}

	/**
	 * Method to return the ArrayList of additional images
	 * @return ArrayList<Image> - all additional sculpture images
	 */
	public static ArrayList<Image> retrieveAdditionalImages(String name) {
		ArrayList<Image> images = new ArrayList<>();
		Image image;
		for (int i = 0; i < 4; i++) {
			try {
				image = new Image(new FileInputStream("artworkImages/" + name + "/" + i + ".png"));
				images.add(image);
			} catch (FileNotFoundException e) {
				System.out.println("No picture found");
			}
		}
		return images;
	}

	/**
	 * Method to return the bids ArrayList
	 * @return ArrayList<Bids> - all bids in the system
	 */
	public static ArrayList<Bid> getBids() {
		return bids;
	}

	public static void setBids(ArrayList<Bid> bids) {
		FileReader.bids = bids;
	}

	/**
	 * Method to check if 2 users are one anothers favourite user
	 * @param user1 - one of the the users
	 * @param user2 - another one of the users
	 * @return boolean - True if they are favourites, false otherwise
	 */
	public static boolean checkIfInFavouriteList(User user1, User user2) {
		for (User favUser : user1.getFavouriteUsers()) {
			if (favUser.getUsername().equals(user2.getUsername())) {
				return true;
			}
		}
		return false;

	}
}
