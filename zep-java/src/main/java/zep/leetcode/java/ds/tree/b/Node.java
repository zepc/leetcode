package zep.leetcode.java.ds.tree.b;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/5/18 0018
 * @description Java
 */
public class Node<T extends Comparable> {

	private int n;
	private final boolean isRoot;
	private boolean isLeaf;
	/**
	 * 关键字
	 */
	private T[] keys;

	private Node parent;
	private Node[] children;


	public Node(int n, Node parent, boolean isLeaf, T[] keys, boolean isRoot) {
		this.n = n;
		this.parent = parent;
		this.isLeaf = isLeaf;
		this.keys = keys;
		this.isRoot = isRoot;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean leaf) {
		isLeaf = leaf;
	}

	public T[] getKeys() {
		return keys;
	}

	public void setKeys(T[] keys) {
		this.keys = keys;
	}

	public Node[] getChildren() {
		return children;
	}
}
