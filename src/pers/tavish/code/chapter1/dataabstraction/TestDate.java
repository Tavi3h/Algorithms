package pers.tavish.code.chapter1.dataabstraction;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;

// args = { 12 31 1999 }
public class TestDate {
	public static void main(String[] args) {
		int m = Integer.parseInt(args[0]);
		int d = Integer.parseInt(args[1]);
		int y = Integer.parseInt(args[2]);
		
		Date date = new Date(m, d, y);
		
		StdOut.println(date);
	}
}
