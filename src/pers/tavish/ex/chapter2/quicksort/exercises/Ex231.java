package pers.tavish.ex.chapter2.quicksort.exercises;

import edu.princeton.cs.algs4.StdRandom;

public class Ex231 {
	
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		StdRandom.shuffle(a);
		show(a, null, null);
		sort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int j = partition(a, lo, hi); // 将数组切分
		System.out.println("j = " + j);
//		sort(a, lo, j - 1); // 排序左半边
//		sort(a, j + 1, hi); // 排序右半边
	}

	private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {
		// 将数组分为a[lo...i-1]，a[i]，a[i+1...hi]
		int i = lo, j = hi + 1;
		T v = a[lo];
		while (true) {
			// 扫描左右，检查扫描是否结束并交换元素
			while (less(a[++i], v)) {
				if (i == hi) {
					break;
				}
			}
			while (less(v, a[--j])) {
				if (j == lo) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			exch(a, i, j);
			show(a, i, j);
		}

		exch(a, lo, j); // 将v = a[j]放入正确的位置
		show(a, i, j);
		return j; // 达成 a[lo..j-1] <= a[j] <= a[j+1..hi]
	}

	private static <T> void show(T[] a, Integer i, Integer j) {
		if (i == null && j == null) {
			System.out.print(" " + "\t" + " " + "\t");
		} else {
			System.out.print(i + "\t" + j + "\t");
		}
		for (int k = 0; k < a.length; k++) {
			if (k < 10) {
				System.out.print(a[k] + " ");
			} else {
				System.out.print(" " + a[k] + " ");
			}
		}
		System.out.println();
	}

	private static <T extends Comparable<? super T>> void exch(T[] a, int i, int j) {
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}

	public static void main(String[] args) {
		String[] a = new String("E A S Y Q U E S T I O N").split(" ");
		System.out.print("i\tj\t");
		for (int i = 0; i < a.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.print(" \t \t");
		for (int i = 0; i < a.length; i++) {
			if (i < 10) {
				System.out.print(a[i] + " ");
			} else {
				System.out.print(" " + a[i] + " ");
			}
		}
		System.out.println();
		sort(a);
	}
}
