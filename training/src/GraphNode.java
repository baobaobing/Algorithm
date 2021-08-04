public class GraphNode implements Comparable<GraphNode> {
    int id;
    int dist;
    // Map<Integer, Integer> costs;

    GraphNode(int id) {
        this.id = id;
        this.dist = Integer.MAX_VALUE;
        // this.costs = new HashMap<>();
    }

    @Override
    public int compareTo(GraphNode that) {
        return this.dist - that.dist;
    }
}
