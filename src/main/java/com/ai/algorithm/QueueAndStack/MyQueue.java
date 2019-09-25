package com.ai.algorithm.QueueAndStack;

import java.util.LinkedList;
import java.util.Stack;

public class MyQueue {
    private Stack<Integer> s1=new Stack<Integer>();
    private Stack<Integer> s2=new Stack<Integer>();
    private int front;

    public static void main(String[] args) {
        MyQueue myQueue=new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.peek());
    }
    public MyQueue() {
    }
    /** Push element x to the back of queue. */
    public void push(int x) {
        if (s1.empty())
            front = x;
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty())
                s2.push(s1.pop());
        }
        return s2.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (!s2.isEmpty()) {
            return s2.peek();
        }
        return front;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
