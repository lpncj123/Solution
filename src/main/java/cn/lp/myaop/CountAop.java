package cn.lp.myaop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.myaop
 * @Author: lp
 * @CreateTime: 2024-07-09  13:58
 * @Description: TODO
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class CountAop {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private final RedisScript<Long> redisScript;

    public CountAop() {
        this.redisScript = new DefaultRedisScript<>(
            "local currentTime = tonumber(ARGV[1])\n" +
                "local oneMinuteAgo = currentTime - 60000\n" +
                "local key = KEYS[1]\n" +
                "redis.call('ZREMRANGEBYSCORE', key, 0, oneMinuteAgo)\n" +
                "redis.call('ZADD', key, currentTime, currentTime)\n" +
                "local count = redis.call('ZCARD', key)\n" +
                "return count", Long.class);
    }
    @Pointcut("@annotation(counted)")
    public void countedMethod(Counted counted) {
    }
    @After("countedMethod(counted)")
    public void afterMethod(Counted counted) {
        log.info("counted:{}",counted.value());
    }
    @Around("countedMethod(counted)")
    @Order(1)
    public Object aroundMethod(ProceedingJoinPoint joinPoint, Counted counted) throws Throwable {
        String key = "method:count"+counted.value();
        long now = System.currentTimeMillis();
//        long oneMinuteAgo = now - 60000;
////        设置key，数据，时间戳
//        redisTemplate.opsForZSet().add(key,now,now);
////        删除60秒之前数据
//        redisTemplate.opsForZSet().removeRangeByScore(key,0,oneMinuteAgo);
////        统计zset里剩余数据，得到60秒之内数据
//        Long count = redisTemplate.opsForZSet().zCard(key);
//        if(count!=null&&count>10){
//            log.warn("Method {} called more than 10 times in the last 60 seconds", counted.value());
//            throw new RuntimeException("Rate limit exceeded");
//        }
        Long count = redisTemplate.execute(redisScript, Collections.singletonList(key), now);

        if (count != null && count > 10) {
            log.warn("Method {} called more than 10 times in the last 60 seconds", counted.value());
            throw new RuntimeException("Rate limit exceeded");
        }
        Object result = joinPoint.proceed();
        log.info("Method {} called {} times", counted.value(), count);
        return result;
    }
}
