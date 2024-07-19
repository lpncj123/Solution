package cn.lp.event.pub;

import cn.lp.event.modal.EventModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.event.pub
 * @Author: lp
 * @CreateTime: 2024-07-08  15:31
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class RedisDelEventPub {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Async("asyncExecutor")
    public void publish(String eventId, String redisName,String time) {
        applicationEventPublisher.publishEvent(new EventModal(this, eventId, redisName,time));
    }
}
