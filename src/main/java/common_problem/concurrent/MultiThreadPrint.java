package common_problem.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程，交替打印1到100
 */
public class MultiThreadPrint {
    private volatile AtomicInteger num = new AtomicInteger(1);
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        //最后线程没有正常退出，始终有一个线程在等待。
        MultiThreadPrint multiThreadPrint = new MultiThreadPrint();
        Thread printl = new Thread(() -> {
            multiThreadPrint.print(multiThreadPrint.condition1, multiThreadPrint.condition2);
        }, "Printer1");
        Thread print2 = new Thread(() -> {
            multiThreadPrint.print(multiThreadPrint.condition2, multiThreadPrint.condition1);
        }, "Printer2");
        printl.start();
        print2.start();
    }

    public void print(Condition runConditon, Condition waitCondition) {
        lock.lock();
        try {
            while (num.get() <= 100) {
                String name=Thread.currentThread().getName();
                System.out.println("+++" + Thread.currentThread().getName() + "+-+" + num.getAndIncrement());
                runConditon.signal();
                //调用await释放锁
                waitCondition.await();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
        //执行结束后释放掉其他锁
        lock.lock();
        runConditon.signal();
        lock.unlock();
    }
}