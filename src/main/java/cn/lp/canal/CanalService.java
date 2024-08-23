//package cn.lp.canal;
//
//import com.alibaba.otter.canal.client.CanalConnector;
//import com.alibaba.otter.canal.protocol.CanalEntry;
//import com.alibaba.otter.canal.protocol.Message;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.List;
//
///**
// * @BelongsProject: interviewexamples
// * @BelongsPackage: cn.lp.canal
// * @Author: lp
// * @CreateTime: 2024-07-25  15:56
// * @Description: TODO
// * @Version: 1.0
// */
////canal alibaba监听binlog方案使用
//@Service
//@Slf4j
//public class CanalService {
//    private final CanalConnector canalConnector;
//
//    @Autowired
//    public CanalService(CanalConnector canalConnector) {
//        this.canalConnector = canalConnector;
//    }
//
//    @PostConstruct
//    public void start() {
//        // 连接Canal
//        canalConnector.connect();
//        // 订阅所有数据库和表
//        canalConnector.subscribe(".*\\..*");
//        // 回滚到未进行ack的地方，下次fetch的时候，可以从最后一个没有ack的地方开始拿
//        canalConnector.rollback();
//        log.info("Canal client started.");
//
//        // 开启一个线程来监听数据库变化
//        new Thread(this::process).start();
//    }
//    @PreDestroy
//    public void stop() {
//        // 断开Canal连接
//        canalConnector.disconnect();
//        log.info("Canal client stopped.");
//    }
//    private void process() {
//        while (true) {
//            try {
//                // 获取指定数量的数据
//                Message message = canalConnector.getWithoutAck(100);
//                long batchId = message.getId();
//                int size = message.getEntries().size();
//                if (batchId == -1 || size == 0) {
//                    Thread.sleep(1000); // 如果没有数据，等待1秒
//                } else {
//                    handleEntries(message.getEntries());
//                }
//                canalConnector.ack(batchId); // 提交确认
//            } catch (Exception e) {
//                log.error("Error processing Canal message", e);
//            }
//        }
//    }
//    private void handleEntries(List<CanalEntry.Entry> entries) {
//        for (CanalEntry.Entry entry : entries) {
//            if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA) {
//                try {
//                    CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
//                    for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
//                        switch (rowChange.getEventType()) {
//                            case INSERT:
//                                handleInsert(rowData);
//                                break;
//                            case UPDATE:
//                                handleUpdate(rowData);
//                                break;
//                            case DELETE:
//                                handleDelete(rowData);
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                } catch (Exception e) {
//                    log.error("Error parsing Canal entry", e);
//                }
//            }
//        }
//    }
//    private void handleInsert(CanalEntry.RowData rowData) {
//        log.info("Insert: {}", rowData);
//    }
//
//    private void handleUpdate(CanalEntry.RowData rowData) {
//        log.info("Update: {}", rowData);
//    }
//
//    private void handleDelete(CanalEntry.RowData rowData) {
//        log.info("Delete: {}", rowData.toBuilder());
//    }
//}
