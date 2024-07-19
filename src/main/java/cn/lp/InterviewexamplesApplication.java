package cn.lp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("cn.lp.mapper")
public class InterviewexamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewexamplesApplication.class, args);
    }

}
