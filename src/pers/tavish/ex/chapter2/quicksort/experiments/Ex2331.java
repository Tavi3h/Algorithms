package pers.tavish.ex.chapter2.quicksort.experiments;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import pers.tavish.code.chapter2.quicksort.QuickT;

public class Ex2331 {
	
	private static <T> void show(T[] a) {
		StdDraw.clear();
		int n = a.length;
		for (int i = 0; i < n; i++) {
			double x = 1.0 * i / n;
			double y = (double) a[i] / 2.0;
			double rw = 0.5 / n;
			double rh = (double) a[i] / 2.0;
			// x 代表线在那个位置 y代表这个线的高度
			// rw rh 理解成平面坐标参数
			StdDraw.filledRectangle(x, y, rw, rh);
		}
	}
	
	public static void main(String[] args) {
		Double[] times = new Double[100];
		for (int i = 0; i < times.length; i++) {
			Double[] arr = new Double[100000];
			for (int j = 0; j < arr.length; j++) {
				arr[j] = StdRandom.uniform();
			}
			
			Stopwatch timer = new Stopwatch();
			QuickT.sort(arr);
			times[i] = timer.elapsedTime();
		}
		show(times);
	}
}
