package ATMBankManager;

import java.util.Scanner;

import ATMOperations.*;

public class ATM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub      // Init Scanner.
        Scanner in = new Scanner(System.in);

        // Init Bank.
        Bank theBank = new Bank("Bank of Tom");

        // Add a new User, called 'aUser', to the Bank
        User aUser = new User("John", "Doe", "1234");//first name,surname,pin
        aUser=theBank.addUser(aUser);
        
        // adaugam doua conturi userului pe care le adugam in lista bancii
        CardAccount newCardAccount = new CardAccount("Card", aUser);//ii face automat uuid
        EconomyAccount newEconomyAccount = new EconomyAccount("Economy", aUser);//ii face automat uuid
        
        
        
        User curUser;   //user temporar pentru bucla
        while (true) {
        	curUser=ATM.LogInSignUp(theBank, in);
            // Stay inside login prompt until succesffuly login. Hence indefinite loop.
            // Stay inside main menu until user quits. Hence indefinite loop.
            ATM.printUserMenu(curUser, in);
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
            
            
            authUser=new User(firstName, lastName, pin);
            authUser = theBank.addUser(authUser);
            
            // Add a new "Checking" Account for our User.
            CardAccount newCardAccount = new CardAccount("Card", authUser);//ii face automat uuid
            EconomyAccount newEconomyAccount = new EconomyAccount("Economy", authUser);//ii face automat uuid
            
            theBank.addAccount(newCardAccount);
            theBank.addAccount(newEconomyAccount);
            
            System.out.println("Your user uuid is"+authUser.getUUID());
            System.out.println("you have succesfully signed up");
            
            return authUser;
        }
        else {
        	authUser= ATM.mainMenuPrompt(theBank, in);
        	
        	return authUser;
        }
        
        
    }
    /**
     * 
     * @param theBank
     * @param in
     * @return
     */
    // Interface.
    public static User mainMenuPrompt(Bank bank, Scanner in) { // Static keyword can be used here, because there's no data in this (ATM) class?
        // Inits.
        String useruuid;
        String pin;
        User authUser=null;

        // Prompt user for userID and Pin combo, until a set is resolved.
        while(authUser == null) {
            System.out.printf("\n\nWelcome to %s\n\n", bank.getName());
            System.out.print("Enter user ID: \n ");
            useruuid = in.nextLine();
            System.out.print("Enter pin: \n");
            pin = in.nextLine();

            // Try to get a match for UserID and Pin combo entered.
            authUser = bank.userLogin(useruuid, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID and/or Pin entered. Try Again.");
            }

        } ; // Loop until 'authUser' has found a successful User login, i.e. Now has a value, (so isn't null).

        return authUser; // Return the User that has been stored in authUser, after being validated.
    }

    
    
    
    public static void printUserMenu(User user, Scanner in) { // "Static" methods allow for self reference, i.e. 'ATM.<something>();'.
        
        // Init.
        int choice;

        
        
        // User menu.
  
        do {
            System.out.printf("Welcome %s, what action would you like to take?\nPress the corresponding number to choose an item from the menu below.\n", user.getFirstName());
            System.out.println(" 1) Show Account Transaction history.");
            System.out.println(" 2) Make a Withdrawel.");
            System.out.println(" 3) Make a Deposit.");
            System.out.println(" 4) Transfer.");
            System.out.println(" 5) QUIT.");
            System.out.println();
            System.out.print("Enter your choice (1-5): ");
            choice = in.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid input. Please input 1-5.");
            }
        } while(choice < 1 || choice > 5);
         

        // Process the choice.
        Transaction tranz=new Transaction(choice,user,in);
        
       

        // Redisplay this menu unless the user quits.
        if (choice !=5) {
            ATM.printUserMenu(user, in); // Recursive call (Recursion). Brings up menu again.
        }
    }

   }
