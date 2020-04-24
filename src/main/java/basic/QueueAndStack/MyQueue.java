package basic.QueueAndStack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 用栈实现队列，解法类似于用队列实现栈，都是用两个设定的数据结构在push时做一次数据的转换。
 */
public class MyQueue {
    private Stack<Integer> stack =new Stack<Integer>();
    private Stack<Integer> temp =new Stack<Integer>();
    @Test
    public void test() {
        MyQueue myQueue=new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        Assert.assertEquals(1,myQueue.peek());
        Assert.assertEquals(1,myQueue.pop());
        Assert.assertEquals(2,myQueue.pop());
        Assert.assertEquals(3,myQueue.peek());
    }
    public MyQueue() {
    }
    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack.isEmpty()) {
            temp.add(stack.pop());
        }
        stack.add(x);
        while (!temp.isEmpty()) {
            stack.add(temp.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}
