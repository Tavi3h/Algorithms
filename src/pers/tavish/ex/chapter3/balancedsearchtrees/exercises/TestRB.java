package pers.tavish.ex.chapter3.balancedsearchtrees.exercises;

import pers.tavish.code.chapter3.balancedsearchtrees.RedBlackBST;

// 练习3.3.21
public class TestRB {
	public static void main(String[] args) {
		RedBlackBST<String, Integer> rbbst = new RedBlackBST<>();
		String[] strings = "Y L P M X H C R A E S".split(" ");
		for (String s : strings) {
			rbbst.put(s, 0);
		}
		
		Iterable<String> queue = rbbst.keys(); 
		for (String s : queue) {
			System.out.print(s + " "); // A C E H L M P R S X Y 
		}
		System.out.println();
		
		System.out.println(rbbst.min()); // A
		System.out.println(rbbst.max()); // Y
		System.out.println(rbbst.floor("Z")); // Y
		System.out.println(rbbst.floor("B")); // A
		System.out.println(rbbst.ceiling("D")); // E
		System.out.println(rbbst.ceiling("Q")); // R
		System.out.println(rbbst.select(3)); // H
		System.out.println(rbbst.rank("T")); // 9
		
		rbbst.delete("M");
		queue = rbbst.keys(); 
		for (String s : queue) {
			System.out.print(s + " "); // A C E H L P R S X Y 
		}
		System.out.println();
		
		rbbst.deleteMin();
		queue = rbbst.keys(); 
		for (String s : queue) {
			System.out.print(s + " "); // C E H L P R S X Y 
		}
		System.out.println();
		
		rbbst.deleteMax();
		queue = rbbst.keys(); 
		for (String s : queue) {
			System.out.print(s + " "); // C E H L P R S X
		}
		System.out.println();
	}
}
