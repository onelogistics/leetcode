package common_problem.concurrent;

import org.junit.Test;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerAndConsumer {
    private int queueSize = 10;
    private Queue<Integer> queue = new LinkedList<Integer>();
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    @Test
    public void testStartup() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        executorService.execute(producer::run);
        executorService.execute(consumer::run);
        Thread.sleep(5000);
    }

    class Consumer implements Runnable {
        volatile boolean flag = true;

        @Override
        public void run() {
            while (flag) {
                lock.lock();
                try {
                    /**
                     * 为什么是while而不是if:思考消费者被唤醒时的场景，如果使用IF语句，消费者被环境后不会再次判断
                     * queue.isEmpty()，它直接向下执行，但此时有可能queue被其他线程操作，已经为空，那么消费线程就会出现异常
                     * 而While则不会有此类问题。
                     */
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("队列为空，等待数据");
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            flag = false;
                        }
                    }
                    Integer integer = queue.poll();
                    System.out.println(String.format("弹出元素：%d, 剩余%d个元素", integer, queue.size()));
                    notFull.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer implements Runnable {
        volatile boolean flag = true;

        @Override
        public void run() {
            while (flag) {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println("队列已满，等待消费");
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            flag = false;
                        }
                    }
                    queue.add(1);
                    System.out.println(String.format("添加元素，剩余%d个元素", queue.size()));
                    notEmpty.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}