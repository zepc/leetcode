package zep.leetcode.java;

import java.util.ArrayList;
import java.util.List;
import zep.leetcode.java.ds.tree.TreeNode;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/15 15:44
 */
public class q_637_AverageOfLevelsInBinaryTree {

	public List<Double> averageOfLevels(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		ArrayList<Double> ans = new ArrayList<>();
		ArrayList<TreeNode> curr = new ArrayList<>();
		ArrayList<TreeNode> next = new ArrayList<>();
		ArrayList<TreeNode> temp;
		curr.add(root);
		while (!curr.isEmpty()) {
			double sum = 0;
			for (TreeNode node : curr) {
				sum += node.val;
				if (node.left != null) {
					next.add(node.left);
				}
				if (node.right != null) {
					next.add(node.right);
				}
			}
			ans.add(sum / curr.size());
			temp = next;
			next = curr;
			curr = temp;
			next.clear();
		}
		return ans;
	}

	public List<Double> solution_02(TreeNode root) {

		ArrayList<Integer> sums = new ArrayList<>();
		ArrayList<Double> counts = new ArrayList<>();
		dfs(root, 1, counts, sums);
		List<Double> averages = new ArrayList<Double>();
		int size = sums.size();
		for (int i = 0; i < size; i++) {
			averages.add(sums.get(i) / counts.get(i));
		}
		return averages;
	}

	void dfs(TreeNode root, int depth, List<Double> counts, List<Integer> ans) {
		if (root == null) {
			return;
		}
		if (ans.size() < depth) {
			ans.add(root.val);
			counts.add(1.0);
		}
		else {
			ans.set(depth - 1, ans.get(depth - 1) + root.val);
			counts.set(depth - 1, counts.get(depth - 1) + 1);
		}
		if (root.left != null) {
			dfs(root.left, depth + 1, counts, ans);
		}
		if (root.right != null) {
			dfs(root.right, depth + 1, counts, ans);
		}
	}


}
