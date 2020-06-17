package hash;

/**
 *
 * @param <K>
 * @param <V>
 */
public class HashEntry<K, V> {

    private K key;

    private V value;

    private boolean removed;

    /**
     * Constructor normal que recibe clave y valor
     * @param key
     * @param value
     */
    public HashEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.removed = false;
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

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equalsToReturn = false;

        // NOTE no es posible realizar un instanceof con un tipo generico, existen mecanismos pero son indirectos.

        equalsToReturn = ((K) obj).equals(this.key);

        return equalsToReturn;
    }
}
