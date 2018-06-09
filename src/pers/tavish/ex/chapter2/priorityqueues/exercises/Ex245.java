package pers.tavish.ex.chapter2.priorityqueues.exercises;

import pers.tavish.code.chapter2.priorityqueues.MaxPQ;

// 练习题2.4.5
public class Ex245 {
	public static void main(String[] args) {
		String[] strings = "E A S Y Q U E S T I O N".split(" ");
		MaxPQ<String> pq = new MaxPQ<>(strings.length);
		for (int i = 0; i < strings.length; i++) {
			pq.insert(strings[i]);
		}
		pq.show();
	}
}
