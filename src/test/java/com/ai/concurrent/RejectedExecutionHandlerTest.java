package com.ai.concurrent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.nio.ch.ThreadPool;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池拒绝策略
 */
public class RejectedExecutionHandlerTest {
    private AtomicInteger atomicInteger;
    private CountDownLatch countDownLatch;
    @Before
    public void before() {
        atomicInteger=new AtomicInteger(0);
        countDownLatch=new CountDownLatch(7);
    }
    @Test(expected =RejectedExecutionException.class )
    public  void testAbortPolicy() {
        ThreadPoolExecutor threadPoolExecutor= getDiffRejectedThreadPool(new ThreadPoolExecutor.AbortPolicy());
        for (int i=0;i<8;i++) {
            threadPoolExecutor.execute(this::run);
        }
    }
    @Test
    public void testDiscardPolicy() throws InterruptedException {
      ThreadPoolExecutor threadPoolExecutor= getDiffRejectedThreadPool(new ThreadPoolExecutor.DiscardPolicy());
        for (int i=0;i<8;i++) {
            threadPoolExecutor.execute(this::run);
        }
        countDownLatch.await();
        Assert.assertEquals(7,atomicInteger.get());
    }
    @Test
    public void testDiscardOldestPolicy() throws IOException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor= getDiffRejectedThreadPool(new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i=0;i<8;i++) {
            threadPoolExecutor.execute(this::run);
        }
        countDownLatch.await();
    }
    @Test
    public void testCallerRunPolicy() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor= getDiffRejectedThreadPool(new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i=0;i<10;i++) {
            threadPoolExecutor.execute(this::run);
        }
        countDownLatch.await();
    }
    @Test
    public void testMyRejectedPolicy() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor= getDiffRejectedThreadPool(new MyRejectedPolicy());
        for (int i=0;i<10;i++) {
            threadPoolExecutor.execute(this::run);
        }
        countDownLatch.await();
        Assert.assertEquals(10,atomicInteger.get());
    }
    @Test(expected =RejectedExecutionException.class )
    public void testSynchronousQueue() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2,4,
                2,TimeUnit.DAYS,
                new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());
        for (int i=0;i<5;i++) {
            threadPoolExecutor.execute(this::run);
        }
        countDownLatch.await();
    }

    public ThreadPoolExecutor getDiffRejectedThreadPool(RejectedExecutionHandler handler) {
        return
        new ThreadPoolExecutor(2,4,
                2, TimeUnit.DAYS,
                new ArrayBlockingQueue<>(3),
                handler);
    }

    public void  run() {
        System.out.println(Thread.currentThread().getName()+":"+atomicInteger.incrementAndGet());
        try {
            Thread.sleep(3000);
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 自定义拒绝策略：线程池扩容一个线程
 */
class MyRejectedPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if(!executor.isShutdown()) {
            executor.setMaximumPoolSize(executor.getMaximumPoolSize()+1);
            executor.execute(r);
        }
    }
}