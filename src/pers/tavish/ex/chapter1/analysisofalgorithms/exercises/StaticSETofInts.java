package pers.tavish.ex.chapter1.analysisofalgorithms.exercises;

import java.util.Arrays;

// 练习题1.4.11
public class StaticSETofInts {

	private int[] a;

	public StaticSETofInts(int[] keys) {

		a = new int[keys.length];
		for (int i = 0; i < keys.length; i++) {
			a[i] = keys[i];
		}

		Arrays.sort(a);
	}

	public boolean contains(int key) {
		return rank(key) != -1;
	}

	public int rank(int key) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < a[mid]) {
				hi = mid - 1;
			} else if (key > a[mid]) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public int howMany(int key) {

		// 如果数组中不包含key，直接返回0
		int idx = rank(key);
		if (idx == -1) {
			return 0;
		}

		int cnt = 1;
		int tmp = idx;
		// 向idx的左侧查找
		while (tmp != 0) {
			if (a[tmp - 1] < key) {
				break;
			} else {
				tmp--;
				cnt++;
			}
		}
		tmp = idx;
		// 向idx右侧查找
		while (tmp != a.length - 1) {
			if (a[tmp + 1] > key) {
				break;
			} else {
				tmp++;
				cnt++;
			}
		}
		return cnt;
	}
}
