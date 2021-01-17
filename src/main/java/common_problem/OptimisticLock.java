package common_problem;

import sun.misc.Unsafe;

import java.io.Serializable;

/**
 * 乐观锁
 *
 * @author JunjunYang
 * @date 2020/1/8 21:46
 */
public class OptimisticLock {
    /**
     * 版本号实现
     *
     * @return
     */
    static class AccountService {
        public boolean transAccount(int delta) {
//            "select version,account from account where id = A";
//             compute account;
//             "update account set account=#{account},version=version+1 where version=#{version} and id = A"
            return true;
        }
    }

    /**
     * CAS实现
     */
    static class AtomicInteger implements Serializable {
        private volatile int value;
        private static final Unsafe unsafe = Unsafe.getUnsafe();
        private static final long valueOffset;

        static {
            try {
                valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
            } catch (Exception e) {
                throw new Error(e);
            }
        }

        public int increateAndGet() {
            for (; ; ) {
                int cur = value;
                int next = value + 1;
                if (unsafe.compareAndSwapInt(this, valueOffset, cur, next))
                    return next;
            }
        }
    }
}
