
//Class Withdrawal represents an ATM withdrawal transaction
public class Withdrawal extends Transaction {
	
	private int amount; // withdrawal amount
	private Keypad keypad;
	private CashDispenser cashDispenser;
	
	//constant corresponding to menu option 'cancel'
	
	private final static int CANCEL = 6;
	//Constructor
	public Withdrawal (int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
		super( userAccountNumber, atmScreen, atmBankDatabase);
		keypad = atmKeypad;
		cashDispenser= atmCashDispenser;
		
		
	} // end constructor
	
	//perform transaction
	@Override
	public void execute(){
		boolean cashDispensed = false;
		double availableBalance;
		
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		// loop until cash is dispensed or user cancels
		
		do {
			//obtain withdrawal amount from user
			amount  = displayMenuofAmounts();
			//check whether user chose a withdrawal amount or cancelled
			if (amount != CANCEL) {
				availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
				
				//check whether user has enough money in the account
				if (amount <= availableBalance) {
					//if cash dispenser has enough money
					if (cashDispenser.isSufficientCashAvailable(amount)) {
						//update the account involved to reflect the withdrawal
						bankDatabase.debit( getAccountNumber(), amount);
						
						cashDispenser.dispenseCash(amount);
						cashDispensed = true; 
						
						screen.displayMessageLine("Please take your cash  now!");
						
					}//end if
					else{
						screen.displayMessageLine("Insufficient cash available in ATM");
					}
			
					
				}// end if
				else { // user chose cancel option
					screen.displayMessageLine("\n Transaction cancelled");
				}
			}	
			} while( !cashDispensed);
		}//end method execute
	
	
	//display a menu of withdrawal amounts and option to cancel
	//return chosen amount or 0 if user cancels
	
	private int displayMenuofAmounts(){
		
		int userChoice = 0;
		Screen screen = getScreen();
		// array of amounts to correspond to menu numbers
		int[] amounts = { 0, 20, 40, 60, 100, 200 };
		
		while(userChoice == 0){
			screen.displayMessageLine("\n Withdrawal Menu: ");
			screen.displayMessageLine("1 - $20");
			screen.displayMessageLine("2 - $40");
			screen.displayMessageLine("3 - $60");
			screen.displayMessageLine("4 - $100");
			screen.displayMessageLine("5 - $200");
			screen.displayMessageLine("6 - Cancel transaction");
			screen.displayMessageLine("\n Choose a withdrawal amount: ");
			
			int input= keypad.getInput(); 
			
			switch (input) {
			
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				userChoice = amounts[input];
				break;
			case CANCEL:
				userChoice = CANCEL;
				break;
				default:
					screen.displayMessageLine("\n Invalid Selection. Try again.");
					
			}// end switch
			
		}//end while 
		return userChoice;
	}//end method displayMenuofAmounts
} // end Class Withdrawal
