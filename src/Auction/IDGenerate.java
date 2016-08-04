package Auction;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class IDGenerate {

    private Random rndm = new Random();
    private final String DEFAULT_DIRECTORY = "Data/";
    
    public IDGenerate(){
        //This class is used to assign the ID for each data
    }
    
    //U
    public String genIDUser(){
        ArrayList<String> populate = new ArrayList();
        try{
            Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_DIRECTORY + "User.txt"));
            scn.nextLine();
            while(scn.hasNextLine()){
                    populate.add(scn.next());
                    scn.nextLine();
            }
            scn.close();
        }
        catch(IOException ex){
            System.out.println("Error file 'User.txt' is not found!");
        }
        if(populate.size() >= 99){
            System.out.println("Error, maximum number of user reached!");
            return null;
        }
        String temp = "U";
        temp += rndm.nextInt(10);
        temp += rndm.nextInt(10);
        while(populate.contains(temp)){
            temp = "U";
            temp += rndm.nextInt(10);
            temp += rndm.nextInt(10);   
        }
        return temp;
    }
    
    //E
    public String genIDBidEng(){
        ArrayList<String> populate = new ArrayList();
        try{
            Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_DIRECTORY + "BidList.txt"));
            scn.nextLine();
            while(scn.hasNextLine()){
                populate.add(scn.next());
                scn.nextLine();
            }
            scn.close();
        }
        catch(IOException ex){
            System.out.println("Error file 'BidList.txt' is not found!");
        }
        
        String temp = "E";
        temp += rndm.nextInt(10);
        temp += rndm.nextInt(10);
        
        while(populate.contains(temp)){
            temp = "E";
            temp += rndm.nextInt(10);
            temp += rndm.nextInt(10);
        }
        
        return temp;
    }
    
    
    //J
    public String genIDBidJap(){
        ArrayList<String> populate = new ArrayList();
        try{
            Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_DIRECTORY + "BidList.txt"));
            scn.nextLine();
            while(scn.hasNextLine()){
                populate.add(scn.next());
                scn.nextLine();
            }
            scn.close();
        }
        catch(IOException ex){
            System.out.println("Error file 'BidList.txt' is not found!");
        }
        
        String temp = "J";
        temp += rndm.nextInt(10);
        temp += rndm.nextInt(10);
        
        while(populate.contains(temp)){
            temp = "J";
            temp += rndm.nextInt(10);
            temp += rndm.nextInt(10);
        }
        
        return temp;        
    }
    
    
    //B
    public String genIDBidBlind(){
        ArrayList<String> populate = new ArrayList();
        try{
            Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_DIRECTORY + "BidList.txt"));
            scn.nextLine();
            while(scn.hasNextLine()){
                populate.add(scn.next());
                scn.nextLine();
            }
            scn.close();
        }
        catch(IOException ex){
            System.out.println("Error file 'BidList.txt' is not found!");
        }
        
        String temp = "B";
        temp += rndm.nextInt(10);
        temp += rndm.nextInt(10);
        
        while(populate.contains(temp)){
            temp = "B";
            temp += rndm.nextInt(10);
            temp += rndm.nextInt(10);
        }
        
        return temp;        
    }
    
    //V
    public String genIDVick(){
        ArrayList<String> populate = new ArrayList();
        try{
            Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_DIRECTORY + "BidList.txt"));
            scn.nextLine();
            while(scn.hasNextLine()){
                populate.add(scn.next());
                scn.nextLine();
            }
            scn.close();
        }
        catch(IOException ex){
            System.out.println("Error file 'BidList.txt' is not found!");
        }
        
        String temp = "V";
        temp += rndm.nextInt(10);
        temp += rndm.nextInt(10);
        
        while(populate.contains(temp)){
            temp = "V";
            temp += rndm.nextInt(10);
            temp += rndm.nextInt(10);
        }
        
        return temp;      
    }
    
    //R
    public String genIDReserve(){
        ArrayList<String> populate = new ArrayList();
        try{
            try (Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_DIRECTORY + "BidList.txt"))) {
                scn.nextLine();
                while(scn.hasNextLine()){
                    populate.add(scn.next());
                    scn.nextLine();
                }
            }
        }
        catch(IOException ex){
            System.out.println("Error file 'BidList.txt' is not found!");
        }
        
        String temp = "R";
        temp += rndm.nextInt(10);
        temp += rndm.nextInt(10);
        
        while(populate.contains(temp)){
            temp = "R";
            temp += rndm.nextInt(10);
            temp += rndm.nextInt(10);
        }
        
        return temp;       
    }
}
