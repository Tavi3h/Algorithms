package pers.tavish.ex.chapter1.analysisofalgorithms.experiments;

import java.util.Random;

import edu.princeton.cs.algs4.Stopwatch;

public class DoublingRatio {
    
    // 测试int类型
    public static double timeTrialofInts(int n) {
        FixedCapacityStackOfInts fcsi = new FixedCapacityStackOfInts(n);
        int[] a = new int[n];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(Integer.MAX_VALUE);
        }
        Stopwatch timer = new Stopwatch();
        // n次push
        for (int i = 0; i < n; i++) {
            fcsi.push(a[i]);
        }
        // n次pop
        for (int i = 0; i < n; i++) {
            fcsi.pop();
        }
        return timer.elapsedTime();
    }

    // 测试Integer类型
    public static double timeTrialInteger(int n) {
        FixedCapacityStack<Integer> fcs = new FixedCapacityStack<>(n);
        int[] a = new int[n];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt(Integer.MAX_VALUE);
        }
        Stopwatch timer = new Stopwatch();
        // n次push
        for (int i = 0; i < n; i++) {
            fcs.push(a[i]);
        }
        // n次pop
        for (int i = 0; i < n; i++) {
            fcs.pop();
        }
        return timer.elapsedTime();
    }

    public static void main(String[] args) {
        System.out.println("N int(s) Integer(s) Diff");
        for (int n = 250; true; n += n) {
            double timeInt = timeTrialofInts(n);
            double timeInteger = timeTrialInteger(n);
            System.out.printf("%d %5.3f %5.3f %5.3f\n", n, timeInt, timeInteger, (timeInteger - timeInt));
        }
    }
}
