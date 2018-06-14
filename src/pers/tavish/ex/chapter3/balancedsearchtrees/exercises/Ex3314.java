package pers.tavish.ex.chapter3.balancedsearchtrees.exercises;

import pers.tavish.code.chapter3.balancedsearchtrees.RedBlackBST;

// 练习题3.3.14
public class Ex3314 {
	public static void main(String[] args) {
		RedBlackBST<String, Integer> rbbst = new RedBlackBST<>();
		String[] strings = "A B C D E F G H I J K".split(" ");
		for (String s : strings) {
			rbbst.put(s, 0);
		}
	}
}
