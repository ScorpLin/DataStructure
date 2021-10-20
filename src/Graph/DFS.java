package Graph;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * DFS两种情况：
 *      1. 输入为 List<GraphNode>, 把所有的点放在一个List中，DFS将其中的点全部遍历一遍(图有可能是连通的也可能是不连通的)
 *      2. 我们已知图是连通的，输入为 GraphNode node
 *
 *
 * DFS:
 *      Time: O(V + E)(GraphNode的形式) 或者 O(V^2)(临接矩阵的形式)
 *      Space:O(V)
 */
public class DFS {

    /**
     * 【递归方式】
     * 不知道图是否连通时可以采用这种方法
     * @param list 所有的node
     */
    public static void dfs(List<GraphNode> list) {
        /**
         * HashSet用来判断当前的点是否加入
         */
        HashSet<GraphNode> visited = new HashSet<>();
        for (GraphNode node : list) {
            /**
             * 如果没有加入，则调用helper()函数将其加入
             */
            if (!visited.contains(node)) {
                helper(node, visited);
            }
        }
    }

    /**
     * 【递归方式】
     * 在已知图是连通的情况下，采用dfs2
     * @param node
     */
    public static void dfs2(GraphNode node) {
        helper(node, new HashSet<>());
    }

    public static void helper(GraphNode node, HashSet<GraphNode> visited) {
        visited.add(node);
        System.out.println(node.label);
        /**
         * DFS的精髓就是不断的递归调用helper()，不断判断当前node的neighbours是否已经全部遍历完了
         */
        for (GraphNode neighbour : node.neighbours) {
            if (!visited.contains(neighbour)) {
                helper(neighbour, visited);
            }
        }
    }

    //===============================================================================

    /**
     *【递归方式】
     * @param matrix 临接矩阵的输入方式
     */
    public static void dfsMatrix(int[][] matrix) {
        // 利用一个一维数组来代替之前class表示方法中dfs的HashSet
        int[] visited = new int[matrix.length];
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == 0) {
                helper(matrix, visited, i);
            }
        }
    }

    public static void helper(int[][] matrix, int[] visited, int vertex) {
        visited[vertex] = 1;
        for (int i = 0; i < visited.length; i++) {
            if (matrix[vertex][i] == 1) {
                if (visited[i] == 0) {
                    helper(matrix, visited, i);
                }
            }
        }
    }

    //=======================【迭代方式】=========================

    /**
     * GraphNode的迭代方式
     * @param graphNode
     */
    public static void dfsIteration(GraphNode graphNode) {
        /**
         * 利用Stack来代替递归方式中的helper函数
         */
        Stack<GraphNode> stack = new Stack<>();
        HashSet<GraphNode> visited = new HashSet<>();
        visited.add(graphNode);
        stack.push(graphNode);
        while (!stack.isEmpty()) {
            GraphNode node = stack.pop();
            // 错误写法：只在这里写一个 visited.add(node), 会出现重复的元素，因为
            //      按照main函数中的添加方式，在元素1没有被pop出来加入到visited中之前，与它相连的元素4和2都会因为元素1是他们的neighbour，会重复加入到stack中
            /**
             * 将当前节点的neighbours一次判断是否访问过，如果没有访问过，则压栈
             */
            for (GraphNode neighbour : node.neighbours) {
                if (!visited.contains(neighbour)) {
                    stack.push(neighbour);
                    visited.add(neighbour);
                }
            }
        }
    }

    /**
     * Matrix的迭代方法
     * @param matrix 图以临接矩阵的方式输入
     */
    public static void dfsMatrixIteration(int[][] matrix) {
        int[] visited = new int[matrix.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < matrix.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                stack.push(i);
                while (!stack.isEmpty()) {
                    Integer vertex = stack.pop();
                    for (int j = 0; j < matrix.length; j++) {
                        if (matrix[vertex][j] == 1) {
                            if (visited[j] == 0) {
                                stack.push(j);
                                visited[j] = 1;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphNode a = new GraphNode(0);
        GraphNode b = new GraphNode(1);
        GraphNode c = new GraphNode(2);
        GraphNode d = new GraphNode(3);
        GraphNode e = new GraphNode(4);
        GraphNode f = new GraphNode(5);

        a.neighbours.add(b);
        a.neighbours.add(d);
        a.neighbours.add(f);

        b.neighbours.add(a);
        b.neighbours.add(e);
        b.neighbours.add(c);

        c.neighbours.add(b);
        c.neighbours.add(e);

        d.neighbours.add(a);
        d.neighbours.add(e);

        e.neighbours.add(d);
        e.neighbours.add(b);
        e.neighbours.add(c);

        f.neighbours.add(a);

        dfs2(a);

        //========================================

        /**
         * 测试临接矩阵的输入
         */
        int[][] matrix = new int[][] {
                {0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0},
                {0, 1, 0, 0, 1, 0},
                {1, 0, 0, 0, 1, 0},
                {0, 1, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 0},
        };
    }
}
