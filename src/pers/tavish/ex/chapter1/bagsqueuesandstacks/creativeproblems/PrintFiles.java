package pers.tavish.ex.chapter1.bagsqueuesandstacks.creativeproblems;

import java.io.File;

public class PrintFiles {
	
	private static void printIndent(int n) {
		for (int i = 0; i < n; i++) {
			System.out.print("|----");
		}
	}

	private static void printFiles(String path, int depth) {
		
		File file = new File(path);
		printIndent(depth);
		System.out.println(file.getName());
		
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				printFiles(f.getAbsolutePath(), depth + 1);
			}
		} 
	}

	public static void printFiles(String path) {
		printFiles(path, 0);
	}
	
	public static void main(String[] args) {
		printFiles("C:\\Users\\Tavish\\Desktop\\temp\\Test\\");
	}
}
