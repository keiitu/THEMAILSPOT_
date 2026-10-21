package com.thespot.themailspot.identitymanagement;
import java.util.Scanner;
//Random number generator import
import java.util.Random;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Keitumetse Chief Mamakoko
 * 
 * POE: Part 02 of 03 (Registration and Login Features)
 * 
 * This program will allow the user to create an account by entering their first name, last name, username, password, and South African cellphone number:
 * 
 */

/**
 * 
 * 
 * 
 * Main class: The entry point to the program; This class is responsible for handling user interaction:
 * prompting for input, running validation loops,and displaying messages. It uses a scanner to read from the console.
 * 
 * 
 
 */


public class Main {

    //Instantiating scanner object: To get user input
    static Scanner userInput = new Scanner(System.in);
    
    


  //The main method: Executes the registration/login flow
    public static void main(String[] args) {
        //Welcoming the user to the program and outlining the requirements for account creation
        System.out.println("""
                           Welcome to The Mail Spot.
                           Please note that account registration requires the following:
                           - First name
                           - Last name
                           - Valid Username
                           - Valid Password
                           - Valid South African Cellphone Number
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
            
            System.out.println("Please enter your username (The username must not be over 5 characters in length, and should contain an underscore): ");
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
            
            
            System.out.println("Please enter a password (Your password must contain at least 8 characters , contains a number, a capital letter and a special character): ");
            password = userInput.nextLine();
            
            //If statement to relay conditions: The password must contain at least 8 characters long, have a special character, number, and a capital letter
           if (registrationData.checkPasswordComplexity(password)){
               
               System.out.println("Password successfully captured");
               break;
           }else{
               
               System.out.println("Your password is incorrectly formatted. Please ensure that your password contains at least 8 characters , contains a number, a capital letter and a special character");
           }
            
        }
        //While loop to keep prompting the user to enter a cellphone number if they enter a number that doesn't satisfy the requirements
        while (true){
            System.out.println ("Please enter a valid South African number (The number must lead with the South African international code +27): ");
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
        System.out.println(registeredUser + "\n");
        
        
        //Login Message
        System.out.println("LOGIN");
        //Calling loadStoredMessagesFromJSON method
        Message.loadStoredMessagesFromJson();
       
        
        //Variables to hold user input values for logging into the system
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
            
            System.out.println(registrationData.returnLoginStatus(true) + "\n");
            isLogged = true;
            
            
            }else{
                    System.out.println(registrationData.returnLoginStatus(false));
                    }
        }
        
        
        //Welcoming user to the program
        System.out.println("WELCOME PAGE");
        System.out.println("Welcome to QuickChat");
         //Creating the Message Object
        
        //Main menu loop
        boolean running = true;
        while (running){
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages (Coming soon)");
            System.out.println("3) Quit");
            System.out.print("Choose: ");
            int choice = userInput.nextInt();
            userInput.nextLine();//Consumes the newline
            
            //If statement to relay what happens when any choice is selcted
            if (choice == 1){
                //Asking user how many messages they want to send
                System.out.print("How many messages do you want to send? ");
                int numMessages = userInput.nextInt();
                userInput.nextLine();//Consume newline
                
                //Looping for the many numbers the user wants to send messages
                for(int i = 0; i < numMessages; i++){
                    //Instantiating random class object
                    Random rand = new Random();
                    //Generating random ID a message
                    String id = String.format("%010d", rand.nextInt(1_000_000_000));
                    
                    //Instantiating message object
                    Message msg = new Message(id, "", "", i, username);
                    
                    //Validating messageID
                     if (!msg.checkMessageID(id)) {
                            System.out.println("Message ID error.");
                            continue; // Skip this message and move to the next one in the for loop
                        }
                
                    // Looping until the recipient is valid
                        String recipient = "";
                        boolean isValidRecipient = false;
                        while (!isValidRecipient) {
                            System.out.print("Enter recipient cell number (Must start with: +27...): ");
                            recipient = userInput.nextLine();

                            String status = msg.checkRecipientCell(recipient);
                            if (status.equals("Cell phone number successfully captured.")) {
                                isValidRecipient = true; 
                            } else {
                                System.out.println(status); 
                            }
                        }
                    //Looping until the correct message length has been entered
                        String messageText = "";
                        boolean isValidLength = false;
                        while (!isValidLength) {
                            System.out.print("Enter message (max 250 characters): ");
                            messageText = userInput.nextLine();

                            String status = msg.checkMessageLength(messageText);
                            if (status.equals("Message ready to send.")) {
                                isValidLength = true;
                            } else {
                                System.out.println(status);
                            }
                        }
                    //Instantiating the message class object
                    msg = new Message (id, recipient, messageText, i, username);
                                    
                    //Generating the message hash when all validation passes
                    msg.createMessageHash();
                    
                    //Presenting the menu to the user
                    String menu = msg.SentMessage();
                    System.out.println(menu);
                           
                }
               // Printing report for the recentclysent messages
                Message printer = new Message("", "", "", 0, ""); // Dummy object to access the static list
                System.out.println("\n--- RECENTLY SENT MESSAGES ---");
                System.out.println(printer.printMessages());
            }else if (choice == 2){
                System.out.println("Coming soon");
            }else if (choice == 3){
                running = false;
            }
            
        }

        
        
        //Closing the scanner
        userInput.close();
    }
    
        
        
        
        
    
        
        
    
      
      
    }
    

