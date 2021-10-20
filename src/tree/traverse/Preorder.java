package tree.traverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 先序遍历
 */
public class Preorder {

    /**
     * 递归写法
     * @param root
     */
    public void preorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return;
        }
        helper(res, root);
    }

    public void helper(List<Integer> list, TreeNode root) {
        if (root == null) return;
        list.add(root.val);
        helper(list, root.left);
        helper(list, root.right);
    }

    //==============================================

    /**
     * 迭代写法
     */
    public void preorderIteration(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            /**
             * 注意：先序遍历，right要写在前面，left写在后面
             * 原因：由于stack的先进后出的特性
             */
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
            res.add(cur.val);
        }
    }

}
