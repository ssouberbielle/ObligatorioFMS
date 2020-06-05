package LinkedList;

public interface MyList<Key> extends Iterable<Key> {
    void add(Key value);
    Key get(int position);
    int getSize();
    void makeEmpty();
    void remove(Key k);
    boolean contains(Key k);
    void showList();
    void addFirst(Key value);


}
