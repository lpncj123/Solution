package cn.lp.distributedLocks;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.distributedLocks
 * @Author: lp
 * @CreateTime: 2024-07-09  15:46
 * @Description: TODO
 * @Version: 1.0
 */
@Component
@Slf4j
public class DistributedLocks {
    @Autowired
    private RedissonClient redissonClient;
    public void payment(Map<String,String> order){
        RLock lock = redissonClient.getLock(order.get("Id"));
        //  尝试加锁，最多等待100秒，上锁后10秒自动解锁
//        if(lock.tryLock(100,10, TimeUnit.SECONDS)){
        try {
            if(lock.tryLock(10,TimeUnit.SECONDS)){
                try {
                    // 业务逻辑
//                    log.info("Lock acquired and executing business logic");
                    Thread.sleep(10000);
                } finally {
                    lock.unlock();
                }
            }else{
//                若redis获取锁失败，使用锁降级到数据库，查询加for update对数据进行锁定，防止其他事务并发修改。但如果redis挂了，那么可以配置nacos参数，进行分布式中间件的切换
                log.info("Lock not acquired：超时");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
