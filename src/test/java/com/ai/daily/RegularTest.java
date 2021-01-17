package com.ai.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {
    @Test
    public void testReplaceSpace() {
        String s="We are happy.";
    }
    @Test
    public void testGreedMode() {
        String reg1="a+";
        String reg2="a+?";
        String str="aaab";
        Assert.assertEquals("aaa",match(reg1,str));
        Assert.assertEquals("a",match(reg2,str));
        String s1="bbb";
    }
    public String match(String regex,String str) {
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(str);
        matcher.find();
        return matcher.group();
    }
    @Test
    public void testNumber() {
        String str1="-1234";
        String str2="+123";
        String str3="1234";
        String noStr1="a1456";
        Assert.assertTrue(str1.matches("[+-]?\\d+"));
        Assert.assertTrue(str2.matches("[+-]?\\d+"));
        Assert.assertTrue(str3.matches("[+-]?\\d+"));
        Assert.assertFalse(noStr1.matches("[+-]?\\d+"));
    }
}
