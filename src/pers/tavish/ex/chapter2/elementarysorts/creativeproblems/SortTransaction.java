package pers.tavish.ex.chapter2.elementarysorts.creativeproblems;

import edu.princeton.cs.algs4.Transaction;
import pers.tavish.code.chapter2.elementarysorts.Selection;

public class SortTransaction {
	public static void main(String[] args) {
        Transaction[] a = new Transaction[4];
        a[0] = new Transaction("Turing   6/17/1990  644.08");
        a[1] = new Transaction("Tarjan   3/26/2002 4121.85");
        a[2] = new Transaction("Knuth    6/14/1999  288.34");
        a[3] = new Transaction("Dijkstra 8/22/2007 2678.40");
        
        Selection.sort(a);
        
        for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}
