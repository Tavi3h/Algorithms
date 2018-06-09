package pers.tavish.ex.chapter2.mergesort.experiments;

import java.lang.reflect.Array;

public class MergeEx2226 {
	
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		sort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		// 将数组a[lo...hi]排序
		if (hi <= lo) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}

	// 在Merge中创建aux数组
	private static <T extends Comparable<? super T>> void merge(T[] a, int lo, int mid, int hi) {
		// 将a[lo...mid]和a[mid + 1...hi]归并
		int i = lo;
		int j = mid + 1;

		@SuppressWarnings("unchecked")
		T[] aux = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
		// 将a[lo...hi]复制到aux[lo...hi]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		for (int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (less(aux[j], aux[i])) {
				a[k] = aux[j++];
			} else {
				a[k] = aux[i++];
			}
		}
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}
}
