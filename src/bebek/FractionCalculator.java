package bebek;

import java.util.ArrayList;
import java.util.Scanner;

public class FractionCalculator {
	static ArrayList<Character> validOperation = new ArrayList<>();
	private char op;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		FractionCalculator calc = new FractionCalculator();

		validOperation.add('+');
		validOperation.add('-');
		validOperation.add('/');
		validOperation.add('*');
		validOperation.add('=');
		validOperation.add('Q');
		System.out.println("This program is a fraction calculator");
		System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
		System.out.println("Please enter your fractions in the form a/b, where a and b are integers.");
		System.out.println("--------------------------------------------------------------------------");
		System.out.print("Please enter an operation (+,-,/,*, = or Q to quit: ");
		calc.getOperation(input);
		input.close();
	}

	public String getOperation(Scanner input) {
		while(!(validOperation.contains(input.next().charAt(0)))) {
			return "Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero:";
		}
		return "_";
	}

	/*
	public boolean validFraction(String input) {

	}

	public Fraction getFraction(Scanner input) {

	}
	*/
}
