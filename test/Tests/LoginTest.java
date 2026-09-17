package Tests;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */



/**
 *
 * @author Keitumetse Chief Mamakoko: Student @Rosebank International - ST10496139
 * 
 * POE: Part 01 of 03 (Registration and Login Features)
 * 
 * 
 * 
 */

import org.junit.Test;
import static org.junit.Assert.*;
import com.thespot.themailspot.identitymanagement.Login;
//This program will verify that methods are executing as expected
public class LoginTest {
   
  
    
   /**
     * AssertEquals Tests
     * Tests if all the methods within the Login Class(Responsible for data validation) work as expected.
     * 
     *
     **/
    @Test//Tests if registration flow works as desired
    public void testSuccessfulRegistrationMessage(){
     Login registrationSuccess = new Login();
     String expected = "Welcome Kyle, Kevins.You have been registered successfully.";
     String actual = registrationSuccess.registerUser("Kyle", "Kevins", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
     assertEquals(expected, actual);
    }
    @Test//Test invalid username case
    public void testInvalidUsernameMessage(){
        Login usernameTest = new Login();
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length";
        String actual = usernameTest.registerUser("Kyle", "Kevins", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(expected, actual);
        
    }
    @Test //Tests invalid password case
    public void testInvalidPasswordMessage(){
        Login passwordTest = new Login();
        String expected = "Password is not correctly formatted; please ensure that your password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = passwordTest.registerUser("Kyle", "Kevins","kyl_1", "password","+27838968976");
        assertEquals(expected, actual);
    }
    @Test//Tests invalid cellphone message
    public void testInvalidCellphoneMessage(){
        Login cellphoneTest = new Login();
        String expected = "Cellphone number is incorrectly formatted or does not contain an international country code; Please correct the number and try again";
        String actual = cellphoneTest.registerUser("Kyle", "Kevins", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals(expected, actual);
    }
    @Test//Tests if login flow works as expected
    public void testSuccessfulLoginMessage(){
     Login loginTest = new Login();
     String expected = "Welcome Kyle, Kevins. it is great to see you again.";
     loginTest.registerUser("Kyle", "Kevins", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
     String actual = loginTest.returnLoginStatus(true);
     assertEquals(expected, actual);
    }
    
    
    /**
     * AssertTrue/False Tests
     * Tests if all the methods within the Login Class(Responsible for data validation) work as expected; Will return either true or false when a method works the way it is intended.
            **/
    @Test
   public void testUserNameValid(){
    Login usernameTest = new Login();
    boolean result = usernameTest.checkUserName("kyl_1");//Assigns result from checking the validity of the username to the boolean variable "result"
    assertTrue("Username should be correctly formatted", result);
       
   }
   
   @Test
   public void testPasswordValid(){
       Login passwordTest = new Login();
       boolean result = passwordTest.checkPasswordComplexity("Ch&&sec@ke99!");//Assigns result from checking the validity of the password to the boolean variable "result"
       assertTrue("Password should meet complexity requirements", result);
   }
   
   @Test
   public void testCellphoneValid(){
    Login cellphoneTest = new Login();
    boolean result = cellphoneTest.checkCellPhoneNumber("+27838968976");//Assigns result from checking the validity of the cellphone to the boolean variable "result"
    assertTrue("Cellphone should be correctly formatted", result);
   }
   
   //AssertFalse tests
   @Test
   public void testUsernameInvalid(){
       Login usernameTest = new Login();
       boolean result = usernameTest.checkUserName("kyle!!!!!!!");//Assigns result from checking the validity of the username to the boolean variable "result"
       assertFalse("Username should be incorrectly formatted", result);
   }
   
   @Test
   public void testPasswordInvalid(){
       Login passwordTest = new Login();
       boolean result = passwordTest.checkPasswordComplexity("password");//Assigns result from checking the validity of the password to the boolean variable "result"
       assertFalse("Password should not meet complexity requirements", result);
   }
   
   @Test
   public void testCellphoneInvalid(){
       Login cellphoneTest = new Login();
       boolean result = cellphoneTest.checkCellPhoneNumber("08966553");//Assigns result from checking the validity of the cellphone to the boolean variable "result"
       assertFalse("Cellphone should be incorrectly formatted", result);
   }
   
   
    
}
