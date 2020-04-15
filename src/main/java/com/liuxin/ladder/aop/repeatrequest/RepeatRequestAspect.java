package com.liuxin.ladder.aop.repeatrequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Component;

/**
 * @description: 定义一个切面
 * @author: liuxin79
 * @date: 2019-09-02 20:15
 */

@Aspect
@Component
public class RepeatRequestAspect {

    private Logger logger = LoggerFactory.getLogger(RepeatRequestAspect.class);

    private static final String REQUEST_METHOD = "request:method:";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 超时时间 5s
     */
    private static final int TIMEOUT = 5*1000;
    /**
     * 定义一个切点
     */
    @Pointcut("execution(* com.liuxin.ladder..*.*(..))")
    public void request() {
    }

    /**
     * @Around 环绕通知, 围绕着目标方法执行
     */
    @Around(value = "request()&& @annotation(repeatRequest)")
    public Object doBefore(ProceedingJoinPoint joinPoint, RepeatRequest repeatRequest) throws Throwable {
        long time = System.currentTimeMillis() + TIMEOUT;
        Object result = null;
        String method = repeatRequest.method();
        Object[] args = joinPoint.getArgs();
        String onlyKeyWorld = generateOnlyKeyWorld(args, method);
        boolean isNotRepeat = lock(onlyKeyWorld,String.valueOf(time));
        try {
            if(isNotRepeat){
                result = joinPoint.proceed();
            }else{
                throw new RuntimeException("重复请求");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            unlock(onlyKeyWorld,String.valueOf(time));

        }
        return result;
    }

    private String generateOnlyKeyWorld(Object[] args, String method) {
        StringBuilder params = new StringBuilder();
        params.append(method);
        for (Object arg : args) {
            params.append(arg);
        }
        return DigestUtils.sha1DigestAsHex(params.toString());
    }

    /**
     * 加锁
     * @param targetId   targetId - 唯一标志
     * @param timeStamp  当前时间+超时时间 也就是时间戳
     * @return
     */
    public boolean lock(String targetId,String timeStamp){
        if(redisTemplate.opsForValue().setIfAbsent(targetId,timeStamp)){
            // 对应setnx命令，可以成功设置,也就是key不存在
            return true;
        }

        // 判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        String currentLock = redisTemplate.opsForValue().get(targetId);
        // 如果锁过期 currentLock不为空且小于当前时间
        if(StringUtils.isNotBlank(currentLock) && Long.parseLong(currentLock) < System.currentTimeMillis()){
            // 获取上一个锁的时间value 对应getset，如果lock存在
            String preLock =redisTemplate.opsForValue().getAndSet(targetId,timeStamp);

            // 假设两个线程同时进来这里，因为key被占用了，而且锁过期了。获取的值currentLock=A(get取的旧的值肯定是一样的),两个线程的timeStamp都是B,key都是K.锁时间已经过期了。
            // 而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的timeStamp已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if(StringUtils.isNotBlank(preLock) && preLock.equals(currentLock) ){
                // preLock不为空且preLock等于currentLock，也就是校验是不是上个对应的商品时间戳，也是防止并发
                return true;
            }
        }
        return false;
    }


    /**
     * 解锁
     * @param target
     * @param timeStamp
     */
    public void unlock(String target,String timeStamp){
        try {
            String currentValue = redisTemplate.opsForValue().get(target);
            if(StringUtils.isNotBlank(currentValue) && currentValue.equals(timeStamp) ){
                // 删除锁状态
                redisTemplate.opsForValue().getOperations().delete(target);
            }
        } catch (Exception e) {
            logger.error("警报！警报！警报！redis解锁异常{}",e);
        }
    }
}
