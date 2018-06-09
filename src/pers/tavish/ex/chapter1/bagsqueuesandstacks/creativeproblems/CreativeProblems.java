package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

import org.junit.Test;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

public class CreativeProblems {

	// 提高题 1.3.45
	public void ifUnderFlow(String sequence ) {
		Stack<Integer> stack = new Stack<>();
		String tmp = "";

		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("-")) {
				tmp += stack.pop();
			} else {
				stack.push(Integer.parseInt(s));
			}
		}
		System.out.println(tmp.equals(sequence));
	}

	// JUnit测试用
	@Test
	public void junitTest() {
		ifUnderFlow("012345");
	}

}
