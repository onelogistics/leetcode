package leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 大数相加
 */
public class AddTwoNumber {
    @Test
    public void test() {
        Assert.assertEquals(add("111", "222"), "333");
        Assert.assertEquals(add("11", "222"), "233");
        Assert.assertEquals(add("99", "999999"), "1000098");
    }

    @Test
    public void test2() {
        Assert.assertEquals(add2("99", "999999"), "1000098");
        Assert.assertEquals(add2("111", "222"), "333");
        Assert.assertEquals(add2("11", "222"), "233");
    }

    public String add(String num1, String num2) {
        int ca = 0;
        int i, j;
        StringBuilder stringBuilder = new StringBuilder();
        for (i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
            //此处可以直接根据i，j的值来判断哪一个字符串更长，而不必在累加完之后额外分析较长的字符串。
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int temp = n1 + n2 + ca;
            ca = temp / 10;
            stringBuilder.append(temp % 10);
        }
        if (ca != 0) {
            stringBuilder.append(ca);
        }
        return stringBuilder.reverse().toString();
    }

    @Deprecated
    public String add2(String num1, String num2) {
        int ca = 0;
        int i, j;
        StringBuilder stringBuilder = new StringBuilder();
        for (i = num1.length() - 1, j = num2.length() - 1; i >= 0 && j >= 0; i--, j--) {
            //此处可以直接根据i，j的值来判断哪一个字符串更长，而不必在累加完之后额外分析较长的字符串。
            int n1 = num1.charAt(i) - '0';
            int n2 = num2.charAt(j) - '0';
            int temp = n1 + n2 + ca;
            ca = temp / 10;
            stringBuilder.append(temp % 10);
        }
        while (i >= 0) {
            int n1 = num1.charAt(i) - '0';
            int temp = n1 + ca;
            ca = temp / 10;
            stringBuilder.append(temp % 10);
            i--;
        }
        while (j >= 0) {
            int n2 = num2.charAt(j) - '0';
            int temp = n2 + ca;
            ca = temp / 10;
            stringBuilder.append(temp % 10);
            j--;
        }
        if (ca != 0) {
            stringBuilder.append(ca);
        }
        return stringBuilder.reverse().toString();
    }


}
