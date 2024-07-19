package cn.lp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp
 * @Author: lp
 * @CreateTime: 2024-07-09  11:13
 * @Description: TODO
 * @Version: 1.0
 */

public class ThreadPoolTest extends TestJunit{
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Test
    public void printPoolParam(){
        System.out.println(threadPoolExecutor.getMaximumPoolSize());
    }
}
