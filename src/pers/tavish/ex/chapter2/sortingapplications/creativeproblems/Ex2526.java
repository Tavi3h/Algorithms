package pers.tavish.ex.chapter2.sortingapplications.creativeproblems;

import java.util.Arrays;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

// 提高题2.5.26
public class Ex2526 {
	public static void drawPolygon(Point2D[] arr) {

		int N = arr.length;
		if (N < 3) {
			throw new IllegalArgumentException();
		}

		// 保护性拷贝
		Point2D[] backup = new Point2D[N];
		System.arraycopy(arr, 0, backup, 0, N);

		// 寻找y坐标最小的点
		Point2D ymin = arr[0];
		for (int i = 1; i < N; i++) {
			if (arr[i].y() < ymin.y()) {
				ymin = arr[i];
				continue;
			}
			if (arr[i].y() == ymin.y() && arr[i].x() < ymin.x()) {
				ymin = arr[i];
			}
		}

		// 此时点ymin为y坐标最小的点（y相同时，x最小）
		// 在此基础上，以ymin为基准按照角度进行排序
		Arrays.sort(arr, ymin.polarOrder());

		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.setPenRadius();
		// 这里实际上需要查找这些点的最大、最小横坐标以及最大、最小纵坐标来确定X、Y的大小
		StdDraw.setXscale(0, 5);
		StdDraw.setYscale(0, 5);
		// 逆时针连接这些点
		for (int i = 0; i < N; i++) {
			StdDraw.line(arr[i].x(), arr[i].y(), arr[(i + 1) % N].x(), arr[(i + 1) % N].y());
		}

		// 恢复arr数组
		arr = backup;
	}

	public static void main(String[] args) {
		Point2D[] arr = new Point2D[] { new Point2D(1, 2), new Point2D(1, 1), new Point2D(2, 1), new Point2D(2, 3),
				new Point2D(3, 3), new Point2D(3, 2) };
		drawPolygon(arr);
	}
}
