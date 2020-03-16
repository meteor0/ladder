package com.liuxin.ladder.aop.redis;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * redis存储 APO实现
 *
 * @author meteor
 */
@Aspect
@Component
public class EnableRedisAspect {

    @Autowired
    StringRedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "@annotation(com.liuxin.ladder.aop.redis.EnableRedis)")
    private void pointcut() {
    }

    @Around(value = "pointcut() && @annotation(enableRedis)")
    public Object processLog(ProceedingJoinPoint joinPoint, EnableRedis enableRedis) throws Throwable {
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        String keyName = null;
        int expireTimes = 0;
        try {
            String prefix = enableRedis.prefix();
            expireTimes = enableRedis.expireTimes();
            String[] params = enableRedis.params();
            if (params.length > 0 && params != null) {
                keyName = RedisUtil.returnRedisKey(joinPoint, prefix, params);
            } else {
                keyName = RedisKeyConstant.SPLIT + prefix;
            }
            String result = getResultFromRedis(keyName);
            if (StringUtils.isNotBlank(result)) {
                return RedisUtil.getValueActualTypeData(joinPoint, result);
            }
            /**
             * 执行调用方法,并获取数据放到redis中
             */
            if (obj == null) {
                obj = joinPoint.proceed(args);
                //赋值
                if (keyName != null && obj != null) {
                    String value = JSON.toJSONString(obj);
                    setResultToRedis(keyName, value, expireTimes);
                    logger.debug("设置缓存key:{}，value:{}", keyName, value);
                }
            }
        } catch (Throwable e) {
            logger.error("redis切面缓存数据错误:{}", e);
        }
        return obj;
    }


    /**
     * 从redis中获取数据
     *
     * @param key
     * @return
     */
    private String getResultFromRedis(String key) {
        String result = redisTemplate.opsForValue().get(key);
        logger.debug("从redis中获取数据 key:{},result:{}", key, result);
        return result;
    }

    /**
     * 将数据存储到redis
     *
     * @param key
     * @param value
     * @param expireTimes
     */
    private void setResultToRedis(String key, String value, int expireTimes) {
        if (key != null && value != null) {
            if (-1 == expireTimes) {
                redisTemplate.opsForValue().set(key, value);
            } else {
                redisTemplate.opsForValue().set(key, value, expireTimes, TimeUnit.SECONDS);
            }
        }
    }
}