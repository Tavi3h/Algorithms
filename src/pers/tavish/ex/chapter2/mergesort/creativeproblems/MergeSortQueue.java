package pers.tavish.ex.chapter2.mergesort.creativeproblems;

import edu.princeton.cs.algs4.Queue;

// 提高题2.2.14
public class MergeSortQueue {

	// a、b队列为升序有序队列，该方法归并两个队列
	public static <T extends Comparable<? super T>> Queue<T> mergeQueue(Queue<T> a, Queue<T> b) {
		// 创建一个新的Queue
		Queue<T> queue = new Queue<T>();

		int N = a.size() + b.size();

		while (queue.size() != N) {
			if (a.size() == 0 && b.size() != 0) { // 如果a取没了，则取b
				queue.enqueue(b.dequeue());
			} else if (a.size() != 0 && b.size() == 0) { // 如果b没了，则取a
				queue.enqueue(a.dequeue());
			} else if (a.peek().compareTo(b.peek()) < 0) { // 比较二者头元素的大小
				queue.enqueue(a.dequeue());
			} else {
				queue.enqueue(b.dequeue());
			}
		}
		return queue;
	}
	
	public static void main(String[] args) {

	}
}
