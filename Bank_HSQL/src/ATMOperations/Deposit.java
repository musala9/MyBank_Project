package ATMOperations;

import java.util.Scanner;

import ATMBankManager.Account;
import ATMBankManager.Transaction;
import ATMBankManager.User;

public class Deposit extends Transaction {

	
  // @Override
    public void Execute(Scanner in) {
    	
	System.out.println("Operation-Deposit");
		
	  // Inits.
		  
	      double _amount;
		  double acctBal;//account balance 
		  System.out.println(this.account.getID());
		  acctBal = this.account.getBalance();
		  String _memo;

		       
      // Get the amount to deposit.
        do {
            System.out.print("Enter the amount to Deposit: £");
            _amount = in.nextDouble();
            if (_amount < 0) {
                System.out.println("Amount must be greater than 0.");
            }
        } while(_amount < 0);

        // Grab/or/Remove rest of the previous input line. - (Gobble up rest of previous input?). Prevents bad formatting.
        in.nextLine();

        // Get a Memo.
        System.out.print("Enter a memo: ");
        _memo = in.nextLine();
   
        this.amount=_amount;
        this.memo=_memo;
      
        // Do the Deposit.
         // account.addsoperation(3,this,_amount,_memo);
        //ce facem cu memo?
        
		
	}

}

   






