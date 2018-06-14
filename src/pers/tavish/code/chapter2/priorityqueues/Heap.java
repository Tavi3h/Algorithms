package pers.tavish.code.chapter2.priorityqueues;

// 官方实现的HeapSort
// 0角标可以参与排序
public class Heap {

	public static <T> void sort(Comparable<T>[] pq) {
		int n = pq.length;
		for (int k = n / 2; k >= 1; k--)
			sink(pq, k, n);
		while (n > 1) {
			exch(pq, 1, n--);
			sink(pq, 1, n);
		}
	}

	private static <T> void sink(Comparable<T>[] pq, int k, int n) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(pq, j, j + 1))
				j++;
			if (!less(pq, k, j))
				break;
			exch(pq, k, j);
			k = j;
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> boolean less(Comparable<T>[] pq, int i, int j) {
		return pq[i - 1].compareTo((T) pq[j - 1]) < 0;
	}

	private static <T> void exch(Comparable<T>[] pq, int i, int j) {
		Comparable<T> swap = pq[i - 1];
		pq[i - 1] = pq[j - 1];
		pq[j - 1] = swap;
	}

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 3, 4, 6, 1, 2, 7, 0, 5 };
		sort(arr);
		for (Integer i : arr) {
			System.out.print(arr[i] + " ");
		}
	}
}
