package tree.traverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历
 */
public class Inorder {

    /**
     * Inorder递归写法
     * @param root
     */
    public void inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return;
        helper(root, res);

        print(res);
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        /**
         * 在递归的写法中，与先序遍历唯一的不同在于：
         *   语句: res.add(root.val)的位置被移动到了中间
         */
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }

    public void print(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i) + " ");
        }
        System.out.println();
    }

    //===================================================

    /**
     * inorder中序遍历迭代写法
     * @param root
     */
    public void inorderIteration(TreeNode root) {
        if (root == null) return;
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                /**
                 * Inorder一开始就需要一次性走到底，所以通过不断的利用：cur = cur.left 到达最底部
                 */
                stack.push(cur);
                cur = cur.left; // 这样 3 就被放在了stack的最低端，所以所以是最后一个出来的
            }
            cur = stack.pop();
            res.add(cur.val);
            /**
             * 如果已经到了二叉树的底部，那么 cur = cur.right 会使得 cur = null
             * 从而，在新一轮循环中的的第二个while循环中，不满足 while 循环的条件，不会对stack进行操作
             */
            cur = cur.right;
        }
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode t1 = new TreeNode(9);
        TreeNode t2 = new TreeNode(8);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(1);
        TreeNode t6 = new TreeNode(7);
        TreeNode t7 = new TreeNode(2);
        TreeNode t8 = new TreeNode(10);

        root.left = t1;
        root.right = t2;
        t1.left = t3;
        t1.right = t4;
        t2.left = t5;
        t2.right = t6;
        t3.left = t7;
        t3.right = t8;

        Inorder inorder = new Inorder();
        inorder.inorder(root);
    }
}
