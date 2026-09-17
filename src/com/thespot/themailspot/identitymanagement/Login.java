/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thespot.themailspot.identitymanagement;


/**
 *
 * @author Keitumetse Chief Mamakoko: 
 * 
 * POE: Part 01 of 03 (Registration and Login Features)
 */

//This program will validate the username, password, and South African cellphone number that is entered by the user
//Login class: The blueprint for instances. It defines the data, and information about the class itself, as well as the objects that will be instantiated.
public class Login {
    
    //Variables for the validated values recieved from the user
    private String storedFirstname;
    private String storedLastname;
    private String storedUsername;
    private String storedPassword;
    private String storedCellphone;
    
        //Method to ensure that any username contains an underscore and is not more than 5 characters long
        public boolean checkUserName(String username){

            return username.contains("_") && username.length() <= 5;

        }
    
        //Method to ensure that any password contains at least 8 characters, a number, capital letter, and a special character
            public boolean checkPasswordComplexity(String password){
                //Local variable for the password minimum length
                int requiredLength = 8;
                
                //checking password length
                if(password.length() < requiredLength){
                    return false;
                }
                //Tracking flags to validate complexity
                boolean hasCapital = false;
                boolean hasNumber = false;
                boolean hasSpecial = false;
                
               //Looping through each character in the password
               for (int i = 0; i < password.length(); i++){
                   char passComplex = password.charAt(i);
                   
                   //Checking if the password meets complexity requirements:
                   
                   //Checking any character within the password is a capital letter
                   if (Character.isUpperCase(passComplex)){
                       hasCapital = true;
                   
                       
                   }else if(Character.isDigit(passComplex)){
                       hasNumber = true;
                       
                       
                   }else if (!Character.isLetterOrDigit(passComplex)){
                       hasSpecial = true;
                   }
                   
               }
                   return hasCapital && hasNumber && hasSpecial;     

            }
        
        /*Regex-based methods to determine password complexity and to validate cellphone number:
        Credits: codaddict, Alan Moore, Isu_guy, Arnaud, and Ishmael Makitla on StackOverflow
        The following code blocks have been adapted to my own approach,and the requirements of the POE
        though I have been thoroughly informed by the abovementioned through their comments in terms of regex manipulation and generation*/
            public boolean checkCellPhoneNumber(String cellphone){
                
                return cellphone.matches("^\\+27[0-9]{9}$");
                
            }
            
       //Helper methods for returning the necessary registration/login flow messaging to the user
            public String getReturnUsernameValid(String username){
                //If statement to relay conditions of the successful username capture
                if (checkUserName(username)){
                    return "Username has been captured successfully";
                    
                }else{
                    return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length";
                }
                
            }
            
       //Helper methods for returning the necessary password validity messaging to the user
            public String getReturnPasswordValid (String password){
             //If statement to relay conditions of the successful password capture
             if(checkPasswordComplexity(password)){
                 return "Password has been successfully captured";
             }else{
                 return "Password is not correctly formatted; please ensure that your password contains at least eight characters, a capital letter, a number, and a special character.";
             }
                
            }
            
       //Helper method for ensuring the necessary cellphone validity messaging to the user
            public String getReturnCellphoneValid (String cellphone){
                //If statement to relay the conditions of the successful cellphone capture
                if (checkCellPhoneNumber (cellphone)){
                    return "Cellphone number successfully captured";
                }else{
                    return "Cellphone number is incorrectly formatted or does not contain an international country code; Please correct the number and try again";
                }
            }
            
            
            
       //Registration method; This method calls the validation methods, stores data and returns the necessary messaging to the user
            public String registerUser(String firstname, String lastname, String username, String password, String cellphone){
                //if statements to validate the username, password, and the cellphone number. Each will return a message depending on the argument it recieves
                if(!checkUserName(username)){
                    return getReturnUsernameValid(username);
                }
              
                  if(!checkPasswordComplexity(password)){
                    return getReturnPasswordValid(password);
                }
                
                if (!checkCellPhoneNumber (cellphone)){
                    return getReturnCellphoneValid(cellphone);
                }
                
                //Storing the data in the declared fields should they pass all validation trials
                this.storedFirstname = firstname;
                this.storedLastname = lastname;
                this.storedUsername = username;
                this.storedPassword = password;
                this.storedCellphone = cellphone;
                
                //returning message (includes the user's first name and last name)
                return "Welcome " + firstname + ", " + lastname + "." + " it is great to see you.";
                
                
                
            }
        //Login method; This method verifies the values entered by the user against the set ones. It also returns status messages
            public boolean loginUser(String username, String password){
                //Since a user can only log in after they have created an account; the following if statement relays the condition that if the stored username does not have a value then no one has registered
                if (storedUsername == null){
                    
                    return false;
                    
                }
                //Comparing the entered username and password with the ones that are stored
                return storedUsername.equals(username)&& storedPassword.equals(password);
                
            }
        //Login method; This method returns the necessary messaging for a successful login and a failed one
            public String returnLoginStatus(boolean loginUser){
                //if statement to relay that if the login was successful, then the welcome message should be shown to the user.
                if (loginUser){
                    return "Welcome "+ storedFirstname + ", " + storedLastname + "."+" it is great to see you again.";
                }else{
                    return "Username or password incorrect. Please try again";  
                }
                    
                }
            }