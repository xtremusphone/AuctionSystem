
package Auction;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class MainMenu {
    
    private User current_user = new User();
    
    public MainMenu(){Menu();}
    
    public void Menu()
    {
        System.out.println("MEGA AUCTION SYSTEM!!!!");
        System.out.println("----------------------------------------------------");
        System.out.println("WELCOME TO THE MAIN MENU");
        System.out.println("----------------------------------------------------");
        System.out.println("Please enter the menu by selecting menu number...");
        System.out.println("1. Seller Registration");
        System.out.println("2. Bidder Registration");
        System.out.println("3. Login");
        System.out.println("4. Start auction");
        System.out.println("5. History");
        System.out.println("6. Exit");
        System.out.print(">> ");
        Scanner scan = new Scanner(System.in);
        int menu = scan.nextInt();
        
        switch(menu)
        {
            case 1 :
                CreateAcc act = new CreateAcc();
                BidList bid = new BidList();
                bid.createBid(act.createAccount());
                Menu();
                break;
            case 2 :
                CreateAcc acc = new CreateAcc();
                acc.createAccount();
                Menu();
                break;
            case 3 :
                Login temp = new Login();
                int choice = 0;
                User use = temp.getCurrentUser();
                System.out.println("WELCOME "+use.getFirstName());
                while(choice != 4)
                {
                    System.out.println("Enter your option : ");
                    System.out.println("1. Edit details");
                    System.out.println("2. Delete account");
                    System.out.println("3. Create new auction");
                    System.out.println("4. Back to main menu");
                    System.out.print(">> ");
                    choice = scan.nextInt();
                    if(choice == 1)
                        temp.editDetails();
                    if(choice == 2)
                    {
                        temp.deleteAcc();
                        break;
                    }   
                    if(choice == 3)
                    {
                        BidList bida = new BidList();
                        bida.createBid(use);
                    }
                    else if(choice > 4 && choice < 1)
                        System.out.println("Invalid option");                   
                }
                Menu();
                break;   
            case 4 :
                System.out.println("List of bids : ");
                Scanner scn = new Scanner(System.in);
                System.out.printf("%-15s\t%-15s\t%-15s\t\n","ItemID","Item","SellerID");
                ArrayList<BidList> tst = new BidList().populateBid();
                for(BidList x: tst){
                    System.out.println(x.getItemID() + "\t\t" + x.getItem() + "\t\t" + x.getSellerID());
                }
                System.out.print("\nEnter itemID to start auction : ");
                String id = scn.nextLine();
                id = id.toUpperCase();
                while(!new BidList().bidExist(id)){
                    System.out.print("Auction does not exist, please re-enter : ");
                    id = scn.nextLine();
                    id = id.toUpperCase();
                }
                
                char type = id.charAt(0);
                BidList start = new BidList(id);
                double base;
                switch(type){
                    case'E':
                        AuctionEng eng = new AuctionEng(id); 
                        break;
                    case'J':
                        Jpn_Auction jpn = new Jpn_Auction(id);
                        break;
                    case'R':
                        AuctionReserve res = new AuctionReserve (id);
                        break;
                    case'B':
                        base = new BidList(id).getBasePrice();
                        BlindAuction blind = new BlindAuction(id, base);
                        break;
                    case'V':
                        base = new BidList(id).getBasePrice();
                        VickreyAuction vick = new VickreyAuction(id, base);
                        break;
                    
                }
                break;
            case 5 :
                System.out.println("Auction ID list : ");
                String dir = "Data/Bid";
                File fle = new File(dir);
                for(File x: fle.listFiles()){
                    String name = x.getName();
                    System.out.println(name.substring(0,name.length()-4));
                }
                System.out.print("Enter the Auction ID : ");
                Scanner sca = new Scanner(System.in);
                String hisID = sca.next();
                BidHistory hist = new BidHistory(hisID.toUpperCase());
                hist.print();
                System.out.println("\nSort by : ");
                System.out.println("1. Ascending amount");
                System.out.println("2. Descending amount");
                System.out.println("3. Name ascending");
                System.out.println("4. Name descending");
                System.out.print(">> ");
                int c = sca.nextInt();
                switch(c)
                {
                    case 1 :
                        hist.sortAsAmount(hist.getUserList(), hist.getAmountList());
                        break;
                    case 2 :
                        hist.sortDesAmount(hist.getUserList(), hist.getAmountList());
                        break;
                    case 3 :
                        hist.sortAsName(hist.getUserList(), hist.getAmountList());
                        break;
                    case 4 :
                        hist.sortDesName(hist.getUserList(), hist.getAmountList());
                        break;
                    default:
                        System.out.println("Error 404 not found!");
                }
                System.out.println("");
                promptEnterKey();
                break;
            case 6 :
                System.exit(0);
                break;
            
        }
    }
    
    public static void AuctionReg()
    {
        try
        {
            PrintWriter reg = new PrintWriter(new FileOutputStream("Auctioner_List.txt"));
            
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public static void BiddingReg()
    {
        try
        {
            PrintWriter reg = new PrintWriter(new FileOutputStream("Bidder_List.txt"));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    
    public void promptEnterKey()
    {
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        MainMenu main =new MainMenu();
    }
}
