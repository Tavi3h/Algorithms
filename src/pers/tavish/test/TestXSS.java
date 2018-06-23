package pers.tavish.test;

public class TestXSS {

    private static long count = 0;
    
    public static void main(String[] args) {
        infinitelyRecursiveMethod(1);

    }
    
    public static void infinitelyRecursiveMethod(long a){
        System.out.println(count++);
        infinitelyRecursiveMethod(a);
    }

}
