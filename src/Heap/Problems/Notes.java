package Heap.Problems;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 考察要点：
 *      1. Heap的实现
 *      2. PriorityQueue(重写Compare函数)
 *      3.
 */
public class Notes {

    /**
     * 重写PriorityQueue中的Compare函数
     */
    public static void compareOverride() {
        int[] nums = new int[]{1, 2, 3, 4};
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int num : nums) {
            priorityQueue.offer(num);
        }

        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }

    public static void main(String[] args) {
        compareOverride();
    }
}
