package com.liuxin.ladder;

import org.junit.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: liuxin79
 * @date: 2020-04-15 14:53
 */
public class RedissonTest extends LadderApplicationTests {

    @Resource
    RedissonClient redissonClient;

  /*  @Resource
    RedissonReactiveClient redissonReactiveClient;

    @Resource
    RedissonRxClient redissonRxClient;*/

    @Test
    public void  redissonTest(){

        RLock lock = redissonClient.getLock("111");
        try {
            lock.lock(-1, TimeUnit.SECONDS);
            lock.tryLock();
            lock.tryLock(20,TimeUnit.SECONDS);
        }catch (Exception ex){
          ex.printStackTrace();
        }finally {
            //释放锁
            lock.unlock();
        }
    }
}
