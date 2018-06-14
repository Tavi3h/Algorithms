package pers.tavish.ex.chapter1.analysisofalgorithms.exercises;

import java.io.File;
import java.math.BigInteger;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

// 练习题 1.4.2
public class ThreeSumBigInteger {

	// 统计和为0的元组的数量
	public static int count(int[] a) {

		// 将int型数组转为BigInteger型数组
		BigInteger[] b = new BigInteger[a.length];

		// 遍历赋值
		int N = b.length;
		for (int i = 0; i < N; i++) {
			b[i] = BigInteger.valueOf(a[i]);
		}

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				for (int k = j + 1; k < N; k++) {
					// 使用大数加法
					// 当三个数的和为0时，signum方法返回0
					if (b[i].add(b[j]).add(b[k]).signum() == 0) {
						cnt++;
					}
				}
			}
		}
		return cnt;
	}

	public static void main(String[] args) {
		int[] a = new In(new File(args[0])).readAllInts();
		StdOut.println(count(a));
	}
}
