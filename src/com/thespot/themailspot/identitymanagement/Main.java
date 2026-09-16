package com.thespot.themailspot.identitymanagement;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Keitumetse Chief Mamakoko
 * 
 * POE: Part 01 of 03 (Registration and Login Features)
 * 
 * 
 */

/**
 * 
 * This program will allow the user to create an account by entering a username, password, and South African cellphone number:
 * 
 * Main class: The entry point to the program; This class is responsible for handling registration
 * 
 
 */


public class Main {
    //Instance variables required for registration: These variables will hold the values that the user inputs
    private String username;
    private String password;
    private String phonenumber;
    private String firstname;
    private String lastname;

    //Instantiating scanner object: To get user input
    static Scanner userInput = new Scanner(System.in);


  //The main method: Executes the registration/login flow
    public static void main(String[] args) {
        //Welcoming the user to the program
        System.out.println("""
                           Welcome to The Mail Spot.
                           Registration requires the following:
                           - Firstname
                           - Lastname
                           - Username
                           - South African Cellphone Number
                           """);
        
      
        //Local variables to hold user input values for registration
        String firstname;
        String lastname;
        String username;
        String password;
        String cellphone;
        String registeredUser;
        
        
        
        
        //Account Creation/Registration 
        System.out.println("REGISTRATION");
        
        
        
        /**
         * 
         * Instantiating Login object: Since the Main class holds the user data, the Login class needs to store that data after a successful registration
         * 
         * This is required to call the validation and messaging methods from the Login class. 
         * 
         * */
        
        Login registrationData = new Login();
    
        //Prompting the user for their first and last names
        
        System.out.println("Please enter your first name: ");
        firstname = userInput.nextLine();
        System.out.println("Please enter your last name: ");
        lastname = userInput.nextLine();
        
        //While loop to keep prompting the user to enter their username if they enter a wrongly formatted username
        while(true){
            
            System.out.println("Please enter your username: ");
            username = userInput.nextLine();
            
            //If statement to relay conditions: username must not be over 5 characters in length, and should contain an underscore
            if (registrationData.checkUserName(username)){
                
                System.out.println("Username successfully captured");
                break;
                
            }else{
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters long");
            }
        
    
        
        }
        //While loop to keep prompting the user to enter a password if they enter a password that doesn't satisfy the complexity requirements
        while(true){
            
            
            System.out.println("Please enter a password: ");
            password = userInput.nextLine();
            
            //If statement to relay conditions: The password must contain at least 8 characters long, have a special character, number, and a capital letter
           if (registrationData.checkPasswordComplexity(password)){
               
               System.out.println("Password successfully captured");
               break;
           }else{
               
               System.out.println("Your password is incorrectly formatted. Please ensure that your password contains at least 8 characters long, contains a number, a capital letter and a special character");
           }
            
        }
        //While loopto keep prompting the user to enter a cellphone number if they enter a number that doesn't satisfy the requirements
        while (true){
            System.out.println ("Please enter a valid South African number: ");
            cellphone = userInput.nextLine();
            
            //If statement to relay conditions: The cellphone number must contain the South African International Country code
            if (registrationData.checkCellPhoneNumber(cellphone)){
                
                System.out.println("Cell number successfully captured.");
                break;
            }else{
                System.out.println("Cell number is incorrectly formatted or does not contain an international country code. Please correct the number and try again");
                
            }
        }
        
        //Registering the user
        registeredUser = registrationData.registerUser(firstname, lastname, username, password, cellphone);
        System.out.println("\n" + registeredUser);
        
        
        //Login 
        System.out.println("LOGIN");
        
        //Variables to hold user input values for loggin into the system
        String loginUsername;
        String loginPassword;
        //Boolean variable to indicate whether the user has logged in or not; This will only be true if the user enters the correct log in details
        boolean isLogged = false;
        
        //While loop to keep prompting the user to log in if their entered details do not match the ones they set when they registered 
        while (!isLogged){
            //Prompting the user for their set username
            System.out.println("Please enter your username: ");
            loginUsername = userInput.nextLine();
            
            //Prompting the user for their set password
            System.out.println("Please enter your password: ");
            loginPassword = userInput.nextLine();
            
            //If statement to relay the login status conditions
            if(registrationData.loginUser(loginUsername, loginPassword)){
            
            System.out.println(registrationData.returnLoginStatus(true));
            isLogged = true;
            
            
            }else{
                    System.out.println(registrationData.returnLoginStatus(false));
                    }
        }
        
        //Closing the scanner
        userInput.close();
    }
    
        
        
        
        
    
        
        
    
      
      
    }
    

