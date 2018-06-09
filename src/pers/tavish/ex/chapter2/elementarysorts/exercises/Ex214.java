package pers.tavish.ex.chapter2.elementarysorts.exercises;

// 练习题 2.1.4
public class Ex214 {
	public static <T> void sort(Comparable<T>[] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			// 将a[i]插入到a[i - 1]、a[i - 2]、a[i - 3]...之中
			int j = 0; // 声明j，用于记录离开for循环时的j值
			for (j = i; j > 0 && less(a[j], a[j - 1]); j--) {
				exch(a, j, j - 1);
			}
			// 打印i和j的值
			System.out.print(i + "\t" + j + "\t");
			// 打印数组
			show(a);
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
		String strings = "EASYQUESTION";
		char[] chars = strings.toCharArray();
		Character[] characters = new Character[chars.length];

		System.out.print("i\tj\t");
		for (int i = 0; i < characters.length; i++) {
			characters[i] = chars[i];
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.print("\t\t");
		for (int i = 0; i < characters.length; i++) {
			System.out.print(characters[i] + " ");
		}
		System.out.println();
		sort(characters);
	}
}
