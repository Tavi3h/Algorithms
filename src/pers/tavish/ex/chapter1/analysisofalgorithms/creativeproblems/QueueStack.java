package pers.tavish.ex.chapter1.analysisofalgorithms.creativeproblems;

import edu.princeton.cs.algs4.Queue;

// 提高题 1.4.28
// 使用一个Queue实现Stack，操作均摊为常数
public class QueueStack<Item> {
	
	private Queue<Item> queue;
	
	public QueueStack() {
		queue = new Queue<>();
	}
	
	// 每次入队后将队列反转
	public void push(Item item) {
		queue.enqueue(item);
		for (int i = 0; i < queue.size() - 1; i++) {
			queue.enqueue(queue.dequeue());
		}
	}
	
	public Item pop() {
		return queue.dequeue();
	}
	
	public static void main(String[] args) {
		
	}
}


