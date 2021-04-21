package zep.leetcode.java.ds.tree.dict_tree;

import java.util.Map;

/**
 * 字典树
 *
 * @author Zhao Peng
 */
public class DicTree {

	private final DicTreeNode root;

	public DicTree() {
		this.root = new DicTreeNode();
	}

	private void addStr(String curWord, DicTreeNode node) {
		// 计数
		node.count++;

		String preWord = node.word;
		String commonPrefix = "";
		if (null != preWord) {
			// 寻找公共前缀
			for (int i = 0; i < curWord.length(); i++) {
				if (preWord.length() > i && curWord.charAt(i) == preWord.charAt(i)) {
					commonPrefix += curWord.charAt(i);
				}
				else {
					break;
				}
			}

			if (commonPrefix.length() > 0) {
				if (commonPrefix.length() == curWord.length() && commonPrefix.length() == preWord.length()) {
					// 单词重复
				}
				else if (commonPrefix.length() == preWord.length() && commonPrefix.length() < curWord.length()) {
					// 剩余的串
					String wordLeft = curWord.substring(commonPrefix.length());
					// 剩余的串去子节点中继续找
					searchChild(wordLeft, node);
				}
				else if (commonPrefix.length() < preWord.length()) {
					// 节点裂变
					DicTreeNode splitNode = new DicTreeNode(true, commonPrefix, node.count);
					splitNode.parent = node.parent;
					splitNode.parent.addChild(commonPrefix, splitNode);
					node.parent.removeChild(node.word);
					node.count--;
					// 节点裂变后剩余字符串
					String strLeft = preWord.substring(commonPrefix.length());
					node.word = strLeft;
					splitNode.addChild(strLeft, node);
					// 单词裂变后的剩余字串
					if (commonPrefix.length() < curWord.length()) {
						splitNode.isWord = false;
						String wordLeft = curWord.substring(commonPrefix.length());
						DicTreeNode leftNode = new DicTreeNode(true, wordLeft, 1);
						splitNode.addChild(wordLeft, leftNode);
					}
				}
			}
			else {
				// 没有公共前缀,添加新的节点
				DicTreeNode newNode = new DicTreeNode(true, curWord, 1);
				node.addChild(curWord, newNode);
			}
		}
		else {
			// 根节点
			if (node.children.size() > 0) {
				searchChild(curWord, node);
			}
			else {
				DicTreeNode newNode = new DicTreeNode(true, curWord, 1);
				node.addChild(curWord, newNode);
			}
		}
	}

	public void searchChild(String wordLeft, DicTreeNode node) {
		boolean isFind = false;
		Map<String, DicTreeNode> childMap = node.children;
		if (childMap.size() > 0) {
			// 遍历孩子节点
			for (String key : childMap.keySet()) {
				DicTreeNode childNode = childMap.get(key);
				// 首字母相同, 则在孩子节点继续添加字串
				if (wordLeft.charAt(0) == childNode.word.charAt(0)) {
					isFind = true;
					addStr(wordLeft, childNode);
					break;
				}
			}
		}
		// 没有首字母相同的孩子, 则将其变为子节点
		if (!isFind) {
			DicTreeNode newNode = new DicTreeNode(true, wordLeft, 1);
			node.addChild(wordLeft, newNode);
		}
	}

	/**
	 * add
	 *
	 * @param word
	 */
	public void add(String word) {
		addStr(word, root);
	}

	/**
	 * find
	 *
	 * @param word
	 * @param node
	 * @return
	 */
	private boolean findStr(String word, DicTreeNode node) {
		boolean isMatch = true;
		String wordLeft = word;
		String preWord = node.word;
		if (null != preWord) {
			// 字串与单词不匹配
			if (word.indexOf(preWord) != 0) {
				isMatch = false;
			}
			else {
				// 匹配, 则计算剩余单词
				wordLeft = word.substring(preWord.length());
			}
		}
		// 如果匹配
		if (isMatch) {
			// 如果还有剩余单词长度
			if (wordLeft.length() > 0) {
				// 遍历孩子继续找
				for (String key : node.children.keySet()) {
					DicTreeNode childNode = node.children.get(key);
					boolean isChildFind = findStr(wordLeft, childNode);
					if (isChildFind) {
						return true;
					}
				}
				return false;
			}
			else {
				// 没有剩余单词长度, 说明已经匹配完毕, 直接返回节点是否为单词
				return node.isWord;
			}
		}
		return false;
	}

	/**
	 * find
	 *
	 * @param word
	 * @return
	 */
	public boolean find(String word) {
		return findStr(word, root);
	}

	/**
	 * count word in tree
	 *
	 * @param prefix
	 * @param node
	 * @return
	 */
	private int countChildStr(String prefix, DicTreeNode node) {
		for (String key : node.children.keySet()) {
			DicTreeNode childNode = node.children.get(key);
			int childCount = countStr(prefix, childNode);
			if (childCount != 0) {
				return childCount;
			}
		}
		return 0;
	}

	/**
	 * count word-string
	 *
	 * @param prefix
	 * @param node
	 * @return
	 */
	private int countStr(String prefix, DicTreeNode node) {
		String preWord = node.word;
		//非根节点
		if (null != preWord) {
			// 前缀与字串不匹配
			if (prefix.indexOf(preWord) != 0 && preWord.indexOf(prefix) != 0) {
				return 0;
				// 前缀匹配字串, 且前缀较短
			}
			else if (preWord.indexOf(prefix) == 0) {
				// 找到目标节点, 返回单词数
				return node.count;
				// 前缀匹配字串, 且字串较短
			}
			else if (prefix.indexOf(preWord) == 0) {
				// 剩余字串继续匹配子节点
				String prefixLeft = prefix.substring(preWord.length());
				if (prefixLeft.length() > 0) {
					return countChildStr(prefixLeft, node);
				}
			}
		}
		else {
			// 根节点, 直接找其子孙
			return countChildStr(prefix, node);
		}
		return 0;
	}

	/**
	 * count tire word
	 *
	 * @param prefix
	 * @return
	 */
	public int count(String prefix) {
		// 处理特殊情况
		if (null == prefix || prefix.trim().isEmpty()) {
			return root.count;
		}
		// 从根结点往下匹配
		return countStr(prefix, root);
	}

	/**
	 * print
	 *
	 * @param node
	 * @param layer
	 */
	private void printNode(DicTreeNode node, int layer) {
		// 层级递进
		for (int i = 0; i < layer; i++) {
			System.out.print("\t");
		}
		// 打印
		System.out.println(node);
		// 递归打印子节点
		for (String str : node.children.keySet()) {
			DicTreeNode child = node.children.get(str);
			printNode(child, layer + 1);
		}
	}

	/**
	 * print tree
	 */
	public void print() {
		printNode(root, 0);
	}
}
