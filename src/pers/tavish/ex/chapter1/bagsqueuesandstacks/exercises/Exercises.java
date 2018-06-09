package pers.tavish.ex.chapter1.bagsqueuesandstacks.exercises;

import java.util.Iterator;

import org.junit.Test;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

public class Exercises {
	// 练习题1.3.2
	public void ex132() {
		Stack<String> stack = new Stack<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (!item.equals("-"))
				stack.push(item);
			else if (!stack.isEmpty())
				StdOut.print(stack.pop() + " ");
		}
		StdOut.println("(" + stack.size() + " left on stack)");
	}

	// 练习题1.3.4
	public boolean parentheses(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push('(');
			}
			if (s.charAt(i) == '{') {
				stack.push('{');
			}
			if (s.charAt(i) == '[') {
				stack.push('[');
			}
			if (s.charAt(i) == ')') {
				if (stack.isEmpty()) {
					return false;
				}
				if (stack.pop() != '(') {
					return false;
				}
			} else if (s.charAt(i) == '}') {
				if (stack.isEmpty()) {
					return false;
				}
				if (stack.pop() != '{') {
					return false;
				}
			} else if (s.charAt(i) == ']') {
				if (stack.isEmpty()) {
					return false;
				}
				if (stack.pop() != '[') {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

	// 练习题1.3.5
	public void ex135(int N) {
		Stack<Integer> stack = new Stack<>();
		while (N > 0) {
			stack.push(N % 2);
			N = N / 2;
		}
		for (int d : stack) {
			StdOut.print(d);
		}
		StdOut.println();
	}

	// 练习题1.3.9
	public void ex139() {
		// 符号栈
		Stack<String> ops = new Stack<>();
		// 数值栈，使用泛型String
		Stack<String> vals = new Stack<>();

		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
				ops.push(s);
			} else if (s.equals(")")) {
				// 遇到右括号，弹出两个操作数和一个运算符，进行字符串链接，而后作为一
				String tmp = ") " + vals.pop() + " " + ops.pop() + " " + vals.pop() + " " + "(";
				vals.push(tmp);
			} else {
				vals.push(s);
			}
		}

		// 结果是逆序，需要调整顺序
		String tmp = vals.pop();
		String result = "";
		for (int i = tmp.length() - 1; i >= 0; i--) {
			result += tmp.charAt(i);
		}

		StdOut.println(result);
	}

	// 练习题1.3.10
	public void infixtoPostfix() {
		Stack<String> stack = new Stack<>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
				// 如果是运算符，则压入栈
				stack.push(s);
			} else if (s.equals(")")) {
				// 如果是右括号，则弹出一个运算符
				StdOut.print(stack.pop() + " ");
			} else if (s.equals("(")) {
				// 如果是左括号，则忽略
			} else {
				// 如果是数字，则直接打印
				StdOut.print(s + " ");
			}
		}
		StdOut.println();
	}

	// 练习题1.3.11
	public double evaluatePostfix(String postfix) {

		Stack<Double> vals = new Stack<>();

		for (int i = 0; i < postfix.length(); i++) {
			char c = postfix.charAt(i);
			switch (c) {
			case '+':
				vals.push(vals.pop() + vals.pop());
				break;
			case '-':
				vals.push(vals.pop() - vals.pop());
				break;
			case '*':
				vals.push(vals.pop() * vals.pop());
				break;
			case '/':
				vals.push(vals.pop() / vals.pop());
				break;
			default:
				vals.push(Double.parseDouble(c + "d"));
				break;
			}
		}
		return vals.pop();
	}

	// 练习题1.3.12
	public static <Item> Stack<Item> copy(Stack<Item> stack) {
		@SuppressWarnings("unchecked")
		Item[] tmp = (Item[]) new Object[stack.size()];

		Iterator<Item> iterator = stack.iterator();

		// 临时存放数据的数组
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = iterator.next();
		}

		// 使用数组对新的Stack进行赋值
		Stack<Item> newStack = new Stack<>();
		for (int i = tmp.length - 1; i >= 0; i--) {
			newStack.push(tmp[i]);
		}
		return newStack;
	}

	// 练习题1.3.16
	public static Date[] readDates(String name) {
		In in = new In(name);
		Queue<Date> queue = new Queue<>();
		while (!in.isEmpty()) {
			queue.enqueue(new Date(in.readString()));
		}
		int N = queue.size();
		Date[] arr = new Date[N];
		for (int i = 0; i < N; i++) {
			arr[i] = queue.dequeue();
		}
		return arr;
	}

	// 练习题1.3.17
	public static Transaction[] readTransactions(String name) {
		In in = new In(name);
		Queue<Transaction> queue = new Queue<Transaction>();
		while (!in.isEmpty()) {
			String[] str = in.readLine().split(" ");
			Transaction tran = new Transaction(str[0], new Date(str[1]), Double.parseDouble(str[2]));
			queue.enqueue(tran);
		}

		int N = queue.size();
		Transaction[] arr = new Transaction[N];
		for (int i = 0; i < N; i++)
			arr[i] = queue.dequeue();
		return arr;
	}

	// 测试用JUnit
	@Test
	public void junitTest() {
		// ex132();
		// System.out.println(parentheses(StdIn.readLine()));
		// ex135(50);
		// ex139();
		// infixtoPostfix();
		// StdOut.println(evaluatePostfix("234+56**+"));

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < 5; i++) {
			stack.push(i);
		}
		Stack<Integer> newStack = copy(stack);
		StdOut.println(stack);
		StdOut.println(newStack);
	}

	// 测试用主函数
	public static void main(String[] args) {
		
	}
}
