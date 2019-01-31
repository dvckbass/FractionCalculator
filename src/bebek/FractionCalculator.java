package bebek;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

public class FractionCalculator {
	static ArrayList<Character> validOperation = new ArrayList<>();
	private static char op;
	private String fraction;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		FractionCalculator calc = new FractionCalculator();
		Fraction frac1;
		Fraction frac2;
		Fraction result = new Fraction();

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
		
		
		do {
			System.out.print("Please enter an operation (+,-,/,*, = or Q to quit): ");
			System.out.print(calc.getOperation(input));
			frac1 = calc.getFraction(input);
			System.out.print("Please enter a fraction(a/b) or integer(a): ");
			frac2 = calc.getFraction(input);
			switch (op) {
			case '+':
				result = frac1.add(frac2);
				break;
			}
			System.out.println(frac1.toString() + " " + op + " " + frac2.toString() + " = " + result.toString());
			System.out.println("--------------------------------------------------------------------------");
		} while(Character.toUpperCase(op) != 'Q');
		

		input.close();
	}

	public String getOperation(Scanner input) {
		this.op = input.next().charAt(0);
		while (!(validOperation.contains(this.op))) {
			if (Character.toUpperCase(this.op) == 'Q') {
				System.exit(0);
			}
			System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
			this.op = input.next().charAt(0);
		}
		return "Please enter a fraction(a/b) or integer(a): ";
	}

	public Fraction getFraction(Scanner input) {
		String[] tmp;
		int num = 0;
		int den = 0;
		this.fraction = input.next();
		if (validFraction(this.fraction)) {
			if (this.fraction.contains("/")) {
				tmp = this.fraction.split("/");
				num = Integer.parseInt(tmp[0]);
				den = Integer.parseInt(tmp[1]);
			} else {
				return new Fraction(num);
			}
		}
		if (validFraction(this.fraction) && this.fraction.contains("/")) {

		}
		while (!(this.validFraction(this.fraction))) {
			System.out.print(
					"Invalid fraction. Please enter (a/b) or (a), " + "where a and b are integers and b is not zero");
			this.fraction = input.next();
		}
		return new Fraction(num, den);

	}

	public static boolean validFraction(String s) {
		boolean valid = false;

		StringBuilder sb = new StringBuilder(s);
		if (s.contains("-")) {
			int indexMinus = sb.indexOf("-");
			if (indexMinus != 0) {
				sb.deleteCharAt(indexMinus);
			}
		}
		String num;
		String den;
		String[] tmp;

		if (sb.toString().contains("/")) {
			tmp = sb.toString().split("/");
			num = tmp[0];
			den = tmp[1];
			if (!(Integer.parseInt(den) == 0)) {
				valid = true;
			}

		} else if (isNumber(sb.toString())) {
			valid = true;
		}

		return valid;
	}

	public static boolean isNumber(String s) {
		StringBuilder sb = new StringBuilder(s);
		if (s.charAt(0) == '-') {
			sb.deleteCharAt(0);
			return StringUtils.isNumeric(sb.toString());
		}
		return StringUtils.isNumeric(s);
	}
}
