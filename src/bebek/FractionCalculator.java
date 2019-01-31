package bebek;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

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
		System.out.print("Please enter an operation (+,-,/,*, = or Q to quit): ");
		System.out.println(calc.getOperation(input));
		input.close();
	}

	public String getOperation(Scanner input) {
		this.op = input.next().charAt(0);
		while(!(validOperation.contains(this.op))) {
			if(Character.toUpperCase(this.op) == 'Q') {
				System.exit(0);
			}
			System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
			this.op = input.next().charAt(0);
		}
		return "Please enter a fraction(a/b) or integer(a):";
	}

	/*
	public boolean validFraction(String input) {

	}

	
	public Fraction getFraction(Scanner input) {

	}
	*/
	
	private boolean isNumber(String s) {
		return StringUtils.isNumeric(s);
	}
}
