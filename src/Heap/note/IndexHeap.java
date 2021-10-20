package Heap.note;

/**
 * 索引堆(Index Heap): 堆排序的改进版
 *      数据：复杂
 *
 * 中心思想：数据和索引分开存储
 *
 */
public class IndexHeap {
    /**
     * LeetCode41. First Missing Positive
     *      Given an unsorted integer array, find the smallest missing positive integer.
     *
     *      Example 1:
     *
     *      Input: [1,2,0]
     *      Output: 3
     *      ==============================
     *
     *      Example 2:
     *
     *      Input: [3,4,-1,1]
     *      Output: 2
     *      =============================
     *
     *      Example 3:
     *
     *      Input: [7,8,9,11,12]
     *      Output: 1
     */
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            /**
             * 将元素换到响应的index上：
             *  nums[nums[i] - 1] != nums[i] 这个判断条件就是 移动index的标准
             */
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

}
