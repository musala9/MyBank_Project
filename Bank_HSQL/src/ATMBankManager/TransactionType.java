package ATMBankManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionType {
	public int ID;
	public String description;
	ResultSet rset;


public ArrayList<TransactionType> getTransactionTypes() {
	ArrayList<TransactionType> list =new ArrayList<TransactionType>();
	ResultSet rset=ATM.myDB.Select("Select * from TransactionType ORDER by ID");
	try {
		while(rset.next()) {
			TransactionType t=new TransactionType();
			t.ID=rset.getInt("ID");
			t.description=rset.getString("description");
			list.add(t);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
}
}