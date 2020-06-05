package queue;

public interface MyPriorityQueue<T> extends MyQueue<T> {
    void enqueueWithPriority(T value, int prio);
}
