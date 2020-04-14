package com.liuxin.ladder.redislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class RedisLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String RELEASE_LOCK_LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    private static final String LOCK_LUA_SCRIPT = "if redis.call('setNx', KEYS[1], ARGV[1]) == 1 then return redis.call('pexpire', KEYS[1], ARGV[2]) else return 0 end";

    public void lock(){
        String value = "123";
        String key = java.util.UUID.randomUUID().toString();
        int expireTime = 1000;
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
        Object result = stringRedisTemplate.execute(redisScript, lockKey, value, expireTime);
    }

    public void reLeaseLock(){
        String value = "123";
        String key = java.util.UUID.randomUUID().toString();
         /** 释放锁lua脚本 */
        // 指定 lua 脚本，并且指定返回值类型
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>(RELEASE_LOCK_LUA_SCRIPT,Long.class);
        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        Long result = stringRedisTemplate.execute(redisScript, Collections.singletonList(key),value);
    }



}

