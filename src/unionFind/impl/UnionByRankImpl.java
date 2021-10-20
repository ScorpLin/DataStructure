package unionFind.impl;

import unionFind.IUnionFind;

public class UnionByRankImpl implements IUnionFind {

    /**
     * 代表了节点个数
     */
    private int count;

    /**
     * 代表了
     */
    private int[] parents;

    /**
     * 权重
     */
    private int[] rank;

    public UnionByRankImpl(int count) {
        this.count = count;
        parents = new int[count];
        this.rank = new int[count];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            rank[i] = 1; // 由于初始化的时候，每一个点都是根节点，所以都赋值为1
        }
    }

    @Override
    public int count() {
        return count;
    }

    /**
     * Time: O(h)
     * @param x
     * @param y
     */
    @Override
    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            return;
        }

        if (rank[xRoot] < rank[yRoot]) {
            /**
             * 当以xRoot为根节点的高度小于以yRoot为根节点的高度时，将xRoot加入到yRoot中，因为这样yRoot的高度不会改变
             */
            parents[xRoot] = yRoot;
        } else if (rank[yRoot] < rank[xRoot]) {
            parents[yRoot] = xRoot;
        } else {
            parents[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    /**
     * Time: O(h)
     * @param index
     * @return
     */
    @Override
    public int find(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("越界");
        }
        /**
         * 如果是根节点的话，index =  parents[index]，进入while循环的逻辑条件是【不是根节点】
         */
        while (index != parents[index]) {
            index = parents[index];
        }

        return index;
    }

    @Override
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
