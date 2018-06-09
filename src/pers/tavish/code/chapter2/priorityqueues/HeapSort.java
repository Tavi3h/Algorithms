package pers.tavish.code.chapter2.priorityqueues;

// 堆排序
// 数组的0角标不能参与排序
public class HeapSort {
	public static <T> void sort(Comparable<T>[] a) {
		int N = a.length - 1;
		// 堆的构造
		for (int k = N / 2; k >= 1; k--) {
			sink(a, k, N);
		}
		// 下沉排序
		while (N > 1) {
			exch(a, 1, N--);
			sink(a, 1, N);
		}
	}

	public static <T> void sink(Comparable<T>[] a, int i, int N) {
		while (2 * i <= N) {
			int j = i * 2;
			if (j < N && less(a[j], a[j + 1])) {
				j++;
			}
			if (!less(a[i], a[j])) {
				break;
			}
			exch(a, i, j);
			i = j;
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
		return v.compareTo((T) w) < 0;
	}

	private static <T> void exch(Comparable<T>[] a, int i, int j) {
		Comparable<T> t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 3, 4, 6, 1, 2, 7, 0, 5 };
		sort(arr);
		for (Integer i : arr) {
			System.out.print(i + " ");
		}
	}
}
