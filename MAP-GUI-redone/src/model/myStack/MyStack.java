package model.myStack;

import java.util.Stack;

public interface MyStack<T> {
    T pop();
    void push(T value);
    boolean isEmpty();
    Stack<T> getContent();
}
