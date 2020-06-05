package hash;

public class ClosedHashNode<K, V> {

    private K key;

    private V value;

    private boolean delete;



    public ClosedHashNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.delete = false;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setKey(K key) {
        this.key = key;
    }
}
