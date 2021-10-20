package Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    int label;

    List<GraphNode> neighbours;

    public GraphNode (int label) {
        this.label = label;
        neighbours = new ArrayList<>();
    }
}
