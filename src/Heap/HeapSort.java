package Heap;

import java.util.Arrays;


/**
 * 1.建堆  (Time: O(n))
 * 2.调整堆( O(logn) )
 * 3.总的时间复杂度：O(nlogn) 最好/最坏/平均
 *
 * 总结：Time: O(nlogn)
 *      Space:O(1)
 *      不稳定排序
 */
public class HeapSort {
    public static void heapSort(int[] nums) {
        /**
         * 第一次 for 循环，我们通过 heapAdjust()，将数组变成最大堆的形式
         * Time: O(n)
         */
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            heapAdjust(nums, i, nums.length);
        }

        /**
         * 第二次 for 循环，由于我们需要排序，所以需要将最大值移动到index的最后，然后进行堆调整
         *
         * Time: O(nlogn)
         */
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            /**
             * Time：O(logn)
             */
            heapAdjust(nums, 0, i);
        }
    }

    /**
     * 这里的 heapAdjust 方法和 MaxHeapImpl中的 siftDownHeapify()逻辑是一样的
     */
    public static void heapAdjust(int[] nums, int start, int length) {
        /**
         * 非叶子节点是所有nums中元素个数的一半
         */
        int child = 2 * start + 1;
        /**
         * 判断 左孩子节点是否存在
         */
        while (child < length) {
            /**
             * 如果左孩子节点存在，那么就判断 左孩子节点和右孩子节(child+1)点哪个大
             *      如果右孩子节点更大，那么 child++，否则不变
             */
            if (child + 1 < length && nums[child] < nums[child + 1]) {
                child++;
            }
            /**
             * 用较大值和父亲节点进行比较，判断是否需要swap
             */
            if (nums[start] < nums[child]) {
                swap(nums, start, child);
            } else {
                break;
            }

            start = child;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2, 23, 30, 8, 32, 31, 41, 1, 15};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
