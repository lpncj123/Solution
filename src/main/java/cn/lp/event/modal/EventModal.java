package cn.lp.event.modal;

import org.springframework.context.ApplicationEvent;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.event.modal
 * @Author: lp
 * @CreateTime: 2024-07-08  15:28
 * @Description: TODO
 * @Version: 1.0
 */
public class EventModal  extends ApplicationEvent {
    private String eventId;
    private String redisName;
    private String time;

    public EventModal(Object source,String eventId,String redisName,String time) {
        super(source);
        this.eventId = eventId;
        this.redisName = redisName;
        this.time = time;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRedisName() {
        return redisName;
    }

    public void setRedisName(String redisName) {
        this.redisName = redisName;
    }
}
