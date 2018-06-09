package pers.tavish.ex.chapter3.balancedsearchtrees.exercises;

import pers.tavish.code.chapter3.balancedsearchtrees.RedBlackBST;

// 练习题3.3.13
public class Ex3313 {
	public static void main(String[] args) {
		RedBlackBST<Integer, Integer> rbbst = new RedBlackBST<>();
		Integer[] ints = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (Integer i : ints) {
			rbbst.put(i, 0);
			System.out.print(rbbst.height() + " ");
		}
	}
}
