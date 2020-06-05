package queue;

import exceptions.ColaVaciaException;
import nodo.Node;

public class Queue<T> implements MyPriorityQueue<T> {

    private Node primero;
    private Node ultimo;
    private int size;


    public Queue(){
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

    @Override
    public void enqueueRight(T value) {
        addLast(value);
    }

    public void enqueue(T value) {
        addFirst(value);
    }

    @Override
    public T dequeueLeft() throws ColaVaciaException {
        if(!isEmpty()){
            Node<T> temp = primero;
            primero = temp.getSiguiente();
            size-=1;
            return temp.getValue();

        }
        else{
            throw new ColaVaciaException();
        }

    }

    public boolean isEmpty() {
        return primero==null;
    }


    public void showList(){
        Node<T> recorrer = primero;
        System.out.println();
        while (recorrer != null) {
            System.out.print("[" + recorrer.getValue() + "]--->");
            recorrer = recorrer.getSiguiente();
        }
    }


    @Override
    public void enqueueWithPriority(T value, int prio) {
        if (prio==1 || isEmpty()){
            addFirst(value);
        }else {
            Node<T> nodo = new Node(value);
            nodo.setPriority(prio);
            size++;
            Node<T> aux = primero;
            while(aux.getSiguiente() != null && prio > aux.getSiguiente().getPriority()) {
                aux = aux.getSiguiente();
            }
            if(aux.getSiguiente() == null){
                ultimo.setSiguiente(nodo);
                ultimo = nodo;
            }else {
                nodo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nodo);
            }

        }





    }

    public void addFirst(T value){
        Node<T> nodo = new Node(value);
        if(!isEmpty()){
            nodo.setSiguiente(primero);
            primero = nodo;
        }else{
            primero = nodo;
            ultimo = primero;
        }
        size++;
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

    public T dequeue() throws ColaVaciaException {
        if (!isEmpty()) {
           Node<T> temp = ultimo;
           removeLast();
            size-=1;
            return temp.getValue();
        }else{
            throw new ColaVaciaException();
        }
    }

    public void removeLast(){
        Node<T> temp = primero;
        if(primero.getSiguiente()==null){
            primero = null;
            ultimo = null;
        }else{
            while(temp.getSiguiente().getSiguiente() != null){
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(null);
            ultimo = temp;
        }

    }

    public int getSize() {
        return size;
    }

}
