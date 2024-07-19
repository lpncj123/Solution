package cn.lp;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp
 * @Author: lp
 * @CreateTime: 2024-07-09  18:02
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
public class TrasmittableThreadLocalTest extends TestJunit {
    @Autowired
    private ThreadPoolExecutor  threadPoolExecutor;
    @Test
    public void testTtl() {
        try {
            ThreadLocal threadLocal = new ThreadLocal();
            threadLocal.set("我是ThreadLocal");
            InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
            inheritableThreadLocal.set("我是InheritableThreadLocal");
            TransmittableThreadLocal transmittableThreadLocal = new TransmittableThreadLocal();
            transmittableThreadLocal.set("我是TransmittableThreadLocal");
            threadPoolExecutor.execute(() -> {
                log.info("我是线程池中的线程，我获取到的ThreadLocal的值是：{}", threadLocal.get());
                log.info("我是线程池中的线程，我获取到的InheritableThreadLocal的值是：{}", inheritableThreadLocal.get());
                log.info("我是线程池中的线程，我获取到的TransmittableThreadLocal的值是：{}", transmittableThreadLocal.get());
            });
            threadLocal.remove();
            inheritableThreadLocal.remove();
            transmittableThreadLocal.remove();
        } catch (Exception e) {
        }

    }
}
