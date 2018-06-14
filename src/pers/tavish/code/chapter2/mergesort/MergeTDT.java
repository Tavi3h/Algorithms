package pers.tavish.code.chapter2.mergesort;

import java.lang.reflect.Array;

// 自顶向下归并排序 
public class MergeTDT {

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

	private static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
		// 将a[lo...mid]和a[mid + 1...hi]归并
		int i = lo;
		int j = mid + 1;

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

	public static void main(String[] args) {
		String[] test = { "b", "a", "c", "d", "f", "e" };
		sort(test);
		for (String string : test) {
			System.out.print(string + " ");
		}
	}
}
