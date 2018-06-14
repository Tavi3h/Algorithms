package pers.tavish.code.chapter2.mergesort;

// 自顶向下归并排序
public class MergeTD {

	@SuppressWarnings("rawtypes")
	private static Comparable[] aux; // 归并所需的辅助数组

	public static <T> void sort(Comparable<T>[] a) {
		aux = new Comparable[a.length];
		sort(a, 0, a.length - 1);
	}

	private static <T> void sort(Comparable<T>[] a, int lo, int hi) {
		// 将数组a[lo...hi]排序
		if (hi <= lo) {
			return;
		}
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}

	@SuppressWarnings("unchecked")
	private static <T> void merge(Comparable<T>[] a, int lo, int mid, int hi) {

		// 将a[lo...hi]复制到aux[lo...hi]
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		// 将a[lo...mid]和a[mid + 1...hi]归并
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
