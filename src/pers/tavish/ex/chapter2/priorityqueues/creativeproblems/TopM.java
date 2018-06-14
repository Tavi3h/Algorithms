package pers.tavish.ex.chapter2.priorityqueues.creativeproblems;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

// 提高题2.4.28
public class TopM {
	public static void main(String[] args) {
		
		int M = StdIn.readInt();
		int N = StdIn.readInt();
		MaxPQ<Point3D> pq = new MaxPQ<>(M + 1);
		
		Stopwatch timer = new Stopwatch();
		for (int i = 0; i < N; i++) {
			pq.insert(new Point3D(StdRandom.uniform(), StdRandom.uniform(), StdRandom.uniform()));
			if (pq.size() > M) {
				pq.delMax();
			}
		}
		
		double time = timer.elapsedTime();

		for (Point3D point3d : pq) {
			System.out.println(point3d);
		}
		System.out.println("Elapsed Time : " + time);
	}
}

// 定义点(x, y, z)
class Point3D implements Comparable<Point3D> {
	private double x;
	private double y;
	private double z;

	public Point3D(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double dist() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	@Override
	public int compareTo(Point3D o) {
		return Double.compare(this.dist(), o.dist());
	}

	@Override
	public String toString() {
		return "Point3D [" + dist() + "] [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}
