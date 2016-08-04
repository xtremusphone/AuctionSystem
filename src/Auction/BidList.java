package Auction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BidList {

    private String ItemID,Item,SellerID;
    private double BasePrice;
    private final String DEFAULT_DIRECTORY = "Data/BidList.txt";
    
    public BidList(){
        
    }
    
    public BidList(String id){
        this.ItemID = id;
        bidExist(id);
    }
   
    public void setItemID(String id){
        this.ItemID = id;
    }
    
    public void setItem(String item){
        this.Item = item;
    }
    
    public void setSellerID(String id){
        this.SellerID = id;
    }
    
    public void setBasePrice(double price){
        this.BasePrice = price;
    }
    
    public String getItemID(){
        return this.ItemID;
    }
    
    public String getItem(){
        return this.Item;
    }
    
    public String getSellerID(){
        return this.SellerID;
    }
    
    public double getBasePrice(){
        return this.BasePrice;
    }
    
    public boolean bidExist(String id){
        try{
            Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_DIRECTORY));
            String idtemp = "";
            scn.nextLine();
            while(scn.hasNextLine()){
                idtemp = scn.next();
                if(idtemp.equalsIgnoreCase(id)){
                    this.ItemID = idtemp;
                    this.Item = scn.next();
                    this.SellerID = scn.next();
                    this.BasePrice = scn.nextDouble();
                    scn.close();
                    return true;
                }
                scn.nextLine();
            }
            scn.close();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
        return false;
    }
    
    public BidList createBid(User seller){
        BidList list = new BidList();
        list.setSellerID(seller.getUserID());
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter your item to bid : ");
        list.Item = scn.nextLine();
        System.out.println("Enter your base price : ");
        list.BasePrice = scn.nextDouble();
        System.out.println("Enter the number of bid type you want to choose for your item.");
        System.out.print("1.English Auction\n2.Japanese Auction\n3.Blind Auction\n4.Vikrey Auction\n5.Reserve Auction\n>> ");
        int input = scn.nextInt();
        while(input < 1 && input > 5){
            System.out.println("Please re-enter, it is not in the list >> ");
            input = scn.nextInt();
        }
        IDGenerate id = new IDGenerate();
        switch(input){
            case 1:
                list.ItemID = id.genIDBidEng();
                break;
            case 2:
                list.ItemID = id.genIDBidJap();
                break;
            case 3:
                list.ItemID = id.genIDBidBlind();
                break;
            case 4:
                list.ItemID = id.genIDVick();
                break;
            case 5:
                list.ItemID = id.genIDReserve();
                break;
            default:
                System.out.println("Bid does not exist");
                break;
        }
        System.out.println("Enter the time for your auction(ex. 12.00 - 12.30) >> ");
        String time = scn.next();
        
        try{
            PrintWriter wrt = new PrintWriter(new FileOutputStream(this.DEFAULT_DIRECTORY,true));
            wrt.printf("%-15s\t%-15s\t%-15s\t%-15s",list.getItemID(),list.getItem(),list.getSellerID(),list.getBasePrice());
            wrt.println();
            wrt.close();
            System.out.println("Your bid id is " + list.ItemID + ", you will need to enter this ID to start auction...");
        }
        catch(IOException ex){
            System.out.println("Error file 'BidList.txt' is not found!");
        }
        return list;
    }
    
    public void deleteBid(String bidID){
        try{
            Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_DIRECTORY));
            PrintWriter wrt = new PrintWriter(new FileOutputStream("Data/Bid_Temp.txt"));
            while(scn.hasNextLine()){
                String temp = scn.nextLine();
                if(!temp.contains(bidID)){
                    wrt.println(temp);
                }
            }
            wrt.close();
            scn.close();
            File dir = new File(this.DEFAULT_DIRECTORY);
            dir.delete();
            File rec = new File("Data/Bid_Temp.txt");
            rec.renameTo(new File(this.DEFAULT_DIRECTORY));
        }
        catch(IOException ex){
            System.out.println("Error file 'BidList.txt' is not found!");
        }
    }
    
    public ArrayList<BidList> populateBid(){
        try{
            Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_DIRECTORY));
            ArrayList<BidList> arr = new ArrayList();
            scn.nextLine();
            while(scn.hasNextLine()){
                BidList temp = new BidList();
                temp.ItemID = scn.next();
                temp.Item = scn.next();
                temp.SellerID = scn.next();
                temp.BasePrice = scn.nextDouble();
                arr.add(temp);
                scn.nextLine();
            }
            scn.close();
            return arr;
        }
        catch(IOException ex){
            
        }
        return null;
    }
}
