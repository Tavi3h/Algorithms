package pers.tavish.code.chapter2.quicksort;

import edu.princeton.cs.algs4.StdRandom;

// 带哨兵的快速排序
public class QuickTSentinel {
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		// 打乱数组，消除对输入的依赖
		StdRandom.shuffle(a);
		sortWithSentinel(a);
	}

	private static <T extends Comparable<? super T>> void sortWithSentinel(T[] a) {
		// 查找数组的最大元素
		int max = 0;
		for (int i = 0; i < a.length; i++) {
			if (less(a[max], a[i])) {
				max = i;
			}
		}
		// 将最大元素放在数组末尾
		exch(a, max, a.length - 1);
		sortWithSentinel(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void sortWithSentinel(T[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int j = partitionWithSentinel(a, lo, hi); // 将数组切分
		sortWithSentinel(a, lo, j - 1); // 排序左半边
		sortWithSentinel(a, j + 1, hi); // 排序右半边
	}

	private static <T extends Comparable<? super T>> int partitionWithSentinel(T[] a, int lo, int hi) {
		// 将数组分为a[lo...i-1]，a[i]，a[i+1...hi]
		int i = lo, j = hi + 1;

		T v = a[lo];

		while (true) {
			while (less(a[++i], v)) {
				// if (i == hi) break;
			}
			while (less(v, a[--j])) {
				// if (j == lo) break;
			}
			if (i >= j) {
				break;
			}
			exch(a, i, j);
		}

		exch(a, lo, j);
		return j;
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
