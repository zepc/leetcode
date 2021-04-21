package zep.leetcode.java;


import zep.leetcode.java.ds.tree.TreeNode;

/**
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * <p>
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * <p>
 * 5 / \ 4   5 / \   \ 1   1   5 输出:
 * <p>
 * 2
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * <p>
 * 1 / \ 4   5 / \   \ 4   4   5 输出:
 * <p>
 * 2 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 */

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/14 0014
 * @description leetcode
 */
public class q_687_LengthOfLogPathWithCommonNode {

	int ans = 0;

	public int longestUnivaluePath(TreeNode root) {

		arrowLength(root);
		return ans;
	}

	private int selfSolution(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = longestUnivaluePath(root.left);
		if (root.left != null && root.val == root.left.val) {
			left++;
		}
		int right = longestUnivaluePath(root.right);
		if (root.right != null && root.val == root.right.val) {
			right++;
		}
		if (root.right != null && root.left != null && root.val == root.right.val && root.val == root.left.val) {
			return right + left;
		}
		else {
			return Math.max(right, left);
		}
	}

	/**
	 * 将任何路径（具有相同值的节点）看作是最多两个从其根延伸出的箭头，
	 * 路径的根将是唯一节点，因此该节点的父节点不会出现在该路径中，而箭头将是根在该路径中只有一个子节点的路径。
	 *
	 * 自己的解答，也将根结点看成是路径了
	 *      知识点：使用全局变量保存结果
	 * @param node
	 * @return
	 */
	private int arrowLength(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = arrowLength(node.left);
		int right = arrowLength(node.right);

		int arrowLeft = 0, arrowRight = 0;
		if (node.left != null && node.left.val == node.val) {
			arrowLeft += left + 1;
		}
		if (node.right != null && node.right.val == node.val) {
			arrowRight += right + 1;
		}
		ans = Math.max(ans, arrowLeft + arrowRight);
		return Math.max(arrowLeft, arrowRight);
	}
}
