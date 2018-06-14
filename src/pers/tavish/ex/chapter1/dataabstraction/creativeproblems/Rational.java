package pers.tavish.ex.chapter1.dataabstraction.creativeproblems;

import edu.princeton.cs.algs4.StdOut;

// 提高题1.2.16
public class Rational {
	private final long numerator;
	private final long denominator;

	public Rational(long numerator, long denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException();
		}

		long gcd = euclid(Math.abs(numerator), Math.abs(denominator));

		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}

	public Rational plus(Rational b) {
		long ntmp = numerator * b.denominator + denominator * b.numerator;
		long dtmp = denominator * b.denominator;
		return new Rational(ntmp, dtmp);
	}

	public Rational minus(Rational b) {
		long ntmp = numerator * b.denominator - denominator * b.numerator;
		long dtmp = denominator * b.denominator;
		return new Rational(ntmp, dtmp);
	}

	public Rational times(Rational b) {
		long ntmp = numerator * b.numerator;
		long dtmp = denominator * b.denominator;
		return new Rational(ntmp, dtmp);
	}

	public Rational divides(Rational b) {
		if (b.numerator == 0) {
			throw new IllegalArgumentException();
		}
		long ntmp = numerator * b.denominator;
		long dtmp = denominator * b.numerator;
		return new Rational(ntmp, dtmp);
	}

	// 欧几里得算法
	private long euclid(long numerator, long denominator) {
		if (numerator % denominator == 0) {
			return denominator;
		} else {
			return euclid(denominator, numerator % denominator);
		}
	}

	@Override
	public String toString() {
		return "Rational [" + numerator + "/" + denominator + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (denominator ^ (denominator >>> 32));
		result = prime * result + (int) (numerator ^ (numerator >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rational other = (Rational) obj;
		if (denominator != other.denominator)
			return false;
		if (numerator != other.numerator)
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		Rational r1 = new Rational(-1, 7);
		Rational r2 = new Rational(2, 5);
		
		Rational r3 = new Rational(-1, 7);
		
		Rational resultplus = r1.plus(r2);
		Rational resultMinus = r1.minus(r2);
		Rational resultTimes = r1.times(r2);
		Rational resultDivides = r1.divides(r2);
		
		StdOut.println(resultplus);
		StdOut.println(resultMinus);
		StdOut.println(resultTimes);
		StdOut.println(resultDivides);
		
		StdOut.println(r1.equals(r1));
		StdOut.println(r1.equals(r2));
		StdOut.println(r1.equals(r3));
	}
}
