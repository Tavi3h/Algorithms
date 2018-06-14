package pers.tavish.code.chapter3.searchingapplications;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pers.tavish.code.chapter3.balancedsearchtrees.RedBlackBST;

public class LookupCSV {
	public static void main(String[] args) {
		In in = new In(args[0]);
		int keyField = Integer.parseInt(args[1]);
		int valField = Integer.parseInt(args[2]);
		RedBlackBST<String, String> rbbst = new RedBlackBST<>();

		while (in.hasNextLine()) {
			String line = in.readLine();
			String[] tokens = line.split(",");
			String key = tokens[keyField];
			String val = tokens[valField];
			rbbst.put(key, val);
		}

		while (!StdIn.isEmpty()) {
			String query = StdIn.readString();
			if (rbbst.contains(query)) {
				StdOut.println(rbbst.get(query));
			}
		}

	}
}
