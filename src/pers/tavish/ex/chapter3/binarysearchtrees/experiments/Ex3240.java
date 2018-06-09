package pers.tavish.ex.chapter3.binarysearchtrees.experiments;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.StdRandom;

// 实验题3.2.40
public class Ex3240 {
	
	public static void main(String[] args) {

		BST<Integer, Integer> bst = null;

		int height = 0;

		// N分别为10^4、10^5、10^6
		for (int N = 10000; N <= 1000000; N *= 10) {
			
			// 100次实验
			for (int t = 0; t < 100; t++) {
				bst = new BST<>();
				while (bst.size() != N) { // bst含有N个结点
					int i = StdRandom.uniform(3 * N);
					bst.put(i, i);
				}
				height += bst.height();
			}

			System.out.println("For N = " + N);
			System.out.println("Average BST height = " + (double) height / 100);
			System.out.println("2.99logN = " + 2.99 * Math.log(N) / Math.log(2) + "\n");
		}
	}
}
