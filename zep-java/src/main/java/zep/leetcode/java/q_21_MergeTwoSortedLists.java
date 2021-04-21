package zep.leetcode.java;

import zep.leetcode.java.ds.list.ListNode;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/14 22:52
 */
public class q_21_MergeTwoSortedLists {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

	public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 != null ? l1 : l2;
		}
		if (l1.val < l2.val) {
			l1.next = mergeTwoListsRecursive(l1.next, l2);
			return l1;
		}
		else {
			l2.next = mergeTwoListsRecursive(l1, l2.next);
			return l2;
		}
	}

}
