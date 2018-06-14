package pers.tavish.ex.chapter2.elementarysorts.exercises;

import java.util.ArrayList;
import java.util.List;

// 练习题 2.1.11
public class ShellEx2111 {

	// 构建希尔数组
	private static Integer[] createShells(int N) {
		List<Integer> shells = new ArrayList<>();
        int h = 1;
        shells.add(0); // 将0添加到数组的第一项，作为结束标志
        while (h < N / 3) {
            shells.add(h);
            h = 3 * h + 1;
        }
        shells.add(h);
        return shells.toArray(new Integer[0]);
	}

	public static <T> void sort(Comparable<T>[] a) {
		int N = a.length;

		Integer[] shells = createShells(N);

		// while循环改为for循环遍历shells数组，数组的第一项为0
		for (int k = shells.length - 1; k > 0; k--) {
			int h = shells[k];
			// 将数组变为h有序
			for (int i = h; i < N; i++) {
				// 将a[i]插入到a[i - h]、a[i - 2 * h]、a[i - 3 * h]...之中
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exch(a, j, j - h);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> boolean less(Comparable<T> v, Comparable<T> w) {
		return v.compareTo((T) w) < 0;
	}

	private static <T> void exch(Comparable<T>[] a, int i, int j) {
		Comparable<T> t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static <T> void show(Comparable<T>[] a) {
		// 在单行中打印数组
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static <T> boolean isSorted(Comparable<T>[] a) {
		// 测试数组元素是否有序
		for (int i = 0; i < a.length; i++) {
			if (less(a[i], a[i - 1]))
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String strings = "EASYSHELLSORTQUESTION";
		char[] chars = strings.toCharArray();
		Character[] characters = new Character[chars.length];
		for (int i = 0; i < characters.length; i++) {
			characters[i] = chars[i];
		}
		sort(characters);
		show(characters);
	}
}
