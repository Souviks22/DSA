package souvik.graph;

import souvik.support.Stack;

public class Topology {
    private final Stack<Integer> reversePostOrder;
    private final boolean[] marked;

    public Topology(Digraph digraph) {
        assert !hasCycle(digraph);
        reversePostOrder = new Stack<>();
        marked = new boolean[digraph.getVertex()];
        for (int v = 0; v < digraph.getVertex(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    public Topology(WeightedDigraph weightedDigraph) {
        reversePostOrder = new Stack<>();
        marked = new boolean[weightedDigraph.getVertex()];
        for (int v = 0; v < weightedDigraph.getVertex(); v++) {
            if (!marked[v]) {
                dfs(weightedDigraph, v);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
                reversePostOrder.push(w);
            }
        }
    }

    private void dfs(WeightedDigraph weightedDigraph, int v) {
        marked[v] = true;
        for (DirectedEdge e : weightedDigraph.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(weightedDigraph, w);
                reversePostOrder.push(w);
            }
        }
    }

    public boolean hasCycle(Digraph digraph) {
        boolean[] checked = new boolean[digraph.getVertex()];
        Stack<Integer> s = new Stack<>();
        s.push(0);
        while (!s.isEmpty()) {
            int v = s.pop();
            checked[v] = true;
            for (int w : digraph.adj(v)) {
                if (checked[w]) return true;
                s.push(w);
            }
        }
        return false;
    }

    public boolean hasCycle(WeightedDigraph weightedDigraph) {
        boolean[] checked = new boolean[weightedDigraph.getVertex()];
        Stack<Integer> s = new Stack<>();
        s.push(0);
        while (!s.isEmpty()) {
            int v = s.pop();
            checked[v] = true;
            for (DirectedEdge e : weightedDigraph.adj(v)) {
                int w = e.to();
                if (checked[w]) return true;
                s.push(w);
            }
        }
        return false;
    }

    public Stack<Integer> order() {
        return reversePostOrder;
    }
}