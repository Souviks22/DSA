package souvik.graphs.weightedAdjList;

import souvik.support.List;

public class WeightedGraph {
    private final int vertex;
    private int edges;
    private final List<Edge>[] adj;

    public WeightedGraph(int vertex) {
        this.vertex = vertex;
        edges = 0;
        adj = (List<Edge>[]) new List[vertex];
        for (int i = 0; i < vertex; i++) {
            adj[i] = new List<>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].push_front(e);
        adj[w].push_front(e);
        edges++;
    }

    public List<Edge> adj(int v) {
        return adj[v];
    }

    public int getVertex() {
        return vertex;
    }

    public int getEdges() {
        return edges;
    }
}