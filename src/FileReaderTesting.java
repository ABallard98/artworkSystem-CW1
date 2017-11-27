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
        ArrayList<Sculpture> sculptures = new ArrayList<>();
        ArrayList<Painting> paintings = new ArrayList<>();

        try{
            users = FileReader.readUserFile();
            for(User u : users){
               System.out.println(u.getUsername() + " was found and added to the system");
            }

            System.out.println("\n");

            paintings = FileReader.readPaintingFile();
            for(Painting p : paintings){
                System.out.println(p.getTitle() + " was found and added to the system");
            }

            System.out.println("\n");

            sculptures = FileReader.readSculptureFile();
            for(Sculpture s : sculptures){
                System.out.println(s.getTitle() + " was found and added to the system");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("error, file not found");
        }





    }

}
