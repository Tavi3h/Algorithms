package pers.tavish.ex.chapter2.elementarysorts.exercises;

// 练习题 2.1.9
public class Ex219 {
	public static <T> void sort(Comparable<T>[] a) {
		int N = a.length;
		int h = 1;
		while (h < N / 3) {
			h = 3 * h + 1;
		}
		while (h >= 1) {
			int i = 0; 
			for (i = h; i < N; i++) {
				int j = 0; // 声明j，用于记录离开for循环时的j值
				for (j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					exch(a, j, j - h);
				}
				System.out.print(h + "\t" + i + "\t" + j + "\t");
				show(a);
			}
			h = h / 3;
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
			if (i < 10) {
				System.out.print(a[i] + " ");
			} else {
				System.out.print(" " + a[i] + " ");
			}
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

		System.out.print("h\ti\tj\t");
		for (int i = 0; i < characters.length; i++) {
			characters[i] = chars[i];
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.print("\t\t\t");
		for (int i = 0; i < characters.length; i++) {
			if (i < 10) {
				System.out.print(characters[i] + " ");
			} else {
				System.out.print(" " + characters[i] + " ");
			}
		}
		System.out.println();
		sort(characters);
	}
}
