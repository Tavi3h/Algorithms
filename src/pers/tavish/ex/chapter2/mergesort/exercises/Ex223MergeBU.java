package pers.tavish.ex.chapter2.mergesort.exercises;

import java.lang.reflect.Array;

// 练习题 2.2.3
public class Ex223MergeBU {
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
		show(a, lo, hi);
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}
	
	public static <T> void show(T[] a, int lo, int hi) {
		for (int i = 0; i < lo; i++) {
			System.out.print("  ");
		}
		for (int i = lo; i <= hi; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		String[] strings = new String("E A S Y Q U E S T I O N").split(" ");
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
