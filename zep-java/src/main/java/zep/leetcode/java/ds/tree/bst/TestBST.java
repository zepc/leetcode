package zep.leetcode.java.ds.tree.bst;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/7 0007
 * @description leetcode
 */
public class TestBST {

	private final Random random = new Random();
	private final int MAX = 16;

	@Test
	public void testPutAndIter() {
		BSTMap<Integer, String> map = new BSTMap<>();
		for (int i = 0; i < MAX; i++) {
			map.put(random.nextInt(MAX), random.nextInt(MAX) + "");
		}
		Iterator<BSTEntry<Integer, String>> itr = map.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next().key + " ");
		}
		System.out.println();
	}


	private final int MAX_TWO = 65545;

	@Test
	public void testPutAndIterWithJDK() {
		BSTMap<Integer, String> bstMap = new BSTMap<>();
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		for (int i = 0; i < MAX_TWO; i++) {
			int key = random.nextInt(MAX_TWO);
			String value = random.nextInt(MAX_TWO) + "";
			bstMap.put(key, value);
			treeMap.put(key, value);
		}
		Assert.assertEquals(bstMap.size(), treeMap.size());
		System.out.println("size = " + bstMap.size());

		Iterator<BSTEntry<Integer, String>> bstItr = bstMap.iterator();
		Iterator<Map.Entry<Integer, String>> treeItr = treeMap.entrySet().iterator();

		while (bstItr.hasNext() && treeItr.hasNext()) {
			Assert.assertEquals(bstItr.next().key, treeItr.next().getKey());
		}

		Assert.assertTrue(!bstItr.hasNext() && !treeItr.hasNext());
	}


	@Test
	public void testDelete() {
		// 叶子节点
		int[] caseOne = {5, 2, 6, 1, 4, 7, 3};
		// 有个孩子节点为null
		int[] caseTwo = {};
		// 两个孩子节点都不为空
		int[] caseThree = {};

		BSTMap<Integer, String> map = new BSTMap<>();
		for (int number : caseOne) {
			map.put(number, number + "");
		}
		String s = map.remove(1);
		System.out.println("s = " + s);
		map.levelOrder();
		System.out.println();

		Iterator<BSTEntry<Integer, String>> itr = map.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next().key + " ");
		}
		System.out.println();
	}


	/*
	 * 3. 顺序插入，然后进行查找
	 * */


	private static final int MAX_THREE = 20480;

	/**
	 * time：3s
	 */
	@Test
	public void testIncrementBST() {
		BSTMap<Integer, String> map = new BSTMap<>();
		for (int i = 0; i < MAX_THREE; i++) {
			map.put(i, i + "");
		}

		for (int i = 0; i < MAX_THREE; i++) {
			map.getValue(random.nextInt(MAX_THREE));
		}
	}

	/**
	 * time：94ms
	 */
	@Test
	public void testIncrementJDK() {
		TreeMap<Integer, String> map = new TreeMap<>();
		for (int i = 0; i < MAX_THREE; i++) {
			map.put(i, i + "");
		}

		for (int i = 0; i < MAX_THREE; i++) {
			map.get(random.nextInt(MAX_THREE));
		}
	}

}
