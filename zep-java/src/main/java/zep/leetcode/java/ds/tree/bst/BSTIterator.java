package zep.leetcode.java.ds.tree.bst;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Copyright (c) 2021-2200 Zep All Rights Reserved
 *
 * @Project: zep-leetcode
 * @Package: zep.leetcode.java.ds.tree.bst
 * @Version: 1.0
 * @Description： Created by Zep on 2021/04/17 16:57
 */
public class BSTIterator<K, V> implements Iterator<BSTEntry<K, V>> {


	private final Stack<BSTEntry<K, V>> stack;

	public BSTIterator(BSTEntry<K, V> root) {
		stack = new Stack<>();
		addLefPath(root);
	}

	/**
	 * 将左子树压入到栈中
	 *
	 * @param p
	 */
	private void addLefPath(BSTEntry<K, V> p) {
		while (p != null) {
			stack.push(p);
			p = p.left;
		}
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public BSTEntry<K, V> next() {
		BSTEntry<K, V> p = stack.pop();
		addLefPath(p.right);
		return p;
	}

	/**
	 * 删除操作跟线程安全相关 modCount
	 */
	@Override
	public void remove() {
		throw new ConcurrentModificationException("Can not remove!");
	}

}