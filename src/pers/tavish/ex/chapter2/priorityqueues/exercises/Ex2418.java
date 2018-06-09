package pers.tavish.ex.chapter2.priorityqueues.exercises;

import pers.tavish.code.chapter2.priorityqueues.MaxPQ;

public class Ex2418 {
	public static void main(String[] args) {
		MaxPQ<Integer> pq = new MaxPQ<>(10);
		pq.insert(4);
		pq.insert(2);
		pq.insert(3);
		pq.insert(1);
		pq.insert(5);
		pq.insert(-1);
		pq.insert(0);
		pq.insert(-2);
		pq.show();
		pq.insert(6);
		pq.insert(7);
		pq.delMax();
		pq.delMax();
		pq.show();
	}
}
