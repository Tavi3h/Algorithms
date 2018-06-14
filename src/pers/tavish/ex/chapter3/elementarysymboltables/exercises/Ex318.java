package pers.tavish.ex.chapter3.elementarysymboltables.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.princeton.cs.algs4.StdIn;

// 练习题 3.1.8
public class Ex318 {
	
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		while (!StdIn.isEmpty()) {
			String word = StdIn.readString();
			if (!map.containsKey(word)) {
				map.put(word, 1);
			} else {
				map.put(word, map.get(word) + 1);
			}
		}
		
		List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue() - o1.getValue();
			}
		});
		
		String word = "";
		for (Entry<String, Integer> entry : list) {
			word = entry.getKey();
			if (word.length() >= 10) {
				System.out.println(word + " " + entry.getValue());
				break;
			}
		}
	}
}	
