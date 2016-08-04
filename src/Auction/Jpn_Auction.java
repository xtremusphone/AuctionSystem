
package Auction;

import java.io.*;
import java.util.*;


public class Jpn_Auction {
    
    String [] temp;
    String [] ID;
    Double [] bids; 
    Double minimum,increment;
    int round = 1;
    String bidID,item;
    Stack <String> id = new Stack <>();

    Scanner input = new Scanner (System.in);

public Jpn_Auction(String item_id) {
        this.bidID = item_id;
        
        BidList bida = new BidList(this.bidID);
        User seller = new User(bida.getSellerID());
        System.out.println("Enter seller password : ");
        Scanner sell = new Scanner(System.in);
        String password = sell.next();
        while(!seller.isPassword(password))
        {
            System.out.print("Wrong Seller Password! Enter again : ");
            password = sell.next();
        }
        
        System.out.println("\nWELCOME TO JAPANESE AUCTION ");
       
System.out.println("\nThe Rules for this auction : \n"+
"• A maximum bid might be left with the auctioneer, who may bid on behalf of the bidder according to the bidder's instructions.\n" +
"• The auction ends when no participant is willing to bid further, at which point the highest bidder pays their bid.\n" +
"• The auctioneer may set a minimum amount by which the next bid must exceed the current highest bid.\n" +
"• The current highest bid is always available to potential bidders. \n"+
"• When the bidding starts no new bidders can join"                );

                //IDGenerate file = new IDGenerate ();
                item = item_id;
                System.out.print("\nSeller please value of increment >> ");
                this.increment = input.nextDouble();
                System.out.print("\nEnter number of bidder >> ");
                int con = input.nextInt();
                 if(con<=0){
                    System.out.println("Bidding cancelled");
                    System.out.println("\n\n\n\n");
                    MainMenu menu = new MainMenu();
                    return;
                    }
                
                manual(con, item);
           
                
        } 

    
private void manual (int a, String item) {
    
 
     ArrayList <String> idList = new ArrayList <>();
     ArrayList <Double> bidsList = new ArrayList <>();
     ArrayList <String> tempBidder = new ArrayList <>();
     
     
     BidList bid = new BidList(bidID);
     this.minimum = bid.getBasePrice();
     
    for(int i=0;i<a;i++){
        System.out.print("Bidder "+(i+1)+" please enter your ID >> ");
        String user = input.next(); 
        User an = new User(user);
        while(!an.userExist()){
             System.out.print("User does not exist. Please re-enter ID >> ");
             user = input.next();
             an = new User (user);
        }
        user = user.toUpperCase();
        tempBidder.add(user);
        id.add(user);
    }      
        
    while(tempBidder.size()!=1){  
        System.out.println("\nJapanese Bid Round "+round);
        round++;
        System.out.println("Price of item : "+this.minimum);
        for(int i=0;i<tempBidder.size();i++){
        System.out.print("Does bidder "+tempBidder.get(i)+" accept the price ? [Press N to quit/Press any key to continue] >> ");
        input.nextLine();
        String s = input.next();
        int hold = i;
        if(s.equalsIgnoreCase("N")){
            System.out.println("Bidder "+tempBidder.remove(hold)+" quit");
            System.out.println("Number of Bidder left : "+tempBidder.size());
            i--;
            if(tempBidder.size()==1){
              idList.add(tempBidder.get(0));
              bidsList.add(this.minimum);   
              break;
            }
        }else{
              idList.add(tempBidder.get(i));
              bidsList.add(this.minimum);
            }
        }
        this.minimum = this.minimum+this.increment;
    }    
    writeFile(bidsList,idList,item);
    read(item);
    Operation(id);
    BidderStatus();
    BidList test = new BidList(this.bidID);
    test.deleteBid(test.getItemID());
    promptEnterKey();
}
       
private void read(String file) {

        try{
        int line = 0;
        BufferedReader txt = new BufferedReader (new FileReader ("Data\\Bid\\"+file+".txt")) ;
        txt.readLine();
        while(txt.readLine()!=null){
            line++;
        }
      
        temp = new String [line];
        
        Scanner scan = new Scanner (new FileInputStream("Data\\Bid\\"+file+".txt"));
        scan.nextLine();
        for(int i=0;scan.hasNextLine();i++){
            temp[i]=scan.nextLine();
        }
     
        ID = new String [line]; 
        bids = new Double[line];
              
        for(int i=0;i<temp.length;i++){
            String save[] = temp[i].split("\t");
            ID[i]=save[0];
            bids[i]  =Double.parseDouble(save[1]);      
        }
    }catch(FileNotFoundException ex){
     System.out.println(ex);
    }catch(IOException ex){
     System.out.println(ex);
    }   
 }
 
private void Operation(Stack Id){
   
    Double max = 0.0;
    int notify = 0 ;
        for(int i=0;i<bids.length;i++){
            if(bids[i]>max){
                max = bids[i];
                notify = i;
            }
        }
     User ab = new User (ID[notify]);   
     BidList bid = new BidList(bidID);
     System.out.println("\n---------------------");
     System.out.println("BIDDING INFORMATION");
     System.out.println("---------------------");
     System.out.println("Winning item : "+bid.getItem().toUpperCase());
     System.out.println("List of Bidder : "+id.toString());
     System.out.println("Frequency of bidding : "+ID.length);
     System.out.println("Total Winning Bid : "+bids[notify]);
     System.out.println("\n-----------------");
     System.out.println("Winner Information");
     System.out.println("------------------");
     System.out.println("Bidder ID : "+ID[notify]);
     System.out.println("Bidder Name : "+ab.getFirstName().toUpperCase()+" "+ab.getLastName().toUpperCase());
  
      
     Update(item,ID[notify],bids[notify]);
 } 
 
private void BidderStatus(){

     System.out.println("\n-------------------------------------");
     System.out.println("Bidder Status      Bidding Frequency");
     System.out.println("-------------------------------------");
     System.out.println("Newbie                     1-2       ");
     System.out.println("Intermediate               3-5       ");
     System.out.println("Pro                        6-∞       ");
     System.out.println("-------------------------------------"); 
     
     List<String> list = Arrays.asList(ID);
     Set<String> count = new HashSet <>(list);
     for(String word : count){
         if(Collections.frequency(list, word)==0 || Collections.frequency(list, word)<=2){
              System.out.println("Bidder ID "+word+" Status : Newbie"+" with "+Collections.frequency(list, word)+" bid[s]");
         }
         else if(Collections.frequency(list, word)>=3 && Collections.frequency(list, word) <=5 ){
               System.out.println("Bidder ID "+word+" Status : Intermediate"+" with "+Collections.frequency(list, word)+" bid[s]");
         }
         else{
               System.out.println("Bidder ID "+word+" Status : Pro"+" with "+Collections.frequency(list, word)+" bid[s]");
         } 
     }
 }

private void writeFile(ArrayList bid, ArrayList id,String name){
     try{
         try (PrintWriter write = new PrintWriter(new FileOutputStream ("Data\\Bid\\"+name+".txt"))) {
             
             write.println("UserID"+"\t"+"Bids");
             
             for(int i=0;i<id.size();i++){
                 
                 write.println(id.get(i)+"\t"+bid.get(i));
                 
             }
             write.close();
         }       
 }catch (IOException e){
     System.out.println(e);
 }    
}

private void Update(String item,String name , Double price){
      
    BidList list = new BidList(this.bidID);
    
     try{
         try (PrintWriter write = new PrintWriter(new FileOutputStream ("Data\\Bid\\"+item+".txt",true))) {
             
             write.println();
             write.println("Winner : "+name);
             write.println("Total Winning Bid : "+price);
             write.println("Item : "+list.getItem());
             
             write.close();
         }
 }catch (IOException e){
     System.out.println(e); }
}

private void promptEnterKey(){
    
    System.out.println("\n\n\n\nPress [Enter] to continue.....");
    Scanner scn = new Scanner (System.in);
    scn.nextLine();
    System.out.println("\n\n\n\n\n\n");
    MainMenu main = new MainMenu ();
    
}

public String[] bubbleSort (String[]data){
        
        for(int i=1;i<data.length;i++){
            for(int j=0;j<(data.length-i);j++){
                if(data[j].compareTo(data[j+1])>0){
                    String temp = data[j];
                    data[j] = data[j+1];
                    data[j+1] = temp;
                }
            }
        }
        return data;
    }

}
