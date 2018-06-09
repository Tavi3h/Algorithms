package pers.tavish.ex.chapter1.programmingmodel.creativeproblems;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class CreativeProblems {

	// 提高题1.1.27
	/*
	 * 该方法用于创建一个存储各种实验情况概率的二维数组。 n为实验次数，k为事件发生的次数，p为事件发生的概率。
	 * 数组中的每个元素代表：n次实验中，事件发生了k次的概率。
	 */
	public static double[][] createBinomial2DArray(int n, int k, double p) {

		// 创建二维数组用于存储每一个计算值
		double[][] array = new double[n + 1][k + 1];

		// 初始化数组的第一个元素，即0次实验，事件发生0次的概率为1。
		array[0][0] = 1.0;

		// 对代表“n次实验中事件发生0次”的元素进行赋值，即二维数组中的第一列k=0进行赋值
		for (int i = 1; i <= n; i++) {
			array[i][0] = array[i - 1][0] * (1 - p);
		}

		// 逐行对二维数组的剩余部分进行赋值
		for (int i = 1; i <= n; i++) {
			// 由于事件发生次数不会超过实验次数，所以限制条件j<=i
			for (int j = 1; j <= i && j <= k; j++) {
				// 这里使用推论：P(n,k,p)=(1-p)*P(n-1,k,p)+p*P(n-1,k-1,p)
				array[i][j] = (1 - p) * array[i - 1][j] + p * array[i - 1][j - 1];
			}
		}
		return array;
	}

	// 提高题1.1.28
	public Integer[] ex1128() {

		// 声明数组
		Integer[] result = null;

		// 读取硬盘上的白名单
		String whitelistpath = "datafiles\\tinyW.txt";
		int[] whitelist = new In(new File(whitelistpath)).readAllInts();

		// 使用Set来处理重复元素
		Set<Integer> set = new HashSet<>();

		// 循环遍历白名单数组并存入Set
		for (int i = 0; i < whitelist.length; i++) {
			set.add(whitelist[i]);
		}

		// 将Set转为数组
		result = set.toArray(new Integer[0]);

		// 对result数组进行排序
		Arrays.sort(result);

		return result;
	}

	// 提高题1.1.29

	// 二分查找方法
	public static int binarySearch(int key, int[] arr) {
		return binarySearch(key, arr, 0, arr.length - 1);
	}

	private static int binarySearch(int key, int[] arr, int lo, int hi) {

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

	// rank()方法，该方法返回数组arr中小于key的元素的数量
	public static int rank(int key, int[] arr) {

		int index = binarySearch(key, arr);

		// 如果在arr中没有找到该key，遍历数组确定key在arr中的位置
		if (index == -1) {

			// 如果0号元素大于key
			if (arr[0] > key) {
				// 则arr中不存在比key小的元素
				return 0;
			}

			// 如果key比最大的元素大，则arr的全部元素均小于key
			if (arr[arr.length - 1] < key) {
				return arr.length;
			}

			// 判断key在arr中的位置，并返回小于key的元素的个数
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] < key && arr[i + 1] > key) {
					return i + 1;
				}
			}
		}

		// 如果找到了该key，由于数组可能存在重复元素，所以需要找到等于key的最小序号的元素
		while (index > 0) {
			// 对find逐次减1，并与key进行比较
			if (arr[index - 1] < key) {
				// 如果左侧元素小于key，直接跳出循环
				break;
			}
			// 如果不小于，意味着左侧元素与key相等，index--，进行下一次比较
			index--;
		}
		return index;
	}

	// count()方法，该方法返回数组中等于该键的元素的数量
	public static int count(int key, int[] arr) {

		int index = binarySearch(key, arr);

		int num = 0;

		// 如果没有在数组中找到key，则返回0
		if (index == -1) {
			return num;
		} else {
			// 如果找到key，则令num = 1
			num = 1;
		}

		// 向该元素的两侧进行查找
		int left = index - 1, right = index + 1;

		// 查找左侧
		while (left >= 0) {
			if (arr[left--] < key) {
				// 如果左侧元素小于key，直接跳出循环
				break;
			} else {
				// 否则，说明左侧元素等于key，num++
				num++;
			}
		}

		// 查找右侧
		while (right <= arr.length - 1) {
			if (arr[right++] > key) {
				// 如果右侧元素大于key，直接跳出循环
				break;
			} else {
				// 否则，说明右侧元素等于key，num++
				num++;
			}
		}
		return num;
	}

	// 提高题1.1.30

	// 欧几里得算法
	private int euclid(int i, int j) {

		if (i % j == 0) {
			return j;
		}

		return euclid(j, i % j);
	}

	private boolean relatprime(int i, int j) {

		boolean mark = false;

		// 如果两个数中存在0，返回false
		if (i == 0 || j == 0) {
			return mark;
		}

		// 如果两个数都等于1，这种情况不用欧几里得算法判断，设置mark为true。
		// 其余情况使用欧几里得算法计算两个数的最大公约数。
		if (i == 1 && j == 1) {
			mark = true;
		} else if (euclid(i, j) == 1) {
			mark = true;
		}

		return mark;
	}

	public boolean[][] ex1130(int n) {

		boolean[][] arr = new boolean[n][n];

		// 遍历数组进行赋值
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = relatprime(i, j);
			}
		}

		return arr;
	}

	// 提高题1.1.31

	/*
	 * 在圆上画点
	 */
	private static double[][] drawPoints(double x0, double y0, double r, int N) {
		double[][] points = new double[N][2];
		StdDraw.setPenRadius(0.005);
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		for (int idx = 0; idx < N; ++idx) {
			// 计算点的坐标值
			double x = x0 + r * Math.cos(2 * Math.PI * idx / N);
			double y = y0 + r * Math.sin(2 * Math.PI * idx / N);
			StdDraw.point(x, y);
			points[idx][0] = x;
			points[idx][1] = y;
		}
		return points;
	}

	/*
	 * 进行随机连接
	 */
	private static void randomLinkPoints(double[][] points, double p) {
		StdDraw.setPenRadius(0.002);
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		int length = points.length;
		for (int i = 0; i < length; ++i) {
			for (int j = 0; j < length; ++j) {
				if (true == StdRandom.bernoulli(p)) {
					StdDraw.line(points[i][0], points[i][1], points[j][0], points[j][1]);
				}
			}
		}
	}

	/*
	 * 画圆
	 */
	private static void drawCircle(double x, double y, double r) {
		StdDraw.setXscale(0, 2 * x);
		StdDraw.setYscale(0, 2 * y);
		StdDraw.setPenRadius(0.003);
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.circle(x, y, r);

	}

	public static void randomLink() {

		// 从控制台读取n、p
		int n = StdIn.readInt();
		double p = StdIn.readDouble();

		// 设置圆的圆心和半径
		double x = 10.0;
		double y = 10.0;
		double r = 9.0;

		// 画园
		drawCircle(x, y, r);

		// 在圆上进行描点
		double[][] points = drawPoints(x, y, r, n);
		randomLinkPoints(points, p);
	}

	// 提高题1.1.32
	public static void dataHistogram(int N, double l, double r, double[] arr) {
		int length = arr.length;
		int[] statistics = new int[N];
		double interval = (r - l) / N;
		// 统计数据分布
		for (int i = 0; i < length; ++i) {
			double remain = arr[i] - l;
			int idx = (int) (remain / interval);
			++statistics[idx];
		}
		// 查找统计结果中最大值，用于绘制直方图时计算柱状图高时
		double max = statistics[0];
		for (int i = 1; i < statistics.length; ++i) {
			if (max < statistics[i])
				max = statistics[i];
		}
		// 绘制直方图
		StdDraw.setXscale(l, r);
		StdDraw.setPenColor(StdDraw.BOOK_BLUE);
		double x0 = l + interval / 2.0;
		for (int i = 0; i < statistics.length; ++i) {
			double x = x0 + i * interval;
			double y = statistics[i] / (max + 1) / 2.0;
			double hw = 0.99 * interval / 2.0;
			double hh = y;
			StdDraw.filledRectangle(x, y, hw, hh);
		}
	}
	
	// 提高题1.1.33
	// 向量点乘
	public static double dot(double[] x, double[] y) {

		// 点乘必须是向量x的长度等于向量y的长度才能运算
		if (x.length != y.length) {
			System.exit(1);
		}

		double result = 0d;

		for (int i = 0; i < x.length; i++) {
			result += x[i] * y[i];
		}

		return result;
	}

	// 矩阵和矩阵之积
	public static double[][] multiple(double[][] a, double[][] b) {

		// 只有矩阵a的列数等于矩阵b的行数时，相乘才有意义
		if (a[0].length != b.length) {
			System.exit(1);
		}

		double[][] result = new double[a.length][b[0].length];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < a[0].length; k++) {
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}

		return result;
	}

	// 转置矩阵
	public static double[][] transpose(double[][] a) {

		int m = a.length;
		int n = a[0].length;

		double[][] result = new double[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				result[i][j] = a[j][i];
			}
		}

		return result;
	}

	// 矩阵和向量之积
	public static double[] mult(double[][] a, double[] x) {
		// 只有矩阵的列数和向量的长度相等时才有意义
		if (a[0].length != x.length) {
			System.exit(1);
		}

		double[] result = new double[a.length];

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < x.length; j++) {
				result[i] += a[i][j] * x[j];
			}
		}

		return result;
	}

	// 向量与矩阵之积
	public static double[] mult(double[] y, double[][] a) {
		// 只有向量的列数和矩阵的行数相等时才有意义
		if (y.length != a.length) {
			System.exit(1);
		}

		double[] result = new double[a[0].length];

		for (int i = 0; i < a[0].length; i++) {
			for (int j = 0; j < y.length; j++) {
				result[i] += y[j] * a[j][i];
			}
		}

		return result;
	}
	
	// 用于打印向量和矩阵的方法
	private static void printVector(double[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	
	private static void printMatrix(double[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void test_matrix() {
		
		// 2 x 3 矩阵
		double[][] a = { { 1, 2, 3 }, { 4, 5, 6 } };
		// 3 x 2矩阵
		double[][] b = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		// 向量x
		double[] x = { 2, 3, 4 };
		// 向量y
		double[] y = { 3, 4, 5 };
		
		//------------测试-------------		
		System.out.println("测试向量点乘开始");
		System.out.println(dot(x, y));
		System.out.println("测试向量点乘结束");
		
		System.out.println();
		
		System.out.println("测试矩阵乘矩阵开始");
		double[][] matMulMat = multiple(a, b);
		printMatrix(matMulMat);
		System.out.println("测试矩阵乘矩阵结束");
		
		System.out.println();
		
		System.out.println("测试矩阵转置开始");
		printMatrix(transpose(a));
		System.out.println("测试矩阵转置结束");
		
		System.out.println();
		
		System.out.println("测试矩阵乘向量开始");
		double[] matMulVec = mult(a, x);
		printVector(matMulVec);
		System.out.println("测试矩阵乘向量结束");
		
		System.out.println();
		
		System.out.println("测试向量乘矩阵开始");
		double[] vecMulMat = mult(y, b);
		printVector(vecMulMat);
		System.out.println("测试向量乘矩阵结束");
	}
	

	// 测试用JUnit
	@Test
	public void junitTest() {
		// double[][] result = createBinomial2DArray(100, 50, 0.25);
		// StdOut.println(result[100][50]);

		// Integer[] arr = ex1128();
		// for (int i = 0; i < arr.length; i++) {
		// StdOut.print(arr[i] + " ");
		// }

		// int[] arr = { 0, 0, 3, 5, 10, 14, 22, 31, 46, 46, 46, 52, 67, 69, 70, 91, 91
		// };
		// int key = 68;
		// int i = rank(key, arr);
		// int j = count(key, arr);
		// for (int k = i; k <= i + j - 1; k++) {
		// StdOut.println((arr[k] == key) + " ");
		// }

		// boolean[][] result = ex1130(6);
		// for (int i = 0; i < 6; i++) {
		// for (int j = 0; j < 6; j ++) {
		// if(result[i][j]) {
		// StdOut.print("√");
		// } else {
		// StdOut.print("x");
		// }
		// }
		// StdOut.println();
		// }
	}

	// 测试用主函数
	public static void main(String[] args) {
		// randomLink();

		// In in = new In(new File("datafiles\\largeW.txt"));
		// double[] whiteList = in.readAllDoubles();
		// double min = Double.POSITIVE_INFINITY;
		// double max = Double.NEGATIVE_INFINITY;
		// for (int i = 0; i < whiteList.length; ++i) {
		// if (min > whiteList[i])
		// min = whiteList[i];
		// if (max < whiteList[i])
		// max = whiteList[i];
		// }
		// // 从控制台读取应该将数据分割的段数
		// StdOut.print("将数据分割的段数：");
		// int N = StdIn.readInt();
		// dataHistogram(N, min, max + 10.0, whiteList);
	}
}
