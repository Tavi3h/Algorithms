package pers.tavish.ex.chapter2.sortingapplications.creativeproblems;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;

// 提高题2.5.12
class Task implements Comparable<Task> {
	private String taskName;
	private double costTime;
	public Task(String name, double time) {
		taskName = name;
		costTime = time;
	}
	
	@Override
	public int compareTo(Task o) {
		return Double.compare(this.costTime, o.costTime);
	}
	
	@Override
	public String toString() {
		return "[" + taskName + ", " + costTime + "]";
	}
}

public class SPT {
	public static void main(String[] args) {
		int n = StdIn.readInt();
		Task[] tasks = new Task[n];
		for (int i = 0; i < tasks.length; i++) {
			String name = StdIn.readString();
			double time = StdIn.readDouble();
			tasks[i] = new Task(name, time);
		}
		
		Arrays.sort(tasks);
		
		for (Task task : tasks) {
			System.out.println(task);
		}
	}
}
