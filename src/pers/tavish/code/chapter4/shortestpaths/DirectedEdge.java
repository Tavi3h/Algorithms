package pers.tavish.code.chapter4.shortestpaths;

// 加权有向边数据结构
public class DirectedEdge {
	private final int v; // 边的起点
	private final int w; // 边的终点
	private final double weight; // 边的权重

	/*
	 * 构造函数
	 */
	public DirectedEdge(int v, int w, double weight) {
		if (v < 0 || w < 0) {
			throw new IllegalArgumentException("Vertex names must be nonnegative integers");
		}
		if (Double.isNaN(weight)) {
			throw new IllegalArgumentException("Weight is NaN");
		}
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	/*
	 * 返回起点
	 */
    public int from() {
        return v;
    }
    
    /*
     * 返回终点
     */
    public int to() {
        return w;
    }
	
    /*
     * 返回权重
     */
    public double weight() {
    	return weight;
    }
    
    @Override
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}
