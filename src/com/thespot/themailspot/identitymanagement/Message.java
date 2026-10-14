package com.thespot.themailspot.identitymanagement;
import java.util.Scanner;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Keitumetse Chief Mamakoko
 * POE Part 02 of 03: Message feature
 */
public class Message {
    //Instantating scanner
    Scanner userInput = new Scanner(System.in);
    
    //Object-level variables
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private int messageNumber;//To show which message is chosen by the user
    private String status;//To show what action is undertaken after the user choses an action
    
  
    
    //Constructor
    public Message (String messageID, String recipient, String messageText, int messageNumber){
       this.messageID = messageID;
       this.recipient = recipient;
       this.messageText = messageText;
       this.messageNumber = messageNumber;
    }
    //Getters
    public String getMessageID(){
        return messageID;   
    }
    public String getRecipient(){
        return recipient;
    }
    public String getMessageText(){
        return messageText;
    }
    public String getMessageHash(){
        return messageHash;
    }
    public String getStatus(){
        return status;
    }
    public int getMessageNumber(){
        return messageNumber;
    }
    
    //Check MessageID method: Returns true if the messageID length is not more than 10 characters long
    public Boolean checkMessageID(String messageID){
        //Method-level variable
        int requiredLength = 10;
        
        return messageID.length() <= requiredLength;
        
    }
    //Check RecipientCell method: Returns true if the recipient's cellphone number starts with South African International Code and is no more than 10 characters long
    public String checkRecipientCell(String recipient){
        Login cellNumCheck = new Login ();
        //If statement to relay conditions: Recipient phone numnber must contain South African International Code and is no more than 10 characters long
        if (cellNumCheck.checkCellPhoneNumber(recipient)){
          return "Cellphone number successfully captured";     
        }else{
          return "Cellphone number is incorrectly formatted or does not contain international code. Please correct the number and try again";
        }
    }
    //Create messageHash method: The system autogenerates a message hash which contains the first two numbers of the message ID, a colon(:), the number of the message(:), and the first and last words in the message
    //The Hash must be diplayed in all caps
    public String createMessageHash(){
        //Getting the first two digits of the MessageID
        String preIDchar = messageID.substring(0,2);
        //Separating message words, and storing into an array
        String[] textChar = messageText.split(" ");
        //Indicating first and last words in the array
        String firstWord = textChar[0];
        String lastWord = textChar[textChar.length - 1];
        //Removing punctuation from the words
        firstWord = firstWord.replaceAll("[^a-zA-Z]", "");
        lastWord = lastWord.replaceAll("[^a-zA-Z]", "");
        //Tranforming the words to uppercase
        firstWord = firstWord.toUpperCase();
        lastWord = lastWord.toUpperCase();
        //Combining the words 
        String combinedWords;
        //Statement relaying conditions if when the message is a single word
        if (firstWord.equals(lastWord)){
            combinedWords = firstWord;
        }else{
           combinedWords = firstWord + lastWord;
        }
        //Building the message hash
        messageHash = preIDchar+ ":" + textChar + combinedWords;
        
       return messageHash;   
    }
    //Sent Message method: Allows the user to choose whether to send, disregard, or to store a message to send later
    public String sentMessage(){
        //Instantiating scanner to read user input
        Scanner userChoice = new Scanner(System.in);
        //Displaying per-message menu:
        System.out.println("MENU");
        System.out.println("""
                           (1) Send Message
                           
                           (2) Disregard Message
                           
                           (3) Store Message
                           
                           """);
        int chosenOption = userChoice.nextInt();
        //Method level variables
    
//        String status;
        
        
        //Switch statement for the menu:
        switch (chosenOption){
            case 1:
                status = "sent";
                return "Message successfully sent.";
 
            case 2:
                status = "discarded";
                return  "Press 0 to delete the message";
                
            case 3: 
                status = "stored";
                return "Message successfully stored";
                
            default:
                return "invalid choice";
                
        }
       
        
    
    }
    
    
    
    
}
