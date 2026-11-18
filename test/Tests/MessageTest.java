/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tests;
import org.junit.Test;
import static org.junit.Assert.*;
import com.thespot.themailspot.identitymanagement.Message;

/**
 *
 * @author Keitumetse Chief Mamakoko: Student @Rosebank International - ST10496139
 * 
 * POE Part 02 of 03: Message feature: 

 */
//This program will check if all the methods within the message class execute as expected
public class MessageTest {
    
    //Before method to ensure that we start on a clean slate
    @org.junit.Before
    public void setUp(){
        Message.storedMessages.clear();
        // Populate with POE Test Data
        Message.storedMessages.add(new Message("0000000001", "+27834557896", "Did you get the cake?", 0, "Dev"));
        Message.storedMessages.add(new Message("0000000002", "+27838884567", "Where are you? You are late! I have asked you to be on time.", 1, "Dev"));
        Message.storedMessages.add(new Message("0000000003", "+27834484567", "Yohoooo, I am at your gate.", 2, "Dev"));
        Message.storedMessages.add(new Message("0838884567", "+27838884567", "It is dinner time !", 3, "Dev")); // POE specified
        Message.storedMessages.add(new Message("0000000005", "+27838884567", "Ok, I am leaving without you.", 4, "Dev"));
        // Generate hashes for the test data
        for (Message m : Message.storedMessages) {
            m.createMessageHash();
        }
    }
    //Tests if print messages works as expected
    @Test
    public void testPrintMessages() {
        // 1. Clear the list to ensure a clean test
        Message.sentMessages.clear();
        // 2. Create a message
        Message msg = new Message("0012345678", "+27718693002", "Hi Mike", 0, "Tester");
        msg.createMessageHash(); // Generate the hash for the printout

        // 3. Manually add it to the static list (simulating a send)
        Message.sentMessages.add(msg);
        // 4. Call printMessages
        String result = msg.printMessages();
        // 5. Assert that the output contains the correct details
        assertTrue("Output should contain Recipient", result.contains("+27718693002"));
        assertTrue("Output should contain Message", result.contains("Hi Mike"));
        assertTrue("Output should contain Hash", result.contains("00:0:HIMIKE"));

        // 6. Clean up
        Message.sentMessages.clear();
    }

    // TEST: checkMessageID(String id)

    @Test
    public void testMessageIDValid() {
        Message msg = new Message("0012345678", "+27718693002", "Hello", 0, "Tester");
        // Passing the ID as an argument
        assertTrue("Message ID should be valid if <= 10 characters", msg.checkMessageID("0012345678"));
    }

    @Test
    public void testMessageIDInvalid() {
        Message msg = new Message("0012345678", "+27718693002", "Hello", 0, "Tester");
        // Passing an ID longer than 10 chars
        assertFalse("Message ID should be invalid if > 10 characters", msg.checkMessageID("00123456789"));
    }

    // TEST: checkRecipientCell(String recipient)

    @Test
    public void testRecipientValid() {
        Message msg = new Message("0012345678", "+27718693002", "Hi Mike", 0, "Tester");
        String expected = "Cell phone number successfully captured.";
        // Passing the recipient as an argument
        String actual = msg.checkRecipientCell("+27718693002");
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientInvalid() {
        Message msg = new Message("0012345678", "08575975889", "Hi Keegan", 0, "Tester");
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        // Passing the invalid recipient as an argument
        String actual = msg.checkRecipientCell("08575975889");
        assertEquals(expected, actual);
    }
    // TEST: checkMessageLength(String messageText)

    @Test
    public void testMessageLengthValid() {
        Message msg = new Message("0012345678", "+27718693002", "Short message", 0, "Tester");
        String expected = "Message ready to send.";
        // Passing the text as an argument
        String actual = msg.checkMessageLength("Short message");
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageLengthInvalid() {
        String longMessage = "a".repeat(251); 
        Message msg = new Message("0012345678", "+27718693002", longMessage, 0, "Tester");
        
        String expected = "Message exceeds 250 characters by 1; please reduce the size.";
        // Passing the long text as an argument
        String actual = msg.checkMessageLength(longMessage);
        assertEquals(expected, actual);
    }

    // TEST: createMessageHash()
    // This one uses the object's internal fields (this.messageID, this.messageText)
    // so it does NOT take an argument.

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message("0012345678", "+27718693002", 
                                  "Hi Mike, can you join us for dinner tonight?", 0, "Tester");
        
        String expected = "00:0:HITONIGHT";
        String actual = msg.createMessageHash();
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateMessageHashSingleWord() {
        Message msg = new Message("0098765432", "+27718693002", "Hello", 1, "Tester");
        
        String expected = "00:1:HELLO";
        String actual = msg.createMessageHash();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDisplayLongestMessage() {
        // The longest message is Message 2
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, Message.getLongestStoredMessage());
    }
     @Test
    public void testSearchByMessageID() {
        // Test Data 4 ID is "0838884567"
        String expected = "Recipient: +27838884567\nMessage: It is dinner time !";
        assertEquals(expected, Message.searchByMessageID("0838884567"));
    }
    
    @Test
    public void testSearchByRecipient() {
        // Search for +27838884567 (Matches Message 2 and 5)
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you?"));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        // Message 2 has the hash starting with "2:1:WHEREON"
        String hashToDelete = Message.storedMessages.get(1).getMessageHash(); 
        
        String result = Message.deleteByHash(hashToDelete);
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", result);
        
        // Ensure it was actually removed
        assertEquals(4, Message.storedMessages.size());
    }
    
}