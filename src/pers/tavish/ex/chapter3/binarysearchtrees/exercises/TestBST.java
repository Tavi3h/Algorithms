package pers.tavish.ex.chapter3.binarysearchtrees.exercises;

import pers.tavish.code.chapter3.binarysearchtrees.BST;

public class TestBST {
	public static void main(String[] args) {
		BST<Integer, String> bst = new BST<>();
		bst.put(8, "A");
		bst.put(5, "B");
		bst.put(12, "C");
		bst.put(3, "D");
		bst.put(7, "E");
		bst.put(10, "F");
		bst.put(14, "G");
		
		/*				8
		 * 		5				12
		 * 	3		7		10		14
		 */
		
		Iterable<Integer> queue = bst.keys(); 
		for (Integer i : queue) {
			System.out.print(i + " "); // 3 5 7 8 10 12 14 
		}
		
		System.out.println(bst.min()); // 3
		System.out.println(bst.max()); // 14
		System.out.println(bst.floor(6)); // 5
		System.out.println(bst.floor(11)); // 10
		System.out.println(bst.floor(9)); // 8
		System.out.println(bst.floor(20)); // 14
		System.out.println(bst.floor(0)); // null
		System.out.println(bst.select(6)); // 14
		System.out.println(bst.select(1)); // 5
//		System.out.println(bst.select(-1)); // throw exception
//		System.out.println(bst.select(7)); // throw exception
		System.out.println(bst.rank(8)); // 3
		System.out.println(bst.rank(0)); // 0
		System.out.println(bst.rank(16)); // 7
		
		bst.delete(7);
		bst.delete(bst.min());
		bst.delete(bst.max());
		queue = bst.keys();
		for (Integer i : queue) {
			System.out.print(i + " "); //  5 8 10 12  
		}
		
		queue = bst.keys(7, 11);
		for (Integer i : queue) {
			System.out.print(i + " "); //  8 10   
		}
	}
}
