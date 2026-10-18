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
//java language import
import java.lang.reflect.Type;
//Gson imports
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Keitumetse Chief Mamakoko: Student @Rosebank International - ST10496139
 * POE Part 02 of 03: Message feature
 */
//This program will handle the core logic related to messaging: Including validation, hash generation, formatting, and the JSON file storage
public class Message {
    //Class -level variable
    private static int totalMessages = 0;
    public static List<Message> sentMessages = new ArrayList<>();
    private static final int MAX_MESSAGE_LENGTH = 250;
   
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
    //Check RecipientCell method: Returns the necessary messaging depending whether the recipient's cellphone number starts with South African International Code and is no more than 10 characters long
    public String checkRecipientCell(String recipient){
        Login cellNumCheck = new Login ();
        //If statement to relay conditions: Recipient phone numnber must contain South African International Code and is no more than 10 characters long
        if (cellNumCheck.checkCellPhoneNumber(recipient)){
          return "Cell phone number successfully captured.";     
        }else{
          return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    //Check message length method: Validates the length of the message typed by the user
    public String checkMessageLength(String messageText) {
    if (messageText.length() <= MAX_MESSAGE_LENGTH) {
        return "Message ready to send.";
    } else {
        int excess = messageText.length() - MAX_MESSAGE_LENGTH;
        return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
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
        messageHash = preIDchar+ ":" + messageNumber + ":"+  combinedWords;
        
       return messageHash;   
    }
    //Sent Message method: Allows the user to choose whether to send, disregard, or to store a message to send later
    public String SentMessage(){
        //Instantiating scanner to read user input
        Scanner userChoice = new Scanner(System.in);
        //Scanner intentionally not closed to avoid closing System.in, which would break the Scanner used in Main
        //Displaying per-message menu:
        System.out.println("Select what you want to do with the message: ");
        while (true){             
            System.out.println("""
                               1) Send Message

                               2) Disregard Message

                               3) Store Message

                               """);
            System.out.println("Choose: ");
            int chosenOption = userChoice.nextInt();
            userChoice.nextLine();

            //Switch statement for the menu:
            switch (chosenOption){
                case 1:
                    status = "sent";
                    sentMessages.add(this);
                    return "Message successfully sent.";
                    

                case 2:
                    status = "discarded";
                    System.out.println("Press 0 to delete the message.");
                    int deleteChoice = userChoice.nextInt();
                    // The message is discarded simply by NOT being saved to the list.
                    return "Press 0 to delete the message.";//Adhering to the POE requirements (UX tradeoff)
                    
                case 3: 
                    status = "stored";
                    storeMessage();
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
    /**
     * Store messages method: stores messages in a JSON file
     * 
     * Retrievable source: 
     * Baeldung.2024.Save Data to a JSON File with Gson. Baeldung.2025. [Online]. Available at: https://www.baeldung.com/gson-save-file 
     **/
    public void storeMessage(){
        //Instantiating the Gson Object
        Gson gson = new Gson();
        //Creating new file object for "messages.json"
        File file = new File("messages.json");
        //Creating an empty list to hold all the messages
        List<Message> messages = new ArrayList<>();
        
   
        // Read existing messages if the file already exists AND is not empty
        if (file.exists() && file.length() > 0) {  // <--- ADD THE LENGTH CHECK
            try (FileReader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<Message>>(){}.getType();
                messages = gson.fromJson(reader, listType);
                if (messages == null) {
                    messages = new ArrayList<>();
                }
            } catch (IOException e) {
                System.out.println("Error reading messages file: " + e.getMessage());
                return;
            } catch (com.google.gson.JsonSyntaxException e) {
                // If the file is corrupted for any reason, start fresh
                messages = new ArrayList<>();
            }
        }
        //Adding this message to the list
        messages.add(this);
        
        //Writing the full list back to the file
        try (FileWriter writer = new FileWriter(file)){
            gson.toJson(messages, writer);
        }catch(IOException e){
            System.out.println("Error writing messages file: " + e.getMessage());
        }
        
    }
    
    
    
  
    
    
    
    
}
