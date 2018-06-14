package pers.tavish.ex.chapter1.casestudyunionfind.creativeproblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

class Connection {
	
	private int p;
	private int q;

	public Connection(int p, int q) {
		this.p = p;
		this.q = q;
	}

	@Override
	public String toString() {
		return "Connection: (" + p + ", " + q + ")";
	}

	public int getP() {
		return p;
	}

	public int getQ() {
		return q;
	}
}

// 提高题1.5.18
public class RandomGrid {

	public static Connection[] generate(int N) {

		List<Connection> list = new ArrayList<>();

		List<Integer> listp = new ArrayList<>();
		List<Integer> listq = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			listp.add(i);
			listq.add(i);
		}

		Collections.shuffle(listp);
		Collections.shuffle(listq);

		for (int i = 0; i < N; i++) {

			int p = listp.get(i);
			int q = listq.get(i);

			// 随机连接p、q或q、p
			if (StdRandom.bernoulli()) {
				list.add(new Connection(p, q));
			} else {
				list.add(new Connection(q, p));
			}
		}
		return list.toArray(new Connection[0]);
	}

	public static void main(String[] args) {

		int N = StdIn.readInt();
		Connection[] connections = generate(N);

		for (int i = 0; i < connections.length; i++) {
			System.out.println(connections[i]);
		}
	}
}
