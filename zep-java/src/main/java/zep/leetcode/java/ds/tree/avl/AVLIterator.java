package zep.leetcode.java.ds.tree.avl;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/12 0012
 * @description leetcode
 */
public class AVLIterator<K, V> implements Iterator<AVLEntry<K, V>> {

	private final Stack<AVLEntry<K, V>> stack;

	public AVLIterator(AVLEntry<K, V> root) {
		stack = new Stack<>();
		addLefPath(root);
	}

	/**
	 * 将左子树压入到栈中
	 *
	 * @param p
	 */
	private void addLefPath(AVLEntry<K, V> p) {
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
	public AVLEntry<K, V> next() {
		AVLEntry<K, V> p = stack.pop();
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
