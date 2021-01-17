package basic.QueueAndStack;

import java.util.Stack;

public class NiBoLan {
    public static void main(String[] args) {
        String str=new NiBoLan().niBoLan("2-(2-1)+3-(2-10)");
        System.out.println(new NiBoLan().calNiBoLan(str));
    }
    //后缀表达值计算值
    public int calNiBoLan(String str) {
        Stack<Integer> numStack=new Stack<>();
        int num=0;
        for (int i=0;i<str.length();i++) {
            char c=str.charAt(i);
            if(Character.isDigit(c)) {
                num=c-'0';
                while (i+1<str.length() && str.charAt(i+1)!=' ') {
                    num=10*num+(str.charAt(i+1)-'0');
                    i++;
                }
                numStack.push(num);
                i++;//略过空格
            }else {
                int b=numStack.pop();
                int a=numStack.pop();
                int tmp;
                switch (c) {
                    case '*':
                        tmp=a*b;
                        break;
                    case '/':
                        tmp=a/b;
                        break;
                    case '+':
                        tmp=a+b;
                        break;
                    case '-':
                        tmp=a-b;
                        break;
                    default:
                        tmp=0;
                        break;
                }
                numStack.push(tmp);
            }
        }
        return numStack.pop();
    }

    /**
     * 中缀表达式转后缀表达式
     * @param str
     * @return
     */
    public String niBoLan(String str) {
        StringBuilder sb=new StringBuilder();
        Stack<Character> opStack=new Stack<>();
        for (int i=0;i<str.length();i++) {
            char c=str.charAt(i);
            if(c==' ') continue;
            if(Character.isDigit(c)) {
                sb.append(c);
                while (i+1<str.length() && Character.isDigit(str.charAt(i+1))) {
                    sb.append(str.charAt(i+1));
                    i++;
                }
                //数字之间以空格分隔
                sb.append(' ');
            }else {
                char tmp;
                if(c=='(') opStack.push(c);
                if(c==')') while ((tmp=opStack.pop())!='(') sb.append(tmp);
                if(c=='*' || c=='/' || c=='+' || c=='-') {
                    //扫描到的运算符优先级大于栈顶，直接压栈。
                    while (!opStack.isEmpty() && getPriority(c)<=getPriority((opStack.peek()))) {
                        sb.append(opStack.pop());
                    }
                    opStack.push(c);
                }
            }
        }
        while (!opStack.isEmpty()) {
            sb.append(opStack.pop());
        }
        return sb.toString();
    }
    int getPriority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
}
