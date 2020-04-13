package basic.QueueAndStack;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    private Queue<Integer> q1=new LinkedList<>();
    private Queue<Integer> q2=new LinkedList<>();
    /** Initialize your data structure here. */
    public MyStack() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }
        q1.add(x);
        while (!q2.isEmpty()) {
            q1.add(q2.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q1.poll();
    }

    /** Get the top element. */
    public int top() {
        return q1.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}
