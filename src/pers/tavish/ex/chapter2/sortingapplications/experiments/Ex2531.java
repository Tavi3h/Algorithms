package pers.tavish.ex.chapter2.sortingapplications.experiments;

import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.StdRandom;

// 提高题2.5.31
public class Ex2531 {
	
	public static void main(String[] args) {

		int M = 1000;
		int N = 1000;
		int T = 10;

		int times = 0;
		for (int i = 0; i < T; i++) {
			// N个数的数组，数组元素均在0到M-1之间
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) {
				arr[j] = StdRandom.uniform(0, M);
			}

			times += checkRepeatElements(arr);
		}

		System.out.println("M = " + M + ", " + "N = " + N);
		System.out.println("理论值：" + M * (1 - Math.exp(-(double) N / M)));
		System.out.println("实际值：" + (double) times / T);
	}

	// 使用Set来排除重复的值
	private static int checkRepeatElements(int[] arr) {

		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}

		return set.size();
	}

	// 排序后遍历数组计算不重复的值
//	private static <T extends Comparable<? super T>> int distinct(T[] a) {
//		if (a.length == 0)
//			return 0;
//		Arrays.sort(a);
//		int distinct = 1;
//		for (int i = 1; i < a.length; i++)
//			if (a[i].compareTo(a[i - 1]) != 0)
//				distinct++;
//		return distinct;
//	}
}
