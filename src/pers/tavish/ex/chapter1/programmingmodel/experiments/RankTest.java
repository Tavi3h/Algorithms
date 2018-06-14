package pers.tavish.ex.chapter1.programmingmodel.experiments;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

// 实验题1.1.38

public class RankTest {

	
	// 二分查找的循环实现
	private int binarySearch(int key, int[] arr, int lo, int hi) {

		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (key < arr[mid]) {
				hi = mid - 1;
			} else if (key > arr[mid]) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	public int binarySearch(int key, int[] arr) {
		return binarySearch(key, arr, 0, arr.length - 1);
	}

	// 暴力查找
	public int bruteForceSearch(int key, int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == key) {
				return i;
			} 
		}
		return -1;
	}
	
	@Test
	public void testSearch() {
		// 测试largeW，查找值846942
		int[] arr = new In(new File("datafiles\\largeW.txt")).readAllInts();
		Arrays.sort(arr);
		
		StdOut.println("二分查找测试largeW开始");
		long start1 = System.currentTimeMillis();
		binarySearch(846942, arr);
		long end1 = System.currentTimeMillis();
		StdOut.println("使用二分查找在largeW.txt中查找846942耗时：" + (end1 - start1) + "ms");
		StdOut.println("二分查找测试largeW结束");
		StdOut.println();
		
		StdOut.println("暴力查找测试largeW开始");
		long start2 = System.currentTimeMillis();
		bruteForceSearch(846942, arr);
		long end2 = System.currentTimeMillis();
		StdOut.println("使用暴力查找在largeW.txt中查找846942耗时：" + (end2 - start2) + "ms");
		StdOut.println("暴力查找测试largeW结束");
		StdOut.println();
		
		// 测试largeT，查找值17672230
		int[] arr2 = new In(new File("datafiles\\largeT.txt")).readAllInts();
		Arrays.sort(arr2);
		
		StdOut.println("二分查找测试largeT开始");
		long start3 = System.currentTimeMillis();
		binarySearch(17672230, arr2);
		long end3 = System.currentTimeMillis();
		StdOut.println("使用二分查找在largeT.txt中查找912684耗时：" + (end3 - start3) + "ms");
		StdOut.println("二分查找测试largeT结束");
		StdOut.println();
		
		StdOut.println("暴力查找测试largeT开始");
		long start4 = System.currentTimeMillis();
		bruteForceSearch(17672230, arr2);
		long end4 = System.currentTimeMillis();
		StdOut.println("使用暴力查找在largeT.txt中查找912684耗时：" + (end4 - start4) + "ms");
		StdOut.println("暴力查找测试largeT结束");
		StdOut.println();
	}
	
}
