package stack;

import nodo.Node;

import java.util.EmptyStackException;

public class Stack<T> implements MyStack<T> {

    private Node primero;
    private Node ultimo;
    private int size;

    public Stack(){
        this.primero = null;
        this.ultimo = null;
        size = 0;
    }

    public void addLast(T value){
        Node<T> nodo = new Node(value);
        if (!isEmpty()){
            ultimo.setSiguiente(nodo);
            ultimo  =  nodo;
        } else {
            ultimo = nodo;
            primero = ultimo;
        }

        size++;
    }


    public int getSize() {
        return size;
    }

    @Override
    public void push(T value) {
        addLast(value);
    }

    @Override
    public T pop() throws EmptyStackException {
        T temp = null;
        try {
            temp = get(getSize());
            removeLast();
            size--;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }
    public T get(int position) {
        int contador = 1;
        Node<T> temp = primero;
        while(contador != position){
            temp = temp.getSiguiente();
            contador++;
        }
        return temp.getValue();

    }

    @Override
    public T top() throws EmptyStackException {
        if (!isEmpty()){
            return get(getSize());
        }else {
            throw new EmptyStackException();
        }

    }

    @Override
    public int size() {
        return 0;
    }


    public void showList() {
        Node<T> recorrer = primero;
        System.out.println();
        while (recorrer != null) {
            System.out.print("[" + recorrer.getValue() + "]--->");
            recorrer = recorrer.getSiguiente();
        }
    }


    public boolean isEmpty() {
        return primero==null;
    }


    public void makeEmpty() {
        primero = null;
        ultimo= null;

    }


    public void removeLast() throws Exception {
        Node<T> temp = primero;
        if(temp.getSiguiente()==null){
            temp = null;
            ultimo = null;
        }else{
            while(temp.getSiguiente().getSiguiente() != null){
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(null);
            ultimo = temp;
        }

    }

    public void remove1(int position) throws Exception {
        if (!isEmpty()){
            if(position == 1){
                Node aux = primero;
                primero = aux.getSiguiente();
                size -= 1;
            }else {
                Node temp1 = primero;
                Node temp2 = temp1.getSiguiente();
                int contador = 1;
                while((contador+1) != position){
                    temp1 = temp2;
                    temp2 = temp2.getSiguiente();
                    contador++;
                }
                temp2 = temp2.getSiguiente();
                temp1.setSiguiente(temp2);
                size = size -1;
            }
        }else if (position> getSize()){
            throw new Exception("Posicion invalida");
        }
        else {
            throw new Exception("Lista vacia");
        }

    }

    public void addPosicion(T value, int posicion) throws Exception {
        if (!isEmpty()) {
            if (posicion == (size + 1)) {
                addLast(value);

            } else if (posicion > 1 && posicion < (size + 1)) {
                Node<T> temp1 = primero;
                Node<T> temp2 = temp1.getSiguiente();
                int contador = 1;
                while ((contador + 1) != posicion) {
                    temp1 = temp2;
                    temp2 = temp2.getSiguiente();
                    contador++;
                }
                Node nodo = new Node(value, temp2);
                temp1.setSiguiente(nodo);
                size++;
            }
        } else if (posicion == 1) {
            addFirst(value);

        } else {
            throw new Exception("error");
        }
    }

    public void addFirst(T value){
        Node<T> nodo = new Node(value);
        if(!isEmpty()){
            nodo.setSiguiente(primero);
            primero = nodo;
        }else{
            ultimo = primero;
        }
        size++;
    }
}
