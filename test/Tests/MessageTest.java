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
 * POE Part 02 of 03: Message feature

 */
//This program will check if all the methods within the message class execute as expected
public class MessageTest {

    // TEST: checkMessageID(String id)


    @Test
    public void testMessageIDValid() {
        Message msg = new Message("0012345678", "+27718693002", "Hello", 0);
        // Passing the ID as an argument
        assertTrue("Message ID should be valid if <= 10 characters", msg.checkMessageID("0012345678"));
    }

    @Test
    public void testMessageIDInvalid() {
        Message msg = new Message("0012345678", "+27718693002", "Hello", 0);
        // Passing an ID longer than 10 chars
        assertFalse("Message ID should be invalid if > 10 characters", msg.checkMessageID("00123456789"));
    }

    // TEST: checkRecipientCell(String recipient)

    @Test
    public void testRecipientValid() {
        Message msg = new Message("0012345678", "+27718693002", "Hi Mike", 0);
        String expected = "Cell phone number successfully captured.";
        // Passing the recipient as an argument
        String actual = msg.checkRecipientCell("+27718693002");
        assertEquals(expected, actual);
    }

    @Test
    public void testRecipientInvalid() {
        Message msg = new Message("0012345678", "08575975889", "Hi Keegan", 0);
        String expected = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        // Passing the invalid recipient as an argument
        String actual = msg.checkRecipientCell("08575975889");
        assertEquals(expected, actual);
    }
    // TEST: checkMessageLength(String messageText)

    @Test
    public void testMessageLengthValid() {
        Message msg = new Message("0012345678", "+27718693002", "Short message", 0);
        String expected = "Message ready to send.";
        // Passing the text as an argument
        String actual = msg.checkMessageLength("Short message");
        assertEquals(expected, actual);
    }

    @Test
    public void testMessageLengthInvalid() {
        String longMessage = "a".repeat(251); 
        Message msg = new Message("0012345678", "+27718693002", longMessage, 0);
        
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
                                  "Hi Mike, can you join us for dinner tonight?", 0);
        
        String expected = "00:0:HITONIGHT";
        String actual = msg.createMessageHash();
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateMessageHashSingleWord() {
        Message msg = new Message("0098765432", "+27718693002", "Hello", 1);
        
        String expected = "00:1:HELLO";
        String actual = msg.createMessageHash();
        assertEquals(expected, actual);
    }
}