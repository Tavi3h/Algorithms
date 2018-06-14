package pers.tavish.ex.chapter1.analysisofalgorithms.creativeproblems;

import edu.princeton.cs.algs4.Stack;

// 提高题 1.4.30
// 使用一个Stack和Steque实现双向队列，操作均摊为常数
public class StackStequeQueue<Item> {
	
	private Stack<Item> stack;
	private TwoStackSteque<Item> steque;
	
	public StackStequeQueue() {
		stack = new Stack<>();
		steque = new TwoStackSteque<>();
	}
	
	// 队列头入列
	public void enqueueHead(Item item) {
		stackToSteque();
		steque.push(item);
	}
	
	// 队列尾入列
	public void enqueueTail(Item item) {
		stackToSteque();
		steque.enqueue(item);
	}
	
	// 队列头出列
	public Item dequeueHead() {
		stackToSteque();
		return steque.pop();
	}
	
	// 队列尾出列
	public Item dequeueTail() {
		stequeToStack();
		return stack.pop();
	}
	
	// 将stack中的元素弹出并压入steque
	private void stackToSteque() {
		while (!stack.isEmpty()) {
			steque.push(stack.pop());
		}
	}
	
	// 将steque中的元素弹出并压入stack
	private void stequeToStack() {
		while (!steque.isEmpty()) {
			stack.push(steque.pop());
		}
	}

	public boolean isEmpty() {
		return stack.isEmpty() && steque.isEmpty();
	}
	
	public static void main(String[] args) {
		
	}
}
