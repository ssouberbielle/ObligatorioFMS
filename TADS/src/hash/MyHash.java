package hash;

public interface MyHash<Key, Data> {
    void put(Key k, Data t);
    int getSize();
    Data get(Key k);
    void remove(Key k);
    boolean contains(Key k);
}
