
/**
 * Created by ayden on 10/11/2017.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class User {

	private String username; // username of the user
	private String firstName; // first name of the user
	private String lastName; // last name of the user
	private String fullName;
	private long phonenumber; // phone number of the user
	private String address; // address of the user
	private String postcode; // post code of the user
	private Date timeLastLoggedIn;
	private Image image;
	private ArrayList<Artwork> artForSale;
	private ArrayList<Artwork> artSold;
	private ArrayList<Artwork> artWon;
	private ArrayList<User> favouriteUsers; // array list of favourite users
	private Integer sellingArtworks;
	private boolean customImage;
	private int avatarIndex;
	private int bidsPlaced;
	private ArrayList<Bid> placedBids;
	private ImageView imgView;

	/**
	 * Constructor for user object
	 *
	 * @param username
	 *            - username of the user
	 * @param fname
	 *            - first name of the user
	 * @param lname
	 *            - last name of the user
	 * @param address
	 *            - address of the user
	 * @param postcode
	 *            - postcode of the user
	 * @param phonenumber
	 *            - phonenumber of the user
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

	public User(String username, String fname, String lname, String address, String postcode, long phonenumber,
			int index) {
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

	public boolean isCustomImage() {
		return customImage;
	}

	public void resolvePicture() {

		if (!isCustomImage() && avatarIndex > 0) {

			try {
				image = new Image(new FileInputStream("avatars/avatar" + avatarIndex + ".png"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public ImageView getImgView() {

		imgView.setImage(image);

		imgView.setFitWidth(100);
		imgView.setFitHeight(100);
		return imgView;
	}
	

	public Image getImage() {
		return image;
	}
	
	

	public String getImagePath() {
		return image.getUrl();
	}

	/**
	 * Method to get the user's username
	 *
	 * @return username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Method to get the user's first name
	 *
	 * @return fistname
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Method to get the user's last name
	 *
	 * @return lastname
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Method to get the user's fullname
	 *
	 * @return full name of the user
	 */
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	/**
	 * Method to get the user's address
	 *
	 * @return address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Method to get the user's postcode
	 *
	 * @return postcode
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Method to get the user's phone number
	 *
	 * @return phone number
	 */
	public long getPhonenumber() {
		return this.phonenumber;
	}

	/**
	 * Method to set the user's username
	 *
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Method to set the user's first name
	 *
	 * @param fname
	 */
	public void setFirstName(String fname) {
		this.firstName = fname;
	}

	/**
	 * Method to set the user's last name
	 *
	 * @param lname
	 */
	public void setLastName(String lname) {
		this.lastName = lname;
	}

	/**
	 * Method to set the user's address
	 *
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Method to set the user's postcode
	 *
	 * @param postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Method to set the users phone number
	 *
	 * @param phonenumber
	 */
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * Method to add a user to this user's favourites
	 *
	 * @param u
	 *            - user
	 */
	public void addUserToFavourites(User u) {

		this.favouriteUsers.add(u);

	}

	/**
	 * Method to remove a user from this user's favourites
	 *
	 * @param u
	 *            - user
	 */
	public void removeUserFromFavourites(User u) {
		try {
			this.favouriteUsers.remove(u);
		} catch (NullPointerException e) {
			throw new NullPointerException("Error, user not found in favourites");
		}
	}

	/**
	 * Method to get this user's favourites
	 *
	 * @return ArrayList of users
	 */
	public ArrayList<User> getFavouriteUsers() {
		return this.favouriteUsers;
	}

	/**
	 * Method to get a string containing the usernames of this user's favourites
	 *
	 * @return String of favourite user's usernames
	 */
	public String getFavouritesString() {
		String output = "";
		if (!favouriteUsers.isEmpty()) {
			for (User e : favouriteUsers) {
				output = e.getUsername() + " " + output;
			}
			return output;
		} else {
			return "";
		}

	}

	/**
	 * Method to get the text file output which will be used to store the user's
	 * information
	 *
	 * @return String textFileOutput
	 */
	public String getTextFileOutput() {
		String output = this.username + "," + this.firstName + "," + this.lastName + "," + this.address + ","
				+ this.phonenumber + "," + this.avatarIndex + "," + this.postcode;
		return output;
	}

	/**
	 * Method to get a string containing all of the user's information
	 *
	 * @return String toString
	 */
	public String toString() {
		String output = this.username + ": \n" + "Name: " + this.getFullName() + "\n" + "Address: " + this.getAddress()
				+ "\n" + "PhoneNumber: " + this.phonenumber
				+ "\n" /* + "Favourites: " + this.getFavouritesString() + "\n" */;
		return output;
	}

	public Date getTimeLastLoggedIn() {
		return timeLastLoggedIn;
	}

	public void setTimeLastLoggedIn(Date timeLastLoggedIn) {
		this.timeLastLoggedIn = timeLastLoggedIn;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ArrayList<Artwork> getArtForSale() {
		return artForSale;
	}

	public void setArtForSale(ArrayList<Artwork> artForSale) {
		this.artForSale = artForSale;
	}

	public ArrayList<Artwork> getArtSold() {
		return artSold;
	}

	public void setArtSold(ArrayList<Artwork> artSold) {
		this.artSold = artSold;
	}

	public ArrayList<Artwork> getArtWon() {
		return artWon;
	}

	public void setArtWon(ArrayList<Artwork> artWon) {
		this.artWon = artWon;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setFavouriteUsers(ArrayList<User> favouriteUsers) {
		this.favouriteUsers = favouriteUsers;
	}

	public void addArtwork(Artwork artwork) {
		artForSale.add(artwork);
	}

	public int getSellingArtworks() {
		return artForSale.size();
	}

	public ArrayList<Bid> getPlacedBids() {
		return placedBids;
	}

	public void setPlacedBids(ArrayList<Bid> placedBids) {
		this.placedBids = placedBids;
	}

	public void addBid(Bid bid) {
		placedBids.add(bid);
	}

	public int getAvatarIndex() {
		return avatarIndex;
	}

	public void setAvatarIndex(int avatarIndex) {
		this.avatarIndex = avatarIndex;
		// resolvePicture();
	}

} // end of class
