package pers.tavish.ex.chapter1.programmingmodel.experiments;

import org.junit.Test;

import edu.princeton.cs.algs4.StdRandom;

// 实验题1.1.35
public class TossDice {

	// 定义骰子的面数
	private final static int SIDES = 6;

	// 定义用来存储理论概率的数组
	private static double[] dist = new double[13];

	// 初始化dist数组
	static {
		for (int i = 1; i <= SIDES; i++) {
			for (int j = 1; j < SIDES; j++) {
				dist[i + j] += 1.0;
			}
		}
		for (int k = 2; k <= 12; k++) {
			dist[k] /= 36.0;
		}
	}

	// 比较偏差的方法
	public boolean testErr(double[] arr) {
		// 循环比较两个数组的对应元素值
		for (int i = 2; i <= 12; i++) {
			// 如果有一项偏差超过0.0001，则返回false
			if (Math.abs(dist[i] - arr[i]) >= 0.001) {
				return false;
			}
		}
		return true;
	}

	// 模拟掷骰子的过程
	public double[] tossSimulate(long n) {
		// 用于存储各个和出现次数的数组
		double[] exp = new double[13];

		// 统计次数
		for (int i = 1; i <= n; i++) {
			exp[StdRandom.uniform(1, 7) + StdRandom.uniform(1, 7)] += 1.0;
		}

		// 计算频率
		for (int j = 2; j <= 12; j++) {
			exp[j] /= n;
		}

		return exp;
	}

	// 实验方法，n为初始实验次数
	public long experment(long n) {

		while (true) {
			
			System.out.println("本次实验次数为：" + n);
			
			// 经过n次实验后，如果达到要求，跳出循环
			if (testErr(tossSimulate(n))) {
				break;
			} else {
				// 否则实验次数乘10倍
				n *= 5;
			}
		}
		return n;
	}
	
	@Test
	public void testExp() {
		System.out.print("实验次数为：" + experment(100));
	}
}
