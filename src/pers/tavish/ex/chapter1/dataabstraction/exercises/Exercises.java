package pers.tavish.ex.chapter1.dataabstraction.exercises;

import org.junit.Test;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Exercises {

	// 练习题 1.2.1
	public static void shortestDistance() {
		int N = StdIn.readInt();
		Point2D[] arr = new Point2D[N];
		double min = Math.sqrt(2d);

		// 生成N个点的坐标，并描点
		for (int i = 0; i < arr.length; i++) {
			double x = StdRandom.uniform();
			double y = StdRandom.uniform();

			arr[i] = new Point2D(x, y);
			arr[i].draw();
		}

		// 计算最小距离，对N个点进行两两比较
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i != j) {
					double tmp = arr[i].distanceTo(arr[j]);
					min = (tmp < min ? tmp : min);
				}
			}
		}
		StdOut.println("min = " + min);
	}

	// 练习题 1.2.2
	public void testInterval1D() {

		// 从控制台读取Interval的个数
		int N = StdIn.readInt();
		// 创建保存Interval的数组
		Interval1D[] intervals = new Interval1D[N];

		// 对数组中的每个Interval进行初始化，min和max从控制台读取
		for (int i = 0; i < N; i++) {
			double min = StdIn.readDouble();
			double max = StdIn.readDouble();
			intervals[i] = new Interval1D(min, max);
		}

		// 判断两个Interval是否相交，无重复比较
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (intervals[i].intersects(intervals[j])) {
					StdOut.println("Interval[" + i + "] : " + intervals[i] + " intersects with interval[" + j + "] : "
							+ intervals[j]);
				}
			}
		}
	}

	// 练习题 1.2.3

	// 用于测试两个Interval2D是否包含
	private static boolean isContain(Interval1D inter1x, Interval1D inter1y, Interval1D inter2x, Interval1D inter2y) {

		// 读取两个Interval1D的min和max
		double inter1xmin = inter1x.min();
		double inter1xmax = inter1x.max();
		double inter1ymin = inter1y.min();
		double inter1ymax = inter1y.max();

		double inter2xmin = inter2x.min();
		double inter2xmax = inter2x.max();
		double inter2ymin = inter2y.min();
		double inter2ymax = inter2y.max();

		// 进行判断
		if (inter1xmin <= inter2xmin && inter1xmax >= inter2xmax && inter1ymin <= inter2ymin
				&& inter1ymax >= inter2ymax) {

			return true;
		}
		return false;

	}

	public static void testInterval2D() {

		// 从控制台读取N、min和max
		int N = StdIn.readInt();
		double min = StdIn.readDouble();
		double max = StdIn.readDouble();

		if (min >= max || min >= 1 || max >= 1) {
			throw new IllegalArgumentException();
		}

		// 创建用于存放Interval2D的数组
		Interval2D[] intervals = new Interval2D[N];

		// 创建用于存放组成每个Interval2D的Interval1D数组
		Interval1D[] intervalx = new Interval1D[N];
		Interval1D[] intervaly = new Interval1D[N];

		// 用于记录两个Interval是否相交的变量
		int countIntersect = 0;

		// 用于记录两个Interval是否存在包含关系的变量
		int countContain = 0;

		// 对数组进行初始化
		for (int i = 0; i < N; i++) {
			while (intervals[i] == null) {
				try {
					intervalx[i] = new Interval1D(StdRandom.uniform(min, max), StdRandom.uniform(min, max));
					intervaly[i] = new Interval1D(StdRandom.uniform(min, max), StdRandom.uniform(min, max));
					intervals[i] = new Interval2D(intervalx[i], intervaly[i]);
				} catch (IllegalArgumentException e) {
					intervals[i] = null;
				}
			}
		}

		// 设置画笔磅数和颜色
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.DARK_GRAY);

		// 绘制数组中的每个元素
		for (int i = 0; i < N; i++) {
			intervals[i].draw();
		}

		// 判断两个Interval是否相交，无重复比较
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (intervals[i].intersects(intervals[j])) {
					StdOut.println("Interval[" + i + "] : " + intervals[i] + " intersects with interval[" + j + "] : "
							+ intervals[j]);
					countIntersect++;
				}
			}
		}

		// 判断两个Interval是否存在包含关系，需重复比较
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j && isContain(intervalx[i], intervaly[i], intervalx[j], intervaly[j])) {
					StdOut.println("Interval[" + i + "] : " + intervals[i] + " contains interval[" + j + "] : "
							+ intervals[j]);
					countContain++;
				}
			}
		}
		StdOut.println("相交的间隔对数：" + countIntersect + "，包含的间隔对数：" + countContain);
	}

	// 练习题1.1.24
	public void ex1124() {
		String string1 = "hello";
		String string2 = string1;
		string1 = "world";
		StdOut.println(string1);
		StdOut.println(string2);
	}

	// 练习题1.1.24
	public void ex1125() {
		String s = "Hello World";
		s.toUpperCase();
		s.substring(6, 11);
		StdOut.println(s);
	}

	// 练习题1.1.26
	public boolean ex1126(String s1, String s2) {
		return (s1.length() == s2.length() && (s1 + s1).indexOf(s2) != -1);
	}

	// 练习题1.1.27
	public static String mystery(String s) {
		int N = s.length();
		if (N <= 1) {
			return s;
		}
		String a = s.substring(0, N / 2);
		String b = s.substring(N / 2, N);
		return mystery(b) + mystery(a);
	}

	// 练习题1.2.9
	public int count(int key, int[] arr, Counter counter) {

		int index = binarySearch(key, arr);

		// 如果没有在数组中找到key，则返回0
		if (index == -1) {
			return 0;
		} else {
			// 如果找到key
			counter.increment();
		}

		// 向该元素的两侧进行查找
		int left = index - 1, right = index + 1;

		// 查找左侧
		while (left >= 0) {
			if (arr[left--] < key) {
				// 如果左侧元素小于key，直接跳出循环
				break;
			} else {
				// 否则，说明左侧元素等于key
				counter.increment();
			}
		}

		// 查找右侧
		while (right <= arr.length - 1) {
			if (arr[right++] > key) {
				// 如果右侧元素大于key，直接跳出循环
				break;
			} else {
				// 否则，说明右侧元素等于key
				counter.increment();
			}
		}
		return counter.tally();
	}

	// 二分查找
	public int binarySearch(int key, int[] arr) {
		return binarySearch(key, arr, 0, arr.length);
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

	// 测试用JUnit
	@Test
	public void junitTest() {
		// testInterval1D();

		// ex1124();

		// ex1125();

		// StdOut.println(ex1126("ACTGACG", "TGACGAC"));

		// StdOut.println(mystery("hello world"));

		int[] arr = { 0, 1, 2, 3, 3, 4, 5, 5, 5, 5, 5, 6, 7, 8 };
		StdOut.println(count(6, arr, new Counter("bsCounter")));
	}

	// 测试用主函数
	public static void main(String[] args) {
		// shortestDistance();

		// testInterval2D();
	}
}
