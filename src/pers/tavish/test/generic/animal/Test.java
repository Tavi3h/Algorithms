package pers.tavish.test.generic.animal;

import java.util.ArrayList;
import java.util.List;

public class Test {

	/*
	 * 方法参数为数组
	 */
	@SuppressWarnings("rawtypes")
	public static void sort1(Comparable[] a) {}

	public static <T> void sort2(Comparable<T>[] a) {}

	public static <T extends Comparable<T>> void sort3(T[] a) {}

	public static <T extends Comparable<? super T>> void sort4(T[] a) {}
	
	/*
	 * 方法参数为List
	 */
	public static <T extends Comparable<T>> void sort5(List<T> a) {}
	
	public static <T extends Comparable<? super T>> void sort6(List<T> a) {}

	public static void main(String[] args) {
		
		Animal[] animals1 = { new Animal(10), new Animal(20) }; // Animal数组1，元素均为Animal
		Animal[] animals2 = { new Animal(15), new Dog(21) }; // Animal数组2， 含有Dog类元素
		Dog[] dogs = { new Dog(12), new Dog(15) }; // Dog数组
		
		List<Animal> animalsList1 = new ArrayList<>(); // Animal List1，元素均为Animal
		animalsList1.add(new Animal(4));
		animalsList1.add(new Animal(14));
		
		List<Animal> animalsList2 = new ArrayList<>(); // Animal List2，含有Dog类元素
		animalsList2.add(new Animal(4));
		animalsList2.add(new Dog(14));
		
		List<Dog> dogsList = new ArrayList<>(); // Dog List
		dogsList.add(new Dog(2));
		dogsList.add(new Dog(3));
		
		// 测试sort1
		sort1(animals1);
		sort1(animals2);
		sort1(dogs);
		
		// 测试sort2
		sort2(animals1);
		sort2(animals2);
		sort2(dogs);
		
		// 测试sort3
		sort3(animals1);
		sort3(animals2);
		sort3(dogs);
		
		// 测试sort4
		sort4(animals1);
		sort4(animals2);
		sort4(dogs);
		
		//测试sort5
		sort5(animalsList1);
		sort5(animalsList2);
//		sort5(dogsList); // 报错：The method sort5(List<T>) in the type Test is not applicable for the arguments (List<Dog>)
		
		// 测试sort6
		sort6(animalsList1);
		sort6(animalsList2);
		sort6(dogsList);
		
	}
}
