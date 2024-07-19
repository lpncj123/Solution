package cn.lp.controller;

import cn.lp.myaop.Counted;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.controller
 * @Author: lp
 * @CreateTime: 2024-07-09  14:26
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("redisSlidingWindowLimitController")
public class RedisSlidingWindowLimitController {
    //zset限流接口
    @GetMapping("zSetXianLiu")
    @Counted("zsetTest")
    public void zSetXianLiu() {
//  模拟缓存没有放入缓存
    }

}
