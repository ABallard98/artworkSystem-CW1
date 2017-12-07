

import java.io.IOException;
import java.util.*;

/**
 * @author Marwan
 * @date 08/12/2017
 */
public class UserTesting {

    public static void main (String[] args){

        User testUser = new User("aballard","ayden","ballard",
                "7 trethellan hill","tr71qa",123456);

        User testUser2 = new User("lucarimew","taylor","stevens",
                "26 beechwood road","sa20jd",118118);

        System.out.println("Attempting to create a text file for " + testUser.getUsername());
        try{
            Writer.writeUserFile(testUser);
            System.out.println("Success!");
        }
        catch(IOException e){
            System.out.println("Error, creating file for " + testUser.getUsername());
        }

        System.out.println();
        System.out.println("Attempting to create a text file for " + testUser2.getUsername());
        try{
        	Writer.writeUserFile(testUser2);
            System.out.println("Success!");
        }
        catch(IOException e){
            System.out.println("Error, creating file for " + testUser2.getUsername());
        }




    }

}
