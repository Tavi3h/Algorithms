package pers.tavish.code.chapter1.bagsqueuesandstacks;


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 用于存储字符串的定容栈的简单实现
public class FixedCapacityStackOfStrings {
	private String[] a; // stack entries
	private int N; // size

	public FixedCapacityStackOfStrings(int cap) {
		this.a = new String[cap];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void push(String item) {
		a[N++] = item;
	}

	public String pop() {
		return a[--N];
	}

	public static void main(String[] args) {
		FixedCapacityStackOfStrings s = new FixedCapacityStackOfStrings(100);
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-")) {
				s.push(item);
			} else if (!s.isEmpty()) {
				StdOut.println(s.pop() + " ");
			}
		}
		StdOut.println("(" + s.size() + " left on stack)");
	}
}
