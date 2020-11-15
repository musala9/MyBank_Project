package dbHelper;

public class Tables {
	
	public static final String tblUser="CREATE TABLE User(" + 
		      "  ID INT PRIMARY KEY IDENTITY," + 
		      "  first_name VARCHAR(50)," + 
		      "  last_name VARCHAR(50)," +
		      "  code BINARY(16) ,"+     
	          "  pin VARCHAR(50));" ;
	 public static final String tblUserTrigger="CREATE TRIGGER trig BEFORE INSERT ON User REFERENCING NEW ROW AS NEW FOR EACH ROW SET NEW.code=UUID();"; 
	 
	public static final String tblAccount=  "CREATE TABLE Account(" + 
		      "  ID INT PRIMARY KEY IDENTITY," + 
		      "  ID_USER INT," + 
		      "  ID_AccountType INT," + 
		      "  code BINARY(16) ,"+     //uuid
		      "  balance DOUBLE DEFAULT 0 );" ;
    public static final String tblAccountTrigger="CREATE TRIGGER trig BEFORE INSERT ON Account REFERENCING NEW ROW AS NEW FOR EACH ROW SET NEW.code=UUID();";
    
	public static final String tblAccountType="CREATE TABLE AccountType(" + 
		      "  ID INT PRIMARY KEY IDENTITY," + 
		      "  operationfee DOUBLE,"+
		      "  withdrawalfee DOUBLE,"+      
		      "  description VARCHAR(10));" ;
	
	public static final String tblTransaction="CREATE TABLE Transaction(" + 
			 "  ID INT PRIMARY KEY IDENTITY," +  //////AUTO INCREMENT SECVENTA
			 "  Operation_ID INT,"  +
			 "  Amount INT,"+
			 "  ID_USER INT, "+
			 "  ID_ACCOUNT INT,"+
			 "  memo VARCHAR(30),"+
			 "  tdate DATE);";
	
	public static final String tblTransactionType="CREATE TABLE TransactionType (" + 
		      "  ID INT PRIMARY KEY IDENTITY," +  
		      "  description VARCHAR(50));" ;
	
	public static final String InsertUsers=" INSERT INTO User (first_name,last_name,pin) VALUES('mimi','trala','1234') ;"+
		                             " INSERT INTO User (first_name,last_name,pin) VALUES('laura','balaura','1234') ;"   ;
	
	public static final String InsertACCType="INSERT INTO AccountType (description,operationfee,withdrawalfee) VALUES ('Savings',0.1,2);"+
		                             	"INSERT INTO AccountType (description,operationfee,withdrawalfee) VALUES ('Credit',0.5,0.7);";
	
	public static final String InsertAccount="INSERT INTO Account (ID_USER,ID_AccountType) VALUES (1,1);"+
		                           	"INSERT INTO Account (ID_USER,ID_AccountType) VALUES (1,0);"+
		                           	"INSERT INTO Account (ID_USER,ID_AccountType) VALUES (0,1);"+
		                           	"INSERT INTO Account (ID_USER,ID_AccountType) VALUES (0,0);";
	
	public static final String InsertTransactionType="INSERT INTO TransactionType (description) VALUES ('Show Account Transaction History'); "+
			                                          "INSERT INTO TransactionType (description) VALUES ('Make a Withdrawel');               "+
	                                                  "INSERT INTO TransactionType (description) VALUES ('Make a Deposit ') ;                "+
	                                                  "INSERT INTO TransactionType (description) VALUES ('Transfer');                        ";
}
