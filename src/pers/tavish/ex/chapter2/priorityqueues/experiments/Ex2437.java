package pers.tavish.ex.chapter2.priorityqueues.experiments;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Ex2437 {
	
	public static void main(String[] args) {

		int T = StdIn.readInt();
		int N = StdIn.readInt();

		int totTimes = 0;

		for (int i = 0; i < T; i++) {

			int size = N + StdRandom.uniform(-N / 2, N / 2);
			MaxPQ<Double> pq = new MaxPQ<>(size);
			while (pq.size() != size) {
				pq.insert(StdRandom.uniform());
			}
			
			int times = 0;

			Stopwatch timer = new Stopwatch();

			while (timer.elapsedTime() < 1) {
				pq.delMax();
				pq.insert(StdRandom.uniform());
				times++;
			}
			
			totTimes += times;
		}
		
		System.out.println("For " + T + " experiments, N = " + N + ", Average times = " + totTimes / T);
	}
}
