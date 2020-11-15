package ATMOperations;

import java.util.Scanner;

import ATMBankManager.Account;
import ATMBankManager.Transaction;
import ATMBankManager.User;

public class Withdrawal extends Transaction {


	//@Override
	public void Execute(Scanner in) {
		
		System.out.println("Operation-Withdrawal");
		
		// Inits.
       
		double _amount;
        double acctBal;//account balance 
        System.out.println(this.account.getID());
		acctBal = this.account.getBalance();
	    String _memo;

      
        // Get the amount to withdraw.
        do {
            System.out.printf("Enter the this.amount to Withdraw (max. £%.02f): £", acctBal);
            _amount = in.nextDouble();
            if (this.amount < 0) {
                System.out.println("this.amount must be greater than 0.");
            } else if (_amount > acctBal) {
                System.out.printf("this.amount must not be greater than\n" + "balance of £%.02f.\n", acctBal);
            }
        } while(this.amount < 0 || this.amount > acctBal);

        // Grab/or/Remove rest of the previous input line. - (Gobble up rest of previous input?). Prevents bad formatting.
        in.nextLine();

        // Get a Memo.
        System.out.print("Enter a _memo: ");
        _memo = in.nextLine();

        this.amount-=_amount;
        this.memo=_memo;
        
        // Do the Withdrawel.
       // this.account.addsoperation(2,this,- this.amount, _memo);
        //ce facem cu memo?
        
   
	}

}






//gunoi posibil refolosibil
/*
 * 
 * 
 *       do {
 * System.out.printf("Enter the number (1-%d) of the account (account's uuid)\n" + "to Withdraw from: ", user.numAccounts());
            fromAcct = in.nextInt()-1;
            if (fromAcct < 0 || fromAcct >= user.numAccounts()) {
                System.out.println("Invalid Account. Please try again.");
            }
        } while(fromAcct < 0 || fromAcct >= user.numAccounts());
 * 
 * 
 * 
 * 
 * user.addAcctTransaction(fromAcct, -1*amount, memo); // Decrease amount being withdrawn.
         if(user.getAccount(fromAcct) instanceof CardAccount) {
        	 user.addAcctTransaction(fromAcct, -0.08*user.getAccount(fromAcct).getBalance(), memo);
         }
         if(user.getAccount(fromAcct) instanceof EconomyAccount) {
        	 user.addAcctTransaction(fromAcct, -0.11*user.getAccount(fromAcct).getBalance(), memo);
         }
 * 
 * 
 * 
 */
