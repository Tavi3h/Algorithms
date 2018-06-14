package pers.tavish.ex.chapter2.quicksort.exercises;

class Quick3wayTEx2312 {

	public static <T extends Comparable<? super T>> void sort(T[] a) {
		// StdRandom.shuffle(a);
		sort(a, 0, a.length - 1);
	}

	// quicksort the subarray a[lo .. hi] using 3-way partitioning
	private static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int lt = lo, gt = hi;
		T v = a[lo];
		int i = lo + 1;
		while (i <= gt) {
			int cmp = a[i].compareTo(v);
			if (cmp < 0) {
				exch(a, lt++, i++);
				show(a, lt, i, gt);
			} else if (cmp > 0) {
				exch(a, i, gt--);
				show(a, lt, i, gt);
			} else {
				i++;
			}
		}

		// a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
//		sort(a, lo, lt - 1);
//		sort(a, gt + 1, hi);
	}
	
	private static <T> void show(T[] a, Integer lt, Integer i, Integer gt) {
		System.out.print(lt + "\t" + i + "\t" + gt + "\t");
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
}

public class Ex2312 {

	public static void main(String[] args) {
		String[] a = new String("B A B A B A B A C A D A B R A").split(" ");
		System.out.print("lt\ti\tgt\t");
		for (int i = 0; i < a.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.print(" \t \t \t");
		for (int i = 0; i < a.length; i++) {
			if (i < 10) {
				System.out.print(a[i] + " ");
			} else {
				System.out.print(" " + a[i] + " ");
			}
		}
		System.out.println();
		Quick3wayTEx2312.sort(a);
	}
}
