package zep.leetcode.java;

import java.util.Arrays;
import zep.leetcode.java.ds.list.ListNode;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/15 16:31
 */
public class q_23_MergeKSortedLists {

	public ListNode mergeKLists(ListNode[] lists) {
		int len = lists.length;
		if (len == 0) {
			return null;
		}
		if (len == 1) {
			return lists[0];
		}
		int mid = len / 2;
		ListNode left = mergeKLists(Arrays.copyOfRange(lists, 0, mid));
		ListNode right = mergeKLists(Arrays.copyOfRange(lists, mid, len));
		return mergeTwoLists(left, right);
	}


	public ListNode solution_01(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		if (lists.length == 1) {
			return lists[0];
		}
		ListNode a = lists[lists.length - 1];
		ListNode b = lists[lists.length - 2];
		ListNode[] subList = new ListNode[lists.length - 1];
		for (int i = 0; i < subList.length - 1; i++) {
			subList[i] = lists[i];
		}
		subList[subList.length - 1] = mergeTwoLists(a, b);
		return solution_01(subList);
	}

	private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return null;
		}
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		ListNode p = l1;
		ListNode q = l2;
		while (p != null && q != null) {
			if (p.val <= q.val) {
				tail.next = p;
				p = p.next;
			}
			else {
				tail.next = q;
				q = q.next;
			}
			tail = tail.next;
		}
		if (q != null) {
			tail.next = q;
		}
		if (p != null) {
			tail.next = p;
		}
		return dummy.next;
	}
}