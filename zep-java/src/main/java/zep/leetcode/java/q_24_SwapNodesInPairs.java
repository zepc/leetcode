package zep.leetcode.java;

import zep.leetcode.java.ds.list.ListNode;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/15 23:57
 */
public class q_24_SwapNodesInPairs {

	public ListNode swapPairs(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return head;
		}
//		ListNode p = head;
//		ListNode temp = p.next;
//		p.next = swapPairs(p.next);
//		p.next = temp.next;
//		temp.next = p;
//		return temp;
		ListNode newHead = head.next;
		head.next = swapPairs(newHead.next);
		newHead.next = head;
		return newHead;
	}

	public ListNode solution_02(ListNode head) {
		if (head == null) {
			return null;
		}
		if (head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		head.next = swapPairs(newHead.next);
		newHead.next = head;
		return newHead;
	}


	public static void main(String[] args) {
		q_24_SwapNodesInPairs test = new q_24_SwapNodesInPairs();
		ListNode first = new ListNode(1);
		ListNode second = new ListNode(2);
		ListNode third = new ListNode(3);
		ListNode fourth = new ListNode(4);
		first.next = second;
		second.next = third;
		third.next = fourth;
		test.swapPairs(first);
	}


}
