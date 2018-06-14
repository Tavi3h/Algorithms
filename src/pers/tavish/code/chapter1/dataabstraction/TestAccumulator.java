package pers.tavish.code.chapter1.dataabstraction;

import edu.princeton.cs.algs4.Accumulator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


// args = { 100000 }
public class TestAccumulator {
	public static void main(String[] args) {
		int T = Integer.parseInt(args[0]);
		Accumulator a = new Accumulator();
		for (int t = 0; t < T; t++) {
			a.addDataValue(StdRandom.uniform());
		}
		StdOut.println(a.mean());
	}
}
