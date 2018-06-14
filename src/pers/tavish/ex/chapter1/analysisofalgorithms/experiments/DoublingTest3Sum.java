package pers.tavish.ex.chapter1.analysisofalgorithms.experiments;

import java.util.Random;

import edu.princeton.cs.algs4.Stopwatch;

// 实验题 1.4.38
public class DoublingTest3Sum {

	// 3-sum的初级算法
	private static int primary3sum(int[] a) {
		int N = a.length;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (i < j && j < k) {
						if (a[i] + a[j] + a[k] == 0) {
							cnt++;
						}
					}
				}
			}
		}
		return cnt;
	}

	// 原threeSum算法
	private static int threeSum(int[] a) {
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
	
	public static double timeTrialofprimary3sum(int n) {
		int[] a = new int[n];
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < n; i++) {
			a[i] = random.nextInt(Integer.MAX_VALUE / 2 - Integer.MIN_VALUE / 2) + Integer.MIN_VALUE / 2;
		}
		Stopwatch timer = new Stopwatch();
		primary3sum(a);
		return timer.elapsedTime();
	}

	public static double timeTrialThreeSum(int n) {
		int[] a = new int[n];
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < n; i++) {
			a[i] = random.nextInt(Integer.MAX_VALUE / 2 - Integer.MIN_VALUE / 2) + Integer.MIN_VALUE / 2;
		}
		Stopwatch timer = new Stopwatch();
		threeSum(a);
		return timer.elapsedTime();
	}
	
	public static void main(String[] args) {
		
		System.out.println("N primary3Sum threeSum");
		for (int n = 250; true; n += n) {
			double primary3Sum = timeTrialofprimary3sum(n);
			double threeSum = timeTrialThreeSum(n);
			System.out.printf("%d %5.4f %5.4f\n", n, primary3Sum, threeSum);
		}
	}
}
