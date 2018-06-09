package pers.tavish.ex.chapter1.analysisofalgorithms.creativeproblems;

import edu.princeton.cs.algs4.Stack;

// 提高题 1.4.31
// 使用三个Stack实现双向队列，操作均摊为常数
public class ThreeStackQueue<Item> {

	private Stack<Item> left; // 操作队列的head
	private Stack<Item> mid; 
	private Stack<Item> right; // 操作队列的tail
	private String midMark; // mid数据来源标记

	public ThreeStackQueue() {
		left = new Stack<>();
		mid = new Stack<>();
		right = new Stack<>();
		midMark = "";
	}

	public void enqueueHead(Item item) {
		// 如果mid中有数据，就将mid中的数据倒回其数据源
		if (!mid.isEmpty()) {
			midCtrl();
		}
		left.push(item);
	}

	public void enqueueTail(Item item) {
		// 如果mid中有数据，就将mid中的数据倒回其数据源
		if (!mid.isEmpty()) {
			midCtrl();
		}
		right.push(item);
	}

	public Item dequeueHead() {
		
		// 如果left中有数据，就直接弹出
		if (!left.isEmpty()) {
			return left.pop();
		}
		// 如果left中没有数据，right中有数据，将right倒入mid，并弹出
		if (!right.isEmpty()) {
			rightToMid();
		}
		return mid.pop();
	}

	public Item dequeueTail() {
		
		// 如果right中有数据，就直接弹出
		if (!right.isEmpty()) {
			return right.pop();
		}
		// 如果right中没有数据，left中有数据，将left倒入mid，并弹出
		if (!left.isEmpty()) {
			leftToMid();
		}
		return mid.pop();
	}
	
	// 控制mid数据倒出的方向，方向由midMark决定
	private void midCtrl() {
		if (midMark.equals("Left")) {
			midToLeft();
		} else if (midMark.equals("Right")) {
			midToRight();
		}
	}
	
	// 将mid中数据倒入left
	private void midToLeft() {
		while (!mid.isEmpty()) {
			left.push(mid.pop());
		}
	}

	// 将left中的数据倒入mid
	private void leftToMid() {
		while (!left.isEmpty()) {
			mid.push(left.pop());
		}
		// 记录mid中的数据来自left
		midMark = "Left";
	}

	// 将mid中数据倒入right
	private void midToRight() {
		while (!mid.isEmpty()) {
			right.push(mid.pop());
		}
	}

	// 将right中的数据倒入mid
	private void rightToMid() {
		while (!right.isEmpty()) {
			mid.push(right.pop());
		}
		// 记录mid中的数据来自right
		midMark = "Right";
	}
	
	public static void main(String[] args) {
		
 	}
}
