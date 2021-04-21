package zep.leetcode.java;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import zep.leetcode.java.ds.tree.TreeNode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3 / \ 9  20 / \ 15  7 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * 1 / \ 2   2 / \ 3   3 / \ 4   4 返回 false 。
 */

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/13 0013
 * @description 判断一个二叉树是否是平衡二叉树
 */
public class q_110_HeightIsBalanced {

	// [3,9,20,null,null,15,7]

	private final Queue<TreeNode> queue = new LinkedList<>();
	private final Map<TreeNode, Integer> heights = new HashMap<>();

	public boolean isBalanced(TreeNode root) {
		lrd(root);
		return checkBalance(root);
	}

	private boolean checkBalance(TreeNode root) {
		while (!queue.isEmpty()) {
			TreeNode q = queue.poll();
			if (q == null) {
				continue;
			}
			if (q.left == null && q.right == null) {
				heights.put(q, 1);
			}
			else {
				int qHeight = 0;
				if (q.left == null) {
					qHeight = heights.get(q.right) + 1;
				}
				else if (q.right == null) {
					qHeight = heights.get(q.left) + 1;
				}
				else {
					qHeight = Math.max(heights.get(q.right), heights.get(q.left)) + 1;
				}
				heights.put(q, qHeight);
				if (Math.abs(heights.getOrDefault(q.right, 0) - heights.getOrDefault(q.left, 0)) > 1) {
					return false;
				}
			}
		}
		return true;
	}

	private void lrd(TreeNode node) {
		if (node != null) {
			lrd(node.left);
			lrd(node.right);
			queue.offer(node);
		}
	}

	// 方法二

	private int depth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = depth(root.left);
		if (left == -1) {
			return -1;
		}
		int right = depth(root.right);
		if (right == -1) {
			return -1;
		}
		return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
	}
}
