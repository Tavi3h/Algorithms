package pers.tavish.ex.chapter3.elementarysymboltables.creativeproblems;

import pers.tavish.code.chapter3.elementarysymboltables.BinarySearchST;

// 提高题3.1.29
public class TestBinarySearch {
	public static void main(String[] args) {
		BinarySearchST<Integer, String> st = new BinarySearchST<>(4);
		st.put(4, "A");
		st.put(2, "B");
		st.put(1, "D");
		st.put(3, "W");
		st.put(6, "T");
		System.out.println(st); // [1, D] [2, B] [3, W] [4, A] [6, T] [null, null] [null, null] [null, null] 

		System.out.println(st.min()); // 1
		System.out.println(st.max()); // 6
		
		System.out.println(st.floor(0)); // null
		System.out.println(st.floor(1)); // 1
		System.out.println(st.floor(2)); // 2
		System.out.println(st.floor(5)); // 4
		
		System.out.println(st.ceiling(3)); // 3
		System.out.println(st.ceiling(4)); // 4
		System.out.println(st.ceiling(5)); // 6
		System.out.println(st.ceiling(8)); // null
		
		System.out.println(st.select(-1)); // null
		System.out.println(st.select(3)); // 4
		System.out.println(st.select(6)); // null
		
		System.out.println(st.rank(0)); // 0
		System.out.println(st.rank(3)); // 2
		System.out.println(st.rank(6)); // 4
		System.out.println(st.rank(8)); // 5
		
		st.deleteMin();
		st.deleteMax();
		System.out.println(st); // [2, B] [3, W] [4, A] [null, null] [null, null] [null, null] [null, null] [null, null]
		
		for (Integer i : st.keys()) {
			System.out.print(i + " "); // 2 3 4 
		}
	}
}
