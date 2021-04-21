package zep.leetcode.java;


import java.util.LinkedList;
import java.util.Queue;
import zep.leetcode.java.ds.tree.TreeNode;

/**
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 4 /   \ 2     7 / \   / \ 1   3 6   9
 * <p>
 * <p>
 * 输出：
 * <p>
 * 4 /   \ 7     2 / \   / \ 9   6 3   1
 */

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/7 0007
 * @description 翻转二叉树
 */
public class q_226_RollingOverBinaryTree {

	public TreeNode invertTree(TreeNode root) {
		if (root != null) {
			// 先序遍历
			TreeNode tmp = root.left;
			root.left = root.right;
			root.right = tmp;

			invertTree(root.left);
			invertTree(root.right);
			return root;
		}
		else {
			return null;
		}
	}

	/**
	 * 层序遍历
	 * @param root
	 * @return
	 */
	public TreeNode invertTreeBfs(TreeNode root) {
		if (root != null) {
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);
			while (!queue.isEmpty()) {
				TreeNode node = queue.poll();
				TreeNode tmp = node.left;
				node.left = node.right;
				node.right = tmp;
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			return root;
		}
		else {
			return null;
		}
	}
}
