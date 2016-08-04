
package Auction;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


public class VickreyAuction {
    
    private final String id;//id of item
    private final double min;//min amount set by auctioner
    ArrayList bidder = new ArrayList();
    LinkedList bidAmount = new LinkedList();//create linked list to store bid amount
    
    
    public VickreyAuction(String id, double min)
    {
        this.id = id;
        this.min = min;
        
        BidList bida = new BidList(id);
        User seller = new User(bida.getSellerID());
        System.out.println("Enter seller password : ");
        Scanner sell = new Scanner(System.in);
        String password = sell.next();
        while(!seller.isPassword(password))
        {
            System.out.print("Wrong Seller Password! Enter again : ");
            password = sell.next();
        }
        bidder();
        display();
        biddingTime();
    }
    
     public void bidder()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("Bidder Registration");
        System.out.println("---------------------------------------------");
        boolean sentinel = true;
        String bidId;
        while(sentinel)
        {
            System.out.print("Enter username of bidder(enter 0 to stop) >> ");
            bidId = scan.next();
            if(bidId.equals("0"))
            {
                sentinel = false;
                break;
            }
            User use = new User(bidId);
            while(!use.userExist())
            {
                System.out.print("Bidder not exist, please enter again(enter 0 to stop) >> ");
                bidId = scan.next();
                use = new User(bidId);
                if(bidId.equals("0"))
                {
                    sentinel = false;
                    break;
                }
            }
            if(sentinel == false)
                break;
            bidder.add(bidId);
        }
    }
    
    public int participant()
    {
        return bidder.size();
    }
    
    public void biddingTime()
    {
        //instruction
        
        Scanner scan = new Scanner(System.in);
        try
        {
            PrintWriter bid = new PrintWriter(new FileOutputStream("Data/Bid/"+id+".txt"));
            BidList item = new BidList(id);
            bid.println("UserID"+"\t"+"Bids");
            for(int i = 0 ; i < participant() ; i++)//all participant in bidding must enter value
            {
                System.out.print(bidder.get(i)+" enter your amount >> ");
                int a = scan.nextInt();
                while(a < min)
                {
                    System.out.println("Bidding must more than minimum ("+min+"). Please enter again");
                    System.out.print(bidder.get(i)+", enter your amount >> ");
                    a = scan.nextInt();
                }
                bidAmount.add(a);
                
                bid.write(bidder.get(i)+"\t"+a);
                bid.println();
                for (int m = 0; m < 50; m++) 
                    System.out.println(); 
            }
            bid.println();
            int secmax = secondhighestBid(bidAmount);
            int winner = highestBid(bidAmount);
            int id = 0;//id for the winner
            id = bidAmount.indexOf(winner);
            bid.println("Winner : "+bidder.get(id));
            bid.println("Price Sold(second highest bid) : "+secmax);
            bid.println("Item : "+item.getItem());
            System.out.println("Winner is bidder "+bidder.get(id));
            System.out.println("Price Sold(second highest bid) : "+secmax);
            System.out.println();
            System.out.println("Bidding Summary : ");
            System.out.println("Item : "+item.getItem());
            System.out.println("ID\tBidding Amount(RM)");
            for(int i = 0 ; i < participant() ; i++)
            {
                System.out.println(bidder.get(i)+"\t"+bidAmount.get(i));
            }
            
            bid.close();
             BidList test = new BidList(this.id);
            test.deleteBid(test.getItemID());
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        promptEnterKey();
    }
    
    public int secondhighestBid(LinkedList e)
    {
        int[] temp = new int[e.size()];
        for(int i = 0 ; i < e.size() ; i++)
            temp[i] = (int) e.get(i);
        temp = bubbleSort(temp);//sorting array
        return temp[1];
    }
    
    public int highestBid(LinkedList e)
    {
        return (int) Collections.max(e);
    }
    
    public void display()
    {
        BidList item = new BidList(id);
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("All bidder who join this bid must put a bidding price higher than the base price set by seller.");  
        System.out.println("Bidder with highest bidding will win the auction but need to pay the price of the second highest bid.");  
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("WELCOME TO VICKREY AUCTION");
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Vickrey Auction for item "+id+" : "+item.getItem());
        System.out.println("Bidders in this auction : "+participant());
        System.out.println("Base Price : RM "+min);
        System.out.print("ID : ");
        System.out.println(bidder.toString());
        
        System.out.println("AUCTION START!");
    }
    
  /** Bubble sort method */
  public int[] bubbleSort(int[] list) 
  {
    boolean needNextPass = true;
    
    for (int k = 1; k < list.length && needNextPass; k++) 
    {
      // Array may be sorted and next pass not needed
      needNextPass = false;
      for (int i = 0; i < list.length - k; i++) 
      {
        if (list[i] < list[i + 1]) 
        {
          // Swap list[i] with list[i + 1]
          int temp = list[i];
          list[i] = list[i + 1];
          list[i + 1] = temp;
          
          needNextPass = true; // Next pass still needed
        }
      }
    }
    return list;
  }
  
  public void promptEnterKey()
    {
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scan = new Scanner(System.in);
        scan.nextLine();
        MainMenu main =new MainMenu();
    }

 
    
}
