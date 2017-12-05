
/**
 * Created by ayden on 10/11/2017.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class User {

	private String username; // Username of the user.
	private String firstName; // First name of the user.
	private String lastName; // Last name of the user.
	private String fullName; // Full name of the user.
	private long phonenumber; // Phone number of the user.
	private String address; // Address of the user.
	private String postcode; // Post code of the user.
	private Date timeLastLoggedIn; // Time of last login.
	private Image image; // Image associated with the user.
	private ArrayList<Artwork> artForSale; // List of Artworks put up for auction.
	private ArrayList<Artwork> artSold; // List of Artworks sold.
	private ArrayList<Artwork> artWon; // List of Artworks won.
	private ArrayList<User> favouriteUsers; // List of favourite users.
	private Integer sellingArtworks; // Number of Artworks currently being sold.
	private boolean customImage; // True or false depending on image type.
	private int avatarIndex; // Index of the avatar chosen.
	private int bidsPlaced; // Number of bid the user has placed.
	private ArrayList<Bid> placedBids; // List of bids placed by the user.
	private ImageView imgView;

	/**
	 * Constructor for a new User object
	 * @param username - username of the user.
	 * @param fname - first name of the user.
	 * @param lname - last name of the user.
	 * @param address - address of the user.
	 * @param postcode - postcode of the user.
	 * @param phonenumber - phonenumber of the user.
	 */
	public User(String username, String fname, String lname, String address, String postcode, long phonenumber) {
		setUsername(username);
		customImage = false;
		imgView = new ImageView();

		setFirstName(fname);
		setLastName(lname);
		setAddress(address);
		setPostcode(postcode);
		setPhonenumber(phonenumber);
		this.favouriteUsers = new ArrayList<>();
		this.sellingArtworks = 0;
		artForSale = new ArrayList<>();
		placedBids = new ArrayList<>();
		avatarIndex = 1;
		try {
			image = new Image(new FileInputStream("avatars/avatar" + avatarIndex + ".png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Makes a new User object with a pre-made avatar as the
	 * associated image.
	 * @param username
	 * @param fname
	 * @param lname
	 * @param address
	 * @param postcode
	 * @param phonenumber
	 * @param index
	 */
	public User(String username, String fname, String lname,
				String address, String postcode, long phonenumber, int index) {
		customImage = false;
		imgView = new ImageView();

		setUsername(username);
		setFirstName(fname);
		setLastName(lname);
		setAddress(address);
		setPostcode(postcode);
		setPhonenumber(phonenumber);
		this.favouriteUsers = new ArrayList<>();
		this.sellingArtworks = 0;
		artForSale = new ArrayList<>();
		placedBids = new ArrayList<>();
		avatarIndex = index;
		
		try {
			image = new Image(new FileInputStream("avatars/avatar" + avatarIndex + ".png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Check if a custom image is being used.
	 * @return true if a custom image is being used or false otherwise
	 */
	public boolean isCustomImage() {
		return customImage;
	}

	/**
	 * Checks if a custom image is being used first. If no
	 * custom image is being used then it tries to find a default avatar
	 * and sets that as the associated image.
	 */
	public void resolvePicture() {
		if (!isCustomImage() && avatarIndex > 0) {
			try {
				image = new Image(new FileInputStream("avatars/avatar" + avatarIndex + ".png"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method used to display an image inside an ImageView
	 * @return ImageView - containing the image
	 */
	public ImageView getImgView() {
		imgView.setImage(image);
		imgView.setFitWidth(100);
		imgView.setFitHeight(100);
		return imgView;
	}

	/**
	 * Method to get the image.
	 * @return Image - image.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Method to get the user's username.
	 * @return String - username.
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Method to get the user's first name.
	 * @return String - firstName.
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Method to get the user's last name.
	 * @return String - lastName.
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Method to get the user's full name.
	 * @return String - fullName.
	 */
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	/**
	 * Method to get the user's address.
	 * @return String - address.
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Method to get the user's postcode.
	 * @return String - postcode.
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Method to get the user's phone number.
	 * @return long - phonenumber.
	 */
	public long getPhonenumber() {
		return this.phonenumber;
	}

	/**
	 * Method to set the user's username.
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Method to set the user's first name.
	 * @param fname - firstName.
	 */
	public void setFirstName(String fname) {
		this.firstName = fname;
	}

	/**
	 * Method to set the user's last name.
	 * @param lname
	 */
	public void setLastName(String lname) {
		this.lastName = lname;
	}

	/**
	 * Method to set the user's address.
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Method to set the user's postcode.
	 * @param postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Method to set the users phone number.
	 * @param phonenumber
	 */
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * Method to add a user to this user's favourites.
	 * @param u - User.
	 */
	public void addUserToFavourites(User u) {
		this.favouriteUsers.add(u);
	}

	/**
	 * Method to remove a user from this user's favourites. Tries to
	 * find the user that should be removed but if that user cannot be found
	 * then an exception is caught and handled.
	 * @param u- User.
	 */
	public void removeUserFromFavourites(User u) {
		try {
			this.favouriteUsers.remove(u);
			Writer.removeFromFavourites(this,u);
		} catch (NullPointerException e) {
			throw new NullPointerException("Error, user not found in favourites.");
		}
	}

	/**
	 * Method to get this user's favourites.
	 * @return ArrayList<User> - favouriteUsers.
	 */
	public ArrayList<User> getFavouriteUsers() {
		return this.favouriteUsers;
	}

	/**
	 * Method to get a string containing the usernames of this user's favourite
	 * users.
	 * @return String - output.
	 */
	public String getFavouritesString() {
		String output = "";
		for (User e : favouriteUsers) {
				output = e.getUsername() + " " + output;
		}
		return output;
	}

	/**
	 * Method to get the text file output which will be used to store the user's
	 * information.
	 * @return String - textFileOutput.
	 */
	public String getTextFileOutput() {
		String output = this.username + "," + this.firstName + "," + this.lastName + "," + this.address + ","
				+ this.phonenumber + "," + this.avatarIndex + "," + this.postcode;
		return output;
	}

	/**
	 * Method to get a string containing all of the user's information.
	 * @return String - toString.
	 */
	public String toString() {
		String output = this.username + ": \n" + "Name: " + this.getFullName() + "\n" + "Address: " + this.getAddress()
				+ "\n" + "PhoneNumber: " + this.phonenumber;
		return output;
	}

	/**
	 * Method to return the time date and time last logged in.
	 * @return Date - timeLastLoggedIn.
	 */
	public Date getTimeLastLoggedIn() {
		return timeLastLoggedIn;
	}

	/**
	 * Allows setting of time last logged in.
	 * @param timeLastLoggedIn
	 */
	public void setTimeLastLoggedIn(Date timeLastLoggedIn) {
		this.timeLastLoggedIn = timeLastLoggedIn;
	}

	/**
	 * Allows setting of an image to a user.
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Gets the list of artworks for sale.
	 * @return ArrayList<Artwork> - artsForSale.
	 */
	public ArrayList<Artwork> getArtForSale() {
		return artForSale;
	}

	/**
	 * Allows setting of artworks for sale
	 * @param artForSale
	 */
	public void setArtForSale(ArrayList<Artwork> artForSale) {
		this.artForSale = artForSale;
	}

	/**
	 * Method to get the list of artworks sold by the user.
	 * @return ArrayList<Artwork> - artSold.
	 */
	public ArrayList<Artwork> getArtSold() {
		return artSold;
	}

	/**
	 * Allows the setting of a list of artworkds sold by the user.
	 * @param artSold
	 */
	public void setArtSold(ArrayList<Artwork> artSold) {
		this.artSold = artSold;
	}

	/**
	 * Method to get a list of artworks won.
	 * @return ArrayList<Artwork> - artWon.
	 */
	public ArrayList<Artwork> getArtWon() {
		return artWon;
	}

	/**
	 * Method to set the list of artwork's a user has won
	 * @param artWon
	 */
	public void setArtWon(ArrayList<Artwork> artWon) {
		this.artWon = artWon;
	}

	/**
	 * Method to set the full name of a user
	 * @param fullName - first name and last name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Method to set a users favourite users
	 * @param favouriteUsers
	 */
	public void setFavouriteUsers(ArrayList<User> favouriteUsers) {
		this.favouriteUsers = favouriteUsers;
	}

	/**
	 * Method to add an artwork to a user's auctions
	 * @param artwork
	 */
	public void addArtwork(Artwork artwork) {
		artForSale.add(artwork);
	}

	/**
	 * Method to get number the artworks that are being sold by the user
	 * @return int - how many artworks are in the ArrayList
	 */
	public int getSellingArtworks() {
		return artForSale.size();
	}

	/**
	 * Method to get the bids that a user has placed
	 * @return ArrayList<Bid> - bids placed by user
	 */
	public ArrayList<Bid> getPlacedBids() {
		return placedBids;
	}

	/**
	 * Method to set the bids placed by a user
	 * @param placedBids
	 */
	public void setPlacedBids(ArrayList<Bid> placedBids) {
		this.placedBids = placedBids;
	}

	/**
	 * Method to add a bid to the users placedBids ArrayList
	 * @param bid - the bid the user has made
	 */
	public void addBid(Bid bid) {
		placedBids.add(bid);
	}

	/**
	 * Method to return the avatar index of a user
	 * @return int - the index of the selected avatar
	 */
	public int getAvatarIndex() {
		return avatarIndex;
	}

	/**
	 * Method to set the avatar index of a user
	 * @param avatarIndex - index of the selected avatar
	 */
	public void setAvatarIndex(int avatarIndex) {
		this.avatarIndex = avatarIndex;
		// resolvePicture();
	}

} // end of class
