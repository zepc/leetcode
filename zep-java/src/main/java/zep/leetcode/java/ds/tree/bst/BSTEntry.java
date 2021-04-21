package zep.leetcode.java.ds.tree.bst;

import java.util.Map;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/7 0007
 * @description leetcode
 */
public class BSTEntry<K, V> implements Map.Entry<K, V> {

	public K key;
	public V value;
	public BSTEntry<K, V> left;
	public BSTEntry<K, V> right;


	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		this.value = value;
		return value;
	}

	public BSTEntry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public BSTEntry(K key, V value, BSTEntry<K, V> left, BSTEntry<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "BSTEntry{" +
				"key=" + key +
				", value=" + value +
				", left=" + left +
				", right=" + right +
				'}';
	}
}
