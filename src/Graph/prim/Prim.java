package Graph.prim;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class Prim {

    public void primMST(Graph graph) {
        boolean[] visited = new boolean[graph.vertices];
        int[] minDists = new int[graph.vertices];
        int[] parents = new int[graph.vertices];

        /**
         * 首先初始化minDists 和 parents 因为visited默认都是false
         */
        Arrays.fill(minDists, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);

        /**
         * Pair: weight, Vertex
         */
        PriorityQueue<Pair<Integer, Integer>> priorityQueue =
                // graph.vertices表示priorityQueue的大小
                new PriorityQueue<>(graph.vertices, new Comparator<Pair<Integer, Integer>>() {
                    @Override
                    public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                        int key1 = o1.getKey();
                        int key2 = o2.getKey();
                        /**
                         * 按照weight进行排序
                         */
                        return key1 - key2;
                    }
                });
        minDists[0] = 0;
        priorityQueue.offer(new Pair<>(minDists[0], 0));

        while (!priorityQueue.isEmpty()) {
            Pair<Integer, Integer> pair = priorityQueue.poll();
            int vertex = pair.getValue();
            visited[vertex] = true;

            List<Edge> list = graph.list[vertex];
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                if (!visited[edge.end]) {
                    /**
                     * 将priorityQueue中的pair取出来
                     */
                    int destination = edge.end;
                    int curDist = edge.weight;
                    /**
                     * curDist代表了当前vertex到destination的距离
                     *   minDists代表的是：距离当前该点最短的vertex的距离
                     *   因此，通过下面的if语句用来找到距离最小的点
                     *
                     * parents中存放的是距离最短的父节点
                     * minDists中存放的是最短距离
                     */
                    if (curDist < minDists[destination]) {
                        priorityQueue.offer(new Pair<>(curDist, destination));
                        parents[destination] = vertex;
                        minDists[destination] = curDist;
                    }
                }
            }
        }
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

        Prim prim = new Prim();
        prim.primMST(graph);
    }
}
