package cn.lp;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp
 * @Author: lp
 * @CreateTime: 2024-07-09  18:02
 * @Description: TODO 测试基类 下次继承它即可，不需要再次配置注解
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJunit {
    public static void main(String[] args) {
        new ArrayList<>();
    }
}
