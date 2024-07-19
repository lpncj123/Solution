package cn.lp;

import cn.hutool.core.date.DateUtil;
import cn.lp.mapper.OrderMapper;
import cn.lp.modal.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Semaphore;


/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp
 * @Author: lp
 * @CreateTime: 2024-07-11  14:21
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
public class DataArchivingTest extends TestJunit {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private Semaphore semaphore;

    @PostConstruct
    public void init() {
        semaphore = new Semaphore(3); // 初始化信号量，控制并发执行的任务数量
    }

    private static final int BATCH_SIZE = 10000;

    @Test
    public void test() {
        LocalDateTime startTime = DateUtil.parseDate("2023-01-01").toLocalDateTime();
        LocalDateTime endTime = DateUtil.parseDate("2025-01-01").toLocalDateTime();
        Order order = orderMapper.selectMinOrderId(startTime, endTime);
//        因为SQL条件是>，所以第一次需要从Id减1开始查，后续查询只需要拿出最大ID作为最小Id即可，表示是否第一次
        boolean flag = false;
        log.info("最小Id:{}", order.getOrderId());
        if (order == null) {
            log.warn("No orders found in the specified date range.");
            return; // 如果没有找到订单，直接返回
        }
        int minOrderId = order.getOrderId();
        List<Order> orders;
        do {
            log.info("查询开始时间: {}", DateUtil.now());
            if (!flag) {
                minOrderId = minOrderId - 1;
            }
            orders = orderMapper.selectOrdersToArchiveById(startTime, endTime, minOrderId, BATCH_SIZE);
            flag = true;
            log.info("查询结束时间: {}", DateUtil.now());
            if (!orders.isEmpty()) {
//                archiveBatch(orders);
//                minOrderId = orders.get(orders.size() - 1).getOrderId();
                try {
                    semaphore.acquire(); // 获取信号量，限制并发执行的任务数量
                    List<Order> finalOrders = orders;
                    threadPoolTaskExecutor.execute(() -> {
                        try {
                            archiveBatch(finalOrders);
                        } finally {
                            semaphore.release(); // 在归档任务完成后释放信号量
                        }
                    });
                    minOrderId = orders.get(orders.size() - 1).getOrderId();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("Semaphore acquire interrupted", e);
                }
            }
            log.info("下次查询最小Id: {}", minOrderId);
        } while (orders.size() == BATCH_SIZE);
        try {
            Thread.sleep(5000); // 等待2秒
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrupted while sleeping.");
        }
    }

    @Transactional
    public void archiveBatch(List<Order> orders) {
        log.info("开始归档订单数据，数量：{}", orders.size());
        log.info("开始时间:{}", DateUtil.now());

        // 批量插入归档数据
        orderMapper.batchInsertOrdersToArchive(orders);
        // 批量删除已归档的数据
        orderMapper.batchDeleteArchivedOrders(orders);
        log.info("结束时间:{}", DateUtil.now());

    }

}
