package pers.tavish.ex.chapter1.dataabstraction.creativeproblems;

import edu.princeton.cs.algs4.In;

public class CreativeProblems {
	
	// 提高题1.2.15
	public static int[] readInts(String name) {
		In in = new In(name);
		String[] words = in.readAll().split("\\s+");
		int[] ints = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			ints[i] = Integer.parseInt(words[i]);
		}
		return ints;
	}
	
	
}
