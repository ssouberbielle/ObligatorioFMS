package LinkedList;

import nodo.Node;
import org.junit.Test;

import static org.junit.Assert.*;


class MyListTest {

    @Test
    public void insert(){
        MyList<Integer> myLinkedList = new LinkedList<Integer>();

        myLinkedList.add(8);
        myLinkedList.add(4);
        myLinkedList.add(5);
        myLinkedList.add(2);
        myLinkedList.showList();
        try {
            myLinkedList.remove(2);
        } catch (Exception e) {
            fail();
        }

        assertEquals(3,myLinkedList.getSize());
        myLinkedList.showList();
        assertTrue(myLinkedList.contains(8));
        assertTrue(myLinkedList.contains(5));
        Node<Integer> nodo = new Node<>(5);
        assertEquals(nodo.getValue(),myLinkedList.get(2));

    }

}