package ATMBankManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

import ATMOperations.Withdrawal;
import ATMOperations.iOperation;
import db.MyDB;

public class Account {
	private Bank bank;
	
	private int ID;
	private User user;
	private AccountType accountType;
	private byte code;
	private double balance;
    
	private Object timestamp;
	
    boolean b;
    
    
   
   public Account(int ID,User user,AccountType accountType) {
	   this.ID=ID;
	   this.user=user;
	   this.accountType=accountType;
	   
   }; 

    
    
    public void setID(int aID) {
    	this.ID=aID;
    }
    
    public int getID() {
    	return this.ID;
    }
  

    public User getUser() {
    	return this.user;
    }
	
	public Object getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Object timestamp) {
		this.timestamp = timestamp;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
	
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
    public double getBalance() {
        return this.balance;
    }
    
    

    
}
   

