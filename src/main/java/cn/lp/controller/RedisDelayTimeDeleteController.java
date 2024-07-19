package cn.lp.controller;

import cn.lp.event.pub.RedisDelEventPub;
import cn.lp.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.controller
 * @Author: lp
 * @CreateTime: 2024-07-08  14:18
 * @Description: TODO
 * @Version: 1.0
 */

@Slf4j
@RestController
@RequestMapping("redisDelayTimeDeleteController")
public class RedisDelayTimeDeleteController {
    private BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>();
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedisDelEventPub redisDelEventPub;
    @PostMapping("update")
    public void update() throws InterruptedException {
        //删除缓存
        redisUtils.del("name");
        //模拟修改数据库
        Thread.sleep(1000);
        redisDelEventPub.publish("1","name","5000");
//        blockingDeque.add("name");
    }
//    @Scheduled(fixedDelay = 100)
//    public void delete() {
//        log.info("定时任务执行");
//        String name = blockingDeque.poll();
//        if (name != null) {
//            log.info("删除缓存：{}", name);
//            redisUtils.del(name);
//        }
//    }

    @GetMapping("getNameData")
    public void getNameData() {
//  模拟缓存没有放入缓存
        boolean b = redisUtils.set("name", "迅力联");
        if (b) {
            log.info("放入缓存");
        }
    }

}
