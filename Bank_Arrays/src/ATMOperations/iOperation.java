package ATMOperations;

import java.util.Scanner;

import ATMBankManager.Account;
import ATMBankManager.User;


public interface iOperation {

	
	public void Execute(User user,Scanner in,Account account);
}
