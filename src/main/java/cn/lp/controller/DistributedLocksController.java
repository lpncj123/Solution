package cn.lp.controller;

import cn.lp.distributedLocks.DistributedLocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.controller
 * @Author: lp
 * @CreateTime: 2024-07-09  14:53
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("distributedLocksController")
public class DistributedLocksController {
    @Autowired
    private DistributedLocks distributedLocks;
    @PostMapping("distributedLocksTest")
    public void distributedLocksTest() {
        Map<String,String> object= new HashMap<>(16);
        object.put("name","lp");
        object.put("Id","10099");
        distributedLocks.payment(object);
    }
}
