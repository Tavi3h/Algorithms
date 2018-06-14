package pers.tavish.ex.chapter1.analysisofalgorithms.experiments;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.princeton.cs.algs4.StdRandom;

public class Experiments {

	// 实验题 1.4.44
	public int ex1444(int N) {
		
		List<Integer> list = new ArrayList<>();

		while (true) {
			int num = StdRandom.uniform(0, N);
			if (!list.contains(num)) {
				list.add(num);
			} else {
				System.out.println("生成第" + list.size() + "个数字后发生重复。");
				break;
			}
		}
		return list.size();
	}

	// 实验题 1.4.45
	// 判断boolean数组是否都为true
	private boolean isAllTrue(boolean[] a) {
		for (int i = 0; i < a.length; i++) {
			if (!a[i]) {
				return false;
			}
		}
		return true;
	}

	public int ex1445(int N) {
		
		boolean[] mark = new boolean[N];

		int cnt = 0;

		// 一直循环，直到mark的所有元素均为true
		while (true) {
			mark[StdRandom.uniform(0, N)] = true;
			cnt++;
			if (isAllTrue(mark)) {
				System.out.println("生成" + cnt + "个数字后所有可能均已出现");
				break;
			}
		}

		return cnt;
	}
	
	@Test
	public void junitTest() {
		int N = 20000;
		int cnt = 0;
		for (int i = 0; i < 5000; i++) {
			cnt += ex1444(N);
		}
		System.out.println("√(πN/2) = " + Math.sqrt(Math.PI * N / 2) + "，平均生成" + cnt / 5000 + "个数字后发生重复。");
	}

	public static void main(String[] args) {

	}
}
