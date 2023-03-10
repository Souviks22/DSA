package souvik.graph;

import souvik.support.List;

public class Digraph {
    private final int vertex;
    private int edges;
    private final List<Integer>[] adj;

    public Digraph(int vertex) {
        this.vertex = vertex;
        adj = (List<Integer>[]) new List[vertex];
        for (int i = 0; i < vertex; i++) {
            adj[i] = new List<>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].pushFront(w);
        edges++;
    }

    public List<Integer> adj(int v) {
        return adj[v];
    }

    public int vertices() {
        return vertex;
    }

    public int edges() {
        return edges;
    }

    public Digraph reverse() {
        Digraph reverseDigraph = new Digraph(vertex);
        for (int v = 0; v < vertex; v++) {
            for (int w : adj[v]) {
                reverseDigraph.addEdge(w, v);
            }
        }
        return reverseDigraph;
    }
}

