package zep.leetcode.java.ds.tree.dict_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/5/19 0019
 * @description Java
 */
public class DicTreeNode {

	boolean isWord;
	String word;
	int count;
	DicTreeNode parent;
	Map<String, DicTreeNode> children;

	public DicTreeNode() {
		children = new HashMap<>();
	}

	public DicTreeNode(boolean isWord, String word, int count) {
		this.isWord = isWord;
		this.word = word;
		this.count = count;
	}

	public void removeChild(String word) {
		children.remove(word);
	}

	public void addChild(String key, DicTreeNode node) {
		children.put(key, node);
		node.parent = this;
	}

	@Override
	public String toString() {
		return "str : " + word + ", isWord : " + isWord + ", count : " + count;
	}

}
