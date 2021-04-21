package zep.leetcode.java.ds.tree;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/7 0007
 * @description leetcode
 */
public class TreeNode {

	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}


	public TreeNode() {
	}

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}