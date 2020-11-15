package ATMBankManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    // User's bank account details.
    private String firstName;
    private String lastName;
    private String uuid; // ID.
    private byte pinHash[]; // Stored Pin code, in a byte Array called pinHash (empty). Will be stored hashed in MD5.
    
    
    private Bank bank;

    /**
     * 
     * @param firstName
     * @param lastName
     * @param pin
     * @param theBank
     */
    // @param firstName, @param lastName, @param pin, @param theBank.
    public User(String firstName, String lastName, String pin) {

        // Set user's name.
        this.firstName = firstName;
        this.lastName = lastName;

        // Store the original pin as MD5 hash. A Try Catch, to catch any errors in an unrecognised getInstance argument string, (if "MD5" was invalid, it would throw the error).
        try {
            // pinHash is a byte array. Use digest function ' getBytes()', from our md MessageDigest hashing object, and use on user's pin, to get the memeory/bytes of the pin object to digest the bytes through our MD5 algorith, to return pin hash bytes. In short, we are taking pin string and returning a MD5 hashed value.

            MessageDigest md = MessageDigest.getInstance("MD5"); // MD5 Hashing algorithm.
            this.pinHash = md.digest(pin.getBytes()); // Pass pin through algo, to hash.
            
        } catch (NoSuchAlgorithmException e) {
            
            System.err.println("error: caught NoSuchAlgorithmException");
            e.printStackTrace(); //
            System.exit(1); // Exit app.
        }

        // Generate a new uuid for user.
        this.uuid =null;//se genereaza in banca--- theBank.getNewUserUUID();
    }
    
    public void setBank(Bank bank) {
    	this.bank=bank;
    }
    
    public Bank getBank() {
    	 return this.bank;
    }
    

    /**
     * 
     * @return
     */
    // "String" is returned. @return uuid (user's uuid). Public method made available, to get access to a Private value.
    public String getUUID() {
        return this.uuid;
    }
    
    public void setUUID(String uuid) {
        this.uuid=uuid;  //atunci cand generam in banca un uuid pt user tre sa il atribuim cumva si ca field instantei user
    }
    
    
    /**
     * 
     * @param aPin
     * @return
     */
    // Check whether given pin matches the particular User's pin. @param aPin (inputted pin to check). @return boolean (whether pin is valid or not).
    public boolean validatePin(String aPin) 
    {
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

    
    public String getFirstName() {
        return this.firstName;
    }


}