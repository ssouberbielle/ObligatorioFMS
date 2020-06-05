package hash;

public class HashNode<K, V> {

    private K key;

    private V value;


    /**
     * Constructor normal que recibe clave y valor
     * @param key
     * @param value
     */
    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
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


}
