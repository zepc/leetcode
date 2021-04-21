package zep.leetcode.java;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import zep.leetcode.java.ds.tree.TreeNode;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0 / \ -3   9 /   / -10  5
 */

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/13 0013
 * @description 将有序数组转化为BST，但是由于题目要求
 * 一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 所以其实是转化为AVL树
 */
public class q_108_SortedArrayToAVL {

	public TreeNode sortedArrayToBST(int[] nums) {
		return solution_02(nums);
	}

	public TreeNode solution_01(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		LeetCodeAVL avl = new LeetCodeAVL();
		for (int num : nums) {
			avl.put(num);
		}
		return avl.root;
	}

	class LeetCodeAVL {

		private int size;
		private TreeNode root;
		private final LinkedList<TreeNode> stack = new LinkedList<>();
		private final Map<TreeNode, Integer> heightMap = new HashMap<>();

		public void put(int key) {
			if (root == null) {
				root = new TreeNode(key);
				stack.push(root);
				size++;
			}
			else {
				TreeNode p = this.root;
				while (p != null) {
					stack.push(p);
					int result = key - p.val;
					if (result == 0) {
						break;
					}
					else if (result < 0) {
						if (p.left == null) {
							p.left = new TreeNode(key);
							size++;
							stack.push(p.left);
							break;
						}
						else {
							p = p.left;
						}
					}
					else {
						if (p.right == null) {
							p.right = new TreeNode(key);
							size++;
							stack.push(p.right);
							break;
						}
						else {
							p = p.right;
						}
					}
				}
			}
			fixAfterInsertion(key);
		}

		public int getHeight(TreeNode p) {
			return heightMap.getOrDefault(p, 0);
		}

		private TreeNode rotateRight(TreeNode p) {
			TreeNode l = p.left;
			p.left = l.right;
			l.right = p;
			int pHeight = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
			int lHeight = Math.max(getHeight(l.left), pHeight) + 1;
			heightMap.put(p, pHeight);
			heightMap.put(l, lHeight);
			return l;
		}

		private TreeNode rotateLeft(TreeNode p) {
			TreeNode r = p.right;
			p.right = r.left;
			r.left = p;
			int pHeight = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
			int rHeight = Math.max(getHeight(r.right), pHeight) + 1;
			heightMap.put(p, pHeight);
			heightMap.put(r, rHeight);
			return r;
		}

		private TreeNode firstLeftThenRight(TreeNode p) {
			p.left = rotateLeft(p.left);
			p = rotateRight(p);
			return p;
		}

		private TreeNode firstRightThenLeft(TreeNode p) {
			p.right = rotateRight(p.right);
			p = rotateLeft(p);
			return p;
		}

		private void fixAfterInsertion(int key) {
			TreeNode p = root;
			while (!stack.isEmpty()) {
				p = stack.pop();
				int newHeight = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
				int pHeight = getHeight(p);
				if (pHeight > 1 && newHeight == pHeight) {
					stack.clear();
					return;
				}
				heightMap.put(p, newHeight);
				int fac = getHeight(p.left) - getHeight(p.right);
				if (Math.abs(fac) <= 1) {
					continue;
				}
				else {
					if (fac == 2) {
						if (key < p.left.val) {
							p = rotateRight(p);
						}
						else {
							p = firstLeftThenRight(p);
						}
					}
					else {
						if (key > p.right.val) {
							p = rotateLeft(p);
						}
						else {
							p = firstRightThenLeft(p);
						}
					}

					if (!stack.isEmpty()) {
						if (key < stack.peek().val) {
							stack.peek().left = p;
						}
						else {
							stack.peek().right = p;
						}
					}
				}
			}
			root = p;
		}
	}


	public TreeNode solution_02(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		return buildFromSorted(0, nums.length - 1, nums);
	}

	/**
	 * 通过数组有序这个特质，利用二分法来做。
	 * 有序 -> 二分
	 */
	private TreeNode buildFromSorted(int lo, int hi, int[] nums) {
		if (hi < lo) {
			return null;
		}
		int mid = (lo + hi) / 2;

		TreeNode left = null;
		if (lo < mid) {
			left = buildFromSorted(lo, mid - 1, nums);
		}

		TreeNode middle = new TreeNode(nums[mid]);
		if (left != null) {
			middle.left = left;
		}
		if (mid < hi) {
			TreeNode right = buildFromSorted(mid + 1, hi, nums);
			middle.right = right;
		}
		return middle;
	}
}
