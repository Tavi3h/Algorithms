package pers.tavish.ex.chapter3.elementarysymboltables.creativeproblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

// 提高题3.1.26
// args: datafiles/words3.txt
public class FrequencyCounterEx3126 {
	
	public static void main(String[] args) {

		// 读取字典：words3.txt
		String[] dictionary = new In(args[0]).readAllStrings();
		ST<String, Integer> st = new ST<>();
		// 存入符号表
		for (String string : dictionary) {
			st.put(string, 0);
		}

		// 默认按照字典顺序排序
		Map<String, Integer> map = new TreeMap<>(new Comparator<String>() {

			private int getIdx(String s) {
				for (int i = 0; i < dictionary.length; i++) {
					if (s.equals(dictionary[i])) {
						return i;
					}
				}
				return -1;
			}
			
			// 按字典顺序排序
			@Override
			public int compare(String o1, String o2) {
				return getIdx(o1) - getIdx(o2);
			}
		});
		
		// 读取标准输入，记录频率
		while (!StdIn.isEmpty()) {
			String word = StdIn.readString();
			// 符号表中存在该key
			if (st.contains(word)) {
				// map中存在该key
				if (map.containsKey(word)) { 
					map.put(word, map.get(word) + 1);
				} else {
					map.put(word, 1);
				}
			}
			// 不存在于字典的单词不进行统计
		}

		// 输出按照字典顺序排序
		System.out.println(map);
		
		// 按频率排序
		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		
		// 输出按照频率顺序排序
		System.out.println(list);
	}
}
