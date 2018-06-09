package pers.tavish.ex.chapter2.sortingapplications.creativeproblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// 提高题2.5.27
public class Ex2527 {
	
	public static <T extends Comparable<? super T>> int[] indirectSort(T[] arr) {
		
		int[] a = new int[arr.length];
		
		Map<Integer, T> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(i, arr[i]);
		}
		
		// 对map进行按值排序
		List<Map.Entry<Integer, T>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, T>>() {
			@Override
			public int compare(Entry<Integer, T> o1, Entry<Integer, T> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		
		int n = 0;
		for (Entry<Integer, T> entry : list) {
			a[n++] = entry.getKey();
		}
		return a;
	}
	
	public static void main(String[] args) {
		String[] arr = "C D A E F B".split(" ");
		int[] a = indirectSort(arr);
		for (int i : a) {
			System.out.print(arr[i] + " ");
		}
	}
}
