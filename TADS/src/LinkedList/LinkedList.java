package LinkedList;


import nodo.Node;

import java.util.Iterator;

public class LinkedList<t> implements MyList<t> {

    private Node<t> first;
    private Node<t> last;
    private int size;
    private t value;

    public t getValue() {
        return value;
    }

    public void setValue(t value) {
        this.value = value;
    }

    public Node<t> getFirst() {
        return first;
    }

    public void setFirst(Node<t> first) {
        this.first = first;
    }

    public Node<t> getLast() {
        return last;
    }

    public void setLast(Node<t> last) {
        this.last = last;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public LinkedList(){
        this.first = null;
        this.last = null;
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
                Node<t> aux = first;
                first = aux.getSiguiente();
                size -= 1;
            }else {
                Node<t> temp1 = first;
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
        Node<t> temp = first;
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
        Node<t> temp = first;
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
         return first == null;
     }

    @Override
    public void makeEmpty() {
        first = null;
        last = null;
        size = 0;
    }


    public void addFirst(t value){
        Node<t> nodo = new Node(value, first);
        first = nodo;
        if (last == null){
            last = first ;
        }
        size++;
    }

    public void addLast(t value){
        Node<t> nodo = new Node(value);
        if (!isEmpty()){
            last.setSiguiente(nodo);
            last  =  nodo;
        } else {
            last = nodo;
            first = last;
        }

        size++;
    }

    public void addPosicion(t value, int posicion) {
        if (!isEmpty()) {
            if (posicion == (size + 1)) {
                addLast(value);

            } else if (posicion > 1 && posicion < (size + 1)) {
                Node<t> temp1 = first;
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
        Node recorrer = first;
        System.out.println();
        while (recorrer != null) {
            System.out.print("[" + recorrer.getValue() + "]--->");
            recorrer = recorrer.getSiguiente();
        }
    }




    public int size() {
        return this.getSize();
    }

    public void remove(t value){
        Node<t> beforeSearchValue = null;
        Node<t> searchValue = this.first;

        // Busco el elemento a eliminar teniendo en cuenta mantener una referencia al elemento anterior
        while (searchValue != null && !searchValue.getValue().equals(value)) {

            beforeSearchValue = searchValue;
            searchValue = searchValue.getSiguiente();

        }

        if (searchValue != null) { // si encontre el elemento a eliminar

            // Verifico si es el primer valor (caso borde) y no es el last
            if (searchValue == this.first&& searchValue != this.last) {

                Node<t> temp = this.first;
                this.first = this.first.getSiguiente(); // salteo el first

                temp.setSiguiente(null); // quito referencia del elemento eliminado al siguiente.
                size--;

                // Verifico si es el primer valor (caso borde) y no el first
            } else if (searchValue == this.last && searchValue != this.first) {

                beforeSearchValue.setSiguiente(null);
                this.last = beforeSearchValue;
                size--;

                // Si es el primer valor y el last (lista de un solo valor)
            } else if (searchValue == this.last && searchValue == this.first) {
                size=0;
                this.first = null;
                this.last = null;

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
        Node<t> temp = first;

        while (temp != null && !temp.getValue().equals(value)) {

            temp = temp.getSiguiente();

        }

        if (temp != null) { // Si no se llego al final de la lista, se encontro el valor

            contains = true;

        }

        return contains;
    }

    public Iterator<t> iterator() {

        return new MyIteratorLinkedList1(first);
    }


    private class MyIteratorLinkedList1 implements Iterator<t> {

        private Node<t> nodo;

        public MyIteratorLinkedList1(Node<t> nodo){
            this.nodo = nodo;
        }
        @Override
        public boolean hasNext() {
            return (nodo != null);
        }

        public Node<t> getNodo() {
            return nodo;
        }

        public void setNodo(Node<t> nodo) {
            this.nodo = nodo;
        }

        @Override
        public t next() {
            t valueToReturn = nodo.getValue();

            nodo = nodo.getSiguiente();

            return valueToReturn;
        }
    }
}
