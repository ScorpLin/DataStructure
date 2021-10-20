package Graph.prim;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    int vertices;

    /**
     * 相当于是一个临接表的方式去存储
     */
    List<Edge>[] list;

    public Graph(int vertices) {
        this.vertices = vertices;
        list = new ArrayList[vertices];
        /**
         * list是一个数组，启宗的每一个元素代表一个点，数组中存放的是与该点相连的边
         */
        for (int i = 0; i < vertices; i++) {
            list[i] = new ArrayList<>();
        }
    }

    public void addEdge(int start, int end, int weight) {
        /**
         * 由于是无向图，所以边要添加两次，start起点添加一次，end终点添加一次
         */
        Edge edge = new Edge(start, end, weight);
        list[start].add(edge);

        edge = new Edge(end, start, weight);
        list[end].add(edge);
    }
}
