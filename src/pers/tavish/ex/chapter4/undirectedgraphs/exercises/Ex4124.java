package pers.tavish.ex.chapter4.undirectedgraphs.exercises;

import edu.princeton.cs.algs4.StdIn;
import pers.tavish.code.chapter4.undirectedgraphs.BreadthFirstPaths;
import pers.tavish.code.chapter4.undirectedgraphs.Graph;
import pers.tavish.code.chapter4.undirectedgraphs.SymbolGraph;

public class Ex4124 {
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
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

		System.out.println("输入年份：");
		int y = Integer.parseInt(StdIn.readLine()); // 读取一个年份
		
		
		while (!StdIn.isEmpty()) {
			String sink = StdIn.readLine(); // 读取符号图的一个顶点
			if (sg.contains(sink)) {
				int t = sg.index(sink); // 将符号图顶点转为无向图顶点
				if (bfs.hasPathTo(t)) {
					// 如果有从起点到该顶点的路径
					for (int v : bfs.pathTo(t)) {

						String name = sg.name(v);
						
						// 以")"结尾的name为电影
						if (name.endsWith(")")) {
							
							// 获取该电影的上映年份
							int year = Integer.parseInt(name.substring(name.indexOf("(") + 1, name.length() - 1));
							
							// 如果年份大于参数y，则跳过该电影
							if (year > y) {
								continue;
							}
						}

						// 打印路径
						System.out.println("   " + name);
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
