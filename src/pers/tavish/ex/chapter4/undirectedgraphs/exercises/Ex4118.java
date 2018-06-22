package pers.tavish.ex.chapter4.undirectedgraphs.exercises;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import pers.tavish.code.chapter4.undirectedgraphs.CC;
import pers.tavish.code.chapter4.undirectedgraphs.Graph;

// 练习题4.1.18
public class Ex4118 {
	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0])); // 读取tinyGex2.txt构造图
		
		CC cc = new CC(G);
		int M = cc.count();

		System.out.println(M + " components");

		@SuppressWarnings("unchecked")
		Bag<Integer>[] components = (Bag<Integer>[]) new Bag[M];

		for (int i = 0; i < M; i++) {
			components[i] = new Bag<>();
		}

		for (int v = 0; v < G.V(); v++) {
			components[cc.id(v)].add(v);
		}

		for (int i = 0; i < M; i++) {
			for (int v : components[i]) {
				System.out.print(v + " ");
			}
			System.out.println();
		}
	}
}
