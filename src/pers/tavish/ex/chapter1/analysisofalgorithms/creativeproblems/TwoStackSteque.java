package pers.tavish.ex.chapter1.analysisofalgorithms.creativeproblems;

import edu.princeton.cs.algs4.Stack;

// 提高题 1.4.29
// 使用两个Stack实现Steque，操作均摊为常数
public class TwoStackSteque<Item> {
	
	private Stack<Item> stackL;
	private Stack<Item> stackR;
	
	public TwoStackSteque() {
		stackL = new Stack<>();
		stackR = new Stack<>();
	}
	
	// 压入item前，将stackR中的数据弹出并压入stackL
	public void push(Item item) {
		stackRToL();
		stackL.push(item);
	}
	
	// 弹出item前，将stackR中的数据弹出并压入stackL
	public Item pop() {
		stackRToL();
		return stackL.pop();
	}
	
	// 入队item前，将stackL中的数据弹出并压入stackR
	public void enqueue(Item item) {
		stackLToR();
		stackR.push(item);
	}

	// 将stackR中的数据弹出并压入stackL
	private void stackRToL() {
		while (!stackR.isEmpty()) {
			stackL.push(stackR.pop());
		}
	}
	
	// 将stackL中的数据弹出并压入stackR
	private void stackLToR() {
		while (!stackL.isEmpty()) {
			stackR.push(stackL.pop());
		}
	}
	
	public boolean isEmpty() {
		return stackL.isEmpty() && stackR.isEmpty();
	}
	
	public static void main(String[] args) {
		
	}
}
