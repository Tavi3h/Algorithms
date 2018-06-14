package pers.tavish.code.chapter3.searchingapplications;

import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 白名单
public class WhitList {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		In in = new In(args[0]);
		while (!in.isEmpty()) {
			set.add(in.readString());
		}
		while (!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if (set.contains(word)) {
				StdOut.print(word + " ");
			}
		}
	}
}
