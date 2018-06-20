package pers.tavish.code.chapter3.searchingapplications;

import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 过滤重复项
public class Dedup {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		while (!StdIn.isEmpty()) {
			String key = StdIn.readString();
			if (!set.contains(key)) {
				set.add(key);
				StdOut.print(key + " ");
			}
		}
	}
}
