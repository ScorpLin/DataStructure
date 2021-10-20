package Graph.kruskal;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Kruskal {

    public void kruskalMST(Graph graph) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((a, b) -> (a.weight - b.weight));
        priorityQueue.addAll(graph.allEdges);

        List<Edge> res = new ArrayList<>();
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6); // 假定有6个点
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 1);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 4, 3);
        graph.addEdge(2, 4, 6);
        graph.addEdge(2, 3, 5);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 5, 2);
        graph.addEdge(4, 5, 6);
    }
}
