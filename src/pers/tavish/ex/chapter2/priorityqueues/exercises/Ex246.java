package pers.tavish.ex.chapter2.priorityqueues.exercises;

import pers.tavish.code.chapter2.priorityqueues.MaxPQ;

// 练习题2.4.6
public class Ex246 {
	public static void main(String[] args) {
		String[] strings = "P R I O * R * * I * T * Y * * * Q U E * * * U * E".split(" ");
		MaxPQ<String> pq = new MaxPQ<>(10);
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].equals("*")) {
				pq.delMax();
			} else {
				pq.insert(strings[i]);
			}
			System.out.print("---- " + strings[i] + "\t");
			pq.show();
		}
	}
}
