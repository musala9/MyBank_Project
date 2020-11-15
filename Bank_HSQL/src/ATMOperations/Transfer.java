package ATMOperations;

import java.util.Scanner;

import ATMBankManager.Account;
import ATMBankManager.Transaction;
import ATMBankManager.User;

public class Transfer extends Transaction{

	private Account toAccount;
	String tempUUID;
	
	public void  Execute(Scanner in) {
		System.out.println("Operation-Transfer");
		
		
		  // Inits.
		
		      double _amount;
			  double acctBal;//account balance 
			  acctBal = this.account.getBalance();
			  String _memo;

			  
			  //Get the account ot transfer to
	          
	         	
		     	 System.out.println("alege cont:");
		     	 bank.PrintUserAccounts(user);
		     	 System.out.println("tasteaza ID-ul contului in care doresti sa transferi");
		     	 
		     	 
		     	 // gasim contul din banca folosindu.ne de ID
		     	
		     	 int tempID = in.nextInt();
		     	 TOaccount=bank.getAcountbyID(tempID,user);
		     	 
		     	 
		     	 if(account==null) {
		     		 System.out.println("Type another ID");
		     	 }
		     	 
		     	 
		     	 else {  
	 
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
        
        this.amount=-_amount;
        this.memo=_memo;
       

	}

	}
}

