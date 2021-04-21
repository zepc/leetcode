package zep.leetcode.java.ds.tree.b;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/5/18 0018
 * @description Java
 */
public class BTreeUtils<T extends Comparable> {

	/**
	 * @param node
	 * @param v
	 * @return
	 */
	public Node searchX(Node node, T v) {

		int i = 1;

		// B树中的关键字是有顺序的，注意这个特点

		while (i <= node.getN() && (v.compareTo(node.getKeys()[i - 1]) > 0)) {
			i++;
		}
		if (i <= node.getN() && (v.compareTo(node.getKeys()[i - 1]) == 0)) {
			return null;
		}
		else if (node.isLeaf()) {
			return null;
		}
		else {

			// 访问磁盘的页面数，为O(h)=O(logtn), t为log的下标

			Node child = node.getChildren()[i - 1];
			return searchX(child, v);
//            readDisk(node, )
		}
	}


	/**
	 * @return
	 */
	public Node createBTree() {
		return new Node<T>(0, null, true, null, true);
	}
}
