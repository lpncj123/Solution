package cn.lp.eventtransaction;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.eventtransaction
 * @Author: lp
 * @CreateTime: 2024-08-23  15:59
 * @Description: TODO
 * @Version: 1.0
 */
public class Person extends ApplicationEvent{
    private String eventId;
    private String name;
    private String age;
    public Person(Object source,String eventId,String name,String age) {
        super(source);
        this.eventId = eventId;
        this.name = name;
        this.age = age;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "Person{" +
                "eventId='" + eventId + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'';
    }
}
