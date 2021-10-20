package unionFind.impl;

import unionFind.IUnionFind;

public class QuickUnionImpl implements IUnionFind {

    /**
     * 代表了节点个数
     */
    private int count;

    /**
     * 代表了根节点
     */
    private int[] parents;

    public QuickUnionImpl(int count) {
        this.count = count;
        parents = new int[count];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
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
        parents[xRoot] = yRoot;
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
