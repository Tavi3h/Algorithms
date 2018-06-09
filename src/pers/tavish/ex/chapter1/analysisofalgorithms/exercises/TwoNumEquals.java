package pers.tavish.ex.chapter1.analysisofalgorithms.exercises;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;

// 练习题1.4.8
public class TwoNumEquals {
	
	// 平方级别
	public static int count(int[] arr) {
		int N = arr.length;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (arr[i] == arr[j]) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	// NlogN级别
	public static int countFast(int[] arr) {
		Arrays.sort(arr);
		int N = arr.length;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (BinarySearch.indexOf(arr, -arr[i]) > i) {
				cnt++;
			}
		}
		return cnt;
	}
}
