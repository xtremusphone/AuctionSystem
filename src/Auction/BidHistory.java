package Auction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BidHistory {

    private String bidID;
    private final String DEFAULT_LOCATION = "Data/Bid/";
    private ArrayList<String> user = new ArrayList();
    private ArrayList<Double> amount = new ArrayList();
    private String winner,winbid,item;
    
    public BidHistory(){
        
    }
    
    public BidHistory(String ID){
        this.bidID = ID;
        readFile();
    }
    
    public void setID(String ID){
        this.bidID = ID;
    }
    
    public String getID(){
        return this.bidID;
    }
    
    public void readFile(){
        if(this.bidID == null){
            System.out.println("BidID not set, please set BidId...");
            return;
        }
        if(!bidExist()){
            System.out.println("Bid does not exist, please set a new bid ID...");
        }
        try{
            Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_LOCATION + this.bidID + ".txt"));
            scn.nextLine();
            while(scn.hasNextLine()){
                String temp = scn.next();
                if(temp.compareToIgnoreCase("winner") != 0){
                    user.add(temp);
                    amount.add(Double.parseDouble(scn.next()));
                }
                else{
                    this.winner = temp + scn.next() +scn.next();
                    break;
                }
                scn.nextLine();
            }
            scn.nextLine();
            this.winbid = scn.nextLine();
            this.item = scn.nextLine();
            scn.close();
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    
    public boolean bidExist(){
        if(!new File(this.DEFAULT_LOCATION + this.bidID + ".txt").exists()){
            return false;
        }
        else{
            return true;
        }
    }
    
    public ArrayList<String> getUserList(){
        return this.user;
    }
    
    public ArrayList<Double> getAmountList(){
        return this.amount;
    }
    
    public void sortAsName(ArrayList<String> usr,ArrayList<Double> amt){
        String[] temp = usr.toArray(new String[usr.size()]);
        Double[] temp1 = amt.toArray(new Double[amt.size()]);
        for(int i = 0; i < temp.length;i++){
            for(int j = 0; j < temp1.length;j++){
                if(temp[i].compareToIgnoreCase(temp[j]) < 0){
                    String unused = temp[i];
                    temp[i] = temp[j];
                    temp[j] = unused;
                    Double un_used = temp1[i];
                    temp1[i] = temp1[j];
                    temp1[j] = un_used;
                }
            }
        }
        System.out.println("UserID\tBid");
        int count = 0;
        for(String x:temp){
            System.out.println(x+"\t"+temp1[count]);
            ++count;
        }
        System.out.println("\n\n" + this.winner);
        System.out.println(this.winbid);
        System.out.println(this.item);
    }
    
    
    public void sortDesName(ArrayList<String> usr,ArrayList<Double> amt){
        String[] temp = usr.toArray(new String[usr.size()]);
        Double[] temp1 = amt.toArray(new Double[amt.size()]);
        for(int i = 0; i < temp.length;i++){
            for(int j = 0; j < temp1.length;j++){
                if(temp[i].compareToIgnoreCase(temp[j]) < 0){
                    String unused = temp[i];
                    temp[i] = temp[j];
                    temp[j] = unused;
                    Double un_used = temp1[i];
                    temp1[i] = temp1[j];
                    temp1[j] = un_used;
                }
            }
        }
        System.out.println("UserID\tBid");
        int count = 0;
        for(String x:temp){
            System.out.println(x+"\t"+temp1[count]);
            ++count;
        }
        System.out.println("\n\n" + this.winner);
        System.out.println(this.winbid);
        System.out.println(this.item);
    }
    
    public void sortAsAmount(ArrayList<String> usr,ArrayList<Double> amt){
        String[] temp = usr.toArray(new String[usr.size()]);
        Double[] temp1 = amt.toArray(new Double[amt.size()]);
        for(int i = 0; i < temp.length;i++){
            for(int j = 0; j < temp1.length;j++){
                if(temp1[i] < temp1[j]){
                    String unused = temp[i];
                    temp[i] = temp[j];
                    temp[j] = unused;
                    Double un_used = temp1[i];
                    temp1[i] = temp1[j];
                    temp1[j] = un_used;
                }
            }
        }
        System.out.println("\nUserID\tBid");
        int count = 0;
        for(String x:temp){
            System.out.println(x+"\t"+temp1[count]);
            ++count;
        }
        System.out.println("\n\n" + this.winner);
        System.out.println(this.winbid);
        System.out.println(this.item);
    }
    
    public void sortDesAmount(ArrayList<String> usr,ArrayList<Double> amt){
        String[] temp = usr.toArray(new String[usr.size()]);
        Double[] temp1 = amt.toArray(new Double[amt.size()]);
        for(int i = 0; i < temp.length;i++){
            for(int j = 0; j < temp1.length;j++){
                if(temp1[i] > temp1[j]){
                    String unused = temp[i];
                    temp[i] = temp[j];
                    temp[j] = unused;
                    Double un_used = temp1[i];
                    temp1[i] = temp1[j];
                    temp1[j] = un_used;
                }
            }
        }
        System.out.println("\nUserID\tBid");
        int count = 0;
        for(String x:temp){
            System.out.println(x+"\t"+temp1[count]);
            ++count;
        }
        System.out.println("\n\n" + this.winner);
        System.out.println(this.winbid);
        System.out.println(this.item);
    }
    
    public void print(){
        String[] temp = this.user.toArray(new String[user.size()]);
        Double[] temp1 = this.amount.toArray(new Double[user.size()]);
        int count = 0;
        System.out.println("\nUserID\tBid");
        for(String x:temp){
            System.out.println(x + "\t" + temp1[count]);
            ++count;
        }
        System.out.println("\n\n" + this.winner);
        System.out.println(this.winbid);
        System.out.println(this.item);
    }
}
