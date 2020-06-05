package nodo;

public class Node<Tipo> {
    private Tipo value;
    private Node siguiente;
    private int priority;


    public Node(Tipo value){
        this.value = value;
        siguiente = null;
        priority = 1;
    }

    public Node(Tipo value, Node siguiente){
        this.value = value;
        this.siguiente = siguiente;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public Tipo getValue() {
        return value;
    }
    public void setValue(Tipo value) {
        this.value = value;
    }

    public void setSiguiente(Node siguiente) {
        this.siguiente = siguiente;
    }
    public Node getSiguiente() {
        return siguiente;
    }


}
