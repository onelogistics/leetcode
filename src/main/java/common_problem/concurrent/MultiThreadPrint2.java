package common_problem.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**N个线程，交替打印1到M
 * 基本思想：构造一个长度为N的Condition环形队列，每个线程都有一个专属的id，只有当计数器对N求余
 * 刚好等于id时，才是这个线程打印的时机，否则就唤醒队列中的下一个线程，自身开始等待。
 */
public class MultiThreadPrint2 {
    //打印的最大阈值
    private static int M = 20;
    //线程个数
    private static int N = 5;
    //计数器,由于每次都是持有锁时才对num进行更新，因此使用基本类型即可，无需使用原子类
    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        //初始化lock
        Lock lock = new ReentrantLock();
        Condition[] conditions = new Condition[N];
        Thread[] threads = new Thread[N];
        //初始化Conditions
        for (int i = 0; i < N; i++) {
            conditions[i] = lock.newCondition();
        }
        //初始化Threads
        for (int i = 0; i < N; i++) {
            threads[i] = new Thread(new Printer(i, lock, conditions), "Printer" + (i + 1));
        }
        //启动线程
        for (int i = 0; i < N; i++) {
            threads[i].start();
            //此处必须要sleep N秒，否则可能会出现thread2被唤醒的时候，thread4刚启动，此时会出现thread2和thread4的竞争
            //虽然由于num % conditions.length != id的判断，thread2还是会先执行，但此时已经绕开了num < M的限制，
            //最后可能会导致输出大于M的值
            Thread.sleep(30);
        }
    }

    /**
     * 打印类
     */
    static class Printer implements Runnable {
        //此线程对应ID
        private int id;
        private Lock lock;
        //N个condition组成一个环
        private Condition[] conditions;

        public Printer(int id, Lock lock, Condition[] conditions) {
            this.id = id;
            this.lock = lock;
            this.conditions = conditions;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (num < M) {
                    //num还没满足条件时，唤醒下一个线程，本线程开始等待
                    if (num % conditions.length != id) {
                        conditions[(id + 1) % conditions.length].signal();
                        conditions[id].await();
                    }
                    System.out.println("+++" + Thread.currentThread().getName() + "+-+" + ++num);
                    conditions[(id + 1) % conditions.length].signal();
                    conditions[id].await();
                }
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
