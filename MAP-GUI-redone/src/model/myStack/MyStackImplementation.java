package model.myStack;

import java.util.Stack;

public class MyStackImplementation<T> implements MyStack<T>{
    private Stack<T> stack = new Stack<>();

    @Override
    public T pop() {return stack.pop();}

    @Override
    public void push(T value) {stack.push(value);}

    @Override
    public boolean isEmpty() {return stack.isEmpty();}

    @Override
    public Stack<T> getContent() {
        return stack;
    }

    @Override
    public String toString() {
        /*StringBuilder stackString = new StringBuilder();
        for(T t : stack) {
            stackString.append(t).append("\n");
        }
        return stackString.toString();*/
        String stackString = "";
        for (T t : stack)
        {
            stackString += "| " + t.toString()+"\n";
        }
        return stackString;
    }



}
