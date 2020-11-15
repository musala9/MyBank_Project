package ATMOperations;

import java.util.Scanner;

import ATMBankManager.Account;
import ATMBankManager.User;

public class Deposit implements iOperation{

   @Override
    public void Execute(User user,Scanner in,Account account) {
	System.out.println("Operation-Deposit");
		
	  // Inits.
		  
	      double _amount;
		  double acctBal;//account balance 
		  acctBal = account.getBalance();
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

        // Do the Deposit.
          account.addsoperation(this,_amount,_memo);
        //ce facem cu memo?
        
        account.applyfees(this);
		
	}

}

   






/*
 * 
 * // Get the account to depsosit into.
        do {
            System.out.printf("Enter the number (1-%d) of the account\n" + "to deposit into: ", user.numAccounts());
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
 */
