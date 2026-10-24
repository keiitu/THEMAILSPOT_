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
//iterator import
import java.util.Iterator;

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
    private static final int MAX_MESSAGE_LENGTH = 250;
    //The 5 required arrays 
    public static List<Message> sentMessages = new ArrayList<>();
    public static List<Message> disregardedMessages = new ArrayList<>();
    public static List<Message> storedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();
   
   
    //Object-level variables
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private int messageNumber;//To show which message is chosen by the user
    private String status;//To show what action is undertaken after the user choses an action
    //Sender Field
    private String sender;

    
    //Constructor
    public Message (String messageID, String recipient, String messageText, int messageNumber, String sender){
       this.messageID = messageID;
       this.recipient = recipient;
       this.messageText = messageText;
       this.messageNumber = messageNumber;
       this.sender = sender;
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
    public String getSender(){
        return sender;
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
         String idPrefix;
            if (messageID != null && messageID.length() >= 2) {
                idPrefix = messageID.substring(0, 2);
            } else {
                idPrefix = messageID; // Fallback if the ID is too short
            }
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
                    messageIDs.add(this.messageID);
                    messageHashes.add(this.messageHash);
                    return "Message successfully sent.";
                    

                case 2:
                    status = "discarded";
                    disregardedMessages.add(this);
                    return "Press 0 to delete the message.";//Adhering to the POE requirements (UX tradeoff)
                    
                case 3: 
                    status = "stored";
                    storeMessage();
                    storedMessages.add(this);
                    messageIDs.add(this.messageID);
                    messageHashes.add(this.messageHash);
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
            sb.append("Message ID: ").append(msg.getMessageID()).append("\n");
            sb.append("Message Hash: ");sb.append(msg.getMessageHash()).append("\n");
            sb.append("Recipient: ");sb.append(msg.getRecipient()).append("\n");
            sb.append("Message: ");sb.append(msg.getMessageText()).append("\n");
           
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
        if (file.exists() && file.length() > 0) {  
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
    //Load Stored Messaged Method: To populate the storedMessages array when the program starts
    public static void loadStoredMessagesFromJson() {
    storedMessages.clear();
    File file = new File("messages.json");
    if (!file.exists() || file.length() == 0) {
        return;
    }
    
    Gson gson = new Gson();
    try (FileReader reader = new FileReader(file)) {
        Type listType = new TypeToken<List<Message>>(){}.getType();
        List<Message> loaded = gson.fromJson(reader, listType);
        if (loaded != null) {
            storedMessages.addAll(loaded);
        }
    } catch (Exception e) {
        System.out.println("Error loading messages: " + e.getMessage());
    }
    }
    //Display sender and recipient of all stored messages method: Shows sender and recipient of stored messages
     public static String displayStoredSendersAndRecipients() {
        if (storedMessages.isEmpty()){
            return "No stored messages.";
        }
         
        StringBuilder sb = new StringBuilder();
        for (Message m : storedMessages) {
            sb.append("Sender: ").append(m.getSender())
              .append("::Recipient: ").append(m.getRecipient()).append("\n");
        }
        return sb.toString();
    }
    //Display the longest stored message method: shows the longest stored message 
      public static String getLongestStoredMessage() {
        if (storedMessages.isEmpty()){
            return "No stored messages.";
        }
            
        Message longest = storedMessages.get(0);
        for (Message m : storedMessages) {
            if (m.getMessageText().length() > longest.getMessageText().length()) {
                longest = m;
            }
        }
        return longest.getMessageText();
    }
    //Search for a messageID and display recipient and message: loops through list searching for corresponding messageID
    public static String searchByMessageID(String id) {
        for (Message m : storedMessages) {
            if (m.getMessageID().equals(id)) {
                return "Recipient: " + m.getRecipient() + "\nMessage: " + m.getMessageText();
            }
        }
        return "Message ID not found.";
    }
    //Search for all messages stored for a particular recipient
    public static String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (Message m : storedMessages) {
            if (m.getRecipient().equals(recipient)) {
                sb.append("Message: \"").append(m.getMessageText()).append("\"\n");
            }
        }
        if (sb.length() == 0) {
            return "No messages found for this recipient.";
        }
        return sb.toString();
    }
    //Delete a message using the message hash
    public static String deleteByHash(String hash) {
        Iterator<Message> iterator = storedMessages.iterator();
        while (iterator.hasNext()) {
            Message m = iterator.next();
            if (m.getMessageHash().equals(hash)) {
                iterator.remove();
                saveStoredMessagesToJson(); //Used to update the JSON file (Added helper method)
                return "Message: \"" + m.getMessageText() + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }
    //Display report listing full detauils of all stored messages
      public static String displayStoredReport() {
        if (storedMessages.isEmpty()){
            return "No stored messages.";
        }
        StringBuilder sb = new StringBuilder();
        for (Message m : storedMessages) {
             sb.append("Message ID: ").append(m.getMessageID()).append("\n");
            sb.append("Message Hash: ").append(m.getMessageHash()).append("\n")
              .append("Recipient: ").append(m.getRecipient()).append("\n")
              .append("Message: ").append(m.getMessageText()).append("\n\n");
        }
        return sb.toString();
    }
    //Helper method to write the JSON file after a deletion
      public static void saveStoredMessagesToJson() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("messages.json")) {
            gson.toJson(storedMessages, writer);
        } catch (IOException e) {
            System.out.println("Error saving messages: " + e.getMessage());
        }
    }
      
    

    
}
    
    
    
  
    
    
    
    

