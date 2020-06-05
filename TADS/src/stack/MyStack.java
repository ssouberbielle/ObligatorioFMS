package stack;

import java.util.EmptyStackException;

public interface MyStack<Tipo> {
    void push(Tipo value);
    Tipo pop()throws EmptyStackException;
    Tipo top() throws EmptyStackException;
    int size();
    void showList();

}
