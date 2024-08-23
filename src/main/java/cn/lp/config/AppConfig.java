package cn.lp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.config
 * @Author: lp
 * @CreateTime: 2024-08-01  14:21
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
//启用事务管理、强制使用cglib代理
@EnableTransactionManagement(proxyTargetClass = true)
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/indexexample");
        dataSource.setUsername("root");
        dataSource.setPassword("P@ssword123");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
