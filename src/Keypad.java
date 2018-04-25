
//represents keypad of ATM
import java.util.Scanner;
public class Keypad {
	private Scanner input; 
	
	//constructor
	public Keypad() 	{
		input = new Scanner(System.in);
	}

	public int getInput() {
		
		return input.nextInt(); //assuming user enters an integer
	} //end method getInput
}
