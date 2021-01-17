package com.ai.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    /**
     * unpark可以先于park执行
     */
    @Test
    public void test() {
        LockSupport.unpark(Thread.currentThread());
        System.out.println(Thread.currentThread().getName());
        LockSupport.park(Thread.currentThread());
        System.out.println(Thread.currentThread().getName());
    }
}
