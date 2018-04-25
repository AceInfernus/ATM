
public class CashDispenser {

	//default initial number of bills in the cash dispenser
	
	private final static int INITIAL_COUNT = 500;
	private int count; // number of $20 bills remaining
	
	//default constructor
	public CashDispenser(){
		count = INITIAL_COUNT;
	}//end default constructor
	
	// simulating dispensing of specified amount of cash
	public void dispenseCash(int amount) {
		int billsRequired = amount / 20; // number of $20 bills required
		count-= billsRequired; // update count of bills
	}// end method dispenseCash
	
	// indicates whether the cash dispenser can dispense the desired amount
	public boolean isSufficientCashAvailable (int amount){
		int billsRequired = amount/20;
		
		if (count >= billsRequired){
			return true;
		}
		else {	
			return false;
		}
	}//end method isSufficientCashAvailable

}
