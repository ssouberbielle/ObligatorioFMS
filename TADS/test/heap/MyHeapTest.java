package heap;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

class MyHeapTest {

    @Test
    public void insert() {
        MyHeap<Integer> heapList = new HeapMax<>(5);

        heapList.insert(13);
        heapList.insert(15);
        heapList.insert(3);
        heapList.insert(30);
        heapList.insert(4);
        heapList.insert(7);
        heapList.insert(20);



        assertEquals(new Integer(30), heapList.getMax());
        heapList.deleteMax();
        assertEquals(new Integer(20), heapList.getMax());


    }
}
