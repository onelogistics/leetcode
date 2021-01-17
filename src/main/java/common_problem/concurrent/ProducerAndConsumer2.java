package common_problem.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author JunjunYang
 * @date 2020/4/28 14:47
 */
public class ProducerAndConsumer2<T> {
    private int size;
    private ReentrantLock lock=new ReentrantLock();
    private Queue<T> queue=new LinkedList<>();
    private Condition notFull=lock.newCondition();
    private Condition notEmpty=lock.newCondition();
    private static final Logger LOGGER= LoggerFactory.getLogger(ProducerAndConsumer2.class);
    public static void main(String[] args) {
        ProducerAndConsumer2<Integer> producerAndConsumer2=new ProducerAndConsumer2<Integer>(10);
        ExecutorService executorService=Executors.newFixedThreadPool(2);
        executorService.execute(()->{
            for(int i=0;i<100;i++){
                producerAndConsumer2.produce(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.execute(()->{
            while(true) {
                System.out.println(producerAndConsumer2.consume());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public  ProducerAndConsumer2(int size) {
        this.size=size;
    }

    /**
     * 生产方法
     * @param t
     */
    public void produce(T t) {
        lock.lock();
        try {
            while(queue.size()==size) {
                notFull.await();
            }
            queue.add(t);
            notEmpty.signal();
        }catch(Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }

    }

    /**
     * 消费方法
     * @return
     */
    public T consume() {
        lock.lock();
        try {
            while(queue.isEmpty()) {
                notEmpty.await();
            }
            T t=queue.poll();
            notFull.signal();
            return t;
        }catch(Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
}
