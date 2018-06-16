package pers.tavish.ex.chapter4.exercises;

import edu.princeton.cs.algs4.StdIn;
import pers.tavish.code.chapter4.undirectedgraphs.DepthFirstPaths;
import pers.tavish.code.chapter4.undirectedgraphs.Graph;
import pers.tavish.code.chapter4.undirectedgraphs.SymbolGraph;

// 练习题4.1.25
public class DegreesOfSeparationDFS {
	
	public static void main(String[] args) {
		// 构造符号图
		SymbolGraph sg = new SymbolGraph(args[0], args[1]);

		// 获取符号图中用整数表示的无向图
		Graph G = sg.G();

		// 获取起点
		String source = args[2];
		if (!sg.contains(source)) {
			System.out.println(source + "not in database.");
			return;
		}

		// 获取起点对应无向图的整数值，并使用该值进行广度优先搜索
		int s = sg.index(source);
		DepthFirstPaths bfp = new DepthFirstPaths(G, s);

		while (!StdIn.isEmpty()) {
			String sink = StdIn.readLine(); // 读取符号图的一个顶点
			if (sg.contains(sink)) { 
				int t = sg.index(sink); // 将符号图顶点转为无向图顶点
				if (bfp.hasPathTo(t)) { 
					// 如果有从起点到该顶点的路径
					for (int v : bfp.pathTo(t)) {
						// 打印路径
						System.out.println("   " + sg.name(v));
					}
				} else {
					System.out.println("Not connected");
				}
			} else {
				System.out.println("Not in database.");
			}
		}
	}
}
