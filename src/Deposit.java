
public class Deposit extends Transaction {

	private double amount;
	private Keypad keypad;
	private DepositSlot depositSlot;
	private final static int CANCEL = 0;
	
	//constructor
	public Deposit(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, DepositSlot atmDepositSlot) {
		super( userAccountNumber, atmScreen, atmBankDatabase);
		
		keypad = atmKeypad;
		depositSlot = atmDepositSlot;
	} // end constructor
	
	//perform transaction
	@Override
	public void execute(){
		
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		amount = promptForDepositAmount();
		
		if (amount != CANCEL) {
			screen.displayMessage("\n Please insert a deposit envelope containing ");
			screen.displayDollarAmount(amount);
			screen.displayMessageLine(".");
			
			boolean envelopeReceived = depositSlot.isEnvelopeReceived();
			
			if ( envelopeReceived) {
				screen.displayMessageLine("\n Your envelope has been received. It will be available once the amount is verified");
				
				bankDatabase.credit(getAccountNumber(), amount);
				
			}//end if
			else {
				screen.displayMessageLine("No envelope inserted. Transaction cancelled.");
				
				
			}// end else
			
			
		}//end if
		else { //if user cancelled
			screen.displayMessageLine("Transaction cancelled.");
			
		}// end else]
		
		
	}//end method execute
	
	// prompt user to enter a deposit amount
	
	private double promptForDepositAmount (){
		Screen screen = getScreen();
		
		screen.displayMessage("\n Please enter a deposit amount in" +" CENTS or (0 to cancel): ");
		
		int input = keypad.getInput();
		
		if (input == CANCEL){
			return CANCEL;
			
		}
		else {
			return(double) input /100; //return dollar amount
			
			
		}
		
	}//end method promptforDepositAmount
	
}
