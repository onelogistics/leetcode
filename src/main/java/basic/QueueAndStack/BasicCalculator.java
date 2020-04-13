package basic.QueueAndStack;

import java.util.Stack;

public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(new BasicCalculator().calculate(" 3+4*2"));
    }
    public int calculate(String s) {
        //额外加一个符号，避免最后一个值没有push进去
        s+='+';
        Stack<Integer> stack=new Stack();
        int result=0;
        int operand=0;
        char sign='+';
        for(char c:s.toCharArray()) {
            if(c==' ') continue;
            if(Character.isDigit(c)) operand=operand*10+(c-'0');
            else {
                if(sign=='+') stack.push(operand);
                if(sign=='-') stack.push(-operand);
                if(sign=='*') stack.push(stack.pop()*operand);
                if(sign=='/') stack.push(stack.pop()/operand);
                sign=c;
                operand=0;
            }
        }
        while(!stack.isEmpty()) result+=stack.pop();
        return result;
    }
}
