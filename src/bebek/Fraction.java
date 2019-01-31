package bebek;

public class Fraction {
	private int num;
	private int den;

	public Fraction(int num, int den) {
		if (den == 0) {
			throw new IllegalArgumentException("den can't be 0");
		} else if (den < 0) {
			this.num = num * (-1);
			this.den = den * (-1);
		} else {
			this.num = num;
			this.den = den;
		}
	}

	public Fraction(int num) {
		this.num = num;
		this.den = 1;
	}

	public Fraction() {
		this.num = 0;
		this.den = 1;
	}

	public int getNum() {
		return num;
	}

	public int getDen() {
		return den;
	}

	@Override
	public String toString() {
		if(den == 1) {
			return Integer.toString(num);
		}
		return num + "/" + den;
	}

	public double toDouble() {
		return num / den;
	}

	public Fraction add(Fraction other) {
		//result of new numerator
		int numRes = (this.num * other.den) + (other.num * this.den);
		//result of new denominator
		int denRes = this.den * other.den;
		return new Fraction(numRes, denRes);
	}

	public Fraction substract(Fraction other) {
		//result of new numerator
		int numRes = (this.num * other.den) - (other.num * this.den);
		//result of new denominator
		int denRes = this.den * other.den;
		return new Fraction(numRes, denRes);
	}

	public Fraction multiply(Fraction other) {

		//result of new numerator
		int numRes = this.num * other.num;
		//result of new denominator
		int denRes = this.den * other.den;
		return new Fraction(numRes, denRes);
	}

	public Fraction divide(Fraction other) {
		//result of new numerator
		int numRes = this.num * other.den;
		//result of new denominator
		int denRes = this.den * other.num;
		return new Fraction(numRes, denRes);
	}

	public boolean equals(Object other) {
		if(other instanceof Fraction) {
			Fraction frac = (Fraction) other;
			if(this.num > frac.num) {
				if(this.num % frac.num == 0 && this.den % frac.den == 0) {
					return true;
				}
			} else if(frac.num % this.num == 0 & frac.den % this.den == 0) {
				return true;
			}
		}
		return false;
	}

	public void toLowestTerms() {
		if(num == den) {
			num = 1;
			den = 1;
		} else {
			int gcd = gcd(this.num, this.den);
			this.num /= gcd;
			this.den /= gcd;
		}

	}

	//find greatest common divisor
	public static int gcd(int num, int den) {
		int tmp = 1;
		while(num != 0 && den != 0) {
			tmp = num % den;
			num = den;
			den = tmp;
		}
		return num;
	}

}
