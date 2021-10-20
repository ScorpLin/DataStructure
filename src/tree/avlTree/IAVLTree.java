package tree.avlTree;

public interface IAVLTree {

    void add(int val);

    int size();

    boolean isEmpty();

    boolean contains(int val);

    void remove(int val);
}
