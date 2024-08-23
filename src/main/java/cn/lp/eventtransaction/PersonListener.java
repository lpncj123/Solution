package cn.lp.eventtransaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.eventtransaction
 * @Author: lp
 * @CreateTime: 2024-08-23  16:03
 * @Description: TODO
 * @Version: 1.0
 */
@Component
@Slf4j
public class PersonListener {
    @Async("asyncExecutor")
    @TransactionalEventListener
    public void personListener(Person person) {
        log.info("接收事件成功:成功提交事务");

    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void personListenerBeforeCommit(Person person) {
        log.info("接收事件成功:事务提交前");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void personListenerAfterRollback(Person person) {
        log.info("接收事件成功:事务回滚");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void personListenerAfterCompletion(Person person) {
        log.info("接收事件成功:事务结束后");
    }
}
