package pers.tavish.ex.chapter1.casestudyunionfind.creativeproblems;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.UF;

// 提高题 1.5.19
public class RandomGridTest {
	
	public static void main(String[] args) {
		
		int N = StdIn.readInt();
		
		Connection[] connections = RandomGrid.generate(N);
		
		UF uf = new UF(N);
		
		for (int i = 0; i < connections.length; i++) {
			int p = connections[i].getP();
			int q = connections[i].getQ();
			
			if (!uf.connected(p, q)) {
				uf.union(p, q);
				System.out.println(connections[i]);
			}
		}
		System.out.println(uf.count() + " components");
	} 
}
