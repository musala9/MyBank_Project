package ATMBankManager;

import java.util.ArrayList;
import java.util.Iterator;

import db.MyDB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    // User's bank account details.
	private int ID;
    private String firstName;
    private String lastName;
    private byte code; //uuid
    private byte pinHash[]; // Stored Pin code, in a byte Array called pinHash (empty). Will be stored hashed in MD5.
    private String pin;
    
    public User() {
    	
    }
    public User(int ID,String pin,String firstName,String lastName) {
    	this.ID=ID;
    	this.pin=pin;
    	this.firstName=firstName;
    	this.lastName=lastName;
    }
   
    
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public byte getCode() {
		return code;
	}

	public void setCode(byte code) {
		this.code = code;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public void setID(int iD) {
		ID = iD;
	}
    
    public int getID() {
    	return this.ID;
    }
    
   

    // Check whether given pin matches the particular User's pin. @param aPin (inputted pin to check). @return boolean (whether pin is valid or not).
    public boolean validatePin(String aPin) {
        try {
            // pinHash is a byte array. Use digest function ' getBytes()', from our md MessageDigest hashing object, and use on user's pin, to get the memeory/bytes of the pin object to digest the bytes through our MD5 algorith, to return pin hash bytes. In short, we are taking pin string and returning a MD5 hashed value.

            MessageDigest md = MessageDigest.getInstance("MD5"); // MD5 Hashing algorithm.
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash); // Static method 'isDigest()', to return a boolean value, after comparing this inputted pin (hashed) with our targetted User's pin. True if a matching hash is found, else false.

        } catch (NoSuchAlgorithmException e) {

            System.err.println("error: caught NoSuchAlgorithmException");
            e.printStackTrace(); //
            System.exit(1); // Exit app.
        }
        return false; // If somehow the execution stream gets here, by getInstance error catch, then we still need to return a boolean value, so we dont get a Java error.
    }

    
    
     

}