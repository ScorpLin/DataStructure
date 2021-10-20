package Heap;

public interface IMaxHeap<E> {
    /**
     * 实际占用大小
     * @return
     */
    int size();

    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 入栈
     * @param e
     * @return
     */
    boolean offer(E e);

    /**
     * 出栈
     * @return
     */
    E poll();

    /**
     * 查看栈顶
     * @return
     */
    E peek();

    /**
     * 打印数据和结果
     */
    void print();
}
