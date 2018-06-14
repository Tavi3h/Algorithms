package pers.tavish.ex.chapter2.mergesort.creativeproblems;

import edu.princeton.cs.algs4.Queue;

// 提高题 2.2.15
public class MergeBUQueue {
	
	public static <T extends Comparable<? super T>> void mergeQueue(Queue<Queue<T>> queue) {
		
		while (queue.size() != 1) {
			
			Queue<T> a = queue.dequeue();
			Queue<T> b = queue.dequeue();
			
			Queue<T> result = MergeSortQueue.mergeQueue(a, b);
			queue.enqueue(result);
		}
	}
	
	public static void main(String[] args) {
		
	}
}
