package pers.tavish.ex.chapter1.analysisofalgorithms.experiments;

import java.util.Random;

import edu.princeton.cs.algs4.Stopwatch;

// 实验题 1.4.39
public class DoublingRatioPrecise {
	
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

	public static void main(String[] args) {
		System.out.println("数据量 重复次数 平均耗时（s）");
		// 设定数据量
		for (int n = 250; true; n += n) {
			// 进行10、100、1000次实验
			for (int i = 10; i <= 1000; i *= 10) {
				// 统计实验总时间
				double time = 0;
				// 进行实验
				for (int j = 0; j < i; j++) {
					time += timeTrialofprimary3sum(n);
				}
				// 输出结果
				System.out.printf("%d %d %10.7f\n", n, i, time / i);
			}
			System.out.println();
		}
	}
}
