import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class constructs a new user object and contains information of that user
 * @author Joshua
 * Created on 5/12/2017
 */
public class User {

	private String username; // Username of the user.
	private String firstName; // First name of the user.
	private String lastName; // Last name of the user.
	private String fullName; // Full name of the user.
	private long phonenumber; // Phone number of the user.
	private String address; // Address of the user.
	private String postcode; // Post code of the user
	private Image image; // Image associated with the user.
	private ArrayList<Artwork> artForSale; // List of Artworks put up for auction.
	private ArrayList<Artwork> artSold; // List of Artworks sold.
	private ArrayList<Artwork> artWon; // List of Artworks won.
	private ArrayList<User> favouriteUsers; // List of favourite users.
	private boolean customImage; // True or false depending on image type.
	private int avatarIndex; // Index of the avatar chosen.
	private ArrayList<Bid> placedBids; // List of bids placed by the user.
	private ImageView imgView;
	private ArrayList<Sculpture> sculptures;  // ArrayList of the users Sculptures for auction
	private ArrayList<Painting> paintings; // ArrayList of the users paintings for auction
	private ArrayList<Bid> wonBids; // ArrayList of the artworks the user has won
	/**
	 * Constructor for a new User object.
	 * 
	 * @param username
	 *            - username of the user.
	 * @param fname
	 *            - first name of the user.
	 * @param lname
	 *            - last name of the user.
	 * @param address
	 *            - address of the user.
	 * @param postcode
	 *            - postcode of the user.
	 * @param phonenumber
	 *            - phonenumber of the user.
	 */
	public User(String username, String fname, String lname, String address, String postcode, long phonenumber) {
		setUsername(username);
		setFirstName(fname);
		setLastName(lname);
		setAddress(address);
		setPostcode(postcode);
		setPhonenumber(phonenumber);
		this.favouriteUsers = new ArrayList<>();
		this.artForSale = new ArrayList<>();
		this.placedBids = new ArrayList<>();
		this.avatarIndex = 1;
		this.customImage = false;
		this.imgView = new ImageView();
		try {
			this.image = new Image(new FileInputStream("avatars/avatar" + avatarIndex + ".png"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * Makes a new User object with a pre-made avatar as the associated image.
	 * 
	 * @param username - username of the user.
	 * @param fname - first name of the user.
	 * @param lname - last name of the user.
	 * @param address - address of the user.
	 * @param postcode - postcode of the user.
	 * @param phonenumber - phonenumber of the user.
	 * @param index - index of the image
	 */
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
		this.artForSale = new ArrayList<>();
		this.placedBids = new ArrayList<>();
		this.avatarIndex = index;
		this.wonBids = new ArrayList<>();
		
		if (index < 100) {
			try {
				this.image = new Image(new FileInputStream("avatars/avatar" + avatarIndex + ".png"));
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		} else {
			try {
				this.image = new Image(new FileInputStream("customAvatars/" + username + ".png"));
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		}

	}

	/**
	 * Check if a custom image is being used.
	 * 
	 * @return true if a custom image is being used or false otherwise
	 */
	public boolean isCustomImage() {
		return customImage;
	}

	/**
	 * Checks if a custom image is being used first. If no custom image is being
	 * used then it tries to find a default avatar and sets that as the associated
	 * image.
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
	 * Method used to display an image inside an ImageView.
	 * 
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
	 * 
	 * @return Image - image.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Method to get the user's username.
	 * 
	 * @return String - username.
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Method to get the user's first name.
	 * 
	 * @return String - firstName.
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Method to get the user's last name.
	 * 
	 * @return String - lastName.
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Method to get the user's full name.
	 * 
	 * @return String - fullName.
	 */
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	/**
	 * Method to get the user's address.
	 * 
	 * @return String - address.
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Method to get the user's postcode.
	 * 
	 * @return String - postcode.
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * Method to get the user's phone number.
	 * 
	 * @return long - phonenumber.
	 */
	public long getPhonenumber() {
		return this.phonenumber;
	}

	/**
	 * Method to set the user's username.
	 * 
	 * @param username - username of the user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Method to set the user's first name.
	 * 
	 * @param fname
	 *            - firstName.
	 */
	public void setFirstName(String fname) {
		this.firstName = fname;
	}

	/**
	 * Method to set the user's last name.
	 * 
	 * @param lname last name to be set
	 */
	public void setLastName(String lname) {
		this.lastName = lname;
	}

	/**
	 * Method to set the user's address.
	 * 
	 * @param address address to be set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Method to set the user's postcode.
	 * 
	 * @param postcode postcode to be set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * Method to set the users phone number.
	 * 
	 * @param phonenumber phone number to be set
	 */
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * Method to add a user to this user's favourites.
	 * 
	 * @param u
	 *            - User.
	 */
	public void addUserToFavourites(User u) {
		this.favouriteUsers.add(u);
	}

	/**
	 * Method to remove a user from this user's favourites. Tries to find the user
	 * that should be removed but if that user cannot be found then an exception is
	 * caught and handled.
	 * 
	 * @param u-
	 *            User.
	 */
	public void removeUserFromFavourites(User u) {
		try {
			this.favouriteUsers.remove(u);
			Writer.removeFromFavourites(this, u);
		} catch (NullPointerException e) {
			throw new NullPointerException("Error, user not found in favourites.");
		}
	}

	/**
	 * Method to get this user's favourites.
	 * 
	 * @return list of favourite users.
	 */
	public ArrayList<User> getFavouriteUsers() {
		return this.favouriteUsers;
	}

	/**
	 * Method to get a string containing the usernames of this user's favourite
	 * users.
	 * 
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
	 * 
	 * @return String - textFileOutput.
	 */
	public String getTextFileOutput() {
		String output = this.username + "," + this.firstName + "," + this.lastName + "," + this.address + ","
				+ this.phonenumber + "," + this.avatarIndex + "," + this.postcode;
		return output;
	}

	/**
	 * Method to get a string containing all of the user's information.
	 * 
	 * @return String - output
	 */
	public String toString() {
		String output = this.username + ": \n" + "Name: " + this.getFullName() + "\n" + "Address: " + this.getAddress()
				+ "\n" + "PhoneNumber: " + this.phonenumber;
		return output;
	}

	
	/**
	 * Allows setting of an image to a user.
	 * 
	 * @param image image of the user
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Gets the list of artworks for sale.
	 * 
	 * @return artworks for sale.
	 */
	public ArrayList<Artwork> getArtForSale() {
		return artForSale;
	}


	/**
	 * Method to get the list of artworks sold by the user.
	 * 
	 * @return artworks being sold
	 */
	public ArrayList<Artwork> getArtSold() {
		return artSold;
	}

	
	/**
	 * Method to get a list of artworks won.
	 * 
	 * @return  won auctions
	 */
	public ArrayList<Artwork> getArtWon() {
		return artWon;
	}

	
	/**
	 * Method to set the full name of a user
	 * 
	 * @param fullName
	 *            - first name and last name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	/**
	 * Method to add an artwork to a user's auctions
	 * 
	 * @param artwork artwork object to be added
	 */
	public void addArtwork(Artwork artwork) {
		artForSale.add(artwork);
	}

	/**
	 * Method to get number the artworks that are being sold by the user
	 * 
	 * @return int - how many artworks are in the ArrayList
	 */
	public int getSellingArtworks() {
		return artForSale.size();
	}

	/**
	 * Method to get the bids that a user has placed
	 * 
	 * @return - bids placed by user
	 */
	public ArrayList<Bid> getPlacedBids() {
		return placedBids;
	}


	/**
	 * Method to add a bid to the users placedBids ArrayList
	 * 
	 * @param b
	 *            - the bid the user has made
	 */
	public void addBid(Bid b){
			
		placedBids.add(b);
		}
	

	/**
	 * Method to return the avatar index of a user
	 * 
	 * @return int - the index of the selected avatar
	 */
	public int getAvatarIndex() {
		return avatarIndex;
	}

	/**
	 * Method to set the avatar index of a user
	 * 
	 * @param avatarIndex
	 *            - index of the selected avatar
	 */
	public void setAvatarIndex(int avatarIndex) {
		this.avatarIndex = avatarIndex;
		// resolvePicture();
	}
	
	/**
	 * Method to return the bids that the user has won
	 * @return the won bids
	 */
	public ArrayList<Bid> getWonBids() {
		ArrayList<Bid> wonBids = new ArrayList<>();
		for (Bid b : wonBids) {

			if (b.isWinningBid()) {
				wonBids.add(b);
			}
		}
		return wonBids;
	}
	
	/**
	 * Adds a bid to the ArrayList of winning bids
	 * @param bid - bid the user made
	 */
	public void addToWon(Bid bid) {
		wonBids.add(bid);
	}

}