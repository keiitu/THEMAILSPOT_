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
   
  
    
    //assertEquals tests
    
    
    
    /**
     * AssertTrue/False Tests
     * Tests if all the methods within the Login Class(Responsible for data validation) work as expected)
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
