package pers.tavish.ex.chapter1.dataabstraction.exercises;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

// 练习题1.2.10
public class VisualCounter {

	// 最大操作次数和计数器的最大绝对值
	private final int N;
	private final int max;

	// 记录当前操作次数的值
	private int opTimes;

	// 进行计数的值
	private int value;

	// 初始化计数器
	public VisualCounter(int N, int max) {

		// 最大操作次数不能小于0，计数器的最大绝对值不能小于等于0
		if (N < 0 || max <= 0) {
			throw new IllegalArgumentException();
		}

		this.N = N;
		this.max = max;

		opTimes = 0;
		value = 0;
	}

	public void increment() {
		if (isDepeleted()) {
			System.out.println("Counter Depeleted, Try a New One.");
			return;
		}

		opTimes++;

		if (isMax() && isPositive()) {
			System.out.println("Counter Has Reached Positive Limit, No More Increment.");
			return;
		} else {
			value++;
		}
	}

	public void decrement() {
		if (isDepeleted()) {
			System.out.println("Counter Depeleted, Try a New One.");
			return;
		}

		opTimes++;

		if (isMax() && !isPositive()) {
			System.out.println("Counter Has Reached Negative Limit, No More Decrement.");
			return;
		} else {
			value--;
		}
	}

	// 描点的方法
	public void draw() {
		StdDraw.point(opTimes, value);
	}

	// 返回计数值
	public int tally() {
		return value;
	}

	// 判断当前value是否是正数
	private boolean isPositive() {
		return value > 0;
	}

	// 判断当前value的绝对值是否等于max
	private boolean isMax() {
		return Math.abs(value) == max;
	}

	// 判断操作次数是否等于最大操作次数
	private boolean isDepeleted() {
		return N == opTimes;
	}

	// 测试用主函数
	public static void main(String[] args) {

		int max = 3;
		int count = 100;

		StdDraw.setXscale(0, count + 1);
		StdDraw.setYscale(-max, max);
		StdDraw.line(0, 0, count + 1, 0);

		VisualCounter vcCounter = new VisualCounter(count, max);

		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius(0.01);

		vcCounter.draw();
		for (int i = 0; i < count; i++) {
			if (StdRandom.bernoulli()) {
				vcCounter.increment();
			} else {
				vcCounter.decrement();
			}
			vcCounter.draw();
		}
	}
}
