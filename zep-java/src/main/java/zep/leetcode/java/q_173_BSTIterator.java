package zep.leetcode.java;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import zep.leetcode.java.ds.tree.TreeNode;
import zep.leetcode.java.ds.tree.bst.BSTEntry;

/**
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * <p>
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 7 /   \ 3     15 / \ 9   20
 * <p>
 * <p>
 * <p>
 * BSTIterator iterator = new BSTIterator(root); iterator.next();    // 返回 3 iterator.next();    //
 * 返回 7 iterator.hasNext(); // 返回 true iterator.next();    // 返回 9 iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15 iterator.hasNext(); // 返回 true iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 */

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/7 0007
 * @description
 *      二叉排序树迭代器
 *
 */
public class q_173_BSTIterator {


	public void solution_01() {

		class BSTIterator {

			private final Iterator<Integer> itr;

			public BSTIterator(TreeNode root) {
				ArrayList<Integer> list = new ArrayList<>();
				itr = list.iterator();
			}


			/**
			 * 方式一： 空间复杂度为O(n)
			 * 使用中序遍历将遍历出元素存储在线性集合中，
			 * 然后遍历该线性集合
			 *
			 * @param p
			 * @param list
			 */
			private void inOrder(TreeNode p, List<Integer> list) {
				if (p != null) {
					inOrder(p, list);
					list.add(p.val);
					inOrder(p, list);
				}
			}

			/*
			 * 利用栈，非递归的中序遍历BST
			 * */


			/**
			 * @return the next smallest number
			 */
			public int next() {
				return itr.next();
			}

			/**
			 * @return whether we have a next smallest number
			 */
			public boolean hasNext() {
				return itr.hasNext();
			}
		}
	}

	public void solution_02() {

		class BSTIterator<K, V> implements Iterator<BSTEntry<K, V>> {


			private final Stack<BSTEntry<K, V>> stack;

			public BSTIterator(BSTEntry<K, V> root) {
				stack = new Stack<>();
				addLefPath(root);
			}

			/**
			 * 将左子树压入到栈中
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
			 * 删除操作跟线程安全相关
			 *  modCount
			 */
			@Override
			public void remove() {
				throw new ConcurrentModificationException("Can not remove!");
			}

		}
	}
}
