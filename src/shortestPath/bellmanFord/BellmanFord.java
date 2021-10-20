package shortestPath.bellmanFord;

import java.util.Arrays;

public class BellmanFord {

    public void bellmanFord(Graph graph) {
        int[] minDist = new int[graph.vertices];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;

        /**
         * BellmanFord的迭代次数为：V - 1次，所以我们从 i = 1 开始遍历
         */
        for (int i = 1; i < graph.vertices; i++) {
            /**
             * 每次迭代都遍历所有的edge
             */
            for (int j = 0; j < graph.allEdges.size(); j++) {
                Edge edge = graph.allEdges.get(j);
                int start = edge.start;
                int end = edge.end;
                int weight = edge.weight;
                if (minDist[start] != Integer.MAX_VALUE && minDist[start] + weight < minDist[end]) {
                    minDist[end] = minDist[start] + weight;
                }
            }
        }

        /**
         * 判断是否有负权环（多一次迭代）
         */
        for (int i = 0; i < graph.allEdges.size(); i++) {
            Edge edge = graph.allEdges.get(i);
            int start = edge.start;
            int end = edge.end;
            int weight = edge.weight;
            if (minDist[start] != Integer.MAX_VALUE && minDist[start] + weight < minDist[end]) {
                System.out.println("负权环");
            }
        }
    }
}
