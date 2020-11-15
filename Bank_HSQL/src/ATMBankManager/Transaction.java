package ATMBankManager;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import ATMOperations.iOperation;

public class Transaction implements iOperation{

	public int ID;
	public TransactionType transactionType;
	public double amount;
	
	public User user;
	public Account account;
	public String memo; // A note attached to a transaction, explaining nature of transaction.
	public Date tdate;
	public iOperation operation;
    ///??   
    
	public Bank bank;
	public Account TOaccount;
 

    /**
     * 
     * @param amount
     * @param inAccount
     */
     
    // Transaction constructor in case of a transaction with No optional Memo attached to it. Set all properties, and Memo to "".
   
    // Create a new Transaction. @param amount (amount involved in transaction), @param inAccount (the account the transaction belongs to).
    
    public Transaction() {};   
    public double getAmount() {
        return this.amount;
    }
    
    
    public boolean Save() {
    	boolean b=true;
    	if(this.amount!=0) {
    		this.amount=this.amount *(100  -  this.account.getAccountType().operationfee  -   this.account.getAccountType().withdrawalfee)/100;
    		
	    	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	    	Date date = new Date(System.currentTimeMillis());
	    
	    	String sql ="INSERT INTO Transaction ( Amount,Operation_ID,ID_USER,ID_ACCOUNT,memo,tdate) ";
	    		   sql+=String.format("VALUES (%f,%d,%d,%d,'%s',CURRENT_DATE )",this.amount,this.transactionType.ID,this.user.getID(),this.account.getID(),this.memo,date);
	    			
	    	 b=ATM.myDB.Execute(sql);   	    	  	
	    	sql=String.format("UPDATE Account SET balance = balance+ %f  WHERE ID =%d",this.amount,this.account.getID() );
	    	b=ATM.myDB.Execute(sql);
	    	
	    	
	    	
	    	
	    	boolean c=true;
	    	//only for transfers
	    	if(this.transactionType.ID==3) {
	    		System.out.println(" Performing the transfer.Processing");
	    		if(this.TOaccount!=null) {
	    		                 this.amount= -this.amount;
			    		    
			    		    	String sql2 ="INSERT INTO Transaction ( Amount,Operation_ID,ID_USER,ID_ACCOUNT,memo,tdate) ";
			    		    		   sql2+=String.format("VALUES (%f,%d,%d,%d,'%s',CURRENT_DATE )",this.amount,this.transactionType.ID,this.user.getID(),this.TOaccount.getID(),this.memo,date);
			    		    			
			    		    		   System.out.println(this.TOaccount.getID());
			    		    		   
			    		    	 c=ATM.myDB.Execute(sql2);   	    	  	
			    		    	sql2=String.format("UPDATE Account SET balance = balance+ %f  WHERE ID =%d",this.amount,this.TOaccount.getID() );
			    		    	c=ATM.myDB.Execute(sql2);
	    		}
	    		else {
	    			System.out.println("TOaccount is null boyz");
	    		}
	    	
    	}
	    
	    	b= b && c;
   
    }
    	return b ;
    }

	@Override
	public void Execute(Scanner in) {
		// TODO Auto-generated method stub
		
	}
    
    

}