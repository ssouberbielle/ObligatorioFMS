package tree;

import LinkedList.MyList;

public interface MySearchBinaryTree<Key extends Comparable<Key>, Value> {

    void insert(Key key, Value value);
    boolean contains(Key key);
    void remove(Key key);
    Value find(Key key);
    MyList<Key> pre_Order();
    MyList<Key> post_Order();
    MyList<Key> in_Order();
    MyList<Key> level_Order();


}
