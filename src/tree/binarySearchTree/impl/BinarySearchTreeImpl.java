package tree.binarySearchTree.impl;

import tree.binarySearchTree.IBinarySearchTree;
import tree.binarySearchTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeImpl implements IBinarySearchTree {

    private TreeNode root;

    private int size;

    public BinarySearchTreeImpl() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(int val) {
        root = add(root, val);
    }

    private TreeNode add(TreeNode node, int val) {
        if (node == null) {
            this.size++;
            return new TreeNode(val);
        }
        /**
         * 考虑如果有重复的情况
         */
        if (node.val == val) {
            throw new IllegalArgumentException("节点已存在");
        }
        /**
         * 判断添加的大小，从而决定是放在左边还是右边
         */
        if (node.val > val) {
            node.left = add(node.left, val);
        } else {
            node.right = add(node.right, val);
        }
        return node;
    }

    /**
     * 如果删除的是一个根节点：将右子树的最小值移动到所删除的位置
     * @param val
     */
    @Override
    public void remove(int val) {
        root = remove(root, val);
    }

    /**
     *
     * @param node 父节点
     * @param val 想要删除的节点的值
     * @return 删除的节点
     */
    private TreeNode remove(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        if (node.val > val) {
            node.left = remove(node.left, val);
        } else if (node.val < val) {
            node.right = remove(node.right, val);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            }
            /**
             * 如果删除的点是根节点，那么需要寻找右子树的最小值。通过调用findMin()来完成
             */
            TreeNode minNode = findMin(node.right);
            /**
             * 更新所要删除的位置的点的值
             */
            node.val = minNode.val;
            /**
             * 更新所要删除的位置的点的右子节点，为了满足搜索二叉树，需要找到最小的值，所以通过remove方法来调用
             */
            node.right = remove(node.right, node.val);
        }
        /**
         * 最终返回的是【新的点】
         */
        return node;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Time: O(logn)
     * @param val
     * @return
     */
    @Override
    public boolean contains(int val) {
        /**
         * 直接使用先序遍历来遍历判断
         */
        return contains(root, val);
    }

    private boolean contains(TreeNode node, int val) {
        if (node == null) return false;
        if (node.val == val) return true;
        /**
         * 由于是二叉查找树（按大小排列），可以每次只查询一遍
         */
        if (node.val > val) {
            return contains(node.left, val);
        } else {
            return contains(node.right, val);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void print() {
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        /**
         * 初始化while循环中所需要用到的参数
         */
        int size;
        TreeNode cur;
        while (!queue.isEmpty()) {
            size = queue.size();
            System.out.println("第" + (level + 1) + "层：");
            for (int i = 0; i < size; i++) {
                cur = queue.poll();
                System.out.print(cur.val);
                if (cur.left != null) {
                    System.out.println(" 的左孩子: " + cur.left.val);
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    System.out.println(" 的右孩子: " + cur.right.val);
                    queue.offer(cur.right);
                }
                System.out.println();
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        BinarySearchTreeImpl binarySearchTree = new BinarySearchTreeImpl();
        binarySearchTree.add(8);
        binarySearchTree.add(5);
        binarySearchTree.add(6);
        binarySearchTree.add(3);
        binarySearchTree.add(10);
        binarySearchTree.add(9);
        binarySearchTree.add(11);

        binarySearchTree.print(); // 测试添加功能

        System.out.println(binarySearchTree.contains(6)); // 测试contains功能
    }
}
