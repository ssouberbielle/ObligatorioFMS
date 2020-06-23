package nodo;

public class Wrapper <K extends Comparable <K>>{
    private K key;
    private int value;


    public Wrapper(K key) {
        this.key = key;

    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public int compareTo(Wrapper<K> o) {
        return this.value - o.value;
    }
}
