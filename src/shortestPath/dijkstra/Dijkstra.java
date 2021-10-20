package shortestPath.dijkstra;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Dijkstra缺点：weight不能为负数
 *
 * Dijkstra变形问题：
 *   1. 给定两条最短路径：找到点即截止
 *   2. 任意顶点对之间的最短路径：每个点调用Dijkstra算法
 */

public class Dijkstra {

    public void dijkstra(Graph graph) {
        /**
         * 用于存放已经遍历的vertex
         */
        boolean[] visited = new boolean[graph.vertices];
        /**
         * 用于存放当前点到原点的最短距离
         */
        int[] minDist = new int[graph.vertices];

        /**
         * 初始化minDist，原点到自身的距离为0，到其余点的距离为无穷大
         */
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;

        /**
         * 在PriorityQueue中存放的是一【Pair<Integer, Integer>】的形式，其中 key：距离； value：原点
         * 权重按照从小到大排列，是个最小堆
         */
        PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>(graph.vertices, new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                int key1 = o1.getKey();
                int key2 = o2.getKey();
                return key1 - key2;
            }
        });
        priorityQueue.offer(new Pair<>(minDist[0], 0));

        while (!priorityQueue.isEmpty()) {
            Pair<Integer, Integer> pair = priorityQueue.poll();
            int vertex = pair.getValue();
            /**
             * 将遍历的当前点在visited数组中赋值为true
             */
            visited[vertex] = true;
            /**
             * 将当前点对应的所有Edge都取出来，用于去寻找距离最小的点
             */
            List<Edge> list = graph.list[vertex];

            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                /**
                 * 设置该if判断语句的目的是：
                 *   当遍历到第二个点时，第一个点已经完成，所以需要把之前遍历过的点排除
                 */
                if (!visited[edge.end]) {
                    /**
                     * destination代表的是【与当前点相连的其他点】
                     */
                    int destination = edge.end;
                    /**
                     * curDist代表的是：
                     *   minDist[vertex]: 原点到vertex的距离
                     *   edge.weight: 当前点到与其相连的其中一条边的距离
                     */
                    int curDist = minDist[vertex] + edge.weight;

                    /**
                     * 将 curDist 与 minDist[destination]进行比较：
                     *      如果 curDist < minDist[destination]，那么就更新minDist[destination]的值
                     */
                    if (curDist < minDist[destination]) {
                        priorityQueue.offer(new Pair<>(curDist, destination));
                        minDist[destination] = curDist;
                    }
                }
            }
        }

        print(minDist);
    }

    public void print(int[] minDist) {
        System.out.println("Dijkstra: ");
        for (int i = 0; i < minDist.length; i++) {
            System.out.println("Source Vertex 0: to vertex " + i + " minDist : " + minDist[i]);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 4);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 6);

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstra(graph);
    }
}
