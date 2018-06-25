package pers.tavish.code.chapter4.directedgraphs;

import java.util.Iterator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

// 非递归的寻找有向环实现
public class NonrecursiveDirectedCycle {
    private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

    /**
     * Determines whether the digraph {@code G} has a directed cycle and, if so,
     * finds such a cycle.
     * @param G the digraph
     */
    @SuppressWarnings("unchecked")
	public NonrecursiveDirectedCycle(Digraph G) {
        int[] edgeTo = new int[G.V()];            // edgeTo[v] = previous vertex on path to v
        boolean[] marked = new boolean[G.V()];    // marked[v] = has vertex v been marked?
        boolean[] onStack = new boolean[G.V()];   // onStack[v] = is vertex on the stack?
        Stack<Integer> stack = new Stack<Integer>();

        // to be able to iterate over each adjacency list, keeping track of which
        // vertex in each adjacency list needs to be explored next
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                onStack[s] = true;
                marked[s] = true;
                stack.push(s);
                while (!stack.isEmpty()) {
                    int v = stack.peek();
                    if (adj[v].hasNext()) {
                        int w = adj[v].next();
                        if (!marked[w]) {
                            // discovered vertex w for the first time
                            marked[w] = true;
                            edgeTo[w] = v;
                            stack.push(w);
                            onStack[w] = true;
                        }
                        // trace back directed cycle
                        else if (onStack[w]) {
                            cycle = new Stack<Integer>();
                            for (int x = v; x != w; x = edgeTo[x]) {
                                cycle.push(x);
                            }
                            cycle.push(w);
                            cycle.push(v);
                            assert check();
                            return;
                        }
                    }
                    else {
                        // v's adjacency list is exhausted
                        int vCopy = stack.pop();
                        assert v == vCopy;
                        onStack[v] = false;
                    }
                }
            }
        }
    }



    /**
     * Does the digraph have a directed cycle?
     * @return {@code true} if the digraph has a directed cycle, {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle, and {@code null} otherwise.
     * @return a directed cycle (as an iterable) if the digraph has a directed cycle,
     *    and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }


    // certify that digraph has a directed cycle if it reports one
    private boolean check() {

        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) first = v;
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle begins with %d and ends with %d\n", first, last);
                return false;
            }
        }


        return true;
    }

    /**
     * Unit tests the {@code NonrecursiveDirectedCycle} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);

        NonrecursiveDirectedCycle finder = new NonrecursiveDirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No directed cycle");
        }
    }
}
