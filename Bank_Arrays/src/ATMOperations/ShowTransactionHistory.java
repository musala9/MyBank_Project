package ATMOperations;

import java.util.Scanner;

import ATMBankManager.Account;
import ATMBankManager.User;

public class ShowTransactionHistory implements iOperation {

	private User _user;
    private Account _account;
    
    /**
     * 
     * @param theUser
     * @param in
     */
	@Override
	public void Execute(User user,Scanner in,Account account) {
		System.out.println("ShowTRansactionHistory");

        // Print the transaction history.
       account.printAcctTransHistory();
		
	}
}
