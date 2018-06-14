package pers.tavish.code.chapter1.dataabstraction;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StaticSETofInts;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WhiteList {
	public static void main(String[] args) {
		int[] w = new In(new File("datafiles\\tinyW.txt")).readAllInts();
		StaticSETofInts set = new StaticSETofInts(w);
		while (!StdIn.isEmpty()) {
			int key = StdIn.readInt();
			if (!set.contains(key)) {
				StdOut.println(key);
			}
		}
	}
}
