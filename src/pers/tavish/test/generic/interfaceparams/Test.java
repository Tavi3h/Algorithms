package pers.tavish.test.generic.interfaceparams;

public class Test {
	
	public static void animalRun(Animal a) {
		a.run();
	}
	
	public static <T extends Animal> void animalRun2(T a) {
		a.run();
	}
	
	public static void main(String[] args) {
		Cat cat = new Cat();
		animalRun(cat);
		animalRun2(cat);
	}
}
