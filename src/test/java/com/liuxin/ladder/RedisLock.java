package com.liuxin.ladder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class RedisLock extends LadderApplicationTests{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String RELEASE_LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private static final String LOCK_LUA_SCRIPT = "if redis.call('setNx', KEYS[1], ARGV[1]) == 1 then return redis.call('pexpire', KEYS[1], ARGV[2]) else return 0 end";

    @Test
    public void lock(){
        String value = "我是value";
        String key = "我是key";
        long expireTime = 100000;
        /** 加锁lua脚本  -- 指定执行的脚本
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        // 指定 lua 脚本
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("redis/DelKey.lua")));
        // 指定返回类型
        redisScript.setResultType(Long.class);
         */
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(LOCK_LUA_SCRIPT,Long.class);
        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        List<String> lockKey = Arrays.asList(key);
        Long result = stringRedisTemplate.execute(redisScript, lockKey, value, String.valueOf(expireTime));
        System.out.println(result==1);
    }

    @Test
    public void reLeaseLock(){
        String value = "我是value";
        String key = "我是key";
         /** 释放锁lua脚本 */
        // 指定 lua 脚本，并且指定返回值类型
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(RELEASE_LOCK_LUA_SCRIPT,Long.class);
        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        Long result = stringRedisTemplate.execute(redisScript, Collections.singletonList(key),value);
        System.out.println(result==1);
    }
}

