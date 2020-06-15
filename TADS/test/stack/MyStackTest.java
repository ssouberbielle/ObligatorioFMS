package stack;

import org.junit.Test;

import static org.junit.Assert.*;

class MyStackTest {

    @Test
    public void insert(){
        MyStack<Integer> myStack = new Stack<>();
        myStack.push(4);
        myStack.push(3);
        myStack.push(9);
        myStack.push(2);
        myStack.push(6);
        myStack.showList();
        myStack.pop();
        myStack.showList();
        assertEquals(myStack.top(), myStack.pop());
        myStack.showList();



    }

}