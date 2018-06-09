package pers.tavish.ex.chapter1.analysisofalgorithms.creativeproblems;

import edu.princeton.cs.algs4.Stack;

// 提高题 1.4.27
// 使用两个Stack实现单向队列，操作均摊为常数
public class TwoStackQueue<T> {
	
	// 用于接受入队的元素
	private Stack<T> stackEn;
	// 用于弹出出队的元素
	private Stack<T> stackDe;
	
	public TwoStackQueue() {
		stackEn = new Stack<>();
		stackDe = new Stack<>();
	}
	
	public void enqueue(T t) {
		stackEn.push(t);
	}
	
	public T dequeue() {
		// 如果stackDe内没有元素，则将stackEn中的元素弹出并压入stackDe中
		if (stackDe.isEmpty()) {
			while (!stackEn.isEmpty()) {
				stackDe.push(stackEn.pop());
			}
		}
		return stackDe.pop();
	}
}
