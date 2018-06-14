package pers.tavish.ex.chapter2.sortingapplications.creativeproblems;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class FileSorter {
	public static void main(String[] args) {
		File dict = new File(args[0]);
		
		File[] files = dict.listFiles();
		
		Arrays.sort(files, new Comparator<File>() {
			@Override
			public int compare(File o1, File o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		for (File file : files) {
			System.out.println(file.getName());
		}
	}
}
