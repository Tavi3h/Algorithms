package pers.tavish.ex.chapter2.sortingapplications.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// 练习题2.5.8
public class Ex258 {

	public static List<Map.Entry<String, Integer>> frequency(String[] arr) {

		Map<String, Integer> map = new HashMap<>();

		// 将数组中得数据存入map
		for (String s : arr) {
			if (map.containsKey(s)) {
				map.put(s, map.get(s) + 1);
			} else {
				map.put(s, 1);
			}
		}

		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

		// 按值对Entry排序
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		return list;
	}

	public static void main(String[] args) {
		String[] arr = { "d", "d", "n", "a", "c", "d", "b", "a", "c", "c", "d", "b", "n" };
		List<Map.Entry<String, Integer>> list = frequency(arr);
		for (Map.Entry<String, Integer> m : list) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}
}
