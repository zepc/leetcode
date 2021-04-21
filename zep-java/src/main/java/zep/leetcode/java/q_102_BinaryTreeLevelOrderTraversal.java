package zep.leetcode.java;

import java.util.ArrayList;
import java.util.List;
import zep.leetcode.java.ds.tree.TreeNode;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @author Zep
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/15 10:46
 */
public class q_102_BinaryTreeLevelOrderTraversal {

	public List<List<Integer>> levelOrder(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		List<List<Integer>> ans = new ArrayList<>();
		ArrayList<Integer> levels = new ArrayList<>();
		ArrayList<TreeNode> currs = new ArrayList<>();
		currs.add(0, root);
		levels.add(0, 1);
		int index = 0;
		while (currs.size() > index) {
			TreeNode node = currs.get(index);
			int level = levels.get(index);
			if (ans.size() < level) {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(node.val);
				ans.add(list);
			}
			else {
				ans.get(level - 1).add(node.val);
			}
			index++;
			if (node.left == null && node.right == null) {
				continue;
			}
			if (node.left != null) {
				currs.add(node.left);
				levels.add(level + 1);
			}
			if (node.right != null) {
				currs.add(node.right);
				levels.add(level + 1);
			}
		}
		return ans;
	}

	public List<List<Integer>> solution_02(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		ArrayList<List<Integer>> ans = new ArrayList<>();
		ArrayList<TreeNode> curr = new ArrayList<>();
		ArrayList<TreeNode> next = new ArrayList<>();
		ArrayList<TreeNode> temp;
		curr.add(root);
		while (!curr.isEmpty()) {
			ArrayList<Integer> values = new ArrayList<>();
			ans.add(values);
			for (TreeNode node : curr) {
				values.add(node.val);
				if (node.left != null) {
					next.add(node.left);
				}
				if (node.right != null) {
					next.add(node.right);
				}
			}
			temp = next;
			next = curr;
			curr = temp;
			next.clear();
		}
		return ans;
	}

	// DFS
	public List<List<Integer>> solution_03(TreeNode root) {
		ArrayList<List<Integer>> ans = new ArrayList<>();
		dfs(root, 1, ans);
		return ans;
	}

	void dfs(TreeNode root, int depth, List<List<Integer>> ans) {
		if (root == null) {
			return;
		}
		if (ans.size() < depth) {
			ArrayList<Integer> numbers = new ArrayList<>();
			numbers.add(root.val);
			ans.add(numbers);
		}
		else {
			ans.get(depth - 1).add(root.val);
		}
		if (root.left != null) {
			dfs(root.left, depth + 1, ans);
		}
		if (root.right != null) {
			dfs(root.right, depth + 1, ans);
		}
	}


	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		TreeNode l = new TreeNode(9);
		TreeNode r = new TreeNode(20);
		TreeNode rl = new TreeNode(15);
		TreeNode rr = new TreeNode(7);
		r.left = rl;
		r.right = rr;
		root.left = l;
		root.right = r;
		q_102_BinaryTreeLevelOrderTraversal test = new q_102_BinaryTreeLevelOrderTraversal();
		test.solution_03(root);
	}
}
