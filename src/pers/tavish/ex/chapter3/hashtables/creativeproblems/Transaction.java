package pers.tavish.ex.chapter3.hashtables.creativeproblems;

import java.util.Comparator;

import edu.princeton.cs.algs4.Date;

// 提高题3.4.25
public class Transaction implements Comparable<Transaction> {

	private final String who; // customer
	private final Date when; // date
	private final double amount; // amount

	private Integer hash;

	public Transaction(String who, Date when, double amount) {
		if (Double.isNaN(amount) || Double.isInfinite(amount))
			throw new IllegalArgumentException("Amount cannot be NaN or infinite");
		this.who = who;
		this.when = when;
		this.amount = amount;
	}

	public Transaction(String transaction) {
		String[] a = transaction.split("\\s+");
		who = a[0];
		when = new Date(a[1]);
		amount = Double.parseDouble(a[2]);
		if (Double.isNaN(amount) || Double.isInfinite(amount))
			throw new IllegalArgumentException("Amount cannot be NaN or infinite");
	}

	public String who() {
		return who;
	}

	public Date when() {
		return when;
	}

	public double amount() {
		return amount;
	}

	@Override
	public String toString() {
		return String.format("%-10s %10s %8.2f", who, when, amount);
	}

	public int compareTo(Transaction that) {
		return Double.compare(this.amount, that.amount);
	}

	@Override
	public boolean equals(Object other) {
		if (other == this)
			return true;
		if (other == null)
			return false;
		if (other.getClass() != this.getClass())
			return false;
		Transaction that = (Transaction) other;
		return (this.amount == that.amount) && (this.who.equals(that.who)) && (this.when.equals(that.when));
	}

	public int hashCode() {
		if (this.hash == null) {
			int hash = 1;
			hash = 31 * hash + who.hashCode();
			hash = 31 * hash + when.hashCode();
			hash = 31 * hash + ((Double) amount).hashCode();
			this.hash = hash;
			return hash;
		} else {
			return this.hash;
		}
	}

	public static class WhoOrder implements Comparator<Transaction> {

		@Override
		public int compare(Transaction v, Transaction w) {
			return v.who.compareTo(w.who);
		}
	}

	public static class WhenOrder implements Comparator<Transaction> {

		@Override
		public int compare(Transaction v, Transaction w) {
			return v.when.compareTo(w.when);
		}
	}

	public static class HowMuchOrder implements Comparator<Transaction> {

		@Override
		public int compare(Transaction v, Transaction w) {
			return Double.compare(v.amount, w.amount);
		}
	}
}
