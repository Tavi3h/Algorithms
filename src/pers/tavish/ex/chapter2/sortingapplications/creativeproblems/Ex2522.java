package pers.tavish.ex.chapter2.sortingapplications.creativeproblems;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

// 提高题2.5.22

class Deal implements Comparable<Deal> {

	protected double price;
	protected double amount;

	public Deal(double price, double amount) {
		this.price = price;
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public int compareTo(Deal o) {
		return Double.compare(price, o.price);
	}
}

class BuyDeal extends Deal {

	public BuyDeal(double price, double amount) {
		super(price, amount);
	}

	@Override
	public String toString() {
		return "BuyDeal [price=" + price + ", amount=" + amount + "]";
	}
}

class SellDeal extends Deal {

	public SellDeal(double price, double amount) {
		super(price, amount);
	}

	@Override
	public String toString() {
		return "SellDeal [price=" + price + ", amount=" + amount + "]";
	}
}

public class Ex2522 {

	public static boolean makeDeal(Deal a, Deal b) {
		
		if (!(a instanceof BuyDeal) || !(b instanceof SellDeal)) {
			throw new IllegalArgumentException();
		}
		
		if (a.getPrice() <= b.getPrice()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		MaxPQ<Deal> buyDeal = new MaxPQ<>();
		MinPQ<Deal> sellDeal = new MinPQ<>();

		while (!buyDeal.isEmpty() && !sellDeal.isEmpty()) {
			Deal buy = buyDeal.delMax();
			Deal sell = sellDeal.delMin();
			if (!makeDeal(buy, sell)) {
				buyDeal.insert(buy);
				sellDeal.insert(sell);
			}
		}
	}
}
