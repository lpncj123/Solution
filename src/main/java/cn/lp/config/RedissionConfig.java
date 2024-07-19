package cn.lp.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.config
 * @Author: lp
 * @CreateTime: 2024-07-09  15:50
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class RedissionConfig {

        @Value("${spring.redis.host}")
        private String redisHost;

        private int port = 6379;

        @Bean
        public RedissonClient getRedisson() {
            Config config = new Config();
            config.useSingleServer().
                setAddress("redis://" + redisHost + ":" + port);
            config.setCodec(new JsonJacksonCodec());
            return Redisson.create(config);
        }

}
