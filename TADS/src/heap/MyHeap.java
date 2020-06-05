package heap;

public interface MyHeap<T extends Comparable<T>> {

    void insert(T value) ;

    T getMax();

    void deleteMax() ;

    int size();

}
