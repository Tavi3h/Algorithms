package pers.tavish.ex.chapter2.sortingapplications.creativeproblems;

import java.lang.reflect.Array;
import java.util.Arrays;

import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;

// 提高题 2.5.17
public class SortStabilityCheck {

	// 检测排序是否为稳定排序
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<? super T>> boolean sort(T[] arr, String method) {

		int N = arr.length;
		T[] backup = (T[]) Array.newInstance(arr.getClass().getComponentType(), N);
		System.arraycopy(arr, 0, backup, 0, N);
		
		// backup使用Arrays.sort()排序，该排序是稳定排序
		Arrays.sort(backup);

		// 对arr数组进行指定方法的排序
		chooseMethod(arr, method);

		// 对比backup数组和arr数组
		for (int i = 0; i < N; i++) {
			if (backup[i] != arr[i]) {
				return false;
			}
		}
		return true;
	}

	private static <T extends Comparable<? super T>> void chooseMethod(T[] arr, String method) {
		if (method.equals("Insertion")) {
			Insertion.sort(arr);
		}
		if (method.equals("Selection")) {
			Selection.sort(arr);
		}
		if (method.equals("Shell")) {
			Shell.sort(arr);
		}
		if (method.equals("Merge")) {
			Merge.sort(arr);
		}
		if (method.equals("Quick")) {
			Quick.sort(arr);
		}
		if (method.equals("Heap")) {
			Heap.sort(arr);
		}
	}

	public static void main(String[] args) {
		Integer[] integers = new Integer[] { new Integer(5), new Integer(8), new Integer(5), new Integer(2),
				new Integer(9) };
		System.out.println(sort(integers, "Merge"));
	}
}
