package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 两种实现方式：
 *      1. GraphNode / 邻接表
 *      2. Matrix
 *
 * Time: O(V + E) / O(V^2)
 * Space:O(V)
 */
public class BFS {

    /**
     * GraphNode形式的【迭代】BFS
     * @param graphNode
     */
    public static void bfs(GraphNode graphNode) {
        HashSet<GraphNode> visited = new HashSet<>();
        Queue<GraphNode> queue = new LinkedList<>();

        visited.add(graphNode);
        queue.offer(graphNode);

        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            for (GraphNode neighbour : node.neighbours) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }
        }
    }

    /**
     * 对上面代码的优化，使得打印的时候可以将每层区分开来 --》 可以用来找到最短距离
     * @param graphNode
     */
    public static void bfs2(GraphNode graphNode) {
        HashSet<GraphNode> visited = new HashSet<>();
        Queue<GraphNode> queue = new LinkedList<>();
        
        visited.add(graphNode);
        queue.offer(graphNode);
        
        while (!queue.isEmpty()) {
            /**
             * 该size变量可以用来判断当前的node下，有几个node与它相连
             * 因为，与当前node的neighbours都会被加入到queue中，所以queue的size就是当前node的neighbours的数量
             */
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                GraphNode node = queue.poll();
                for (GraphNode neighbour : node.neighbours) {
                    if (!visited.contains(neighbour)) {
                        visited.add(neighbour);
                        queue.offer(neighbour);
                    }
                }
            }
        }
    }

    //========================【Matrix形式】===========================
    public static void bfsMatrixIteration(int[][] matrix) {
        int[] visited = new int[matrix.length];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                queue.offer(i);
                while (!queue.isEmpty()) {
                    Integer vertex = queue.poll();
                    for (int j = 0; j < matrix.length; j++) {
                        if (matrix[vertex][j] == 1 && visited[j] == 0) {
                            queue.offer(j);
                            visited[j] = 1;
                        }
                    }
                }
            }
        }
    }

    /**
     * 对上述的Matrix输入形式的代码进行优化，使其实现分层打印
     */
    public static void bfsMatrixIteration2(int[][] matrix) {
        int[] visited = new int[matrix.length];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                queue.offer(i);

                while (!queue.isEmpty()) {
                    int size = queue.size();
                    /**
                     * 下面的 for (int j = 0; j < size; j++)是用来控制分层的
                     */
                    for (int j = 0; j < size; j++) {
                        Integer vertex = queue.poll();
                        for (int k = 0; k < matrix.length; k++) {
                            if (matrix[vertex][k] == 1 && visited[k] == 0) {
                                visited[k] = 0;
                                queue.offer(k);
                            }
                        }
                    }
                }
            }
        }
    }
}
