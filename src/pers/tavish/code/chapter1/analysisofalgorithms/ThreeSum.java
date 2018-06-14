package pers.tavish.code.chapter1.analysisofalgorithms;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSum {

	// 统计和为0的元组的数量
	public static int count(int[] a) {
		int N = a.length;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				for (int k = j + 1; k < N; k++) {
					if (a[i] + a[j] + a[k] == 0) {
						cnt++;
					}
				}
			}
		}
		return cnt;
	}

	public static void main(String[] args) {
		Stopwatch timer = new Stopwatch();
		int[] a = new In(new File(args[0])).readAllInts();
		StdOut.println(count(a));
		System.out.println(timer.elapsedTime());
	}
}
