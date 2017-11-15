import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by ayden on 10/11/2017.
 */

import java.util.*;
import java.io.*;

public class FileReaderTesting {

    public static void main (String[] args){

        ArrayList<User> users = new ArrayList<>();
        ArrayList<Bid> bids = new ArrayList<>();
        ArrayList<Sculpture> scluptures = new ArrayList<>();
        ArrayList<Painting> paintings = new ArrayList<>();

        try{
           users = FileReader.readUserFile();
           for(User u : users){
               System.out.println(u.getUsername() + " was found and added to the system");
           }
        }
        catch(FileNotFoundException e){
            System.out.println("error, file not found exception");
        }

        System.out.println("\n");

        try{
            paintings = FileReader.readPaintingFile();
            for(Painting p : paintings){
                System.out.println(p.getName() + " was found and added to the system");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("error, file not found exception");
        }





    }

}
