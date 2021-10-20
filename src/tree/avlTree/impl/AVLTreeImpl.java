package tree.avlTree.impl;

import tree.avlTree.AVLNode;
import tree.avlTree.IAVLTree;

public class AVLTreeImpl implements IAVLTree {

    AVLNode root;

    int size;

    public AVLTreeImpl() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(int val) {
        root = add(root, val);
    }

    private AVLNode add(AVLNode node, int val) {
        if (node == null) {
            size++;
            return new AVLNode(val);
        }
        if (node.val == val) {
            throw new IllegalArgumentException("节点已存在");
        }
        if (node.val > val) {
            node.left = add(node.left, val);
        } else {
            node.right = add(node.right, val);
        }
        /**
         * 更新高度
         */
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        /**
         * balanceFactor是平衡因子，对于AVL树来说，在加入新的节点的时候需要判断高度
         */
        int balanceFactor = getBalanceFactor(node);

        /**
         * 以下的 if 语句用来判断是否是【LL】形式，即左边长
         */
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        /**
         * 判断是否是RR
         */
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        /**
         * 判断是否是LR
         */
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        /**
         * 判断是否是RL
         */
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode c = y.left;

        y.left = x;
        x.left = c;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    private AVLNode rightRotate(AVLNode x) {
        AVLNode y = x.left;
        AVLNode c = y.right;

        y.right = x;
        x.left = c;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    private int getBalanceFactor(AVLNode node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private int getHeight(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    /**
     * 判断是否是二叉查询树
     * @return
     */
    private boolean isBST(AVLNode node) {
        if (node == null) return true;
        return helper(node, null, null);
    }

    public boolean helper(AVLNode node, Integer min, Integer max) {
        if (node == null) return true;
        if (min != null && node.val <= min) return false;
        if (max != null && node.val >= max) return false;

        return helper(node.left, min, node.val) && helper(node.right, node.val, max);
    }

    /**
     * 判断是否是AVL树
     * @param node
     * @return
     */
    private boolean isBalanced(AVLNode node) {
        if (node == null) return true;

        return helper(root) == -1;
    }

    public int helper(AVLNode node) {
        if (root == null) return 0;
        int l = helper(root.left);
        int r = helper(root.right);
        /**
         * 在下面的 if 语句加入 l == -1 和 r == -1 的原因是：
         *   一旦出现-1说明就不是AVL树，所以可以直接返回-1
         */
        if (l == -1 || r == -1 || Math.abs(l - r) > 1) {
            return -1;
        }
        return Math.max(l, r) + 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(int val) {
        return false;
    }

    @Override
    public void remove(int val) {
        root = remove(root, val);
    }

    private AVLNode remove(AVLNode node, int val) {
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
            AVLNode minNode = findMin(node.right);
            node.val = minNode.val;

            node.right = remove(node.right, node.val);
        }
        return node;
    }

    public AVLNode findMin(AVLNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
