
package Auction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
import java.io.PrintWriter;

public class MegaAuction {

    public static void main(String[] args) throws Exception{

       initialize();
       MainMenu menu = new MainMenu(); 
           
    }
    
    public static void initialize(){
        String DEFAULT_DIRECTORY = "Data";
        File dir = new File(DEFAULT_DIRECTORY);
        if(!dir.exists())
            dir.mkdir();
        dir = new File(DEFAULT_DIRECTORY + "/Bid");
        if(!dir.exists())
            dir.mkdir();
        if(!new File(DEFAULT_DIRECTORY + "/User.txt").exists()){
        try{
            PrintWriter wrt = new PrintWriter(new FileOutputStream(DEFAULT_DIRECTORY + "/User.txt"));
            wrt.printf("%-15s\t%-15s\t%-15s\t%-15s%s","UserID","FirstName","LastName","Password","E-Mail");
            wrt.println();
            wrt.close();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        }
        if(!new File(DEFAULT_DIRECTORY + "/BidList.txt").exists()){
            try{
                PrintWriter wrt = new PrintWriter(new FileOutputStream(DEFAULT_DIRECTORY + "/BidList.txt"));
                wrt.printf("%-15s\t%-15s\t%-15s\t%-15s","ItemID","Item","SellerID","BasePrice");
                wrt.println();
                wrt.close();
            }
            catch(IOException ex){
                System.out.println(ex);
            }
        }
    }
}
