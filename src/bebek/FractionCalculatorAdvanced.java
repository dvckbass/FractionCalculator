package bebek;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

public class FractionCalculatorAdvanced {
	static ArrayList<Character> validOperation = new ArrayList<>();
	private static char op;
	private String fraction;
	private static boolean equal;
	private static boolean quit;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		FractionCalculatorAdvanced calc = new FractionCalculatorAdvanced();
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

		do {
			System.out.print("Enter an operation (q to quit): ");
			System.out.println(calc.getOperation(input));
			/*
			 * frac1 = calc.getFraction(input);
			 * System.out.print("Please enter a fraction(a/b) or integer(a): "); frac2 =
			 * calc.getFraction(input); switch (op) { case '+': result = frac1.add(frac2);
			 * break; case '-': result = frac1.substract(frac2); break; case '/':
			 * if(frac2.getNum() == 0) { divideByZero = true;
			 * System.out.println(frac1.toString() + " / 0 = undefined"); } else { result =
			 * frac1.divide(frac2); } break; case '*': result = frac1.multiply(frac2);
			 * break; case '=': equal = frac1.equals(frac2); break; } if(!divideByZero) {
			 * if(result != null) { result.toLowestTerms();
			 * System.out.println(frac1.toString() + " " + op + " " + frac2.toString() +
			 * " = " + result.toString()); } else { System.out.println(frac1.toString() +
			 * " " + op + " " + frac2.toString() + " is " + equal); } }
			 */
		} while (!quit);

		input.close();

	}

	public String getOperation(Scanner input) {

		String inputString = input.nextLine();
		String[] inputArray = inputString.split(" ");
		String frac1String = null;
		String frac2String = null;
		Fraction frac1 = null;
		Fraction frac2 = null;
		Fraction result = null;
		//holder for num and den
		int[] nums = new int[2];
		if(Character.toUpperCase(inputString.charAt(0)) == 'Q' && inputString.length() == 1) {
			quit = true;
		}

		while (inputArray.length != 3 || !(validLine(inputString))) {
			if(Character.toUpperCase(inputString.charAt(0)) == 'Q' && inputString.length() == 1) {
				quit = true;
			}
			if (quit) {
				System.exit(0);
			}
			System.out.println("Invalid operation. Must be \"[FRAC] [OPERATION] [FRAC]\".");
			System.out.print("Enter an operation (q to quit): ");
			inputString = input.nextLine();
			inputArray = inputString.split(" ");


		}

		if (inputArray.length == 3) {
			frac1String = inputArray[0];
			frac2String = inputArray[2];
			this.op = inputArray[1].charAt(0);
			if (isNumber(frac1String)) {
				frac1 = new Fraction(Integer.parseInt(frac1String));
			}
			if (isNumber(frac2String)) {
				frac2 = new Fraction(Integer.parseInt(frac2String));
			}
			if(frac1String.contains("/")) {
				nums = splitFraction(frac1String);
				frac1 = new Fraction(nums[0], nums[1]);
			}
			if(frac2String.contains("/")) {
				nums = splitFraction(frac2String);
				frac2 = new Fraction(nums[0], nums[1]);
			}
			switch (op) {
			case '+':
				result = frac1.add(frac2);
				break;
			case '-':
				result = frac1.substract(frac2);
				break;
			case '/':
				if (frac2.getNum() == 0) {
					// divideByZero = true;
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
		}
		result.toLowestTerms();
		return frac1.toString() + " " + op + " " + frac2.toString() + " = " + result.toString();
	}

	public boolean validLine(String inputString) {
		String frac1String = null;
		String frac2String = null;
		String[] inputArray = inputString.split(" ");
		if (inputArray.length == 3) {
			frac1String = inputArray[0];
			frac2String = inputArray[2];
			this.op = inputArray[1].charAt(0);
			if (validFraction(frac1String) && validFraction(frac2String) && validOperation.contains(this.op)) {
				return true;
			}
		}
		return false;
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
			if (isNumber(num) && isNumber(den) && (!(Integer.parseInt(den) == 0))) {
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
	
	public int[] splitFraction(String fraction) {
		String[] stringArray = fraction.split("/");
		int[] nums = new int[2];
		nums[0] = Integer.parseInt(stringArray[0]);
		nums[1] = Integer.parseInt(stringArray[1]);
		return nums;
	}

}
