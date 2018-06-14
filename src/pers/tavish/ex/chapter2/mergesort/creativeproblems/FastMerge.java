package pers.tavish.ex.chapter2.mergesort.creativeproblems;

import java.lang.reflect.Array;

// 提高题2.2.10
public class FastMerge {
	
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		T[] aux = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
		sort(a, aux, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void sort(T[] a, T[] aux, int lo, int hi) {
		// 将数组a[lo...hi]排序
		if (hi <= lo) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		merge(a, aux, lo, mid, hi);
	}

	// 实现FastMerge
	private static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
		
		// 复制数组的前半边
		for (int i = lo; i <= mid; i++) {
			aux[i] = a[i];
		}

		// 按照降序复制数组的后半边
		for (int j = mid + 1; j <= hi; j++) {
			aux[j] = a[hi - j + mid + 1];
		}

		int i = lo, j = hi;
		for (int k = lo; k <= hi; k++) {
			if (less(aux[j], aux[i])) {
				a[k] = aux[j--];
			} else {
				a[k] = aux[i++];
			}
		}
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}
}
