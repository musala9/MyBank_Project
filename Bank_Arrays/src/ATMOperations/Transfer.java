package ATMOperations;

import java.util.Scanner;

import ATMBankManager.Account;
import ATMBankManager.User;

public class Transfer implements iOperation{

	private Account toAccount;
	String tempUUID;
	
	public void  Execute(User user,Scanner in,Account account) {
		System.out.println("Operation-Transfer");
		
		
		  // Inits.
		
		      double _amount;
			  double acctBal;//account balance 
			  acctBal = account.getBalance();
			  String _memo;

			  
	        
	        
       //Get the account ot transfer to
	        
	      System.out.println("Alege contul in care transferi banii:");
	    	 user.getBank().PrintUserAccounts(user);
	    	 System.out.println("tasteaza uuid-ul contului dorit");	
	    	 //trebuie sa gasim contul din banca folosindu.ne de uuid
	    	  tempUUID=in.next();
	    	 toAccount=user.getBank().getAcountbyUUID(tempUUID);
	    	
	    	 
	    	 
	    	 
        // Get the _amount to transfer.
	    	 
        do {
            System.out.printf("Enter the _amount to Transfer (max. £%.02f): £", acctBal);
            _amount = in.nextDouble();
            if (_amount < 0) {
                System.out.println("_amount must be greater than 0.");
            } else if (_amount > acctBal) {
                System.out.printf("_amount must not be greater than\n" + "balance of £%.02f.\n", acctBal);
            }
        } while(_amount < 0 || _amount > acctBal);
    
        
        
        
     // Grab/or/Remove rest of the previous input line. - (Gobble up rest of previous input?). Prevents bad formatting.
        
        in.nextLine();

        
        
        
        // Get a Memo.
        
        System.out.print("Enter a memo for both accounts: ");
        _memo = in.nextLine();
        
        
        
        // Perform the Transfer.
	    account.addsoperation(this,-_amount,_memo);	   
	    toAccount.addsoperation(this, _amount, _memo+" transfer to this account");     
	     account.applyfees(this);

	}


}


































/*
 * 
 *  // Get account to transfer to.
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" + "to transfer to: ", user.numAccounts());
            toAcct = in.nextInt()-1;
            if (toAcct < 0 || toAcct >= user.numAccounts()) {
                System.out.println("Invalid Account. Please try again.");
            }
        } while(toAcct < 0 || toAcct >= user.numAccounts());

 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
