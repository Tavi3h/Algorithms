package pers.tavish.ex.chapter1.programmingmodel.exercises;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercises {

	// 练习题1.1.3
	public void check3Int() {

		int a = StdIn.readInt();
		int b = StdIn.readInt();
		int c = StdIn.readInt();

		if (a == b && b == c) {
			StdOut.println("equal");
		} else {
			StdOut.println("not equal");
		}
	}

	// 练习题1.1.5
	public void check2double(double x, double y) {
		StdOut.println((x > 0 && x < 1) && (y > 0 && y < 1));
	}

	// 练习题1.1.6
	public void ex116() {
		int f = 0;
		int g = 1;
		for (int i = 0; i <= 15; i++) {
			StdOut.print(f);
			f = f + g;
			g = f - g;
		}
	}

	// 练习题1.1.7a
	public void ex117_a() {
		double t = 9.0;
		while (Math.abs(t - 9.0 / t) > .001) {
			t = (9.0 / t + t) / 2.0;
		}
		StdOut.printf("%.5f\n", t);
	}

	// 练习题1.1.7b
	public void ex117_b() {
		int sum = 0;
		for (int i = 1; i < 1000; i++) {
			for (int j = 0; j < i; j++) {
				sum++;
			}
		}
		StdOut.println(sum);
	}

	// 练习题1.1.7c
	public void ex117_c() {
		int sum = 0;
		for (int i = 1; i < 1000; i *= 2) {
			for (int j = 0; j < 1000; j++) {
				sum++;
			}
		}
		StdOut.println(sum);
	}

	// 练习题1.1.8
	public void ex118() {
		System.out.println('b');
		System.out.println('b' + 'c');
		System.out.println((char) ('a' + 4));
	}

	// 练习题1.1.9
	public void ex119(int n) {

		StringBuilder sb = new StringBuilder();

		for (int i = n; i > 0; i /= 2) {
			sb.insert(0, i % 2);
		}

		StdOut.println(sb.toString());
	}

	// 练习题1.1.11
	public void ex1111() {

		// 创建二维数组
		boolean[][] bools = new boolean[10][10];

		// 对二维数组进行赋值
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				bools[i][j] = StdRandom.bernoulli();
			}
		}

		// 打印列号（0-9）
		StdOut.print(" ");
		for (int k = 0; k < 10; k++) {
			StdOut.print(k);
		}
		StdOut.println();

		// 逐行打印
		for (int i = 0; i < 10; i++) {
			// 打印当前行号
			StdOut.print(i);
			// 打印当前行的布尔值
			for (int j = 0; j < 10; j++) {
				if (bools[i][j]) {
					StdOut.print("*");
				} else {
					StdOut.print(" ");
				}
			}
			// 打印换行
			StdOut.println();
		}
	}

	// 练习题1.1.12
	public void ex1112() {
		int[] a = new int[10];
		for (int i = 0; i < 10; i++) {
			a[i] = 9 - i;
		}
		for (int i = 0; i < 10; i++) {
			a[i] = a[a[i]];
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(a[i]);
		}
	}

	// 练习题1.1.13
	public void ex1113(int m, int n) {

		// 以boolean型二维数组为例
		boolean[][] arr = new boolean[m][n];

		// 创建转置后的数组
		boolean[][] transp = new boolean[n][m];

		// 对数组进行赋值
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = StdRandom.bernoulli();
			}
		}

		// 进行转置
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				transp[i][j] = arr[j][i];
			}
		}

	}

	// 练习题1.1.14
	public static int lg(int n) {

		int maxInt = 0;
		while (n != 1 && n != 0) {
			n /= 2;
			maxInt++;
		}
		StdOut.println(maxInt);
		return maxInt;
	}

	// 练习题1.1.15
	public static Integer[] histogram(int[] arr, int m) {

		Integer[] result = new Integer[m];

		// 统计arr中各元素的数量
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			if (!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
			} else {
				map.put(arr[i], map.get(arr[i]) + 1);
			}
		}

		// 对数组result进行赋值
		for (int j = 0; j < result.length; j++) {
			Integer tmp = map.get(j);
			result[j] = (tmp == null ? null : tmp);
		}

		return result;
	}

	// 练习题1.1.16
	public static String exR1(int n) {
		if (n <= 0) {
			return "";
		}
		return exR1(n - 3) + n + exR1(n - 2) + n;
	}

	// 练习题1.1.17
	public static String exR2(int n) {
		String s = exR2(n - 3) + n + exR2(n - 2) + n;
		if (n <= 0) {
			return "";
		}
		return s;
	}

	// 练习题1.1.18a
	public static int mystery(int a, int b) {
		if (b == 0) {
			return 0;
		}
		if (b % 2 == 0) {
			return mystery(a + a, b / 2);
		}
		return mystery(a + a, b / 2) + a;
	}

	// 练习题1.1.19
	public static long[] F(int n) {

		long[] arr = new long[n];

		arr[0] = 0;
		arr[1] = 1;
		for (int i = 2; i < arr.length; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}
		return arr;
	}

	// 练习题1.1.20
	public static double ex1120(int n) {

		if (n == 1) {
			return 0;
		}

		return Math.log(n) + ex1120(n - 1);
	}

	// 练习题1.1.21
	public void ex1121() {

		List<String[]> list = new ArrayList<>();

		while (true) {
			String string = StdIn.readLine();
			// 设置结束输入的标志
			if (!string.equals("STOP")) {
				// 将输入的字符串拆分为字符串数组并储存
				list.add(string.split(","));
			} else {
				break;
			}
		}

		// 这里没有使用printf()
		StdOut.println("name\t" + "num1\t" + "num2\t" + "(num1 / num2)");
		for (int i = 0; i < list.size(); i++) {
			String[] tmp = list.get(i);
			StdOut.println(tmp[0] + "\t" + tmp[1] + "\t" + tmp[2] + "\t"
					+ String.format("%.2f", (Double.parseDouble(tmp[1]) / Double.parseDouble(tmp[2]))));
		}
	}

	// 练习题1.1.22
	public static int rank(int key, int[] a) {
		return rank(key, a, 0, a.length - 1, 0);
	}

	private static int rank(int key, int[] a, int lo, int hi, int depth) {

		// 设置基准情况
		if (lo > hi) {
			return -1;
		}

		// 打印递归深度
		StdOut.println("Recursive depth: " + depth + "->");

		// 打印lo、hi前的缩进，使用*表示
		for (int i = 0; i < depth; i++) {
			StdOut.print("*");
		}

		StdOut.println("lo = " + lo + " hi = " + hi);

		// 开始二分查找
		int mid = lo + (hi - lo) / 2;

		if (key < a[mid]) {
			return rank(key, a, lo, mid - 1, depth + 1);
		} else if (key > a[mid]) {
			return rank(key, a, mid + 1, hi, depth + 1);
		} else {
			return mid;
		}
	}

	// 练习题1.1.23
	public int binarySearch(int key, int[] arr) {
		return binarySearch(key, arr, 0, arr.length - 1);
	}

	private int binarySearch(int key, int[] arr, int lo, int hi) {

		if (lo > hi) {
			return -1;
		}
		int mid = lo + (hi - lo) / 2;

		if (key < arr[mid]) {
			return binarySearch(key, arr, lo, mid - 1);
		} else if (key > arr[mid]) {
			return binarySearch(key, arr, mid + 1, hi);
		} else {
			return mid;
		}
	}

	public void ex1123() {

		// 设置标记
		char symbol = '+';

		// 读取硬盘上的白名单
		String whitelistpath = "datafiles\\tinyW.txt";
		int[] whitelist = new In(new File(whitelistpath)).readAllInts();

		// 将白名单排序
		Arrays.sort(whitelist);

		// 输入待查整数
		int key = StdIn.readInt();

		// 获取查询结果
		int result = binarySearch(key, whitelist);

		// 判断并输出
		if (symbol == '+' && result == -1) {
			StdOut.println(key);
		}
		if (symbol == '-' && result != -1) {
			StdOut.println(key);
		}
	}

	// 练习题1.1.24
	public int euclid() {

		int p = StdIn.readInt();
		int q = StdIn.readInt();

		return euclid(p, q, 0);

	}

	private int euclid(int p, int q, int depth) {

		StdOut.println("Recursive depth: " + depth + " p = " + p + " q = " + q);

		if (p % q == 0) {
			return q;
		} else {
			return euclid(q, p % q, depth + 1);
		}
	}

	// 测试用JUnit
	@Test
	public void junitTest() {
		// check3Int();

		// check2double(0.2, 0.3);

		// ex116();

		// ex117_a();
		// ex117_b();
		// ex117_c();

		// ex118();

		// ex119(23);

		// ex1111();

		// ex1112();

		ex1113(2, 3);

		// lg(64);

		// int[] arr = { 1, 2, 3, 4, 5, 10, 1, 2, 3, 0, 0 };
		// histogram(arr, 13);

		// StdOut.println(exR1(6));

		// exR2(3);

		// StdOut.println(mystery(2, 25));
		// StdOut.println(mystery(3, 11));

		// StdOut.println(ex1120(5));

		// ex1121();

		// int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		// StdOut.println(rank(7, arr));

		// ex1123();

		// StdOut.println(euclid());
	}
}
