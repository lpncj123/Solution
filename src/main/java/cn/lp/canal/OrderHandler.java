//package cn.lp.canal;
//
//import cn.lp.modal.Order;
//import org.springframework.stereotype.Component;
//import top.javatool.canal.client.annotation.CanalTable;
//import top.javatool.canal.client.handler.EntryHandler;
//// 使用canal-spring-boot-starter进行注解配置使用
//@Component
//@CanalTable(value = "orders")  //Order为要监控的表名
//public class OrderHandler implements EntryHandler<Order> {
//
//    @Override
//    public void insert(Order order) {
//        System.err.println("添加：" + order);
//    }
//
//    @Override
//    public void update(Order before, Order after) {
//        System.err.println("修改改前：" + before);
//        System.err.println("修改改后：" + after);
//    }
//
//    @Override
//    public void delete(Order Order) {
//        System.err.println("删除：" + Order);
//    }
//}
