package pers.tavish.ex.chapter2.elementarysorts.experiments;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

// 实验题 2.1.30
public class SortCompare2130 {
	private static Integer[] createShells(int N, int t) {
		List<Integer> shells = new ArrayList<>();
		int h = 1;
		int k = 0;
		while (h < N / 3) {
			h = (int) Math.pow(t, k++);
			shells.add(h);
		}
		shells.add(h);
		return shells.toArray(new Integer[0]);
	}

	public static <T> double sort(Comparable<T>[] a, int t) {
		int N = a.length;

		Stopwatch timer = new Stopwatch();
		Integer[] shells = createShells(N, t);
		double time = timer.elapsedTime(); // 记录创建shells数组所用的时间
		
		// while循环改为for循环遍历shells数组，数组的第一项为0
		for (int k = shells.length - 1; k > 0; k--) {
			int h = shells[k];
			// 将数组变为h有序
			for (int i = h; i < N; i++) {
				// 将a[i]插入到a[i - h]、a[i - 2 * h]、a[i - 3 * h]...之中
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exch(a, j, j - h);
				}
			}
		}
		return time;
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

	public static <T> boolean isSorted(Comparable<T>[] a) {
		// 测试数组元素是否有序
		for (int i = 0; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	public static double time(Double[] a, int t) {
		Stopwatch timer = new Stopwatch();
		double time = sort(a, t);
		return timer.elapsedTime() - time; // 返回的时间里扣除了创建shells数组的时间
	}

	public static double timeRandomInput(Double[] a, int t) {
		double total = 0d;
		total += time(a, t);
		return total;
	}

	public static void main(String[] args) {

		int N = 1000000;
		Double[] a = new Double[N];
		for (int i = 0; i < N; i++) {
			a[i] = StdRandom.uniform();
		}
		
		Double[] backup = new Double[N];
		for (int t = 2; t <= 10; t++) {
			System.arraycopy(a, 0, backup, 0, N);
			double time = timeRandomInput(backup, t);
			System.out.println("N = " + N + " t = " + t + " 耗时：" + time);
		}
	}
}
