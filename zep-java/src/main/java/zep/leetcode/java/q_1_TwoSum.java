package zep.leetcode.java;

import java.util.HashMap;
import java.util.Map;

public class q_1_TwoSum {

	public int[] sum(int target, int[] nums) {
		return solution_02(target, nums);
	}

	private final int[] result = new int[2];

	public int[] solution_01(int target, int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					result[0] = i;
					result[1] = j;
					break;
				}
			}
		}
		return result;
	}


	/**
	 * 上面的方法中： 时间复杂度：O(n^2)，空间复杂度：O(1) 如果想要减少在内层循环中，O(n)的时间复杂度，所以需要一种更有效的方法来检查数组中是否存在目标元素。
	 * 如果存在，我们需要找出它的索引。
	 * <p>
	 * 保持数组中的每个元素与其索引相互对应的最好方法是什么？ 哈希表。
	 * <p>
	 * 通过以空间换取速度的方式，我们可以将查找时间从 O(n) 降低到 O(1)，哈希表正是为此目的而构建的， 它支持以近似恒定的时间进行快速查找。
	 * 我用“近似”来描述，是因为一旦出现冲突，查找用时可能会退化到 O(n)。
	 * <p>
	 * 哈希表查找时间为O(1)
	 */
	public int[] solution_02(int target, int[] nums) {
		// <value, index>
		Map<Integer, Integer> map = new HashMap(nums.length);
		for (int i = 0; i < nums.length; i++) {
			int another = target - nums[i];
			if (map.containsKey(another) && map.get(another) != i) {
				return new int[]{i, map.get(another)};
			}
			map.put(nums[i], 1);
		}
		return null;
	}

}