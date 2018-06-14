package pers.tavish.code.chapter3.elementarysymboltables;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// args：8
// input files：tale.txt
public class FrequencyCounter {
	
	public static void main(String[] args) {
		
		int minlen = Integer.parseInt(args[0]);
		ST<String, Integer> st = new ST<>();
		while (!StdIn.isEmpty()) {
			String word = StdIn.readString();
			// 忽略较短单词
			if (word.length() < minlen) {
				continue;
			}
			if (!st.contains(word)) {
				st.put(word, 1);
			} else {
				st.put(word, st.get(word) + 1);
			}
		}

		// 找出出现频率最高的单词
		String max = " ";
		st.put(max, 0);

		for (String word : st.keys()) {
			if (st.get(word) > st.get(max)) {
				max = word;
			}
		}
		StdOut.println(max + " " + st.get(max));
	}
}
