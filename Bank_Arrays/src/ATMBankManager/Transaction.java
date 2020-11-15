package ATMBankManager;


import java.util.Scanner;

import ATMOperations.Deposit;
import ATMOperations.ShowTransactionHistory;
import ATMOperations.Transfer;
import ATMOperations.Withdrawal;
import ATMOperations.iOperation;

public class Transaction {
    private double amount;
    private String memo; // A note attached to a transaction, explaining nature of transaction.
    private String tempUUID;
    private Account inAccount; // Account transaction was performed.
    private String AUUID;      //Account UUID
    // Overloading contructors: Two constructors for a new Transaction. The correct one is deduced by the arguments passed with a call, done by Java/JVM.

    /**
     * 
     * @param amount
     * @param inAccount
     */
    // Transaction constructor in case of a transaction with No optional Memo attached to it. Set all properties, and Memo to "".
   
    // Create a new Transaction. @param amount (amount involved in transaction), @param inAccount (the account the transaction belongs to).
    
    public Transaction(int choice,User user,Scanner in) {
    	iOperation operation=new Deposit();
    	
    	 switch(choice) {
         case 1:
             operation=new ShowTransactionHistory();
             break;
         case 2:
         	operation=new Withdrawal();
             break;
         case 3:
         	operation=new Deposit();
             break;
         case 4:
           operation=new Transfer();
             break;
         case 5:
             // Grab/or/Remove rest of the previous input line. - (Gobble up rest of previous input?). Prevents bad formatting.
             in.nextLine();
             System.out.println("good bye");
             System.exit(1); // Exit app.
             break;
         default:
             System.out.println("Invalid input. Please input 1-5.");
             //ATM.printUserMenu(theUser, in); // Recursive call (Recursion). Brings up menu again.
             return;
     }
    	//alege cont
    	 System.out.println("alege cont:");
    	 user.getBank().PrintUserAccounts(user);
    	 System.out.println("tasteaza uuid-ul contului dorit");
    	 
    	 //trebuie sa gasim contul din banca folosindu.ne de uuid
    	
    	 tempUUID=in.next();
    	 Account _tempacc=user.getBank().getAcountbyUUID(tempUUID);
    	 if(_tempacc==null) {
    		 System.out.println("Type another uuid");
    	 }
    	 else {
    		 operation.Execute(user, in,_tempacc );
    	 }
    	
    	 //aici ar trebui sa adaugam informatiile in soperations
    	 
    	 
    	//ATM.printUserMenu(user, in);
    }
    
    
    
    public double getAmount() {
        return this.amount;
    }

}