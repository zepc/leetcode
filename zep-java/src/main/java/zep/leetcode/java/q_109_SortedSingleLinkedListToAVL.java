package zep.leetcode.java;

import zep.leetcode.java.ds.list.ListNode;
import zep.leetcode.java.ds.tree.TreeNode;

/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * <p>
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0 / \ -3   9 /   / -10  5
 */

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/13 0013
 * @description leetcode
 */
public class q_109_SortedSingleLinkedListToAVL {

	public TreeNode sortedListToBST(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode tail = head;

		while (tail.next != null) {
			tail = tail.next;
		}

		return buildFromSortedLink(head, tail);
	}

	private TreeNode buildFromSortedLink(ListNode head, ListNode tail) {
		// 递归出口
		if (head == tail) {
			return new TreeNode(head.val);
		}

		ListNode mid = head;
		ListNode newTail = head;
		ListNode preMid = head;
		int tag = 0;
		while (newTail != tail) {
			newTail = newTail.next;
			if (tag % 2 == 1) {
				if (tag > 1) {
					preMid = preMid.next;
				}
				mid = mid.next;
			}
			tag++;
		}
		TreeNode left = null;
		if (head != mid) {
			left = buildFromSortedLink(head, preMid);
		}
		TreeNode middle = new TreeNode(mid.val);

		if (left != null) {
			middle.left = left;
		}

		TreeNode right = buildFromSortedLink(mid.next, newTail);

		if (right != null) {
			middle.right = right;
		}

		return middle;
	}
}
