package cn.lp.controller;

import cn.hutool.core.date.DateUtil;
import cn.lp.mapper.OrderMapper;
import cn.lp.modal.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.controller
 * @Author: lp
 * @CreateTime: 2024-07-11  14:44
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("orderController")
@Slf4j
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;
    @GetMapping("getOrder")
    public void getOrder() {
        LocalDateTime startTime = DateUtil.parseDate("2023-01-01").toLocalDateTime();
        LocalDateTime endTime = DateUtil.parseDate("2025-01-01").toLocalDateTime();
        List<Order> orders = orderMapper.selectOrdersToArchiveById(startTime, endTime, 0, 10);
        orders.forEach(order -> {
            log.info("订单id:{}", order.getOrderId());
        });

    }
}
