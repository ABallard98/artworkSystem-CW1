import java.io.FileWriter;

/**
 * @author Marwan
 * @date 07/12/2017
 */
public class TestingFavouriteRemoving {

    public static void main(String[] args){

        User testUser = new User("test1","ayden","ballard",
                "7 trethellan hill","tr71qa",123456);

        User testUser2 = new User("test2","taylor","stevens",
                "26 beechwood road","sa20jd",118118);

     /*   testUser.addUserToFavourites(testUser2);
        Writer.addToFavourites(testUser,testUser2);
        System.out.println("favourite added");
        testUser.removeUserFromFavourites(testUser2); */
        
        FileReader.initialize();
        
        User user1 = FileReader.getUser("elmarko");
        
        User user2 = FileReader.getUser("asd");

        if(user1 != null && user2 != null) {
        	System.out.println("Users loaded");
        }
        
        user1.removeUserFromFavourites(user2);
        
        
        System.out.println("favourite removed");



    }



}
