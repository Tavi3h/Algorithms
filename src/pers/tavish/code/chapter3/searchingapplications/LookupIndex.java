package pers.tavish.code.chapter3.searchingapplications;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pers.tavish.code.chapter3.balancedsearchtrees.RedBlackBST;

// 反向索引
public class LookupIndex {
	public static void main(String[] args) {
		In in = new In(args[0]); // 索引数据库
		String sp = args[1]; // 获取分隔符
		
		RedBlackBST<String, Queue<String>> st = new RedBlackBST<>();
		RedBlackBST<String, Queue<String>> ts = new RedBlackBST<>();
		
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(sp);
			String key = a[0];
			for (int i = 1; i < a.length; i++) {
				String val = a[i];
				if (!st.contains(key)) {
					st.put(key, new Queue<>());
				}
				if (!ts.contains(val)) {
					ts.put(val, new Queue<>());
				}
				
				st.get(key).enqueue(val);
				ts.get(val).enqueue(key);
			}
		}
		
		while (!StdIn.isEmpty()) {
			String query = StdIn.readLine();
			if (st.contains(query)) {
				for (String s : st.get(query)) {
					StdOut.println(" " + s);
				}
			}
			
			if (ts.contains(query)) {
				for (String s : ts.get(query)) {
					StdOut.println(" " + s);
				}
			}
		}
	}
}
