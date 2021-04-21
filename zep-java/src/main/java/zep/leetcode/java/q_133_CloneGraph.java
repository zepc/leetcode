package zep.leetcode.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created on 2020/10/13.
 *
 * @author Zhao Peng
 */
public class q_133_CloneGraph {

	class Node {

		public int val;
		// 邻接表
		public List<Node> neighbors;

		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}

		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}

	public Node cloneGraph(Node node) {
		return solution_02(node);
	}

	public Node solution_01(Node node) {
		if (node == null) {
			return node;
		}
		// 哈希表存储
		Map<Node, Node> visited = new HashMap<>();
		Stack<Node> stack = new Stack<>();

		// 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
		Node root = new Node(node.val, new ArrayList());

		stack.push(node);

		while (!stack.isEmpty()) {
			Node curNode = stack.pop();
			if (!visited.containsKey(curNode)) {
				Node cloneNode = new Node(node.val, new ArrayList<>());
				visited.put(curNode, cloneNode);
				List<Node> neighNodes = curNode.neighbors;
				for (Node nn : neighNodes) {
					Node clonenn = new Node(nn.val, new ArrayList<>());
					cloneNode.neighbors.add(clonenn);
					stack.push(nn);
				}
			}
		}
		return root;
	}


	private final Map<Node, Node> visited = new HashMap<>();

	public Node solution_02(Node node) {
		if (node == null) {
			return node;
		}

		if (visited.containsKey(node)) {
			return visited.get(node);
		}

		// 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
		Node cloneNode = new Node(node.val, new ArrayList());
		// 哈希表存储
		visited.put(node, cloneNode);

		// 遍历该节点的邻居并更新克隆节点的邻居列表
		for (Node neighbor : node.neighbors) {
			cloneNode.neighbors.add(cloneGraph(neighbor));
		}
		return cloneNode;
	}

}
