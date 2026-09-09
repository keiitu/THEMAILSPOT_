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
    boolean result = usernameTest.checkUserName("kyl_1");
    assertTrue("Username should be valid", result);
       
   }
   
   
    
}
