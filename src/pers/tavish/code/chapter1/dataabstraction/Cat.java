package pers.tavish.code.chapter1.dataabstraction;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;


// args = { datafiles\\in1.txt datafiles\\in2.txt datafiles\\out.txt }
public class Cat { 

    public static void main(String[] args) { 
        Out out = new Out(args[args.length - 1]);
        for (int i = 0; i < args.length - 1; i++) {
            In in = new In(args[i]);
            String s = in.readAll();
            out.println(s);
            in.close();
        }
        out.close();
    }
}
