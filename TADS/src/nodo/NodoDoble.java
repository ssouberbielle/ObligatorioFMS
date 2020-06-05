package nodo;

public class NodoDoble<T> {
    private  T value;
    private NodoDoble siguiente;
    private NodoDoble anterior;

    public NodoDoble(T value){
        this.value = value;
        this.anterior = null;
        this.siguiente = null;

    }

    public NodoDoble(T value, NodoDoble siguiente, NodoDoble anterior){
        this.value = value;
        this.siguiente = siguiente;
        this.anterior = anterior;

    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public T getValue() {
        return value;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public void setValue(T value) {
        this.value = value;
    }






}
