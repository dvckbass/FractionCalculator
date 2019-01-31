package bebek;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

public class FractionCalculator {
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
			case '-':
				result = frac1.substract(frac2);
				break;
			case '/':
				if(frac2.getNum() == 0) {
					divideByZero = true;
					System.out.println(frac1.toString() + " / 0 = undefined");
				} else {
					result = frac1.divide(frac2);
				}
				break;
			case '*':
				result = frac1.multiply(frac2);
				break;
			case '=':
				equal = frac1.equals(frac2);
				break;
			}
			if(!divideByZero) {
				if(result != null) {
					result.toLowestTerms();
					System.out.println(frac1.toString() + " " + op + " " + frac2.toString() + " = " + result.toString());
				} else {
					System.out.println(frac1.toString() + " " + op + " " + frac2.toString() + " is " + equal);
				}
			}
			System.out.println("--------------------------------------------------------------------------");
		} while(Character.toUpperCase(op) != 'Q');
		

		input.close();
	}

	public String getOperation(Scanner input) {
		String inputString = input.nextLine();
		this.op = inputString.charAt(0);
		while ((!(validOperation.contains(this.op))) || inputString.length() > 1  ) {
			if (Character.toUpperCase(this.op) == 'Q') {
				System.exit(0);
			}
			
			
			System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
			inputString = input.nextLine();
			this.op = inputString.charAt(0);
		}
		
		return "Please enter a fraction(a/b) or integer(a): ";
	}

	public Fraction getFraction(Scanner input) {
		String[] tmp;
		int num = 0;
		int den = 1;
		this.fraction = input.nextLine();
		if (validFraction(this.fraction)) {
			if (this.fraction.contains("/")) {
				tmp = this.fraction.split("/");
				num = Integer.parseInt(tmp[0]);
				den = Integer.parseInt(tmp[1]);
			} else {
				num = Integer.parseInt(this.fraction);
				return new Fraction(num);
			}
		}
		if (validFraction(this.fraction) && this.fraction.contains("/")) {

		}
		while (!(this.validFraction(this.fraction))) {
			System.out.print(
					"Invalid fraction. Please enter (a/b) or (a), " + "where a and b are integers and b is not zero: ");
			this.fraction = input.nextLine();
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
			if(isNumber(num) && isNumber(den) && (!(Integer.parseInt(den) == 0))) {
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
