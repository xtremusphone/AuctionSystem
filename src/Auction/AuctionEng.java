
package Auction;
import java.io.*;
import java.util.*;

public class AuctionEng {
  
    String [] temp;
    String [] ID;
    Double [] bids;
    Double minimum ;
    String bidID,item;
    Stack <String> temp_Bidder = new Stack (); 
    Scanner input = new Scanner (System.in);

public AuctionEng(String item_id){    
    
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

System.out.println("\nWELCOME TO ENGLISH AUCTION ");     
System.out.println("\nThe Rules for this auction : \n"+
"• A maximum bid might be left with the auctioneer, who may bid on behalf of the bidder according to the bidder's instructions.\n" +
"• The auction ends when no participant is willing to bid further, at which point the highest bidder pays their bid.\n" +
"• The auctioneer may set a minimum amount by which the next bid must exceed the current highest bid.\n" +
"• The current highest bid is always available to potential bidders. \n"+
"• When the bidding starts new bidders can join [Make Sure Bidder have their ID first]" );
                item = item_id;
                manual(item);
} 
   
private void manual (String item){
   
     ArrayList <String> idList = new ArrayList <>();
     ArrayList <Double> bidsList = new ArrayList <>();
     BidList list = new BidList(this.bidID);
     this.minimum = list.getBasePrice();
     
    System.out.println("\nStarting price for item "+list.getItemID()+" : "+this.minimum);
    System.out.print("\nStart bidding ? [press N/n to stop ] >> ");
    //input.nextLine();
    String user =  input.nextLine();
      
    if(user.equalsIgnoreCase("n")){
        System.out.println("Bidding cancelled");
        System.out.println("\n\n\n\n");
        MainMenu menu = new MainMenu();
        return;
    }
  

    while(!user.equalsIgnoreCase("N")){
        
        System.out.print("\nEnter ID >> ");
        String id = input.nextLine();
        User an = new User(id);
        while(!an.userExist()){
             System.out.print("User does not exist. Please re-enter ID >> ");
             id = input.next();
             an = new User (id);
        }
        id = id.toUpperCase();
        idList.add(id);
        if(!temp_Bidder.contains(id)){
            temp_Bidder.add(id);
        }
        
        System.out.print("Enter Bid >> ");
        Double bid = input.nextDouble();
        while(bid<=this.minimum){
            System.out.println("Your Bid must be more than "+this.minimum);
            System.out.print("Enter new Bid >> ");
            bid = input.nextDouble();
        }
        bidsList.add(bid);
        this.minimum = bid;
        input.nextLine();
        
    System.out.print("\nContinue Bidding ? (Press [N/n] to stop ) >> ");
    user =  input.nextLine();
    
    }

    writeFile(bidsList,idList,item);
    read(item);
    Operation();
    BidderStatus();
    BidList test = new BidList(this.bidID);
    test.deleteBid(test.getItemID());
    promptEnterKey();
} // method to handle all the workflow of the English bid

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
     System.out.println(e); }
} //write a txt file after the bidding is done to store all the bids
       
private void read(String file){

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
 } // read the existed txt file to get the bid and bidder information
 
private void Operation(){
   
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
     System.out.println("List of Bidder : "+temp_Bidder.toString());
     System.out.println("Frequency of bidding : "+ID.length);
     System.out.println("Total Winning Bid : "+bids[notify]);
     System.out.println("\n---------------------");
     System.out.println("Winner Information");
     System.out.println("---------------------");
     System.out.println("Bidder ID : "+ID[notify]);
     System.out.println("Bidder Name : "+ab.getFirstName().toUpperCase()+" "+ab.getLastName().toUpperCase());
       
     Update(item,ID[notify],bids[notify]);

 }  // use to determine the end result of bidding 
 
private void BidderStatus(){
     System.out.println("\n-------------------------------------");
     System.out.println("Bidder Status      Bidding Frequency");
     System.out.println("-------------------------------------");
     System.out.println("Newbie                     1-2       ");
     System.out.println("Intermediate               3-5       ");
     System.out.println("Pro                        5-∞       ");
     System.out.println("-------------------------------------"); 
     
     List<String> list = Arrays.asList(ID);
     Set<String> count = new HashSet <>(list);

     for(String word : count){
         
         if(Collections.frequency(list, word)==0){
              System.out.println("Bidder ID "+word+" Status : None"+" with "+Collections.frequency(list, word)+" bid[s]");
         }
         else if(Collections.frequency(list, word)>=1 && Collections.frequency(list, word) <=2 ){
               System.out.println("Bidder ID "+word+" Status : Newbie"+" with "+Collections.frequency(list, word)+" bid[s]");
         }
         else if(Collections.frequency(list, word)>=3 && Collections.frequency(list, word) <=5 ){
               System.out.println("Bidder ID "+word+" Status : Intermediate"+" with "+Collections.frequency(list, word)+" bid[s]");
         }
         else{
               System.out.println("Bidder ID "+word+" Status : Pro"+" with "+Collections.frequency(list, word)+" bid[s]");
         } 
     }
     
     
     
 } // Operate the bidder status

private void Update(String item,String name, Double price){
    
     
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
} // Update information of bidding in the text file

private void promptEnterKey(){
    
    System.out.println("\n\n\n\nPress [Enter] to continue..... ");
    Scanner scn = new Scanner (System.in);
    scn.nextLine();
     System.out.println("\n\n\n\n\n\n");
    MainMenu main = new MainMenu ();
    
} //method use to prompt user to enter

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