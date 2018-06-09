package pers.tavish.ex.chapter1.analysisofalgorithms.creativeproblems;

class Element {

	private int row;
	private int col;
	private Integer val;

	public Element() {
		this.row = -1;
		this.col = -1;
		this.val = null;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String toString() {
		return "Element:[" + row + "][" + col + "], val = " + val;
	}
}

// 提高题 1.4.19
public class MatrixLocalMinimum {

	// 判断是否为该行的局部最小值
	private static boolean isRowLocalMinimum(int[] arr, int colIdx) {
		return arr[colIdx] < arr[colIdx - 1] && arr[colIdx] < arr[colIdx + 1];
	}

	// 判断是否为该列的局部最小值
	private static boolean isColLocalMinimum(int[][] matrix, int rowIdx, int colIdx) {
		return matrix[rowIdx][colIdx] < matrix[rowIdx - 1][colIdx]
				&& matrix[rowIdx][colIdx] < matrix[rowIdx + 1][colIdx];
	}

	public static Element getLocalMinimum(int[][] matrix) {
		int N = matrix.length; 

		if (N < 3) {
			throw new IllegalArgumentException();
		}

		Element e = new Element();

		int rowIdx = N / 2; // 行号
		int colIdx = N / 2; // 列号

		while (rowIdx > 0 && rowIdx < N - 1 && colIdx > 0 && colIdx < N - 1) {
			int[] arr = matrix[rowIdx];
			// 判断是否是该行的局部最小值
			if (isRowLocalMinimum(arr, colIdx)) {
				// 判断是否是该列的局部最小值
				if (isColLocalMinimum(matrix, rowIdx, colIdx)) {
					// 如果该值即是行局部最小值又是列局部最小值，则对e进行赋值并返回
					e.setRow(rowIdx);
					e.setCol(colIdx);
					e.setVal(matrix[rowIdx][colIdx]);
					break;
				} else {
					// 如果该值不是列的局部最小值，对行号进行调整
					if (matrix[rowIdx + 1][colIdx] > matrix[rowIdx - 1][colIdx]) {
						rowIdx--;
					} else {
						rowIdx++;
					}
				}
			} else {
				// 如果该值不是行的局部最小值，对列号进行调整
				if (arr[colIdx + 1] > arr[colIdx - 1]) {
					colIdx--;
				} else {
					colIdx++;
				}
			}
		}
		return e;
	}

	public static void main(String[] args) {
		int[][] matrix = { 
				{ 1, 2, 3, 4 }, 
				{ 5, 6, 7, 8 }, 
				{ 9, 10, 11, 12 }, 
				{ 13, 14, 15, 16 } };
		Element element = getLocalMinimum(matrix);
		System.out.println(element);
	}
}
