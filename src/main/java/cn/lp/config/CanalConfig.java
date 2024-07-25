package cn.lp.config;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.config
 * @Author: lp
 * @CreateTime: 2024-07-25  15:56
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class CanalConfig {

    @Bean
    public CanalConnector canalConnector() {
        // 创建Canal连接
        return CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1", 11111), "example", "", "");
    }
}
