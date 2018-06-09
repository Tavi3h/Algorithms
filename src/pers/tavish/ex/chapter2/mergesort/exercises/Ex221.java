package pers.tavish.ex.chapter2.mergesort.exercises;

import java.lang.reflect.Array;

// 练习题2.2.1
public class Ex221 {
	
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		int N = a.length;
		T[] aux = (T[]) Array.newInstance(a.getClass().getComponentType(), N);

		merge(a, aux, 0, (N - 1) / 2, N - 1);
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
			show(a, k);
		}
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}

	public static <T> void show(T[] a, int k) {
		for (int i = 0; i <= k; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] strings = new String("A E Q S U Y E I N O S T").split(" ");
		for (int i = 0; i < strings.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < strings.length; i++) {
			System.out.print(strings[i] + " ");
		}
		System.out.println();
		sort(strings);
	}
}
