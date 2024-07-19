package cn.lp.event.listener;

import cn.lp.event.modal.EventModal;
import cn.lp.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.event.listener
 * @Author: lp
 * @CreateTime: 2024-07-08  15:36
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
public class DelayListener {
    @Autowired
    private RedisUtils redisUtils;
    @Async("asyncExecutor")
    @EventListener(EventModal.class)
    public void delayListener(EventModal eventModal) throws InterruptedException {
        log.info("延迟删除redis数据，key:{},redisName:{}，time:{}",eventModal.getEventId(),eventModal.getRedisName(),eventModal.getTime());
        Thread.sleep(Long.parseLong(eventModal.getTime()));
        redisUtils.del(eventModal.getRedisName());
        log.info("删除redis数据成功");
    }
}
