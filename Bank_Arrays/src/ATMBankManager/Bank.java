package ATMBankManager;

import java.util.ArrayList;
import java.util.Random;


	public class Bank {
	    // Bank's details.
	    private String name;
	    private ArrayList<User> users; // Each element of type 'User', in the ArrayList 'users'.
	    private ArrayList<Account> accounts; // Each element of type 'Account', in the ArrayList 'accounts'.
	    
	    /**
	     * 
	     * @param name
	     */
	    // Bank Constructor, to create a new Bank object, with empty users and accounts lists. @param name (the name of the bank).
	    public Bank(String name) {
	        this.name = name;
	        this.users = new ArrayList<User>();
	        this.accounts = new ArrayList<Account>();
	    }

	    /**
	     * 
	     * @return
	     */
	    // "public" = scope of accessibility of this func/method. "String" = returned data type from this function/method.
	    // Generate new uuid for user, and check to ensure it doesn't exist in the users list. @return uuid.
	    public String getNewUserUUID() {
	        
	        // Inits.
	        String uuid;
	        Random rng = new Random();
	        int len = 6; // Characters length.
	        boolean nonUnique; // A flag.

	        // Loop until nonUnique flag is true.
	        do {
	            // Generate number.
	            uuid = "";
	            for (int i = 0; i < len; i++) {

	                // Generate a number between 0 and <10, cast as integer object (primitive int to non-primitive Integer), to allow access to number methods, such as 'toString()', (since uuid is expecting a String, and we're generating a number to append to uuid).
	                uuid += ((Integer)rng.nextInt(10)).toString();
	            }

	            // Check uuid is unique.
	            nonUnique = false;
	            // Iterate through this class' 'users' ArrayList, through all the elements of type 'User', storing the current interated element in 'u'.
	            for (User u: this.users) {
	                if (uuid.compareTo(u.getUUID()) == 0) {
	                    nonUnique = true;
	                    break;
	                }
	            }
	        } while (nonUnique); // loop to do once, check condition, and then decide if to iterate again.

	        return uuid;
	    }

	    /**
	     * 
	     * @return
	     */
	    // "String" = returned data type from this function/method. Generate new uuid for account, and check to ensure it doesn't exist in the users list. @return uuid.
	    public String getNewAccountUUID() {
	                
	        // Inits.
	        String uuid;
	        Random rng = new Random();
	        int len = 10; // Characters length.
	        boolean nonUnique; // A flag.

	        // Loop until nonUnique flag is true.
	        do {
	            // Generate number.
	            uuid = "";
	            for (int i = 0; i < len; i++) {
	                // Generate a number between 0 and <10, cast as integer object (primitive int to non-primitive Integer), to allow access to number methods, such as 'toString()', (since uuid is expecting a String, and we're generating a number to append to uuid).
	                uuid += ((Integer)rng.nextInt(10)).toString();
	            }

	            // Check uuid is unique.
	            nonUnique = false;
	            // Iterate through this class' 'accounts' ArrayList, through all the elements of type 'Account', storing the current interated element into 'a'.
	            for (Account a: this.accounts) {
	                if (uuid.compareTo(a.getUUID()) == 0) {
	                    nonUnique = true;
	                    break;
	                }
	            }
	        } while (nonUnique); // loop to do once, check condition, and then decide if to iterate again.

	        return uuid;
	    }

	    /**
	     * 
	     * @param anAcct
	     */
	    //  Encapsulation - this public encapsulated (self-contained) method allows an account to be added to the private ArrayList 'accounts', form an outside class (using this method). There are two of these public methods for each list, which are mirrors (a user's list and bank's list).
	    // Adds account to accounts list. @param anAcct (the account to add to Bank's list).
	    public void addAccount(Account anAcct) {
	        this.accounts.add(anAcct);
	    }

	    /**
	     * 
	     * @param firstName
	     * @param lastName
	     * @param pin
	     * @return
	     */
	    // "User" indicates a returned value of type 'User'. Create a new user, and add to users list. @param firstName (user's first name), @param lastName (user's last name), @param pin (user's pin code). @return (the new User, as an object).
	    public User addUser(User user) {
	    	user.setBank(this);
	        user.setUUID(getNewUserUUID());
	        this.users.add(user);
	        // Print log message.
	        System.out.printf("New user %s with ID %s created.\n", user.getFirstName(),  user.getUUID()); // Print user information.
	        return user;
	    }

	    /**
	     * 
	     * @param userID
	     * @param pin
	     * @return
	     */
	    // Login with some correct credentials, by associating the input with a particular userID and pin (if valid). User Login. @param userID (uuid of user logging in), @param pin (user's pin code). @return User object (Object, and only if correct login details, else null returned).
	    public User userLogin(String userID, String pin) {
	        
	        // Search users list for userID.
	        for (User u: this.users) {
	            // Check userID is correct.
	            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
	                return u; // return user with correct id and pin provided.
	            }
	        }
	        return null; // return null is userID or Pin is incorrect.
	    }

	    /**
	     * 
	     * @return
	     */
	    // Get and return name of the Bank. @return name (the name of the bank).
	    public String getName() {
	        return this.name;
	    }
	    
	    
	    
	   public void PrintUserAccounts(User user) {
	    	 boolean accfound = false;
	    	 int contor=1;
	            // Iterate through this class' 'accounts' ArrayList, through all the elements of type 'Account', storing the current interated element into 'acc'.
	            for (Account acc: this.accounts) {
	                if ( user.getUUID().compareTo   ( acc.getUser().getUUID() )  == 0) {
	                    accfound = true;
	                    contor++;
	                    System.out.println(contor +" "+acc.getUUID()+" ");
	                    
	                    if(acc instanceof CardAccount)
	                    	 System.out.print(" CardAccount\n");
	                    if(acc instanceof EconomyAccount)
	                    	 System.out.print(" EconomyAccount\n");
	                }
	            }
	            
	            if(!accfound) {
	            	System.out.println("This user does not have any accounts at this banck\n"+"try again");
	            }
	    }
	    
	    
	    
	    public Account getAcountbyUUID(String tempUUID) {
	    	boolean accfound=false;
	    	for (Account acc: this.accounts) {
                if ( tempUUID.compareTo   ( acc.getUUID() )  == 0 ) {
                    accfound = true;
                    
                    if(acc instanceof CardAccount)
                    	 System.out.print(" CardAccount\n");
                    if(acc instanceof EconomyAccount)
                    	 System.out.print(" EconomyAccount\n");
                    return acc;
                }
            }
	    	
	    		System.out.println("Nu exista niciun cont in aceasta banca cu acest uuid");
	    		//Account acc=null;
	    		return null;
	    	
	    }
	}
