package basic.QueueAndStack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列实现栈
 * 基本思想：使用两个队列，其中一个队列用来存储真实数据，
 * 另一个临时队列用来将数据顺序颠倒重新入队列，此方法只在push时有较大的性能损耗。
 */
public class MyStack {
    public static void main(String[] args) {
        MyStack myStack=new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.pop();
        System.out.println(myStack.top());
    }
    private Queue<Integer> queue =new LinkedList<>();
    private Queue<Integer> temp =new LinkedList<>();
    /** Initialize your data structure here. */
    public MyStack() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        while (!queue.isEmpty()) {
            temp.add(queue.poll());
        }
        queue.add(x);
        while (!temp.isEmpty()) {
            queue.add(temp.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
