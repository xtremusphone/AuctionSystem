package Auction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class User {

    private String firstnme,lastnme,userID,password,email;
    private final String DEFAULT_LOCATION = "Data/User.txt";
    
    public User(){
        
    }
    
    public User(String id){
        this.userID = id;
        userExist();
    }
    
    public String getFirstName(){
        return this.firstnme;
    }
    
    public String getLastName(){
        return this.lastnme;
    }
    
    public boolean isPassword(String pass){
        return this.password.equals(pass);
    }
    
    public String getUserID(){
        return this.userID;
    }
    
    public void setFirstName(String name){
        this.firstnme = name;
    }
    
    public void setLastName(String name){
        this.lastnme = name;
    }
    
    public void setPassword(String pass){
        this.password = pass;
    }
    
    public void setUserID(String id){
        this.userID = id;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String item){
        this.email = item;
    }
    
    public boolean userExist(){
        try{
            File dir = new File(DEFAULT_LOCATION);
            Scanner scn = new Scanner(new FileInputStream(dir));
            scn.nextLine();
            while(scn.hasNextLine()){
                String idt = scn.next();
                String frstname = scn.next();
                if(idt.equalsIgnoreCase(this.userID)){
                    setUserID(idt);
                    setFirstName(frstname);
                    setLastName(scn.next());
                    setPassword(scn.next());
                    setEmail(scn.next());
                    scn.close();
                    return true;
                }
                scn.nextLine();
            }
            scn.close();
            return false;
        }
        catch(IOException ex){
            System.out.println("Error file 'User.txt' is not found!");
        }
        return false;
    }
}
