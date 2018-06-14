package pers.tavish.ex.chapter1.programmingmodel.experiments;

import org.junit.Test;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

// 实验题1.1.37

public class BadShuffleTest {

	public int[][] shuffle(int m, int n) {

		// 创建大小为m的数组
		int[] arr = new int[m];

		// 创建用于记录结果的二维数组
		int[][] result = new int[m][m];

		// 进行n次打乱
		for (int i = 1; i <= n; i++) {

			// 打乱前对数组进行初始化
			for (int j = 0; j < arr.length; j++) {
				arr[j] = j;
			}

			// 对数组进行打乱，不使用StdRandom的标准打乱
			shuffle(arr);

			// 记录每次打乱后的结果
			record(result, arr);
		}

		return result;

	}

	// 直接在原有StdRandom的源码上修改
	private void shuffle(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			// 此时返回的是 0 ~ n-1 之间的随机整数
			// 而不是i ~ n-1之间的随机整数
			int r = StdRandom.uniform(n);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	// 记录打乱结果
	private void record(int[][] result, int[] arr) {

		for (int j = 0; j < arr.length; j++) {
			result[arr[j]][j]++;
		}
	}

	// 打印二维数组
	public void printArray(int[][] result) {
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				StdOut.print(result[i][j] + "\t");
			}
			StdOut.println();
		}
	}

	@Test
	public void testShuffle() {
		int m = StdIn.readInt();
		int n = StdIn.readInt();
		printArray(shuffle(m, n));
	}
}
