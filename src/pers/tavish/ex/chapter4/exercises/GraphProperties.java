package pers.tavish.ex.chapter4.exercises;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import pers.tavish.code.chapter4.undirectedgraphs.BreadthFirstPaths;
import pers.tavish.code.chapter4.undirectedgraphs.DepthFirstSearch;
import pers.tavish.code.chapter4.undirectedgraphs.Graph;

// 练习题 4.1.16
public class GraphProperties {
	
	private Graph G; // 保存图的引用
	private int[] eccentricities; // 保存每一个顶点的离心率
	private int diameter; // 保存图的直径
	private int radius; // 保存图的半径
	private List<Integer> centers; // 保存图的中点
		
	public GraphProperties(Graph G) {
		// 测试图的连通性
		DepthFirstSearch dfs = new DepthFirstSearch(G, 0); 
		if (dfs.count() != G.V()) {
			// 图不连通，抛出异常
			throw new IllegalArgumentException("Graph G is not connected.");
		}
		this.G = G;
		
		calculateEcc(); // 计算每一点的离心率
		calculateDR(); // 计算直径和半径
		calculateCenter(); // 计算中点
	}

	/*
	 * v的离心率
	 */
	public int eccentricity(int v) {
		return eccentricities[v];
	}
	
	/*
	 * 图的直径
	 */
	public int diameter() {
		return diameter;
	}
	
	/*
	 * 图的半径
	 */
	public int radius() {
		return radius;
	}
	
	/*
	 * 随机返回图的一个中点
	 */
	public int center() {
		return centers.get(StdRandom.uniform(centers.size()));
	}
	
	/*
	 * 返回图G
	 */
	public Graph G() {
		return G;
	}
	
	// 计算每个顶点的离心率、图的直径和半径
	private void calculateEcc() {
		
		int V = G.V();
		eccentricities = new int[V]; // 初始化离心率数组
		BreadthFirstPaths bfp = null;
		int ecc = 0; // 离心率
		for (int i = 0; i < V; i++) { 
			// 对每一个顶点进行一次广度优先搜索
			bfp = new BreadthFirstPaths(G, i);
			// 计算起点i与每一个点的距离
			for (int j = 0; j < V; j++) {
				if (i == j) {
					continue;
				}
				int dist = bfp.distTo(j);
				// 遍历出距离的最大值即为起点i的离心率
				if (dist > ecc) {
					ecc = dist;
				}
			}
			eccentricities[i] = ecc;
		}
	}


	// 计算图的直径和半径，即数组eccentricities[]的最大值和最小值
	private void calculateDR() {
		diameter = Integer.MIN_VALUE;
		radius = Integer.MAX_VALUE;
		for (int ecc : eccentricities) {
			if (ecc > diameter) {
				diameter = ecc;
			}
			if (ecc < radius) {
				radius = ecc;
			}
		}
	}
	
	// 计算图中点
	private void calculateCenter() {
		centers = new ArrayList<Integer>();
		for (int i = 0; i < eccentricities.length; i++) {
			if (eccentricities[i] == radius) {
				centers.add(i);
			}
		}
	}
	
	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0])); // mediumG.txt
		GraphProperties gp = new GraphProperties(G); 
		
		System.out.println(gp.eccentricity(25)); // 14
		System.out.println(gp.diameter()); // 14
		System.out.println(gp.radius()); // 13
		System.out.println(gp.center()); // 9
	}
}
