package ATMBankManager;

import java.util.ArrayList;
import java.util.Scanner;

import ATMOperations.*;
import db.Hsql;
import db.MyDB;

public class ATM {
	static final String connectionMysql= "jdbc:mysql://127.0.0.1:3306/myBank";//127.0.0.1=localhost  --conventie
    static final String connectionHsql= "jdbc:hsqldb:mem:sampledb";
    //"jdbc:hsqldb:file:sampledb";
    static Bank theBank;
    public static MyDB myDB;
    
	public static void main(String[] args) {
		myDB=new Hsql();
		boolean b=myDB.Connect(connectionHsql, "", "");
		if (b)
			System.out.println("connection succesed");
	
		
		// TODO Auto-generated method stub      // Init Scanner.
        Scanner in = new Scanner(System.in);

        // Init Bank && create database
        theBank = new Bank("Bank of Tom"); 
        
        User curUser;   //temporary user for the while loop
        while (true) {
        	curUser=ATM.mainMenuPrompt(theBank, in);
            // Stay inside login prompt until succesffuly login. Hence indefinite loop.
            // Stay inside main menu until user quits. Hence indefinite loop.
            ATM.printUserMenu(curUser, in);
        }
	}
 
    
	
   
    /**
     * 
     * @param theBank
     * @param in
     * @return
     */
    // Interface.
    public static User mainMenuPrompt(Bank bank, Scanner in) { // Static keyword can be used here, because there's no data in this (ATM) class
        // Inits.
        int ID;
        String pin;
        User authUser=null;

        // Prompt user for userID and Pin combo, until a set is resolved.
        while(authUser == null) {
            System.out.printf("\n\nWelcome to %s\n\n", bank.getName());
            System.out.print("Enter user ID: \n ");
            ID = in.nextInt();
            System.out.print("Enter pin: \n");
            pin = in.next();

            // Try to get a match for UserID and Pin combo entered.
            authUser = bank.userLogin(ID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID and/or Pin entered. Try Again.");
            }

        } ; // Loop until 'authUser' has found a successful User login, i.e. Now has a value, (so isn't null).

        return authUser; // Return the User that has been stored in authUser, after being validated.
    }

    
    
    
    public static void printUserMenu(User user, Scanner in) { // "Static" methods allow for self reference, i.e. 'ATM.<something>();'.
        
        // Init menu list 
        int choice;
        ArrayList<TransactionType> types=new TransactionType().getTransactionTypes();
        int menu_size=types.size()+1;
        
        
        // Enter option
        
          do {
            System.out.printf("Welcome %s, what action would you like to take?\n Press the corresponding number to choose an item from the menu below.\n", user.getFirstName());

            for(TransactionType t : types) {
            	System.out.println(String.format(" %d) %s .",t.ID+1,t.description));
            }
            System.out.println(String.format(" %d) QUIT.",menu_size));
            System.out.println();
            System.out.print("Enter your choice : ");
            choice = in.nextInt();

            if (choice < 1 || choice > menu_size) {
                System.out.println(String.format("Invalid input. Please input 1-%d.",menu_size));
            }
        } while(choice < 1 || choice > menu_size);
         
          TransactionType tranzType=null;
          if(choice>=1  && choice<=4) {                        //in consola se introduce 1-4
        	  tranzType=types.get(choice-1);                   //in db e 0-3
          }         
          else {
        	  if(choice==5) {
        		  in.nextLine();
                  System.out.println("good bye");
                  System.exit(1); // Exit app.
        	  }
          }
          
          
        //alege cont 
          
         	
     	 System.out.println("alege cont:");
     	 theBank.PrintUserAccounts(user);
     	 System.out.println("tasteaza ID-ul contului dorit");
     	 
     	 
     	 // we find the account by ID 
     	
     	 int tempID = in.nextInt();
     	 Account account=theBank.getAcountbyID(tempID,user);
     	 
     	 
     	 if(account==null) {
     		 System.out.println("Type another ID");
     	 }
     	 
     	 
     	 else {
     	       // Process the choice.
     		 
             Transaction tranz=null;
        	 switch(tranzType.ID) {
             case 0:
            	 tranz=new ShowTransactionHistory();
                 break;
             case 1:
            	 tranz=new Withdrawal();
                 break;
             case 2:
            	 tranz=new Deposit();
                 break;
             case 3:
            	 tranz=new Transfer();
                 break;
             default:
                 System.out.println("Invalid input. Please input 1-5.");
                 return;
          }
           
             tranz.user=user;
             tranz.account=account;
             tranz.transactionType=tranzType;
             
             tranz.bank=theBank;
             
             tranz.Execute(in);
             boolean b=tranz.Save();
             
             
             if(b) {
            	 System.out.println("Thank you for using our bank");
             }
             else {
            	 System.out.println("Transaction failed");
             }
             tranz=null;
     	 }
  
 
        
       
        // Redisplay this menu unless the user quits.
        if (choice !=5) {
            ATM.printUserMenu(user, in); // Recursive call (Recursion). Brings up menu again.
        }
     	 
    }
  
     	 
     	 ///log in or sign up
         public static User LogInSignUp(Bank theBank, Scanner in){
         	String userID;
         	String firstName;
         	String lastName;
             String pin,pin2;
             User authUser;
             Account authAccount;
             
         	int choice;
             
           do {
         	System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
             System.out.print("Enter: ");
             System.out.print("1) Sign up"+"\n");
             System.out.print("2) Log in" +"\n");
             System.out.println("- >");
             choice =in.nextInt();
             
             if (choice != 1 && choice != 2) {
                 System.out.println("Invalid input. Please input 1 or 2.");
             }
         } while(choice != 1 && choice != 2); 
             
             if(choice==1) {
             	System.out.println("first name ");
                 firstName = in.nextLine();
                 
                 System.out.println("last name ");
                 lastName = in.nextLine();
                 do {
                 	System.out.print("Enter pin: ");
                     pin = in.nextLine();
                     System.out.print("Verify pin: ");
                     pin2 = in.nextLine();
                     
                     if(pin.compareTo(pin2)!=0 )
                     	System.out.println("pin doesn't match/Try again");
                     
                 }while(pin.compareTo(pin2)!=0);
                 
                 
                // authUser=new User(firstName, lastName, pin);          
                 System.out.println("you have succesfully signed up");
                 
                 return null;// authUser;
             }
             else {
             	authUser= ATM.mainMenuPrompt(theBank, in);
             	
             	return authUser;
             }
             
             
         }

   }
