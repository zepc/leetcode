package zep.leetcode.java.ds.二分查找;

/**
 * @author Zep
 */
public class BinarySearch {

	/*
	 * 二分搜索，需要注意的三点：
	 * 1. 循环退出条件，注意是 low<=high，而不是 low<high
	 * 2. mid的取值，mid=low+(high-low)>>1
	 * 3. low和high的更新，low=mid+1，high=mid-1
	 * */

	/**
	 * 二分查找
	 *
	 * @param target 待查找的元素
	 * @param nums   数据集
	 * @return 如果查找成功返回该元素的位置，如果失败返回-1
	 */
	public int binarySearchMatrix(int target, int[] nums) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int low = 0, high = nums.length - 1, mid = 0;
		while (low <= high) {
			mid = low + (high - low) >> 1;
			if (target == nums[mid]) {
				return mid;
			}
			else if (target < nums[mid]) {
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
		return -1;
	}

	// todo 二分搜索的四个变种

	/**
	 * 1. 查找第一个与target相等的元素，时间复杂度O(logn)
	 */
	public int searchFirstEqualElement(int target, int[] nums) {
		int low = 0, high = nums.length - 1, mid = 0;
		while (low <= high) {
			mid = low + (high - low) >> 1;
			if (target > nums[mid]) {
				low = mid + 1;
			}
			else if (target < nums[mid]) {
				high = mid - 1;
			}
			else {
				// 如果mid左边的数据也是target，向左搜索
				if (mid == 0 || nums[mid - 1] != target) {
					return mid;
				}
				high = mid - 1;
			}
		}
		return -1;
	}

	/**
	 * 2. 查找最后一个与target相等的元素，时间复杂度O(logn)
	 */
	public int searchLastEqualElement(int target, int[] nums) {
		int low = 0, high = nums.length - 1, mid = 0;
		while (low <= high) {
			mid = low + (high - low) >> 1;
			if (target > nums[mid]) {
				low = mid + 1;
			}
			else if (target < nums[mid]) {
				high = mid - 1;
			}
			else {
				// 如果mid左边的数据也是target，向左搜索
				if (mid == nums.length - 1 || nums[mid + 1] != target) {
					return mid;
				}
				low = mid + 1;
			}
		}
		return -1;
	}

	/**
	 * 3. 二分查找第一个大于等于target的元素，时间复杂度O(logn)
	 */
	public int searchFirstGreaterElement(int target, int[] nums) {
		int low = 0, high = nums.length - 1, mid = 0;
		while (low <= high) {
			mid = low + (high - low) >> 1;
			if (target <= nums[mid]) {
				if (mid == 0 || nums[mid - 1] < target) {
					return mid;
				}
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
		return -1;
	}


	/**
	 * 4. 二分查找第一个小于等于target的元素，时间复杂度O(logn)
	 */
	public int searchFirstLessElement(int target, int[] nums) {
		int low = 0, high = nums.length - 1, mid = 0;
		while (low <= high) {
			mid = low + (high - low) >> 1;
			if (target >= nums[mid]) {
				if (mid == nums.length - 1 || nums[mid + 1] > target) {
					return mid;
				}
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}
		return -1;
	}
}
