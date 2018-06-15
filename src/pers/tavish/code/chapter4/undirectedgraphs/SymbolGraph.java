package pers.tavish.code.chapter4.undirectedgraphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import pers.tavish.code.chapter3.balancedsearchtrees.RedBlackBST;

// 符号图实现
public class SymbolGraph {

	private RedBlackBST<String, Integer> st; // 符号名-->索引
	private String[] keys; // 索引-->符号名
	private Graph G; // 图

	public SymbolGraph(String stream, String sp) {

		st = new RedBlackBST<>();
		In in = new In(stream); // 第一遍

		// 构造索引
		while (in.hasNextLine()) {

			String[] a = in.readLine().split(sp); // 读取字符串

			for (int i = 0; i < a.length; i++) { // 为每个不同字符串关联一个索引
				if (!st.contains(a[i])) {
					st.put(a[i], st.size());
				}
			}
		}

		keys = new String[st.size()]; // 用来获得顶点名的反向索引是一个数组

		for (String name : st.keys()) {
			keys[st.get(name)] = name;
		}

		G = new Graph(st.size());
		in = new In(stream); // 第二遍

		// 构造图
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(sp); // 将每一行的第一个顶点和该行的其他顶点相连
			int v = st.get(a[0]);
			for (int i = 1; i < a.length; i++) {
				G.addEdge(v, st.get(a[i]));
			}
		}
		System.out.println("Constructor Done...");
	}

	public boolean contains(String s) {
		return st.contains(s);
	}

	public int index(String s) {
		return st.get(s);
	}

	public String name(int v) {
		return keys[v];
	}

	public Graph G() {
		return G;
	}

	public static void main(String[] args) {
		String filename = args[0];
		String delim = args[1];
		SymbolGraph sg = new SymbolGraph(filename, delim);

		Graph G = sg.G();

		while (!StdIn.isEmpty()) {
			String source = StdIn.readLine();
			for (int w : G.adj(sg.index(source))) {
				System.out.println("   " + sg.name(w));
			}
		}
	}
}

/*
key1
   val5
   val4
   val3
   val2
   val1
key2
   val7
   val6
   val5
   val4
   val3
val3
   key2
   key1
 */


/*
SymbolGraph.txt:
	key1,val1,val2,val3,val4,val5
	key2,val3,val4,val5,val6,val7
	
ST:
	(key1,0) (val,1) (val2,2) (val3,3) (val4,4) (val5,5) (key2,6) (val6,7) (val7,8)
	 
keys:
	0		1		2		3		4		5		6		7		8
   key1	   val1    val2    val3    val4    val5    key2    val6    val7
   
G:

  1	  ———3———   7	 
   \ /	     \ / 	
	0 —— 4 —— 6 
   / \	     / \ 
  2	  ———5———   8	
*/


