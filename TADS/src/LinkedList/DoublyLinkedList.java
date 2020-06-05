package LinkedList;


import nodo.NodoDoble;

import java.util.Iterator;

public class DoublyLinkedList<T> implements MyList<T> {

    private NodoDoble<T> primero;
    private NodoDoble<T> ultimo;
    private int size;

    public DoublyLinkedList(){
        this.primero = null;
        this.ultimo = null;
        size = 0;
    }

    public boolean isEmpty(){
        return primero == null;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void add(T value) {
        this.addLast(value);
    }

    public void remove(int position) throws Exception {
        if (position > getSize()){
            throw new Exception("error");
        }
        else if (!isEmpty()){
            if(position == 1){
                NodoDoble<T> aux = primero;
                primero = aux.getSiguiente();
                size--;
            }else {
                NodoDoble<T> temp1 = primero;
                NodoDoble<T> temp2 = temp1.getSiguiente();
                int contador = 1;
                while((contador+1) != position){
                    temp1 = temp2;
                    temp2 = temp2.getSiguiente();
                    contador++;
                }
                temp2 = temp2.getSiguiente();
                temp2.setAnterior(temp1);
                temp1.setSiguiente(temp2);
                size--;
            }
        }else {
            throw  new Exception("Error");
        }
    }

    public T get(int position) {
        int contador = 0;
        NodoDoble<T> temp = primero;
        while(contador != position){
            temp = temp.getSiguiente();
            contador++;
        }
        return temp.getValue();
    }

    public void showList() {
        NodoDoble<T> recorrer = primero;
        System.out.println();
        while (recorrer != null) {
            System.out.print("[" + recorrer.getValue() + "]--->");
            recorrer = recorrer.getSiguiente();
        }
    }

    public void addupward(T value){
        if (!isEmpty()) {
            if((Integer) primero.getValue() > (Integer)value){
                NodoDoble<T> nodo = new NodoDoble<T>(value);
                nodo.setSiguiente(primero);
                primero.setAnterior(nodo);
                primero = nodo;
                size++;
                return;
            }
            NodoDoble<T> actual = primero;
            while (actual.getSiguiente() != null && ((Integer) actual.getSiguiente().getValue()) < ((Integer) value)) {
                actual = actual.getSiguiente();


            }
            NodoDoble<T> nodo = new NodoDoble<T>(value);
            nodo.setAnterior(actual);
            nodo.setSiguiente(actual.getSiguiente());
            if(actual.getSiguiente() !=null){
                actual.getSiguiente().setAnterior(nodo);
            }
            actual.setSiguiente(nodo);

        }else {
            NodoDoble<T> nodo = new NodoDoble<T>(value);
            primero = nodo;
            ultimo = nodo;



        }
        size++;
    }
    public  void addLast(T value){
        if (!isEmpty()){
            NodoDoble<T> nodo = new NodoDoble<T>(value, null, ultimo);
            ultimo.setSiguiente(nodo);
            ultimo = nodo;



        }else{
            NodoDoble<T> nodo = new NodoDoble<T>(value);
        }
        size++;
    }

    public void addFirst(T value){
        if(!isEmpty()){
            NodoDoble<T> nodo = new NodoDoble<>(value,primero, null);
            primero.setAnterior(nodo);
            nodo = primero;

        }else{
            NodoDoble<T> nodo = new NodoDoble<T>(value);
        }
        size++;

    }

    public void makeEmpty() {
        primero = null;
        ultimo = null;
        size = 0;
    }


    @Override
    public Iterator<T> iterator() {
        return new MyIteratorDoublyLinkedList<>(primero);
    }

    public void remove(T type){
        NodoDoble<T> tempToRemove= primero;
        T valueToReturn = null;
        while(tempToRemove != null && valueToReturn == null){
            if(tempToRemove.getValue().equals(type)){
                valueToReturn = tempToRemove.getValue();
            }
            tempToRemove = tempToRemove.getSiguiente();
        }



    }

    public T get(T value){
        NodoDoble<T> temp = primero;
        T valueToReturn = null;
        if(contains(value)) {
            while (temp.getValue() != value) {
                temp = temp.getSiguiente();
            }
            valueToReturn = temp.getValue();
        }

        return valueToReturn;

    }

    @Override
    public boolean contains(T k) {
        NodoDoble<T> temp = primero;
        boolean ifind = false;
        while(temp.getSiguiente() != null){
            if(temp.getValue().equals(k)){
                ifind = true;
                break;
            }
            temp = temp.getSiguiente();
        }
        return ifind;
    }

}
