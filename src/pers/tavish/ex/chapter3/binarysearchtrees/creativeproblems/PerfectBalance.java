package pers.tavish.ex.chapter3.binarysearchtrees.creativeproblems;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import pers.tavish.code.chapter3.binarysearchtrees.BST;

// 提高题3.2.25
public class PerfectBalance {

	// precondition: a[] has no duplicates
	private static <T extends Comparable<? super T>> void perfect(BST<T, Integer> bst, T[] a) {
		Arrays.sort(a); // 对数组进行排序
		perfect(bst, a, 0, a.length - 1);
	}

	// precondition: a[lo..hi] is sorted
	private static <T extends Comparable<? super T>> void perfect(BST<T, Integer> bst, T[] a, int lo, int hi) {
		if (hi < lo) {
			return;
		}
		Integer mid = lo + (hi - lo) / 2;
		bst.put(a[mid], mid);
		perfect(bst, a, lo, mid - 1);
		perfect(bst, a, mid + 1, hi);
	}

	public static void main(String[] args) {
		String[] words = StdIn.readAllStrings();
		BST<String, Integer> bst = new BST<>();
		perfect(bst, words);
	}
}
