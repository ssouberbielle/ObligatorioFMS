package LinkedList;

import nodo.NodoDoble;

import java.util.Iterator;

public class MyIteratorDoublyLinkedList<T> implements Iterator<T> {
    private NodoDoble<T> nodo;

    public MyIteratorDoublyLinkedList(NodoDoble<T> nodo){
        this.nodo = nodo;
    }
    @Override
    public boolean hasNext() {
        return (nodo != null);
    }

    public NodoDoble<T> getNodo() {
        return nodo;
    }

    public void setNodo(NodoDoble<T> nodo) {
        this.nodo = nodo;
    }

    @Override
    public T next() {
        T valueToReturn = nodo.getValue();

        nodo = nodo.getSiguiente();

        return valueToReturn;
    }
}
