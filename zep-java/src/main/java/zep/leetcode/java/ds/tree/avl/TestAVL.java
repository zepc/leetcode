package zep.leetcode.java.ds.tree.avl;


import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Zhao Peng
 * @version V1.0.0
 * @date 2019/10/13 0013
 * @description leetcode
 */
public class TestAVL {


	private final Random random = new Random();
	private final int MAX = 16;

	@Test
	public void testPutAndIter() {
		AVLMap<Integer, String> map = new AVLMap<>();
		for (int i = 0; i < MAX; i++) {
			map.put(random.nextInt(MAX), random.nextInt(MAX) + "");
		}
		Iterator<AVLEntry<Integer, String>> itr = map.iterator();
		while (itr.hasNext()) {
			System.out.print(itr.next().key + " ");
		}
		System.out.println();
	}


	private final int MAX_TWO = 65545;

	@Test
	public void testPutAndIterWithJDK() {
		AVLMap<Integer, String> avlMap = new AVLMap<>();
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		for (int i = 0; i < MAX_TWO; i++) {
			int key = random.nextInt(MAX_TWO);
			String value = random.nextInt(MAX_TWO) + "";
			avlMap.put(key, value);
			treeMap.put(key, value);
		}
		Assert.assertEquals(avlMap.size(), treeMap.size());
		System.out.println("size = " + avlMap.size());

		Iterator<AVLEntry<Integer, String>> bstItr = avlMap.iterator();
		Iterator<Map.Entry<Integer, String>> treeItr = treeMap.entrySet().iterator();

		while (bstItr.hasNext() && treeItr.hasNext()) {
			Assert.assertEquals(bstItr.next().key, treeItr.next().getKey());
		}

		Assert.assertTrue(!bstItr.hasNext() && !treeItr.hasNext());
	}

	private static final int MAX_FOUR = 20480;

	@Test
	public void testDelete() {
		AVLMap<Integer, String> map = new AVLMap<>();
		TreeMap<Integer, String> treeMap = new TreeMap<>();
		for (int i = 0; i < MAX_FOUR; i++) {
			map.put(i, i + "");
			treeMap.put(i, i + "");
		}

		for (int i = 0; i < MAX_FOUR >> 1; i++) {
			map.remove(i);
			treeMap.remove(i);
		}

		map.checkBalance();
		Assert.assertTrue(map.size() == treeMap.size());

		System.out.println("map.size() = " + map.size());

		Iterator<AVLEntry<Integer, String>> avlItr = map.iterator();
		Iterator<Map.Entry<Integer, String>> treeItr = treeMap.entrySet().iterator();

		while (avlItr.hasNext() && treeItr.hasNext()) {
			Assert.assertEquals(avlItr.next().key, treeItr.next().getKey());
		}

		Assert.assertTrue(!avlItr.hasNext() && !treeItr.hasNext());
	}

	private static final int MAX_THREE = 65535;

	/**
	 * time:152ms
	 */
	@Test
	public void testIncrementAVL() {
		AVLMap<Integer, String> map = new AVLMap<>();
		for (int i = 0; i < MAX_THREE; i++) {
			map.put(i, i + "");
		}

		map.checkBalance();

		for (int i = 0; i < MAX_THREE; i++) {
			map.getValue(random.nextInt(MAX_THREE));
		}

	}

	/**
	 * time: 101ms
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

	@Test
	public void testBasic() {
		int div = 1 / 2;
		System.out.println("div = " + div);
	}

}
