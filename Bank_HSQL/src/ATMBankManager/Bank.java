package ATMBankManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


	public class Bank {
	    // Bank's details.
	    private String name;
	   
	    
	    
	    /**
	     * 
	     * @param name
	     */
	    // Bank Constructor, to create a new Bank object, with empty users and accounts lists. @param name (the name of the bank).
	    public Bank(String name) {
	        this.name = name;
	        

			boolean b=ATM.myDB.Execute(dbHelper.Tables.tblUser);
			b=ATM.myDB.Execute(dbHelper.Tables.tblAccount);		
			b=ATM.myDB.Execute(dbHelper.Tables.tblAccountTrigger);
			b=ATM.myDB.Execute(dbHelper.Tables.tblTransaction);
			b=ATM.myDB.Execute(dbHelper.Tables.tblTransactionType);
			b=ATM.myDB.Execute(dbHelper.Tables.tblAccountType);
			b=ATM.myDB.Execute(dbHelper.Tables.InsertACCType);
			b=ATM.myDB.Execute(dbHelper.Tables.InsertUsers);
			b=ATM.myDB.Execute(dbHelper.Tables.InsertAccount);
			b=ATM.myDB.Execute(dbHelper.Tables.InsertTransactionType);
			
	    }

	    /**
	     * 
	     * @param userID
	     * @param pin
	     * @return
	     */
	    // Login with some correct credentials, by associating the input with a particular userID and pin (if valid). User Login. @param userID (uuid of user logging in), @param pin (user's pin code). @return User object (Object, and only if correct login details, else null returned).
	    public User userLogin(int userID, String pin) {
	    	User user=null;
	    	String Upin="";
	    	String firstName="";
	    	String lastName="";
	    	ResultSet rset=ATM.myDB.Select("SELECT pin,first_name,last_name FROM User WHERE ID="+userID+";");
	        try {
					if(rset.next()) {
							Upin=rset.getString("pin");
							firstName=rset.getString("first_name");
							lastName=rset.getString("last_name");
					}		
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        
	        if(Upin.equals(pin)) {
	        	user=new User(userID,pin,firstName,lastName);
	        	return user;
	        }
	       else {
	    	   System.out.println("authentification failed.try again");
	    	   return user;
	       }
	        
	        
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
	    	int uID=user.getID();
	    	ResultSet rset=ATM.myDB.Select("SELECT Account.ID, Account.balance,AccountType.description "+
	    			"FROM Account "+
	    			"INNER JOIN AccountType ON Account.ID_AccountType = AccountType.ID WHERE Account.ID_USER="  +  uID  +  ";");
	    	
	    	 System.out.println("The records selected are:");
	         int rowCount = 0;
			 try {
				while(rset.next()) {   // Move the cursor to the next row, return false if no more row
				        int ID=rset.getInt("ID");
				         double balance=rset.getDouble("balance");
					      String AccountType=rset.getString("AccountType.description");

				        System.out.println(ID + ", " + balance + ", " + AccountType);
				        ++rowCount;
				     }
				 System.out.println("Total number of records = " + rowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    public User getUserbyID(int uID) {
	    	User user=new User();
	    	ResultSet rset=ATM.myDB.Select(String.format("SELECT * FROM user WHERE ID=%d",uID));
	    	try {
	    		while(rset.next()) {
	    			user.setID(rset.getInt("ID"));
	    			user.setFirstName(rset.getString("first_name"));
	    			user.setLastName(rset.getString("last_name"));
	    			
	    		}
	    		
	    	}catch(Exception ex) {
	    		System.out.println("user not found bank.getUserbyID");
	    	}
	    	return user;
	    }
	    
	    public Account getAcountbyID(int aID,User user) {
	    	ResultSet rset=ATM.myDB.Select("SELECT AccountType.*, Account.balance,Account.ID "+
	    			"FROM Account "+
	    			"INNER JOIN AccountType ON Account.ID_AccountType = AccountType.ID WHERE Account.ID="  +  aID  +  ";");
			 try {
				while(rset.next()) {   // Move the cursor to the next row, return false if no more row
		                  
					      AccountType accountType=new AccountType();
					      accountType.ID=rset.getInt("AccountType.ID");
					      accountType.name=rset.getString("AccountType.description");
					      accountType.withdrawalfee=rset.getDouble("AccountType.withdrawalfee");
						  accountType.operationfee=rset.getDouble("AccountType.operationfee");
						 
						  
					      Account account=new Account(rset.getInt("Account.ID"),user,accountType);
					      account.setBalance(rset.getDouble("balance"));
				        return account;
				     }
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("there was a problem with finding the account");
				return null;
			}
	    	return null;
	    }
	    
	    
	   
	}
