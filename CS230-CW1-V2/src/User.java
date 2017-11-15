/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;

public class User
{

    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String postcode;
    private long phonenumber;

    private ArrayList <User> favouriteUsers;

    public User(String username, String fname, String lname, String address, String postcode, long phonenumber){
        this.username = username;
        this.firstName = fname;
        this.lastName = lname;
        this.address = address;
        this.postcode = postcode;
        this.phonenumber = phonenumber;

        this.favouriteUsers = new ArrayList<>();
    }

    public String getUsername(){
        return this.username;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    public String getAddress(){
        return this.address;
    }

    public String getPostcode(){
        return this.postcode;
    }

    public long getPhonenumber(){
        return this.phonenumber;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setFirstName(String fname){
        this.firstName = fname;
    }

    public void setLastName(String lname){
        this.lastName = lname;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPostcode(String postcode){
        this.postcode = postcode;
    }

    public void setPhonenumber(long phonenumber){
        this.phonenumber = phonenumber;
    }


    public void addUserToFavourites(User u){
        if(u != this){
            this.favouriteUsers.add(u);
        }
    }

    public void removeUserFromFavourites(User u){
        try{
            this.favouriteUsers.remove(u);
        }
        catch(NullPointerException e){
            throw new NullPointerException("Error, user not found in favourites");
        }
    }

    public ArrayList<User> getFavouriteUsers(){
        return this.favouriteUsers;
    }

    public String getFavouritesString(){
        String output = "";
        for(User e : favouriteUsers){
            output = e.getUsername() + " " + output;
        }
        return output;
    }

    public String getTextFileOutput(){
        String output = this.username + "," + this.firstName + "," + this.lastName +
                "," + this.address + "," + this.phonenumber + "," + this.postcode;
        return output;
    }

    public String toString(){
        String output = this.username + ": \n" +
                "Name: " + this.getFullName() + "\n" +
                "Address: " + this.getAddress() + "\n" +
                "PhoneNumber: " + this.phonenumber + "\n";
        return output;
    }









}
