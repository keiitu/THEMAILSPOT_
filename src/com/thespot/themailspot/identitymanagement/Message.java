package com.thespot.themailspot.identitymanagement;
//Scanner Import
import java.util.Scanner;
//Array list import
import java.util.ArrayList;
import java.util.List;
//Input/output imports
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//Gson imports
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    //Class -level variable
    private static int totalMessages = 0;
    private static List<Message> sentMessages = new ArrayList<>();
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
        messageHash = preIDchar+ ":" + messageNumber + combinedWords;
        
       return messageHash;   
    }
    //Sent Message method: Allows the user to choose whether to send, disregard, or to store a message to send later
    public String SentMessage(){
        //Instantiating scanner to read user input
        Scanner userChoice = new Scanner(System.in);
        //Scanner intentionally not closed to avoid closing System.in, which would break the Scanner used in Main
        //Displaying per-message menu:
        System.out.println("MENU");
        while (true){             
            System.out.println("""
                               (1) Send Message

                               (2) Disregard Message

                               (3) Store Message

                               """);
            int chosenOption = userChoice.nextInt();

            //Switch statement for the menu:
            switch (chosenOption){
                case 1:
                    status = "sent";
                    sentMessages.add(this);
                    return "Message successfully sent.";
                    

                case 2:
                    status = "discarded";
                    return  "Press 0 to delete the message.";

                case 3: 
                    status = "stored";
                    return "Message successfully stored.";

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    break;

            }
        
       }
    }
    //Retun total message method: returns the total number of messages sent
    public int returnTotalMessages(){
        return sentMessages.size();
    }
    //Print Message method: returns all the messages sent while the program is running
    public String printMessages(){
        //Instantiating string builder
        StringBuilder sb = new StringBuilder();
        //If statement to relay empty case condition
        if (sentMessages.isEmpty()){
            return "No messages sent.";
        }
        //Looping through the list and appending the strings
        for (Message msg : sentMessages){
            sb.append("Recipient: ");sb.append(msg.getRecipient()).append("\n");
            sb.append("Message: ");sb.append(msg.getMessageText()).append("\n");
            sb.append("Message Hash: ");sb.append(msg.getMessageHash()).append("\n");
        }
        return sb.toString();
    
    
    }
  
    
    
    
    
}
