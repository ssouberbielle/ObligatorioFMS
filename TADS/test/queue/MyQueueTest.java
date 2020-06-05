package queue;

import exceptions.ColaVaciaException;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    @Test
    public void insert() {
        MyQueue<Integer> myQueueList = new Queue();
        myQueueList.enqueue(3);
        myQueueList.enqueueRight(2);
        myQueueList.enqueueRight(3);
        myQueueList.enqueueRight(5);
        myQueueList.enqueueRight(6);
        myQueueList.enqueue(8);
        myQueueList.showList();

        try {
            myQueueList.dequeueLeft();
            myQueueList.dequeue();
        } catch (ColaVaciaException e) {
            fail();
        }
        myQueueList.showList();

        assertEquals(4, myQueueList.getSize());

        MyPriorityQueue<Integer> q1 = new Queue<Integer>();
        q1.enqueue(3);
        q1.enqueue(4);
        q1.enqueueWithPriority(6, 1);
        q1.enqueueWithPriority(2, 2);
        q1.enqueueWithPriority(8, 6);
        q1.enqueueWithPriority(1, 10);
        q1.showList();
        assertEquals(6, q1.getSize());
    }

}