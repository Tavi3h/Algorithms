package pers.tavish.ex.chapter3.searchingapplications.creativeproblems;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;

// 提高题3.5.22、
// 以CSV中0列为key，1到m-1分别为值构建ST数组
// ST数组分别为0-1，0-2，0-3，...，0-m-1索引
// args[1]：数组的初始大小，需大于等于csv文件中每行字符串分割出的tokens.length - 1
//没有考虑CSV中的内部逗号问题，
// 例如：0837101306522,,"Elevate - elevate CD, 10 songs www.youthelevate.com"  这个""中的逗号不应该作为分隔符
public class FullLookupCSV {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		In in = new In(args[0]); // 要读取的csv文件

		int size = Integer.parseInt(args[1]); // 数组的大小

		if (size < 1) {
			throw new IllegalArgumentException("索引数组大小不能小于1");
		}

		@SuppressWarnings("rawtypes")
		RedBlackBST[] rbbsts = new RedBlackBST[size];

		int cnt = -1; // 使用的数组角标
		
		while (in.hasNextLine()) {

			String[] tokens = in.readLine().split(",");

			for (int i = 1; i < tokens.length; i++) {
				if (rbbsts[i - 1] == null) {
					rbbsts[i - 1] = new RedBlackBST<>();
					cnt++;
				}
				rbbsts[i - 1].put(tokens[0], tokens[i]); // tokens[i]可能是空字符串""
			}
		}
		
		System.out.println(cnt);

		System.out.println("索引构建完毕，可以开始检索....");

		while (!StdIn.isEmpty()) {

			String[] querys = StdIn.readString().split(","); // 查询以“,”分隔参数

			String key = querys[0]; // 要查找的键
			int col = Integer.parseInt(querys[1]); // 要查找的索引列

			if (col > cnt) {
				throw new IllegalArgumentException("无当前列的索引");
			}

			if (rbbsts[col].contains(key)) {
				String result = (String) rbbsts[col].get(key);
				result = result.equals("") ? "当前列中该键的索引为空字符串" : result;
				System.out.println(result);
			} else {
				System.out.println("该值不在索引中");
			}
		}
	}
}
