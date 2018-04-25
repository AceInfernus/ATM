
public class Account {

	private int accountNumber;
	private int pin;
	private double availableBalance; //funds availabe
	private double totalBalance; // funds available + pending deposits
	
	//default constructor
	public Account (int theAccountNumber, int thePIN,
	double theAvailableBalance, double theTotalBalance ) {
		
		accountNumber = theAccountNumber;
		pin = thePIN;
		availableBalance = theAvailableBalance;
		totalBalance = theTotalBalance;
	}// end default constructor
	
	//verify pin
	public boolean validatePIN( int userPIN) {
		if (userPIN == pin)
			return true;
		else 
			return false;
		
	} // end method validatePIN
	
	public double getAvailableBalance() {
		return availableBalance;
		
	}
	public double getTotalBalance() {
		return totalBalance;
		
	}
	
	//credits an amount to the account
	public void credit(double amount) {
		totalBalance += amount;
	} // end method credit
	
	//debit amount
	public void debit (double amount) {
		availableBalance -= amount;
		totalBalance -= amount;
	}
	
	//returns account number
    public int getAccountNumber(){
    	return accountNumber;
    }
    
    
	
	
	
}
