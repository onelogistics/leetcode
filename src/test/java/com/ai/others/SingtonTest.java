package com.ai.others;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingtonTest {
    @Test
    public void testInnerClass() {
        Sington sington=Sington.getInstance();
        Sington3 sington3=Sington3.INSTANCE;
        sington3.setData("aaaa");
        Sington3 sington31=Sington3.INSTANCE;
        System.out.println(sington3==sington31);
    }
    @Test
    public void testAbstract() {
        Test2 test2=new Test2();
    }
}