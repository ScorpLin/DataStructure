package unionFind.impl;

import unionFind.IUnionFind;


public class PathCompressionImpl implements IUnionFind {

    /**
     * 代表了节点个数
     */
    private int count;

    /**
     * 代表了根节点
     */
    private int[] parents;

    /**
     * 权重
     */
    private int[] size;

    public PathCompressionImpl(int count) {
        this.count = count;
        this.parents = new int[count];
        this.size = new int[count];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
            size[i] = 1; // 由于初始化的时候，每一个点都是根节点，所以都赋值为1
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
        /**
         * 在这里进行权重的比较union：
         *   parents中存放的是根节点
         */
        if (size[xRoot] < size[yRoot]) {
            parents[xRoot] = yRoot;
            size[yRoot] += size[xRoot];
        } else {
            parents[yRoot] = xRoot;
            size[xRoot] += size[yRoot];
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
            /**
             * 路径压缩，每个路径都减少1
             */
            parents[index] = parents[parents[index]];
            index = parents[index];
        }

        return index;
    }

    public int find2(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("越界");
        }
        /**
         * 直接将路径压缩到根节点
         */
        if (index != parents[index]) {
            parents[index] = find(parents[index]);
        }

        return parents[index];
    }

    @Override
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
