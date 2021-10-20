package Heap.impl;

import Heap.IMaxHeap;

public class MaxHeapImpl<E extends Comparable<E>> implements IMaxHeap<E> {
    private int capacity;

    private int size;

    private E[] data;

    public MaxHeapImpl(E[] data) {
        this.capacity = data.length;
        this.size = data.length;
        this.data = data;
        heapify(data);
    }

    /**
     * 小结Heapify：
     *  1. 找到非叶子节点
     *  2. 对【非叶子节点】SiftDown
     *  3. Time: O(n)
     *
     * @param data
     */
    public void heapify(E[] data) {
        for (int i = getParentIndex(size - 1); i >= 0; i--) {
            siftDownHeapify(i);
        }
    }

    public MaxHeapImpl() {
        this.capacity = 16;
        this.size = 0;
        this.data = (E[]) new Comparable[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * offer的时间复杂度为 O(nlogn)
     * @param e
     * @return
     */
    @Override
    public boolean offer(E e) {
        if (data.length == size) {
            expandCapacity();
        }
        data[size] = e;
        size++;
        /**
         * 往堆中添加元素是一定要用到 siftUp() 方法的
         */
        siftUp();
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            throw new IllegalArgumentException("堆为空");
        }
        E item = data[0];
        data[0] = data[size - 1];
        data[size - 1] = null;
        size--;
        /**
         * 通过 siftDown 保证最大堆的性质
         */
        siftDown();

        return item;
    }

    /**
     * 返回的是 data 的第一个元素
     * @return
     */
    @Override
    public E peek() {
        if (size == 0) {
            throw new IllegalArgumentException("堆为空");
        }
        return data[0];
    }

    @Override
    public void print() {
        for (int i = 0; i < data.length; i++) {
            System.out.println(data[i] + " ");
        }
    }

    /**
     * 扩容操作
     */
    private void expandCapacity() {
        E[] temp = (E[]) new Comparable[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            temp[i] = data[i];
        }
        capacity *= 2;
        data = temp;
    }

    /**
     * 通过子节点的 index 去计算得到 父节点的 index
     * @param childIndex
     * @return
     */
    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    /**
     * 通过父亲节点找到 左子节点的index
     * @param parentIndex
     * @return
     */
    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /**
     * 通过父亲节点找到 右子节点的index
     * @param parentIndex
     * @return
     */
    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    /**
     * 当前节点的左子节点的值
     * @param index
     * @return
     */
    private E leftChild(int index) {
        return data[getLeftChildIndex(index)];
    }

    /**
     * 当前节点的右子节点的值
     * @param index
     * @return
     */
    private E rightChild(int index) {
        return data[getRightChildIndex(index)];
    }

    /**
     * 当前节点的父亲节点的值
     * @param index
     * @return
     */
    private E parent(int index) {
        return data[getLeftChildIndex(index)];
    }

    private void swap(int i, int j) {
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    private void siftUp() {
        int index = size - 1;
        /**
         * while 循环中的判断语句：
         *      两个条件：
         *          1. 需要保证 父亲节点的index没有小于0
         *          2. 需要保证 父亲节点的值 <  当前节点的值
         */
        while (getLeftChildIndex(index) < size && parent(index).compareTo(data[index]) < 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void siftDown() {
        int index = 0;
        // 往下 siftDown 的时候，边界条件为：判断有没有左子节点
        while (getLeftChildIndex(index) < size) {
            /**
             * 在往下 siftDown 的过程中，需要判断大小再进行替换
             * 所以我们先假定 较大的孩子节点为 左孩子节点，然后再进行比较
             */
            int biggerChildIndex = getLeftChildIndex(index);
            /**
             * 这条 if 语句中有两个条件需要判断：
             *      第一个是需要 右子节点存在
             *      第二个是需要 右子节点 > 左子节点
             */
            if (getRightChildIndex(index) < size && rightChild(index).compareTo(leftChild(index)) > 0) {
                // 如果满足上述条件，那么将 右子节点的index 赋值给 biggerChildIndex
                biggerChildIndex = getRightChildIndex(index);
            }
            /**
             * 值较大的 子节点 才需要和 父节点 进行比较
             */
            if (data[index].compareTo(data[biggerChildIndex]) > 0) {
                break;
            } else {
                swap(index, biggerChildIndex);
            }
            index = biggerChildIndex;
        }
    }

    private void siftDownHeapify(int index) {
        // 往下 siftDown 的时候，边界条件为：判断有没有左子节点
        while (getLeftChildIndex(index) < size) {
            /**
             * 在往下 siftDown 的过程中，需要判断大小再进行替换
             * 所以我们先假定 较大的孩子节点为 左孩子节点，然后再进行比较
             */
            int biggerChildIndex = getLeftChildIndex(index);
            /**
             * 这条 if 语句中有两个条件需要判断：
             *      第一个是需要 右子节点存在
             *      第二个是需要 右子节点 > 左子节点
             */
            if (getRightChildIndex(index) < size && rightChild(index).compareTo(leftChild(index)) > 0) {
                // 如果满足上述条件，那么将 右子节点的index 赋值给 biggerChildIndex
                biggerChildIndex = getRightChildIndex(index);
            }
            /**
             * 值较大的 子节点 才需要和 父节点 进行比较
             */
            if (data[index].compareTo(data[biggerChildIndex]) > 0) {
                break;
            } else {
                swap(index, biggerChildIndex);
            }
            index = biggerChildIndex;
        }
    }
}
