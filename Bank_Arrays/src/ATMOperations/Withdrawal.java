package ATMOperations;

import java.util.Scanner;

import ATMBankManager.Account;
import ATMBankManager.CardAccount;
import ATMBankManager.EconomyAccount;
import ATMBankManager.User;

public class Withdrawal implements iOperation{


	

	@Override
	public void Execute(User user, Scanner in,Account account) {
		
		System.out.println("Operation-Withdrawal");
		//cand intra in functie deja stie contul 
		//suma din tastatura
		
		
		// Inits.
       
		double _amount;
        double acctBal;//account balance 
        String _memo;

        acctBal = account.getBalance();

        // Get the amount to withdraw.
        do {
            System.out.printf("Enter the _Amount to Withdraw (max. £%.02f): £", acctBal);
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
        System.out.print("Enter a _memo: ");
        _memo = in.nextLine();

        // Do the Withdrawel.
        account.addsoperation(this,_amount,_memo);
        //ce facem cu memo?
        
        account.applyfees(this);
   
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
