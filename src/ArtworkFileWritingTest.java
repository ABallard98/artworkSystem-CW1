import java.io.*;

/**
 * Created by ayden on 11/11/2017.
 */
public class ArtworkFileWritingTest {

    public static void main (String[] args) throws IOException{
    	User spongeBob = new User("SpongeGod1", "Spongebob", "Squarepants", "bikini bottom", "p1n3ap113", 1111);
    	User dan = new User("Daniel123", "dan", "hart", "123", "4567", 123456);
    	User dan2 = new User("Daniel321", "dan", "hart", "123", "CF7654", 654321);
    	Writer.writeUserFile(dan);
    	Writer.writeUserFile(dan2);
    	spongeBob.setAvatarIndex(5);
    	Writer.writeUserFile(spongeBob);
        //GETTING USERS FOR TESTING
       /* ArrayList<User> users = new ArrayList<>();
        try{
            users = FileReader.readUserFile();
            System.out.println(users.size());
            for(User u : users){
                System.out.println(u.toString() + "\n");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("error, file not found exception");
        }

        User testUser1 = users.get(0);
        User testUser2 = users.get(1);
        User testUser3 = users.get(2);

        //CREATING PAINTINGS FOR FILE WRITING TEST
        Painting painting = new Painting(testUser1, new Date(), "Starry night",
                "Van Gogh",1904,10,199.99,
                20,30);

        Painting painting2 = new Painting(testUser2, new Date(),"The scream","Marckel",
                1679,15,10.99,50,40);

        //ATTEMPTING TO USE FILE WRITER ON PAINTINGS
        System.out.println("Attempting to create files for the paintings");
        ArrayList<Painting> paintings = new ArrayList<>();
        paintings.add(painting);
        paintings.add(painting2);
        try{
            for(Painting p : paintings){
            	Writer.writePaintingFile(p);
                System.out.println("Success!");
            }
        }
        catch(IOException e){
            System.out.println("Error creating artwork file");
        }

        //CREATING SCULPTURES FOR FILE WRITING TEST
        Sculpture sculp = new Sculpture(testUser2,new Date(),"the thinker",
                "Auguste Rodin",1199,20,21000,10,
                100,50,"Marble");

        Sculpture sculp2 = new Sculpture(testUser1,new Date(),"guitar","pablo picasso",
                1800,50,100,100,100,100,
                "Chalk");

        //ATTEMPTING TO USE FILE WRITER ON SCULPTURES
        System.out.println("Attempting to create files for the sculptures");
        ArrayList<Sculpture> sculptures = new ArrayList<>();
        sculptures.add(sculp);
        sculptures.add(sculp2);
        try {
            for (Sculpture s : sculptures) {
            	Writer.writeSculptureFile(s);
                System.out.println("Success!");
            }
        }
        catch(IOException e){
            System.out.println("Error creating sculpture file");
        }

        //MAKING BIDS ON ITEMS
        Bid bid1 = new Bid("Sculpture",testUser2,999999,sculp,new Date());
        Bid bid2 = new Bid("Sculpture",testUser2, 10000, sculp2, new Date());

        //ATTEMPTING TO USE FILE WRITER ON BIDS
        System.out.println("Attempting to create files for the bids");
        ArrayList<Bid> bids = new ArrayList<>();
        bids.add(bid1);
        bids.add(bid2);
        try{
            for(Bid b : bids){
            	Writer.writeBidFile(b);
                System.out.println(b.toString());
            }
        }
        catch(IOException e){
            System.out.println("Error creating bid file");
        }

*/
    }




}
