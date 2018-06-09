package pers.tavish.ex.chapter2.priorityqueues.experiments;


import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Ex2436 {
	
	public static void main(String[] args) {
		
		int T = StdIn.readInt(); // 重复实验次数
		int N = StdIn.readInt();

		double time = 0d;

		for (int i = 0; i < T; i++) {
			
			int size = N + StdRandom.uniform(-N / 2, N / 2); // 队列的大小随机
			MaxPQ<Double> pq = new MaxPQ<>(size); // 创建队列
			
			Stopwatch timer = new Stopwatch();

			// 填满队列
			while(pq.size() != size) {
				pq.insert(StdRandom.uniform());
			}
			
			// 删除一半
			while (pq.size() != size / 2) {
				pq.delMax();
			}
			
			// 再次填满
			while (pq.size() != size) {
				pq.insert(StdRandom.uniform());
			}
			
			// 全部删除
			while (pq.size() != 0) {
				pq.delMax();
			}

			time += timer.elapsedTime();
		}

		System.out.println("For " + T + " experiments, N = " + N + ", Average time = " + time / T);
	}
}
