package cn.lp.mapper;

import cn.lp.modal.Order;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.mapper
 * @Author: lp
 * @CreateTime: 2024-07-11  14:09
 * @Description: TODO
 * @Version: 1.0
 */
public interface OrderMapper {
    List<Order> selectOrdersToArchiveById(@Param("startTime") LocalDateTime startTime,
                                       @Param("endTime") LocalDateTime endTime,
                                       @Param("lastOrderId") int lastOrderId,
                                       @Param("limit") int limit);

    void batchInsertOrdersToArchive(@Param("orders") List<Order> orders);

    void batchDeleteArchivedOrders(@Param("orders") List<Order> orders);
    Order selectMinOrderId(@Param("startTime") LocalDateTime startTime,
                           @Param("endTime") LocalDateTime endTime);
}
