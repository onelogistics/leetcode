package basic.QueueAndStack;

import java.util.Stack;

public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(new BasicCalculator().calculate2(" 3+4*2"));
        System.out.println(new BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    /**
     * 227. Basic Calculator II
     * 加减乘除四种符号
     * https://leetcode.com/problems/basic-calculator-ii/
     * 解法：依次压栈，直到遇到操作符时，才对上一个操作符进行处理
     *
     * @param s
     * @return
     */
    public int calculate2(String s) {
        //在运算符外额外加一个符号，避免最后一个值没有push进去
        s += '+';
        Stack<Integer> stack = new Stack();
        int result = 0;
        //上一个操作数
        int operand = 0;
        //上一个操作符
        char sign = '+';
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            if (Character.isDigit(c)) operand = operand * 10 + (c - '0');
            else {
                //遇见运算符时，根据上一个运算符的状态决定如何进行处理
                if (sign == '+') {
                    stack.push(operand);
                } else if (sign == '-') {
                    stack.push(-operand);
                } else if (sign == '*') {
                    stack.push(stack.pop() * operand);
                } else if (sign == '/') {
                    stack.push(stack.pop() / operand);
                }
                sign = c;
                operand = 0;
            }
        }
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    /**
     * 224. Basic Calculator
     * https://leetcode.com/problems/basic-calculator/
     * 加减括号，四种符号
     * Example 1:
     * Input: s = "1 + 1"
     * Output: 2
     * <p>
     * Example 2:
     * Input: s = " 2-1 + 2 "
     * Output: 3
     * <p>
     * Example 3:
     * Input: s = "(1+(4+5+2)-3)+(6+8)"
     * Output: 23
     * <p>
     * 1 <= s.length <= 3 * 105
     * s consists of digits, '+', '-', '(', ')', and ' '.
     * s represents a valid expression.
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        //正负号
        int sign = 1;
        //操作数
        int operand = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                operand = operand * 10 + c - '0';
            }else if(c=='+') {
                result+=sign*operand;
                operand=0;
                sign=1;
            }else if(c=='-') {
                result+=sign*operand;
                operand=0;
                sign=-1;
            }else if(c=='(') {
                //将括号前的结果和正负号push到栈中
                stack.push(result);
                stack.push(sign);
                //重置值
                result=0;
                sign=1;
            }else if(c==')'){
                //计算括号内的结果
                result+=sign*operand;
                //将括号前的符号pop出来
                result*=stack.pop();
                //加上括号前的结果
                result+=stack.pop();
                operand=0;
            }
        }
        result+=operand*sign;
        return result;
    }
}
