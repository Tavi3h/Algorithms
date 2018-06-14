package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

import edu.princeton.cs.algs4.StdIn;
import pers.tavish.ex.chapter1.bagsqueuesandstacks.linkedlistexercises.DLinkedList;

// 提高题 1.3.40 
public class MoveToFront {

	public static void main(String[] args) {

		DLinkedList<Character> dll = new DLinkedList<>();

		while (!StdIn.isEmpty()) {
			char[] chars = StdIn.readString().toCharArray();
			for (int i = 0; i < chars.length; i++) {
				int idx = dll.indexOf(chars[i]);
				if (idx != -1) {
					dll.remove(idx);
				}
				dll.addFirst(chars[i]);
			}
		}
		System.out.println(dll);
	}
}
