package pers.tavish.ex.chapter1.analysisofalgorithms.exercises;

import org.junit.Test;

// 练习题
public class Exercises {

	// 练习题1.4.10
	public static int rank(int[] arr, int key) {

		int lo = 0;
		int hi = arr.length - 1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if (key < arr[mid]) {
				hi = mid - 1;
			} else if (key > arr[mid]) {
				lo = mid + 1;
			} else {
				// mid等于0时，说明已经遍历到数组的最左侧
				// 且此时角标0的值等于key，直接返回0
				while (mid != 0) {
					// 如果mid左侧元素小于key，说明当前mid为最小角标
					if (arr[mid - 1] < key) {
						// 跳出循环
						break;
					} else {
						// 如果mid左侧元素不小于key，此时说明mid左侧元素等于key
						// mid减1，进入下一次循环
						mid--;
					}
				}
				return mid;
			}
		}
		return -1;
	}

	// 练习题1.4.12
	public static void printTheCommon(int[] a, int[] b) {
		for (int i = 0, j = 0; i < a.length && j < b.length;) {
			if (a[i] < b[j]) {
				i++;
			} else if (a[i] > b[j]) {
				j++;
			} else {
				System.out.println("Common Element: " + a[i]);
				i++;
				j++;
			}
		}
	}

	// 测试用Junit
	@Test
	public void junitTest() {
		int[] a = { 0, 1, 2, 3, 4, 5 };
		int[] b = { 0, 2, 4, 7 };
		printTheCommon(a, b);
	}
}
