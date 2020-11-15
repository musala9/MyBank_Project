package ATMOperations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import ATMBankManager.ATM;
import ATMBankManager.ATM.*;
import ATMBankManager.Account;
import ATMBankManager.Transaction;
import ATMBankManager.TransactionType;
import ATMBankManager.User;

public class ShowTransactionHistory extends Transaction  {

	
    /**
     * 
     * 
     * @param in
     */
	@Override
	public void Execute(Scanner in) {
		System.out.println("ShowTransactionHistory");
		ResultSet rset=ATM.myDB.Select("SELECT * FROM Transaction JOIN TransactionType ON Transaction.Operation_ID=TransactionType.ID WHERE Transaction.ID_ACCOUNT = "+this.account.getID() +" ;" );
    	try {
			while(rset.next()) {
				try {
					String op=rset.getString("TransactionType.description");
					op=String.format("%1$-25s", op);
					String t=String.format("%d\t%s\t%f\t%s\t%s", rset.getInt("ID"),op,rset.getDouble("Amount"),rset.getString("memo"),rset.getDate("tdate").toLocalDate());
					System.out.println(t);
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("couldn't print account hystory");
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

 		
	}
}
