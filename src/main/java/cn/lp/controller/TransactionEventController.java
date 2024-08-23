package cn.lp.controller;

import cn.lp.eventtransaction.PersonPub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.controller
 * @Author: lp
 * @CreateTime: 2024-08-23  16:05
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("transactionEventController")
@Slf4j
public class TransactionEventController {
    @Autowired
    private PersonPub personPub;
   @GetMapping("testTE")
    public void testTE(){
       log.info("发送事件");
       personPub.publish("1","testTE","2024-08-23 16:05:00");
   }
}
