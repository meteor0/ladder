package com.liuxin.ladder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: liuxin79
 * @date: 2019-11-11 15:46
 */

public class RedissonLockTest extends LadderApplicationTests {

    @Autowired
    RedissonClient redissonClient;
    // 请求总数
    public static int clientTotal = 10;
    // 同时并发执行的线程数
    public static int threadTotal = 50;

    @Test
    public void redissonLockTest() throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量，此处用于控制并发的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        //闭锁，可实现计数器递减
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    //执行此方法用于获取执行许可，当总计未释放的许可数不超过threadTotal时,允许通行，否则线程阻塞等待，直到获取到许可。
                    semaphore.acquire();
                    //TODO task
                    lockDecreaseStock();
                    semaphore.release();
                } catch (Exception e) {
                    //log.error("exception", e);
                    e.printStackTrace();
                }
                //闭锁减一
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();//线程阻塞，直到闭锁值为0时，阻塞才释放，继续往下执行
        executorService.shutdown();
    }

    public static volatile Integer TOTAL = 10;

    public void lockDecreaseStock() throws InterruptedException {
        RLock lock = redissonClient.getLock("lock");
        lock.lock(4, TimeUnit.SECONDS);
        lock.lockAsync();
        if (TOTAL > 0) { TOTAL--; }
        System.out.println("======减完库存后,当前库存===" + TOTAL);
        //如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }
}
