package cn.lp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.service
 * @Author: lp
 * @CreateTime: 2024-08-01  14:24
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class MyService {
//propagation配置事务传播机制、isolation配置事务隔离级别
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public void test() {
        System.out.println("test");
    }
}
