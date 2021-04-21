package zep.leetcode.java.ds.tree.bst;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/7 0007
 * @description leetcode
 */
public class BSTMap<K, V> implements Iterable<BSTEntry<K, V>> {

	private int size;
	private BSTEntry<K, V> root;
	private Comparator<K> cmp;

	@SuppressWarnings("unchecked")
	private int compare(K a, K b) {
		if (cmp != null) {
			return cmp.compare(a, b);
		}
		else {
			Comparable<K> c = ((Comparable<K>) a);
			return c.compareTo(b);
		}
	}

	public BSTMap(Comparator<K> cmp) {
		this.cmp = cmp;
	}

	public BSTMap() {
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public V put(K key, V value) {
		if (root == null) {
			root = new BSTEntry<>(key, value);
			size++;
		}
		else {
			BSTEntry<K, V> p = this.root;
			while (p != null) {
				int result = compare(key, p.key);
				if (result == 0) {
					p.value = value;
					break;
				}
				else if (result < 0) {
					if (p.left == null) {
						p.left = new BSTEntry<>(key, value);
						size++;
						break;
					}
					else {
						p = p.left;
					}
				}
				else {
					if (p.right == null) {
						p.right = new BSTEntry<>(key, value);
						size++;
						break;
					}
					else {
						p = p.right;
					}
				}
			}
		}
		return value;
	}

	@Override
	public Iterator<BSTEntry<K, V>> iterator() {
		return new BSTIterator<>(root);
	}

	private BSTEntry<K, V> getEntry(K key) {
		BSTEntry<K, V> p = this.root;
		while (p != null) {
			int compareResult = compare(key, p.key);
			if (compareResult == 0) {
				return p;
			}
			else if (compareResult < 0) {
				p = p.left;
			}
			else {
				p = p.right;
			}
		}
		return null;
	}

	public boolean containsKey(K key) {
		BSTEntry<K, V> p = getEntry(key);
		return p != null;
	}

	public boolean containsValue(V value) {
		Iterator<BSTEntry<K, V>> itr = this.iterator();
		while (itr.hasNext()) {
			if (itr.next().getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	public V getValue(K key) {
		BSTEntry<K, V> p = getEntry(key);
		return p != null ? p.getValue() : null;
	}

	/**
	 * 最小节点
	 *
	 * @param p
	 * @return
	 */
	public BSTEntry<K, V> getFirstEntry(BSTEntry<K, V> p) {
		if (p == null) {
			return null;
		}
		while (p.left != null) {
			p = p.left;
		}
		return p;
	}

	/**
	 * 最大节点
	 *
	 * @param p
	 * @return
	 */
	public BSTEntry<K, V> getLastEntry(BSTEntry<K, V> p) {
		if (p == null) {
			return null;
		}
		while (p.right != null) {
			p = p.right;
		}
		return p;
	}

	private BSTEntry<K, V> deleteEntry(BSTEntry<K, V> p, K key) {
		if (p == null) {
			return null;
		}
		int compareResult = compare(key, p.key);
		if (compareResult == 0) {
			if (p.left == null && p.right == null) {
				p = null;
			}
			else if (p.left != null && p.right == null) {
				p = p.left;
			}
			else if (p.left == null && p.right != null) {
				p = p.right;
			}
			else {
				// size为偶数的话，size&1=0
				// size为奇数的话，size&1=1
				// 为什么这样写？能减少树的深度，避免树倾斜/过深
				if ((size & 1) == 0) {
					BSTEntry<K, V> rightMin = getFirstEntry(p.right);
					p.key = rightMin.key;
					p.value = rightMin.value;
					BSTEntry<K, V> newRight = deleteEntry(p.right, p.key);
					p.right = newRight;
				}
				else {
					BSTEntry<K, V> leftMax = getLastEntry(p.left);
					p.key = leftMax.key;
					p.value = leftMax.value;
					BSTEntry<K, V> newLeft = deleteEntry(p.left, p.key);
					p.left = newLeft;
				}
			}

		}
		else if (compareResult > 0) {
			BSTEntry<K, V> newRight = deleteEntry(p.right, key);
			p.right = newRight;
		}
		else {
			BSTEntry<K, V> newLeft = deleteEntry(p.left, key);
			p.left = newLeft;
		}
		return p;
	}

	public V remove(K key) {
		BSTEntry<K, V> entry = getEntry(key);
		if (entry == null) {
			return null;
		}
		V oldValue = entry.getValue();
		// root 需要重新赋值，可能查找出的节点就是root本身，此时root已经被删除了，不能再根据root访问树了，树丢了，所以需要重新赋值
		root = deleteEntry(root, entry.getKey());
		size--;
		return oldValue;
	}

	public void levelOrder() {
		Queue<BSTEntry<K, V>> queue = new LinkedList<>();
		queue.offer(root);
		// 利用preCount和pCount可以实现换行输出，这是一个换行输出的模板
		int preCount = 1;
		int pCount = 1;
		while (!queue.isEmpty()) {
			preCount--;
			BSTEntry<K, V> p = queue.poll();
			System.out.print(p.value + " ");
			if (p.left != null) {
				queue.offer(p.left);
				pCount++;
			}
			if (p.right != null) {
				queue.offer(p.right);
				pCount++;
			}
			if (preCount == 0) {
				preCount = pCount;
				System.out.println();
			}
		}
	}
}
