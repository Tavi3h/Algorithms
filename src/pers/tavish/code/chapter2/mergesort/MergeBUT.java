package pers.tavish.code.chapter2.mergesort;

import java.lang.reflect.Array;

// 自底向上归并排序
public class MergeBUT {

	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		// 进行lgN次两两归并
		int N = a.length;
		T[] aux = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
		for (int sz = 1; sz < N; sz = sz + sz) {
			for (int lo = 0; lo < N - sz; lo += sz + sz) {
				merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
			}
		}
	}

	private static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {

		// 将a[lo...hi]复制到aux[lo...hi]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		int i = lo, j = mid + 1;
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
