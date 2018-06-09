package pers.tavish.test.generic.animal;

public class Animal implements Comparable<Animal>{
	protected int age;
	
	public Animal(int age) {
		this.age = age;
	}
	
	@Override
	public int compareTo(Animal o) {
		return this.age - o.age;
	}
}

class Dog extends Animal {

	public Dog(int age) {
		super(age);
	}
}