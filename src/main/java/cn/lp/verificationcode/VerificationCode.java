package cn.lp.verificationcode;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.verificationcode
 * @Author: lp
 * @CreateTime: 2024-07-09  17:03
 * @Description: TODO
 * @Version: 1.0
 */
@Component
@Slf4j
public class VerificationCode {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedissonClient redisClient;

    public String getVerificationCode() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 6);
    }

    public void VerificationCode(String merchantId) {
        Object o = getVerificationCode("verificationCode" + merchantId);
        if (o != null) {
            log.info("验证码还有：{}秒才能重复发送", getRemainingExpirationTime("verificationCode" + merchantId));
            return;
        }
        String s = getVerificationCode();
        redisTemplate.opsForValue().set("verificationCode" + merchantId, s, 60, TimeUnit.SECONDS);
        //发送信息，需要加分布式锁，避免重复发送，因为上面判断有可能两个线程同时判断为空重复发送短信
        log.info("验证码为：{}", s);
        RLock lock = redisClient.getLock(merchantId);
        try {
            if (lock.tryLock(60, TimeUnit.SECONDS)) {
                String verificationCode = getVerificationCode("verificationCode" + merchantId);
                Long remainingExpirationTime = getRemainingExpirationTime("verificationCode" + merchantId);
                log.info("验证码为：{},有效时间为：{}", verificationCode, remainingExpirationTime);
                if (verificationCode != null && remainingExpirationTime >= 0) {
                    log.info("发送短信验证码为：{}", verificationCode);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Long getRemainingExpirationTime(String merchantId) {
        return redisTemplate.getExpire(merchantId, TimeUnit.SECONDS);
    }

    public String getVerificationCode(String merchantId) {
        return (String) redisTemplate.opsForValue().get(merchantId);
    }
}
