package pers.tavish.ex.chapter3.elementarysymboltables.exercises;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdRandom;

// 练习题3.1.7
public class Ex317 {
	public static void main(String[] args) {

		ST<Integer, Integer> st = null;
		for (int N = 10; N <= 1000000; N *= 10) {
			int total = 0;
			for (int T = 0; T < 10; T++) { // 10次平均
				st = new ST<>();
				for (int i = 0; i < N; i++) {
					st.put(StdRandom.uniform(0, 1000), 0);
				}
				total += st.size();
			}
			System.out.println("N = " + N + ", 平均有不同键：" + (double) total / 10);
		}
	}
}
