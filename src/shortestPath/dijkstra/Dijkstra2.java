package shortestPath.dijkstra;


import java.util.Arrays;

/**
 * 当输入为矩阵形式时的Dijkstra算法
 */
public class Dijkstra2 {

    public void dijkstra(int[][] matrix) {
        /**
         * 初始化操作
         */
        int vertices = matrix.length;
        boolean[] visited = new boolean[vertices];
        int[] minDist = new int[vertices];

        Arrays.fill(minDist, Integer.MAX_VALUE);
        minDist[0] = 0;

        for (int i = 0; i < vertices; i++) {
            /**
             * 在这段代码中，我们不用PriorityQueue，priorityQueue的作用就是用来寻找最小的weight依次比较
             */
            int vertex = getMinVertex(visited, minDist);
            visited[vertex] = true;

            for (int j = 0; j < vertices; j++) {
                if (!visited[j] && matrix[vertex][j] != 0) {
                    int curDist = matrix[vertex][j] + minDist[vertex];
                    if (curDist < minDist[j]) {
                        minDist[j] = curDist;
                    }
                }
            }
        }
        print(minDist);
    }

    /**
     * 用来寻找当前的vertex
     * @param visited
     * @param minDist
     * @return
     */
    public int getMinVertex(boolean[] visited, int[] minDist) {
        int min = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && minDist[i] < min) {
                min = minDist[i];
                vertex = i;
            }
        }
        return vertex;
    }

    public void print(int[] minDist) {
        System.out.println("Dijkstra: ");
        for (int i = 0; i < minDist.length; i++) {
            System.out.println("Source Vertex 0: to vertex " + i + " minDist : " + minDist[i]);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0,3,4,2,7},
                {3,0,4,6,3},
                {4,4,0,5,8},
                {2,6,5,0,6},
                {7,3,8,6,0}
        };
        Dijkstra2 dijkstra2 = new Dijkstra2();
        dijkstra2.dijkstra(matrix);
    }

}
