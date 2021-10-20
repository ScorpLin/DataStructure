package tree.traverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 后序遍历: 左 右 中
 */
public class Postorder {

    /**
     * postorder后序遍历：递归写法
     * @param root
     */
    public void postorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return;
        helper(root, res);
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
    }

    //====================================

    /**
     * postorder后序遍历：迭代写法
     */
    public void postorderIteration(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
            res.addFirst(cur.val);
        }
    }
}
