package Auction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    
    private final String DEFAULT_LOCATION = "Data/User.txt";
    private User current_session;
    
    public Login(){
        getLogIn();
    }
    
    public boolean getLogIn(){
        boolean loggedIn = false;
        Scanner scn = new Scanner(System.in);
        System.out.println("------------Account Login Menu------------");
        System.out.println("Enter your ID : ");
        String temp = scn.nextLine();
        User log = new User(temp);
        while(!log.userExist()){
            System.out.println("Invalid login ID, enter Y to retry or R to register : ");
            String input = scn.next();
            if(input.equalsIgnoreCase("Y")){
                System.out.println("Please re-enter your ID : ");
                log = new User(scn.next());
            }
            else if(input.equalsIgnoreCase("R")){
                log = createAcc();
                loggedIn = true;
            }
            else
                System.out.println("\nPlease re-enter Y or R!");
        }
        if(loggedIn == false){
            System.out.println("Enter your password : ");
            while(!log.isPassword(scn.next())){
                System.out.println("Please re-enter your password : ");
            }
            this.current_session = log;
        }
        return true;
    }
    
    public User getCurrentUser(){
        return this.current_session;
    }
    
    //need to be fixed due to new changes
    //fixed at 7/5/2016 @ 12:28
    //changes made @12:52
    public void deleteAcc(){
        if(!new File(this.DEFAULT_LOCATION).exists()){
            System.out.println("Error file 'User.txt' is not found!");
            return;
        }
        if(current_session == null){
            System.out.println("Error, no account were logged in!");
            return;
        }
        try{
            Scanner scn =  new Scanner(new FileInputStream(this.DEFAULT_LOCATION));
            PrintWriter wrt = new PrintWriter(new FileOutputStream("Data/User_temp.txt"));
            while(scn.hasNextLine()){
                String temp = scn.nextLine();
                if(!temp.contains(this.current_session.getUserID()))
                    wrt.println(temp);
            }
            wrt.close();
            scn.close();
            File dir = new File(this.DEFAULT_LOCATION);
            dir.delete();
            File temps = new File("Data/User_temp.txt");
            temps.renameTo(new File(this.DEFAULT_LOCATION));
        }
        catch(IOException ex){
            System.out.println("File 'User.txt' can't be access");
        }
    }
    
    //need to be fixed due to new changes
    //fixed at 7/5/2015 @ 12:20
    public User createAcc(){
        Scanner scn = new Scanner(System.in);
        User create = new User();
        System.out.println("------------Account Creation Form------------");
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
            wrt.printf("%-15s\t%-15s\t%-15s\t%-15s\t%s",create.getUserID(),create.getFirstName(),create.getLastName(),create.getPassword(),create.getEmail());
            wrt.println();
            wrt.close();
            this.current_session = create;
            System.out.println("");
            System.out.println(".\n.\n.\nYour ID is : " + create.getUserID());
            System.out.println("Please remember your ID as logging in to your account requires your ID...");
            System.out.println("Writing file at : " + new File(this.DEFAULT_LOCATION).getAbsolutePath());
            return create;
        }
        catch(IOException e){
            System.out.println("File 'User.txt' can't be access");
        }
        scn.close();
        return null;
    }
    
    public void editDetails(){
        if(this.current_session == null)
            return;
        User temp = this.current_session;
        deleteAcc();
        Scanner scn = new Scanner(System.in);
        char cont = ' ';
        while(cont != 'n' && cont != 'N'){
            System.out.println("Which details you want to change : ");
            System.out.println("1.Name");
            System.out.println("2.Password");
            System.out.println("3.E-mail \n>>");
            int input = scn.nextInt();
            while(input > 3 && input < 1){
                System.out.print("\nSelection is not in list, please re-enter : ");
                input = scn.nextInt();
            }
            switch(input){
                case 1:
                    System.out.println("Please enter your name : ");
                    temp.setFirstName(scn.next());
                    temp.setLastName(scn.next());
                    break;
                case 2:
                    System.out.println("Please enter your new password : ");
                    temp.setPassword(scn.next());
                    break;
                case 3:
                    System.out.println("Please enter your e-mail : ");
                    temp.setEmail(scn.next());
                    break;
            }
            System.out.println("Do you want to change more [enter n to stop]?");
            scn.nextLine();
            String in = scn.nextLine();
            cont = (char)in.charAt(0);
        }
        try{
            PrintWriter wrt = new PrintWriter(new FileOutputStream(this.DEFAULT_LOCATION,true));
            wrt.printf("%-15s\t%-15s\t%-15s\t%-15s\t%s",temp.getUserID(),temp.getFirstName(),temp.getLastName(),temp.getPassword(),temp.getEmail());
            wrt.println();
            wrt.close();
            this.current_session = temp;
            System.out.println("Your ID remains the same, which is " + temp.getUserID() + " ,do not forget..");
            System.out.println("Writing file at : " + new File(this.DEFAULT_LOCATION).getAbsolutePath());
        }
        catch(IOException e){
            System.out.println("File 'User.txt' can't be access");
        }        
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
