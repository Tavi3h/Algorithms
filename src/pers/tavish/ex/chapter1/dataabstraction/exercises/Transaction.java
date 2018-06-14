package pers.tavish.ex.chapter1.dataabstraction.exercises;

import edu.princeton.cs.algs4.Date;

// 练习题1.2.13 & 1.2.14
public class Transaction {
	private final String who;
	private final Date when;
	private final double amount;

	public Transaction(String who, Date when, double amount) {
		this.who = who;
		this.when = when;
		this.amount = amount;
	}

	public Transaction(String transaction) {
		String[] fields = transaction.split(" ");
		if (fields.length != 3) {
			throw new IllegalArgumentException();
		}
		this.who = fields[0];
		this.when = new Date(fields[1]);
		this.amount = Double.parseDouble(fields[2]);
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
		return who + "-" + when.toString() + "-" + amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + when.hashCode();
		result = prime * result + who.hashCode();
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
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (when == null) {
			if (other.when != null)
				return false;
		} else if (!when.equals(other.when))
			return false;
		if (who == null) {
			if (other.who != null)
				return false;
		} else if (!who.equals(other.who))
			return false;
		return true;
	}
}
