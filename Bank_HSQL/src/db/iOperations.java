package db;

import java.sql.ResultSet;

public interface iOperations {
	public boolean Connect(String connectionString,String user,String password);
	public boolean Execute(String sql);
	public ResultSet Select(String sql);

}
