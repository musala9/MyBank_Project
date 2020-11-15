package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class MyDB implements iOperations{

	
	Connection connection;
	Statement statement;
	
	public boolean Connect(String connectionString, String user, String password) {
		 try {
			connection = DriverManager.getConnection(connectionString,user,password);
		} catch (SQLException e) {	
			e.printStackTrace();
			return false;
		}
		 
		 try {
			 statement = connection.createStatement();
			} catch (SQLException e) {	
				e.printStackTrace();
				return false;
			}
		return true;
	}

	
	public boolean Execute(String sql) { //update+delete+insert+create
    // Step 3: Execute a SQL SELECT query. The query result is returned in a 'ResultSet' object.
       System.out.println("The SQL statement is: " + sql + "\n");  // Echo for debugging
       int countInserted;
	try {
		countInserted = statement.executeUpdate(sql);
		 System.out.println(countInserted + " records inserted.\n");
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
      
       return true;
		
	}

	public ResultSet Select(String sql) {
       System.out.println("The SQL statement is: " + sql + "\n"); // Echo For debugging

       try {
		ResultSet rset = statement.executeQuery(sql);
		return rset;
	} catch (SQLException e) {
		
		e.printStackTrace();
		return null; //////!!!!!!!!!!
	}
		
	}
	
	public void Close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
