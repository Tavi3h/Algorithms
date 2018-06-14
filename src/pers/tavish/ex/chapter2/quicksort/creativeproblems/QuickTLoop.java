package pers.tavish.ex.chapter2.quicksort.creativeproblems;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

// 提高题 2.3.20
public class QuickTLoop {
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		// 打乱数组，消除对输入的依赖
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		Stack<Integer> stack = new Stack<>();
		if (lo < hi) {
			stack.push(hi);
			stack.push(lo);
			while (!stack.isEmpty()) {
				int m = stack.pop();
				int n = stack.pop();
				int idx = partition(a, m, n);
				if (m < idx - 1) {
					stack.push(idx - 1);
					stack.push(m);
				}
				if (n > idx + 1) {
					stack.push(n);
					stack.push(idx + 1);
				}
			}
		}
	}

	private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {
		T pivot = a[lo];
		while (lo < hi) {
			while (lo < hi && !less(a[hi], pivot)) {
				hi--;
			}
			a[lo] = a[hi];
			while (lo < hi && !less(pivot, a[lo])) {
				lo++;
			}
			a[hi] = a[lo];
		}
		a[lo] = pivot;
		return lo;
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}
	
	public static void main(String[] args) {
		
		Integer[] arr = new Integer[20];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		StdRandom.shuffle(arr);
		sort(arr);
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
	}
}
