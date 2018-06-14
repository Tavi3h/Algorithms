package pers.tavish.ex.chapter3.searchingapplications.creativeproblems;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// 提高题3.5.20 
// ST的底层实现为TreeMap，SET的底层实现为TreeSet
public class Concordance {

	public static void main(String[] args) {

		int CONTEXT = 5;

		In in = new In(args[0]);
		String[] words = in.readAllStrings();
		ST<String, SET<Integer>> st = new ST<String, SET<Integer>>();

		// build up concordance
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			if (!st.contains(s)) {
				st.put(s, new SET<Integer>());
			}
			SET<Integer> set = st.get(s);
			set.add(i);
		}
		StdOut.println("Finished building concordance");

		// process queries
		while (!StdIn.isEmpty()) {
			
			String query = StdIn.readString();
			
			SET<Integer> set = st.get(query);
			if (set == null) {
				set = new SET<Integer>();
			}
			for (int k : set) {
				for (int i = Math.max(0, k - CONTEXT + 1); i < k; i++) {
					StdOut.print(words[i] + " ");
				}
				StdOut.print("*" + words[k] + "* ");
				for (int i = k + 1; i < Math.min(k + CONTEXT, words.length); i++) {
					StdOut.print(words[i] + " ");
				}
				StdOut.println();
			}
			StdOut.println();
		}
	}
}
