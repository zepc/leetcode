package zep.leetcode.java;

import java.util.LinkedList;
import java.util.List;
import zep.leetcode.java.ds.tree.TreeNode;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/17 14:12
 */
public class q_95_UniqueBST2 {

	public LinkedList<TreeNode> generate_trees(int start, int end) {
		LinkedList<TreeNode> trees = new LinkedList<>();
		if (start > end) {
			trees.add(null);
			return trees;
		}
		for (int i = start; i <= end; i++) {
			LinkedList<TreeNode> leftTrees = generate_trees(start, i - 1);
			LinkedList<TreeNode> rightTrees = generate_trees(i + 1, end);
			for (TreeNode left : leftTrees) {
				for (TreeNode right : rightTrees) {
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					trees.add(root);
				}
			}
		}
		return trees;
	}

	public List<TreeNode> generateTrees(int n) {
		if (n == 0) {
			return new LinkedList<>();
		}
		return generate_trees(1, n);
	}

	// 1. 生成结果树，怎么返回/存在哪里
	// 2. 怎么避免循环引用，如何正确的处理索引
	// 理解递归
	public TreeNode generate(TreeNode root, int start, int[] numbers) {
//		for (int i = start; i < numbers.length; i++) {
//			if (root == null) break;
//			if (numbers[i]  > root.val)
//				root.right = generate(new TreeNode(numbers[i]), i, numbers);
//			if (numbers[i] < root.val)
//				root.left = generate(new TreeNode(numbers[i]), i, numbers); // 如果start为2，则递归的时候直接退出了，不会再遍历到2
//		}

		return root;
	}

	public static void main(String[] args) {
		q_95_UniqueBST2 test = new q_95_UniqueBST2();
		test.generateTrees(3);
	}
}
