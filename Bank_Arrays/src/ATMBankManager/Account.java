package ATMBankManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

import ATMOperations.Withdrawal;
import ATMOperations.iOperation;

public class Account {
	private double operationfee;
	private double withdrawalfee;
    private String name;
    private String uuid; // Account's uuid (ID). Different from user's.
    private User holder; // Account holder (user who owns account).
  //  private ArrayList<iOperation> operations; // List of operations
    private ArrayList <String> soperations;
    private double balance;
	private Object timestamp;
    /**
     * 
     * @param name
     * @param holder
     * @param theBank
     */
    // Create a new Account. @param name (String Name of Account), @param holder (User object that owns/hold this account), @param theBank (bank that issues this Account).
    public Account(String name, User holder) {
        
        // Set account name and holder.
        this.name = name;
        this.holder = holder;

        // Get new account uuid.
        this.uuid=this.holder.getBank().getNewAccountUUID();
       // this.uuid = this.holder.getBank().getNewAccountUUID();

        // init transactions.
        this.soperations = new ArrayList<String>();
        
        
        //adaugam in lista de conturi a bancii
        this.holder.getBank().addAccount(this);
    }

    public User getUser() {
    	return this.holder;
    }
	
	public double getOperationfee() {
		return operationfee;
	}
	
	
	public void setOperationfee(double operationfee) {
		this.operationfee = operationfee;
	}
	
	
	public double getWithdrawalfee() {
		return withdrawalfee;
	}
	
	
	public void setWithdrawalfee(double withdrawalfee) {
		this.withdrawalfee = withdrawalfee;
	}
	
    public String getUUID() {
        return this.uuid;
    }
    
    
    public double getBalance() {
        return balance;
    }
    
    
    public void addsoperation(iOperation operation,double amount,String memo) {
    	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    	Date date = new Date(System.currentTimeMillis());
    	this.soperations.add(operation+" "+amount+" "+memo+" "+formatter.format(date));
    	this.balance+=amount;
    }
    
   

    
    public String getSummaryLine() {
         return String.format("%s account balance: %f  holder's name: %s", this.uuid, balance,this.name); // Similar to 'printf()'. "%.02f" Wildcard for a double with 2 decimal places. 
    }

 
    
    // Print the Operations history for the Account.
    public void printAcctTransHistory() {
        System.out.printf("\nOperations history for account %s\n", this.uuid);
        for (int i = this.soperations.size()-1; i >= 0; i--) {
        	System.out.println(this.soperations.get(i));
           // System.out.println(this.operations.get(i).getSummaryLine());
        }
        System.out.println(this.getSummaryLine());
    }
    
    
    public void applyfees(iOperation operation) {
    	this.balance-=this.operationfee;
    	
    	if(operation instanceof Withdrawal) {
    		this.balance-=this.withdrawalfee;
    	}
    }
    
}
   
