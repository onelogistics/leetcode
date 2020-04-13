package basic.QueueAndStack;

import java.util.Stack;

public class MinStack {
    private Stack<Long> stack=new Stack<>();
    private long min;
    /** initialize your data structure here. */
    public MinStack() {
    }

    public void push(int x) {
        if(stack.isEmpty()) {
            stack.push(0L);
            min=x;
        }else {
            stack.push(x-min);
            if(x<min) min=x;
        }
    }
    public void pop() {
        if(stack.isEmpty()) return;
        long x=stack.pop();
        if(x<0) min=min-x;
    }

    public int top() {
        long x=stack.peek();
        if(x>0) return (int) (min+x);
        else return (int) min;
    }

    public int getMin() {
        return (int) min;
    }
}
