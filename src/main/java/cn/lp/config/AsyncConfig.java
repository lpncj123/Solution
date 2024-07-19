package cn.lp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.config
 * @Author: lp
 * @CreateTime: 2024-07-08  15:57
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);  // 核心线程数
        executor.setMaxPoolSize(10);  // 最大线程数
        executor.setQueueCapacity(100);  // 队列大小
        executor.setThreadNamePrefix("Async-");  // 线程名称前缀
        executor.initialize();
        return executor;
    }
}