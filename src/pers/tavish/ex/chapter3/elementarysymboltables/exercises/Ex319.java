package pers.tavish.ex.chapter3.elementarysymboltables.exercises;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

// 练习题3.1.9
public class Ex319 {
	public static void main(String[] args) {
		int threshold = Integer.parseInt(args[2]);

		ST<String, Integer> st = new ST<>();

		String lastWord = "";
		int putCount = 0;

		while (!StdIn.isEmpty()) {
			String word = StdIn.readString();
			// 忽略较短单词
			if (word.length() < threshold) {
				continue;
			}
			if (!st.contains(word)) {
				st.put(word, 1);
			} else {
				st.put(word, st.get(word) + 1);
			}
			lastWord = word;
			putCount++;
		}
		System.out.println("======" + "threshold = " + threshold + "======");
		System.out.println("最后处理的单词：" + lastWord);
		System.out.println("共处理单词数：" + putCount);
	}
}
