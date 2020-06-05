package LinkedList;

import nodo.Node;

import java.util.Iterator;

public class MyIteratorLinkedList<T> implements Iterator<T> {

    private Node<T> nodo;

    public MyIteratorLinkedList(Node<T> nodo){
        this.nodo = nodo;
    }
    @Override
    public boolean hasNext() {
        return (nodo != null);
    }

    @Override
    public T next() {
        T valueToReturn = nodo.getValue();

        nodo = nodo.getSiguiente();

        return valueToReturn;
    }




}
