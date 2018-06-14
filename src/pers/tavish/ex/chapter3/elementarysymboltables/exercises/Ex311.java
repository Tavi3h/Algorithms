package pers.tavish.ex.chapter3.elementarysymboltables.exercises;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

// 练习题 3.1.1
public class Ex311 {
	public static void main(String[] args) {
		ST<String, Double> st = new ST<>();
		st.put("A+", 4.33);
		st.put("A", 4.00);
		st.put("A-", 3.67);
		st.put("B+", 3.33);
		st.put("B", 3.00);
		st.put("B-", 2.67);
		st.put("C+", 2.33);
		st.put("C", 2.00);
		st.put("C-", 1.67);
		st.put("D", 1.00);
		st.put("F", 0.00);

		int n = 0;
		double total = 0;
		while (!StdIn.isEmpty()) {
			total += st.get(StdIn.readString());
			n++;
		}
		System.out.println("平均分为：" + total / n);
	}
}
