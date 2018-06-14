package pers.tavish.ex.chapter2.sortingapplications.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 练习题 2.5.4
public class Ex254 {
	public static String[] dedup(String[] a) {

		// 创建一个数组a的拷贝
		String[] arr = new String[a.length];
		System.arraycopy(a, 0, arr, 0, a.length);

		// 先对数组进行排序
		Arrays.sort(arr);

		List<String> list = new ArrayList<>();
		list.add(arr[0]);
		String tmp = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i].equals(tmp)) {
				continue;
			}
			list.add(arr[i]);
			tmp = arr[i];
		}

		return list.toArray(new String[0]);
	}

	public static void main(String[] args) {
		String[] a = new String[] { "2", "3", "1", "5", "1", "3", "a", "t", "3", "a", "3", "x", "0", "x" };
		String[] arr = dedup(a);
		for (String string : arr) {
			System.out.print(string + " ");
		}
	}
}
