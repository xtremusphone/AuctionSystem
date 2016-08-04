package Auction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateAcc {

    private final String DEFAULT_LOCATION = "Data/User.txt";
    
    public CreateAcc(){
    }
    
    public User createAccount(){
        System.out.println("------------Account Creation Form------------");
        Scanner scn = new Scanner(System.in);
        User create = new User();
        System.out.println("Enter your username : ");
        String tem = scn.next();
        while(userExist(tem)){
            System.out.println("UserID already exist, please re-enter : ");
            tem = scn.next();
        }
        create.setUserID(tem);
        System.out.println("Enter your name (must have first and last name): ");
        create.setFirstName(scn.next());
        create.setLastName(scn.next());
        System.out.println("Enter your password : ");
        create.setPassword(scn.next());
        System.out.println("Enter your e-mail : ");
        create.setEmail(scn.next());
        try{
            PrintWriter wrt = new PrintWriter(new FileOutputStream(this.DEFAULT_LOCATION,true));
            wrt.printf("%-15s\t%-15s\t%-15s\t%-15s%s",create.getUserID(),create.getFirstName(),create.getLastName(),create.getPassword(),create.getEmail());
            wrt.println();
            wrt.close();
            System.out.println("");
            System.out.println(".\n.\n.\nYour ID is : " + create.getUserID());
            System.out.println("Please remember your ID as logging in to your account requires your ID...");
            System.out.println("Writing file at : " + new File(this.DEFAULT_LOCATION).getAbsolutePath());
            return create;
        }
        catch(IOException e){
            System.out.println("File 'User.txt' can't be access");
        }
        return null;
    }
    
    private boolean userExist(String usrname){
        ArrayList<String> populate = new ArrayList();
        try{
            Scanner scn = new Scanner(new FileInputStream(this.DEFAULT_LOCATION));
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
        if(populate.contains(usrname)){
            return true;
        }
        else{
            return false;
        }
    }
}
