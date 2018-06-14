package pers.tavish.ex.chapter2.elementarysorts.experiments;

// 实验题 2.1.26
public class InsertionInt {
	public static void sort(int[] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			// 将a[i]插入到a[i - 1]、a[i - 2]、a[i - 3]...之中
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
			}
		}
	}

	private static boolean less(int v, int w) {
		return v < w;
	}

	private static void exch(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	@SuppressWarnings("unused")
	private static void show(int[] a) {
		// 在单行中打印数组
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static boolean isSorted(int[] a) {
		// 测试数组元素是否有序
		for (int i = 0; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}
}
