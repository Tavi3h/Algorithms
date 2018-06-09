package pers.tavish.ex.chapter2.quicksort.exercises;

public class Ex232 {
	
	public static <T extends Comparable<? super T>> void sort(T[] a) {
		sort(a, 0, a.length - 1);
	}

	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		if (hi <= lo) {
			show(a, lo, null, hi);
			return;
		}
		int j = partition(a, lo, hi); // 将数组切分
		show(a, lo, j, hi);
		sort(a, lo, j - 1); // 排序左半边
		sort(a, j + 1, hi); // 排序右半边
	}

	private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {
		// 将数组分为a[lo...i-1]，a[i]，a[i+1...hi]
		int i = lo, j = hi + 1;
		T v = a[lo];
		while (true) {
			// 扫描左右，检查扫描是否结束并交换元素
			while (less(a[++i], v)) {
				if (i == hi) {
					break;
				}
			}
			while (less(v, a[--j])) {
				if (j == lo) {
					break;
				}
			}
			if (i >= j) {
				break;
			}
			exch(a, i, j);
		}

		exch(a, lo, j); // 将v = a[j]放入正确的位置
		return j; // 达成 a[lo..j-1] <= a[j] <= a[j+1..hi]
	}

	private static <T> void show(T[] a, Integer lo, Integer j, Integer hi) {
		if (j == null) {
			System.out.print(lo + "\t" + " " + "\t" + hi + "\t");
		} else {
			System.out.print(lo + "\t" + j + "\t" + hi + "\t");
		}
		for (int k = 0; k < a.length; k++) {
			if (k < 10) {
				System.out.print(a[k] + " ");
			} else {
				System.out.print(" " + a[k] + " ");
			}
		}
		System.out.println();
	}

	private static <T extends Comparable<? super T>> void exch(T[] a, int i, int j) {
		T t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static <T extends Comparable<? super T>> boolean less(T v, T w) {
		return v.compareTo(w) < 0;
	}

	public static void main(String[] args) {
		String[] a = new String("E A S Y Q U E S T I O N").split(" ");
		System.out.print("lo\tj\thi\t");
		for (int i = 0; i < a.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.print("  \t \t  \t");
		for (int i = 0; i < a.length; i++) {
			if (i < 10) {
				System.out.print(a[i] + " ");
			} else {
				System.out.print(" " + a[i] + " ");
			}
		}
		System.out.println();
		sort(a);
	}
}
