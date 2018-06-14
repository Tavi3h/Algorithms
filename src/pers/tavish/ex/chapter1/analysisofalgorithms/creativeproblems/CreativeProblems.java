package pers.tavish.ex.chapter1.analysisofalgorithms.creativeproblems;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;

// 提高题
public class CreativeProblems {
	// 提高题 1.4.14
	public static int fourSum(int[] arr) {
		Arrays.sort(arr);
		int N = arr.length;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				for (int k = j + 1; k < N; k++) {
					if (BinarySearch.indexOf(arr, -arr[i] - arr[j] - arr[k]) > k) {
						cnt++;
					}
				}
			}
		}
		return cnt;
	}

	// 提高题 1.4.15 - - - 线性级别的TwoSumFast
	public static int linearTwoSumFaster(int[] arr) {
		Arrays.sort(arr);
		int cnt = 0;
		int lo = 0;
		int hi = arr.length - 1;
		while (lo < hi) {
			if (arr[lo] + arr[hi] > 0) {
				hi--;
			} else if (arr[lo] + arr[hi] < 0) {
				lo++;
			} else {
				cnt++;
				hi--;
				lo++;
			}
		}
		return cnt;
	}

	// 提高题 1.4.15 - - - 平方级别的ThreeSumFast
	public static int squareThreeSumFaster(int[] arr) {
		Arrays.sort(arr);
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			int lo = i + 1;
			int hi = arr.length - 1;
			while (lo < hi) {
				if (arr[i] + arr[lo] + arr[hi] > 0) {
					hi--;
				} else if (arr[i] + arr[lo] + arr[hi] < 0) {
					lo++;
				} else {
					cnt++;
					hi--;
					lo++;
				}
			}
		}
		return cnt;
	}

	// 提高题 1.4.16
	public static void ex1416(double[] arr) {

		Arrays.sort(arr); // 该排序操作为NlogN

		double minDiff = Double.MAX_VALUE;
		double minA = 0;
		double minB = 0;

		// 遍历数组的操作为N
		for (int i = 0; i < arr.length; i++) {
			if (arr[i + 1] - arr[i] < minDiff) {
				minA = arr[i];
				minA = arr[i + 1];
				minDiff = arr[i + 1] - arr[i];
			}
		}
		System.out.println("最接近的一对为：" + minA + " " + minB);
	}

	// 提高题 1.4.17
	public static void ex1417(double[] arr) {
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
			if (arr[i] < min) {
				min = arr[i];
			}
		}
		System.out.println("最遥远的一对为：" + min + " " + max);
	}

	// 提高题 1.4.18 - - - 递归写法
	public static int ex1418(int[] arr) {
		return ex1418(arr, 0, arr.length - 1);
	}

	private static int ex1418(int[] arr, int lo, int hi) {

		int mid = (lo + hi) / 2;

		// 当mid到达数组边缘时，此时边缘值就是局部最小值，直接返回
		// 如果满足条件则mid就是局部最小值角标，否则向mid-1和mid+1中较小的一边进行二分查找
		if ((mid == 0 || mid == arr.length - 1) || (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1])) {
			return mid;
		} else if (arr[mid - 1] < arr[mid + 1]) {
			return ex1418(arr, lo, mid - 1);
		} else {
			return ex1418(arr, mid + 1, hi);
		}
	}

	// 提高题 1.4.18 - - - 循环写法
	public static int ex1418_2(int[] arr) {

		int lo = 0;
		int hi = arr.length - 1;

		while (true) {
			int mid = (lo + hi) / 2;
			// 当mid到达数组边缘时，此时边缘值就是局部最小值，直接返回
			// 如果满足条件则mid就是局部最小值角标，否则向mid-1和mid+1中较小的一边进行二分查找
			if ((mid == 0 || mid == arr.length - 1) || (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1])) {
				return mid;
			} else if (arr[mid - 1] < arr[mid + 1]) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
	}

	// 提高题 1.4.20
	public static int ex1420(int[] arr, int key) {
		// 查找双调数组中最大值的角标
		int lo = 0;
		int hi = arr.length - 1;

		int max = (lo + hi) / 2;
		// 寻找最大元素的角标，logN
		while (true) {
			if (arr[max] > arr[max - 1] && arr[max] > arr[max + 1]) {
				break;
			}
			if (arr[max] < arr[max + 1]) {
				lo = max;
			} else {
				hi = max;
			}
			max = (lo + hi) / 2;
		}

		// 遍历结束，此时arr[max]为最大元素
		if (arr[max] == key) {
			return max;
		}

		// 以max为界，将数组拆分为两部分，分别进行二分查找

		// 查找max左边，log(N/2)
		lo = 0;
		hi = max - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < arr[mid]) {
				hi = mid - 1;
			} else if (key > arr[mid]) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}

		// 查找max右边，log(N/2)
		lo = max + 1;
		hi = arr.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < arr[mid]) {
				lo = mid + 1;
			} else if (key > arr[mid]) {
				hi = mid - 1;
			} else {
				return mid;
			}
		}

		// 程序如果运行到这里，说明没有找到
		return -1;
	}

	// 提高题 1.4.22 - - - 不补齐数组
	public static int fibonacciRank(int[] arr, int key) {
		int fk = 1;
		int fk_1 = 1;
		int fk_2 = 0;

		while (fk < arr.length) {
			fk = fk + fk_1;
			fk_1 = fk_1 + fk_2;
			fk_2 = fk - fk_1;
		}
		System.out.println(fk);

		int lo = 0;

		while (fk_2 >= 0) {
			int i = lo + fk_2 > arr.length - 1 ? arr.length - 1 : lo + fk_2;
			if (arr[i] == key) {
				return i;
			} else if (arr[i] < key) {
				lo = lo + fk_2;
			}

			fk = fk_1;
			fk_1 = fk_2;
			fk_2 = fk - fk_1;
		}
		return -1;
	}

	// 提高题 1.4.22 - - - 补齐数组

	// 创建斐波那契数列，该数列的最后一位是斐波那契数列中第一个大于等于i的值
	private static Integer[] createFibonacci(int i) {

		// 使用List保存每一个数值，最后将List转为Array
		List<Integer> fibList = new ArrayList<>();
		fibList.add(0);
		fibList.add(1);

		// 计算斐波那契数列，当数列中的最后一个元素大于等于i时结束循环
		for (int idx = 0; fibList.get(fibList.size() - 1) < i; idx++) {
			fibList.add(fibList.get(idx) + fibList.get(idx + 1));
		}

		// 返回数组
		return fibList.toArray(new Integer[0]);
	}

	public static int fibonacciRank_2(int[] arr, int key) {

		// 创建斐波那契数列
		Integer[] fib = createFibonacci(arr.length + 1);

		// 对数组进行扩充
		int[] extArr = new int[fib[fib.length - 1] - 1];
		for (int i = 0; i < extArr.length; i++) {
			if (i >= arr.length) {
				extArr[i] = arr[arr.length - 1];
			} else {
				extArr[i] = arr[i];
			}
		}

		int k = fib.length - 1;
		int lo = 0;
		int hi = arr.length - 1;

		while (lo <= hi) {
			int mid = lo + fib[k - 1] - 1;
			if (key < extArr[mid]) {
				hi = mid - 1;
				k -= 1;
			} else if (key > extArr[mid]) {
				lo = mid + 1;
				k -= 2;
			} else {
				if (mid < arr.length) {
					return mid;
				} else {
					return arr.length - 1;
				}
			}
		}
		return -1;
	}

	@Test
	public void junitTest() {
		int[] arr = { -5, -1, 0, 2, 3, 4, 5, 17, 21, 22 };
		System.out.println(fibonacciRank_2(arr, 2));
	}

	public static void main(String[] args) {
		int[] a = new In(new File(args[0])).readAllInts();
		System.out.println(squareThreeSumFaster(a));
	}
}
