package pers.tavish.code.chapter4.minimumspanningtrees;

// 加权无向图的数据类型
public class Edge implements Comparable<Edge> {

	private final int v; // vertex v
	private final int w; // vertex w
	private final double weight; // weight of v-w

	/*
	 * 构造函数
	 */
	public Edge(int v, int w, double weight) {
		if (v < 0 || w < 0) {
			throw new IllegalArgumentException("vertex index must be a nonnegative integer");
		}
		if (Double.isNaN(weight)) {
			throw new IllegalArgumentException("Weight is NaN");
		}
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	/*
	 * 返回weight
	 */
	public double weight() {
		return weight;
	}

	/*
	 * 返回其中一个顶点
	 */
	public int either() {
		return v;
	}

	/*
	 * 返回另一个顶点
	 */
	public int other(int vertex) {
		if (vertex == v) {
			return w;
		} else if (vertex == w) {
			return v;
		} else {
			throw new IllegalArgumentException("Illegal endpoint");
		}
	}

	@Override
	public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
	}
	
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
