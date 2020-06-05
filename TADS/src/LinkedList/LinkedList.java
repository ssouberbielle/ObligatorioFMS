package LinkedList;


import nodo.Node;

import java.util.Iterator;

public class LinkedList<t> implements MyList<t> {

    private Node<t> primero;
    private Node<t> ultimo;
    private int size;

    public LinkedList(){
        this.primero = null;
        this.ultimo = null;
        size = 0;
    }


    public int getSize() {
        return size;
    }


    @Override
    public void add(t value) {
        addLast(value);
    }

    public void remove(int position) throws Exception {
        if (!isEmpty()){
            if(position == 1){
                Node<t> aux = primero;
                primero = aux.getSiguiente();
                size -= 1;
            }else {
                Node<t> temp1 = primero;
                Node<t> temp2 = temp1.getSiguiente();
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
        }else {
            throw  new Exception("Error");
        }

    }

    public t get(int position) {
        int contador = 0;
        Node<t> temp = primero;
        t valueToReturn = null;
        while(contador != position){
            temp = temp.getSiguiente();
            contador++;
        }
        if(temp != null){
            valueToReturn = temp.getValue();
        }


         return valueToReturn;

    }

    public t get(t value){
        Node<t> temp = primero;
        t valueToReturn = null;
        if(contains(value)) {
            while (temp.getValue() != value) {
                temp = temp.getSiguiente();
            }
            valueToReturn = temp.getValue();
        }

        return valueToReturn;

    }


     public boolean isEmpty() {
         return primero == null;
     }

    @Override
    public void makeEmpty() {
        primero = null;
        ultimo = null;
        size = 0;
    }


    public void addFirst(t value){
        Node<t> nodo = new Node(value, primero);
        primero = nodo;
        if (ultimo == null){
            ultimo = primero ;
        }
        size++;
    }

    public void addLast(t value){
        Node<t> nodo = new Node(value);
        if (!isEmpty()){
            ultimo.setSiguiente(nodo);
            ultimo  =  nodo;
        } else {
            ultimo = nodo;
            primero = ultimo;
        }

        size++;
    }

    public void addPosicion(t value, int posicion) {
        if (!isEmpty()) {
            if (posicion == (size + 1)) {
                addLast(value);

            } else if (posicion > 1 && posicion < (size + 1)) {
                Node<t> temp1 = primero;
                Node<t> temp2 = temp1.getSiguiente();
                int contador = 1;
                while ((contador + 1) != posicion) {
                    temp1 = temp2;
                    temp2 = temp2.getSiguiente();
                    contador++;
                }
                Node<t> nodo = new Node(value, temp2);
                temp1.setSiguiente(nodo);
                size++;
            }
        } else if (posicion == 1) {
            addFirst(value);

        }
    }

    public void showList() {
        Node recorrer = primero;
        System.out.println();
        while (recorrer != null) {
            System.out.print("[" + recorrer.getValue() + "]--->");
            recorrer = recorrer.getSiguiente();
        }
    }




    public int size() {
        return this.getSize();
    }

    public Iterator<t> iterator() {

        return new MyIteratorLinkedList<>(primero);
    }

    public void remove(t value){
        Node<t> beforeSearchValue = null;
        Node<t> searchValue = this.primero;

        // Busco el elemento a eliminar teniendo en cuenta mantener una referencia al elemento anterior
        while (searchValue != null && !searchValue.getValue().equals(value)) {

            beforeSearchValue = searchValue;
            searchValue = searchValue.getSiguiente();

        }

        if (searchValue != null) { // si encontre el elemento a eliminar

            // Verifico si es el primer valor (caso borde) y no es el ultimo
            if (searchValue == this.primero&& searchValue != this.ultimo) {

                Node<t> temp = this.primero;
                this.primero = this.primero.getSiguiente(); // salteo el primero

                temp.setSiguiente(null); // quito referencia del elemento eliminado al siguiente.
                size--;

                // Verifico si es el primer valor (caso borde) y no el primero
            } else if (searchValue == this.ultimo && searchValue != this.primero) {

                beforeSearchValue.setSiguiente(null);
                this.ultimo = beforeSearchValue;
                size--;

                // Si es el primer valor y el ultimo (lista de un solo valor)
            } else if (searchValue == this.ultimo && searchValue == this.primero) {
                size=0;
                this.primero = null;
                this.ultimo = null;

            } else { // resto de los casos

                beforeSearchValue.setSiguiente(searchValue.getSiguiente());
                searchValue.setSiguiente(null);
                size--;

            }

        } else {

            // Si no es encuentra el valor a eliminar no se realiza nada

        }


    }

    @Override
    public boolean contains(t value) {
        boolean contains = false;
        Node<t> temp = primero;

        while (temp != null && !temp.getValue().equals(value)) {

            temp = temp.getSiguiente();

        }

        if (temp != null) { // Si no se llego al final de la lista, se encontro el valor

            contains = true;

        }

        return contains;
    }


}
