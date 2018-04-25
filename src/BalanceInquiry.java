
//represents a balance inquiry ATN transaction
public class BalanceInquiry extends Transaction {

	
	//Constructor
	public BalanceInquiry (int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
		super (userAccountNumber,atmScreen, atmBankDatabase);
	} // end constructor
	
	//performs the transaction
	@Override
	public void execute(){
		// get references to bank database and screen
		
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		//get the available balance for the current account
		
		double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
		
		// get the total balance for the current account
		
		double totalBalance = bankDatabase.getTotalBlance(getAccountNumber());
		
		//display the balance info on screen
		
		screen.displayMessageLine("\n Balance Information: ");
		screen.displayMessage(" - Available balance: ");
		screen.displayDollarAmount(availableBalance);
		screen.displayMessage("\n - Total Balance: ");
		screen.displayDollarAmount(totalBalance);
		screen.displayMessageLine("");
	} //end method execute
}
