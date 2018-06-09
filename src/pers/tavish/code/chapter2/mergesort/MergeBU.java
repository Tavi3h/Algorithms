package pers.tavish.code.chapter2.mergesort;

// 自底向上归并排序
public class MergeBU {
	
	@SuppressWarnings("rawtypes")
	private static Comparable[] aux;

	public static <T> void sort(Comparable<T>[] a) {
		// 进行lgN次两两归并
		int N = a.length;
		aux = new Comparable[N];
		for (int sz = 1; sz < N; sz = sz + sz) {
			for (int lo = 0; lo < N - sz; lo += sz + sz) {
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T> void merge(Comparable<T>[] a, int lo, int mid, int hi) {

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

	@SuppressWarnings("unchecked")
	private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
		return v.compareTo((T) w) < 0;
	}
	
	public static void main(String[] args) {
		String[] test = {"b", "a", "c", "d", "f", "e"};
		sort(test);
		for (String string : test) {
			System.out.print(string + " ");
		}
	}
}
