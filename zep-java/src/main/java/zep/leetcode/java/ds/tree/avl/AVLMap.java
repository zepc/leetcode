package zep.leetcode.java.ds.tree.avl;


import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/12 0012
 * @description leetcode
 */
public class AVLMap<K, V> implements Iterable<AVLEntry<K, V>> {

	private int size;
	private AVLEntry<K, V> root;
	// 当多个线程访问的时候，需要注意线程安全的问题
	private final LinkedList<AVLEntry<K, V>> stack = new LinkedList<>();
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

	public AVLMap(Comparator<K> cmp) {
		this.cmp = cmp;
	}

	public AVLMap() {
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public V put(K key, V value) {
		if (root == null) {
			root = new AVLEntry<>(key, value);
//            System.out.println("root = " + root);
			stack.push(root);
			size++;
		}
		else {
			AVLEntry<K, V> p = this.root;
			while (p != null) {
				stack.push(p);
				int result = compare(key, p.key);
				if (result == 0) {
					p.value = value;
					break;
				}
				else if (result < 0) {
					if (p.left == null) {
						p.left = new AVLEntry<>(key, value);
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
						p.right = new AVLEntry<>(key, value);
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
		return value;
	}

	public int getHeight(AVLEntry<K, V> p) {
		return p == null ? 0 : p.height;
	}

	/**
	 * 右旋
	 *
	 * @param p
	 * @return
	 */
	private AVLEntry<K, V> rotateRight(AVLEntry<K, V> p) {
		AVLEntry<K, V> l = p.left;
		p.left = l.right;
		l.right = p;
		p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
		l.height = Math.max(getHeight(l.left), p.height) + 1;
		return l;
	}

	/**
	 * 左旋
	 *
	 * @param p
	 * @return
	 */
	private AVLEntry<K, V> rotateLeft(AVLEntry<K, V> p) {
//        System.out.println("p = " + p);
		AVLEntry<K, V> r = p.right;
		p.right = r.left;
		r.left = p;
		p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
		r.height = Math.max(getHeight(r.right), p.height) + 1;
		return r;
	}

	/**
	 * 先左旋，后右旋
	 *
	 * @param p
	 * @return
	 */
	private AVLEntry<K, V> firstLeftThenRight(AVLEntry<K, V> p) {
		p.left = rotateLeft(p.left);
		p = rotateRight(p);
		return p;
	}

	/**
	 * 先右旋，后左旋
	 *
	 * @param p
	 * @return
	 */
	private AVLEntry<K, V> firstRightThenLeft(AVLEntry<K, V> p) {
		p.right = rotateRight(p.right);
		p = rotateLeft(p);
		return p;
	}

	/**
	 * 调整AVL树
	 *
	 * @param key
	 */
	private void fixAfterInsertion(K key) {
		AVLEntry<K, V> p = root;
		while (!stack.isEmpty()) {
			// 不断弹栈的过程就是回溯的过程
			p = stack.pop();
			// 优化
			int newHeight = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
			// p的高度未发生变化，清空栈，直接返回
			// p.height > 1 排除叶子节点
			if (p.height > 1 && newHeight == p.height) {
				stack.clear();
				return;
			}
			p.height = newHeight;
//            p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
			int fac = getHeight(p.left) - getHeight(p.right);
			if (Math.abs(fac) <= 1) {
				continue;
			}
			else {
				// 插入到了左子树
				if (fac == 2) {
					// 左
					if (compare(key, p.left.key) < 0) {
						p = rotateRight(p);
					}
					else {
						p = firstLeftThenRight(p);
					}
				}
				else {
					// 左
					if (compare(key, p.right.key) > 0) {
						p = rotateLeft(p);
					}
					else {
						p = firstRightThenLeft(p);
					}
				}

				if (!stack.isEmpty()) {
					if (compare(key, stack.peek().key) < 0) {
						stack.peek().left = p;
					}
					else {
						stack.peek().right = p;
					}
				}
			}
		}
		// 更改根结点
		root = p;
	}

	public void checkBalance() {
		postOrderCheckBalance(root);
	}

	private void postOrderCheckBalance(AVLEntry<K, V> p) {
		if (p != null) {
			postOrderCheckBalance(p.left);
			postOrderCheckBalance(p.right);
//            Assert.assertTrue(Math.abs(getHeight(p.left) - getHeight(p.right)) <= 1);
		}
	}

	private AVLEntry<K, V> getEntry(K key) {
		AVLEntry<K, V> p = this.root;
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
		AVLEntry<K, V> p = getEntry(key);
		return p != null;
	}

	public boolean containsValue(V value) {
		Iterator<AVLEntry<K, V>> itr = this.iterator();
		while (itr.hasNext()) {
			if (itr.next().getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	public V getValue(K key) {
		AVLEntry<K, V> p = getEntry(key);
		return p != null ? p.getValue() : null;
	}

	/**
	 * 最小节点
	 *
	 * @param p
	 * @return
	 */
	public AVLEntry<K, V> getFirstEntry(AVLEntry<K, V> p) {
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
	public AVLEntry<K, V> getLastEntry(AVLEntry<K, V> p) {
		if (p == null) {
			return null;
		}
		while (p.right != null) {
			p = p.right;
		}
		return p;
	}

	private AVLEntry<K, V> deleteEntry(AVLEntry<K, V> p, K key) {
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
					AVLEntry<K, V> rightMin = getFirstEntry(p.right);
					p.key = rightMin.key;
					p.value = rightMin.value;
					AVLEntry<K, V> newRight = deleteEntry(p.right, p.key);
					p.right = newRight;
				}
				else {
					AVLEntry<K, V> leftMax = getLastEntry(p.left);
					p.key = leftMax.key;
					p.value = leftMax.value;
					AVLEntry<K, V> newLeft = deleteEntry(p.left, p.key);
					p.left = newLeft;
				}
			}

		}
		else if (compareResult > 0) {
			AVLEntry<K, V> newRight = deleteEntry(p.right, key);
			p.right = newRight;
		}
		else {
			AVLEntry<K, V> newLeft = deleteEntry(p.left, key);
			p.left = newLeft;
		}
		p = fixAfterDeletion(p);
		return p;
	}

	private AVLEntry<K, V> fixAfterDeletion(AVLEntry<K, V> p) {
		if (p == null) {
			return null;
		}
		else {
			p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
			int fac = getHeight(p.left) - getHeight(p.right);
			if (fac == 2) {
				if (getHeight(p.left.left) - getHeight(p.left.right) > 0) {
					p = rotateRight(p);
				}
				else {
					p = firstLeftThenRight(p);
				}
			}
			else if (fac == -2) {
				if (getHeight(p.right.right) - getHeight(p.right.left) > 0) {
					p = rotateLeft(p);
				}
				else {
					p = firstRightThenLeft(p);
				}
			}
		}
		return p;
	}

	public V remove(K key) {
		AVLEntry<K, V> entry = getEntry(key);
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
		Queue<AVLEntry<K, V>> queue = new LinkedList<>();
		queue.offer(root);
		// 利用preCount和pCount可以实现换行输出，这是一个换行输出的模板
		int preCount = 1;
		int pCount = 1;
		while (!queue.isEmpty()) {
			preCount--;
			AVLEntry<K, V> p = queue.poll();
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

	@Override
	public Iterator<AVLEntry<K, V>> iterator() {
		return new AVLIterator<>(root);
	}

}
