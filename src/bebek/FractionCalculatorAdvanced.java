package bebek;

import java.util.ArrayList;
import java.util.Scanner;

public class FractionCalculatorAdvanced {
	static ArrayList<Character> validOperation = new ArrayList<>();
	private static char op;
	private String fraction;
	private static boolean equal;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		FractionCalculator calc = new FractionCalculator();
		Fraction frac1;
		Fraction frac2;
		Fraction result = null;
		boolean divideByZero = false;

		validOperation.add('+');
		validOperation.add('-');
		validOperation.add('/');
		validOperation.add('*');
		validOperation.add('=');
		validOperation.add('Q');

		System.out.println("This program is a fraction calculator");
		System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
		System.out.println("Valid operations are of the form \"[FRAC] [OPERATION] [FRAC]\".");
		System.out.println("[FRAC] can be either a single integer or two integers separated by \"/\".");
		System.out.println("[OPERATION] can be +, -, *, / or =.");
		
		

	}

}
