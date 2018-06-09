package pers.tavish.ex.chapter2.sortingapplications.exercises;

import java.io.File;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;

// 练习题2.5.2
public class Ex252 {

	// 字符串二分查找
	public static int indexOf(String[] arr, String key) {
		int lo = 0;
		int hi = arr.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key.compareTo(arr[mid]) < 0) {
				hi = mid - 1;
			} else if (key.compareTo(arr[mid]) > 0) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		String[] arr = new In(new File(args[0])).readAllStrings();
		Arrays.sort(arr);

		int N = arr.length;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (indexOf(arr, arr[i] + arr[j]) != -1) {
					System.out.println("Found : " + arr[i] + " " + arr[j] + " " + arr[i] + arr[j]);
				} else if (indexOf(arr, arr[j] + arr[i]) != -1) {
					System.out.println("Found : " + arr[j] + " " + arr[i] + " " + arr[j] + arr[i]);
				}
			}
		}
	}
}
