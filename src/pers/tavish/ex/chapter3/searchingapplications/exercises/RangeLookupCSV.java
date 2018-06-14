package pers.tavish.ex.chapter3.searchingapplications.exercises;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import pers.tavish.code.chapter3.balancedsearchtrees.RedBlackBST;

// 练习题3.5.13
public class RangeLookupCSV {
	
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
			String query_1 = StdIn.readString(); // 接受参数1
			String query_2 = StdIn.readString(); // 接受参数2
			
			// 如果参数1大于参数2
			if(query_1.compareTo(query_2) > 0) {
				throw new IllegalArgumentException();
			}
			
			for (String string : rbbst.keys(query_1, query_2)) {
				System.out.println(rbbst.get(string));
			}
			System.out.println();
		}
	}
}
