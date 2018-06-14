package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

// 提高题 1.3.37
public class Josephus {

	public static void main(String[] args) {

		int N = Integer.parseInt(args[0]);
		int M = Integer.parseInt(args[1]);

		Queue<Integer> queue = new Queue<Integer>();
		for (int i = 0; i < N; i++) {
			queue.enqueue(i);
		}

		while (!queue.isEmpty()) {
			
			// 将报数小于M的人依次出列并加入队尾
			for (int i = 1; i < M; i++) {
				queue.enqueue(queue.dequeue());
			}
			
			// 将报数为M的人出列，最后出列的位置就是Josephus的位置
			StdOut.print(queue.dequeue() + " ");
		}
	}
}
