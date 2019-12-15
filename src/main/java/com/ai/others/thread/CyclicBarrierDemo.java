package com.ai.others.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**循环栅栏，栅栏：当指定数量的线程到达栅栏时，一起开始执行。循环：凑够指定数量的线程执行之后，栅栏对下一波线程依旧有效
 * @author JunjunYang
 * @date 2019/11/24 11:55
 */
public class CyclicBarrierDemo {
    public static final Logger LOG= LoggerFactory.getLogger(CyclicBarrierDemo.class);
    private static int threadNum=20;
    private static CyclicBarrier cyclicBarrier=new CyclicBarrier(5,()-> System.out.println(Thread.currentThread().getName()+"到达屏障"));
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(threadNum);
        for (int i=0;i<threadNum;i++) {
            Thread.sleep(1000);
            executorService.submit(() -> {
                LOG.info("{} is ready",Thread.currentThread().getName());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                LOG.info("{} is begin do something",Thread.currentThread().getName());
            });
        }
    }
}
