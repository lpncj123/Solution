package cn.lp.eventtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.eventtransaction
 * @Author: lp
 * @CreateTime: 2024-08-23  16:01
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class PersonPub {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Async("asyncExecutor")
    @Transactional
    public void publish(String eventId,String name,String age) {
        applicationEventPublisher.publishEvent(new Person(this,eventId,name,age));
    }
}
