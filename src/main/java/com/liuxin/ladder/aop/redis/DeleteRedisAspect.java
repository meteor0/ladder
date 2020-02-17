package com.liuxin.ladder.aop.redis;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


/**
 * redis存储 APO实现
 *
 * @author meteor
 */
@Aspect
@Component
public class DeleteRedisAspect {

    @Autowired
    StringRedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "@annotation(com.liuxin.ladder.aop.redis.DeleteRedis)")
    private void pointcut() {
    }

    @Around(value = "pointcut() && @annotation(deleteRedis)")
    public Object processLog(ProceedingJoinPoint joinPoint, DeleteRedis deleteRedis) throws Throwable {
        // 定义返回对象、得到方法需要的参数
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        String keyName = null;
        try {
            String prefix = deleteRedis.prefix();
            String[] params = deleteRedis.params();
            if(params.length>0 && params != null){
                keyName = RedisUtil.returnRedisKey(joinPoint, prefix, params);
            }else{
                keyName = RedisKeyConstant.SPLIT+prefix;
            }
            /**
             * 删除redis中key
             */
            deleteResultFromRedis(keyName);
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            logger.error("统计某方法执行耗时环绕通知出错", e);
        }
        return obj;
    }


    /**
     * 从redis中获取数据
     *
     * @param key
     * @return
     */
    private void deleteResultFromRedis(String key) {
        logger.debug("从redis中删除数据 key:{}", key);
         redisTemplate.delete(key);
    }
}