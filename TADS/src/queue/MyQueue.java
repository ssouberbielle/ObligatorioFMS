package queue;
import exceptions.ColaVaciaException;

public interface MyQueue<T> {
    public void enqueue(T value);
    public void enqueueRight(T value);
    public T dequeueLeft() throws ColaVaciaException;
    public T dequeue() throws ColaVaciaException;
    public boolean isEmpty();
    void showList();
    int getSize();

}
