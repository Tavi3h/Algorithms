package pers.tavish.code.chapter5.stringsorts;

import edu.princeton.cs.algs4.StdRandom;

// 三项字符串快速排序
public class Quick3String {

	private static final int CUTOFF = 15; // 切换到插入排序的阈值

	private static int charAt(String s, int d) {
		return d < s.length() ? s.charAt(d) : -1;
	}

	public static void sort(String[] a) {
		StdRandom.shuffle(a);
		sort(a, 0, a.length - 1, 0);
	}

	private static void sort(String[] a, int lo, int hi, int d) {

		// cutoff to insertion sort for small subarrays
		if (hi <= lo + CUTOFF) {
			insertion(a, lo, hi, d);
			return;
		}
		
		int lt = lo, gt = hi;
		int v = charAt(a[lo], d);
		int i = lo + 1;
		while (i <= gt) {
			int t = charAt(a[i], d);
			if (t < v) {
				exch(a, lt++, i++);
			} else if (t > v) {
				exch(a, i, gt--);
			} else {
				i++;
			}
		}

		// a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
		sort(a, lo, lt - 1, d);
		if (v >= 0) {
			sort(a, lt, gt, d + 1);
		}
		sort(a, gt + 1, hi, d);
	}
	
	/*
	 * 辅助用插入排序
	 */
	// insertion sort a[lo..hi], starting at dth character
	private static void insertion(String[] a, int lo, int hi, int d) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
				exch(a, j, j - 1);
			}
		}
	}

	// exchange a[i] and a[j]
	private static void exch(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// is v less than w, starting at character d
	private static boolean less(String v, String w, int d) {
		// assert v.substring(0, d).equals(w.substring(0, d));
		for (int i = d; i < Math.min(v.length(), w.length()); i++) {
			if (v.charAt(i) < w.charAt(i)) {
				return true;
			}
			if (v.charAt(i) > w.charAt(i)) {
				return false;
			}
		}
		return v.length() < w.length();
	}
	
	public static void main(String[] args) {
		String[] test = { "A248SF", "B3712D", "123MSH", "04JFBQ", "34NF88", "MZNNE2", "123NDF" };
		
		sort(test);
		
		for (String string : test) {
			System.out.println(string);
		}
	}
}
