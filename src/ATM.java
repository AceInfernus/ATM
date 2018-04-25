
public class ATM {

	private boolean userAuthenticated;
	private int currentAccountNumber;
	private Screen screen;
	private Keypad keypad;
	private CashDispenser cashDispenser;
	private BankDatabase bankDatabase;
	private DepositSlot depositSlot;
	
	// main menu options represented by constants
	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;
	
	//default constructor ATM
	public ATM() {
		userAuthenticated = false;
		currentAccountNumber = 0;
		screen = new Screen();
		keypad = new Keypad();
		cashDispenser = new CashDispenser();
		depositSlot = new DepositSlot();
		bankDatabase = new BankDatabase();
		
	} // end default constructor ATM
	
	// start ATM
	public void run () {
		//welcome and authenticate user, perform transactions
		
		while (true) {
			
			while (!userAuthenticated) {
				screen.displayMessageLine ("\n Welcome!");
				authenticateUser();
			} //end  while
			
			performTransactions();
			userAuthenticated = false; //reset before next ATM session
			currentAccountNumber = 0; //reset for next session
			screen.displayMessageLine("\n Thank you! Goodbye!");
			
		} // end while
	} //end method run
	
	//attempts to authenticate user against database
	
	private void authenticateUser() {
		screen.displayMessage ("\n Please enter your account number: ");
		int accountNumber = keypad.getInput();
		screen.displayMessage("\n Enter your PIN: ");
		int pin = keypad.getInput(); 
		
		
		// set userAuthenticated to boolean value returned by database
		
		userAuthenticated = bankDatabase.authenticateUser (accountNumber, pin);
		
		// check if authentication is successful
		if (userAuthenticated) {
			currentAccountNumber = accountNumber;
		} //end if
		
		else {
			screen.displayMessageLine ("Invalid account number or PIN. Please try again.");
			
		} 
	}// end method authenticateUser
		
		
		//display main menu and perform transactions
		
		private void performTransactions() {
			//local variable to store transaction currently being processed
			
			Transaction currentTransaction = null;
			boolean userExited = false; // user has not chosen to exit
			
			//loop while user has not chosen option to exit system
			while (!userExited) {
				//show main menu and get user selection
				int mainMenuSelection = displayMainMenu();
				
				//decide how to proceed based on user's menu selection
				
				switch (mainMenuSelection) {
				case BALANCE_INQUIRY:
				case WITHDRAWAL:
				case DEPOSIT:
				
				//initialize as new object of chosen type
				
				currentTransaction = createTransaction( mainMenuSelection);
				
				currentTransaction.execute(); 
				break;
				
				case EXIT:
					screen.displayMessageLine ( "\n Exiting the system.");
					userExited = true;
					break;
					
					default: //user did not enter an integer from 1-4
						screen.displayMessageLine ( "\n Please enter a valid selection.");
						break;
						
						
				} //end switch
			} //end while
			
		} // end method performTransactions
		
		//display the main menu and return an input selection
		private int displayMainMenu(){
			
			screen.displayMessageLine("\n Main Menu:");
			screen.displayMessageLine("1- View Account Balance");
			screen.displayMessageLine("2- Withdraw cash");
			screen.displayMessageLine("3- Deposit funds");
			screen.displayMessageLine("4- Exit \n ");
			screen.displayMessageLine("Enter a choice: ");
			return keypad.getInput(); // return user's selection
		} // end method displayMainMenu
		
		// return object of specified Transaction subclass
		private Transaction createTransaction( int type ) {
			
			Transaction temp = null; 
			//determine which type of Transaction to create
			
			switch (type) {
			case BALANCE_INQUIRY:
				temp = new BalanceInquiry( currentAccountNumber, screen, bankDatabase);
				break;
				
			case WITHDRAWAL: 
				temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
				break;
			case DEPOSIT:
				temp = new Deposit ( currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
				break;
			} // end switch (type)
			return temp; // return the newly created object
		} //end method createTransaction
	} 
	
	

