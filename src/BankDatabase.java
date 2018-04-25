
public class BankDatabase {

	private Account[] accounts; //array of accounts
	
	//default constructor
	public BankDatabase() {
		accounts = new Account [2];// 2 accounts for testing
		accounts[0] = new Account(12345, 54321, 1000.0, 1200.0);
		accounts[1] = new Account( 45632, 57384, 200.0, 200.0);
		
	}// end of default constructor
	
	//retrieve Account object containing specified account number
	
	private Account getAccount (int accountNumber) {
		for( Account currentAccount : accounts) {
			if (currentAccount.getAccountNumber() == accountNumber) 
				return currentAccount;
		} //end for
		return null; // if no matching account fiund
	} // end method getAccount
	
	// determine whether user-specified account number and PIN match
	
	public boolean authenticateUser( int userAccountNumber, int userPIN) {
		//attempt to retrieve the account with account number
		Account userAccount = getAccount( userAccountNumber);
		
		if (userAccount != null)
			return userAccount.validatePIN(userPIN);
		else
			return false; 
	} // end method authenticateUser
	
	//return available balance if Account with specified account number
	public double getAvailableBalance ( int userAccountNumber) {
		return getAccount( userAccountNumber).getAvailableBalance();
	} // end method getAvailableBalance
	
	//return total balance of account
	public double getTotalBlance( int userAccountNumber) {
		return getAccount( userAccountNumber).getTotalBalance();
	} // end method getTotalBalance
	
	//credit amount to the current account
	public void credit( int userAccountNumber, double amount){
		getAccount( userAccountNumber).credit(amount);
	} // end method credit
	
	// debit amount to current account
	public void debit (int userAccountNumber, double amount) {
		getAccount( userAccountNumber).debit(amount);
	} // end method debit
	
}
