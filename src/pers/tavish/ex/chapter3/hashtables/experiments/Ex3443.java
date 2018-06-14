package pers.tavish.ex.chapter3.hashtables.experiments;

import edu.princeton.cs.algs4.StdRandom;

// 实验题3.4.43
public class Ex3443 {

	public static void main(String[] args) {

		int M = 10000;

		LinearProbingHashSTEx3438<Integer, Integer> lphst = new LinearProbingHashSTEx3438<>(M);

		while (lphst.size() != M) {
			lphst.put(StdRandom.uniform(Integer.MAX_VALUE), 0);
		}
		System.out.println("M = " + M + "，理论比较次数：" + Math.sqrt(Math.PI / 2) * Math.pow(M, (double) 3 / 2) + "，实际比较次数："
				+ lphst.getDetectTimes());
	}
}
