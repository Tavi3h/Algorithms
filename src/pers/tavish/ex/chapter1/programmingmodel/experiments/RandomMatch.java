package pers.tavish.ex.chapter1.programmingmodel.experiments;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// 实验题1.1.39

public class RandomMatch {

	// 二分查找的循环实现
	private int binarySearch(int key, int[] arr, int lo, int hi) {

		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (key < arr[mid]) {
				hi = mid - 1;
			} else if (key > arr[mid]) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public int binarySearch(int key, int[] arr) {
		return binarySearch(key, arr, 0, arr.length - 1);
	}

	// 生成6位数的随机数组
	public int[] randomArray(int length) {
		int[] arr = new int[length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = StdRandom.uniform(100000, 1000000);
		}
		return arr;
	}

	// 对两个数组进行比较，统计两个数组中的相同元素个数
	public int countSameElements(int[] arr1, int[] arr2) {
		int count = 0;
		for (int i = 0; i < arr1.length; i++) {
			if (binarySearch(arr1[i], arr2) != -1) {
				count++;
			}
		}
		return count;
	}

	// 随机匹配，t为实验次数
	public void randomMatch(int t) {

		for (int i = 1000; i <= 1000000; i *= 10) {

			int count = 0;

			// 循环进行实验
			for (int j = 0; j < t; j++) {

				int[] arr1 = randomArray(i);
				int[] arr2 = randomArray(i);

				count += countSameElements(arr1, arr2);
			}

			StdOut.println("N = " + i + "时，" + t + "次实验的平均值为：" + (double) (count / t));

		}
	}
	
	@Test
	public void testRandomMatch() {
		randomMatch(100);
	}
}
