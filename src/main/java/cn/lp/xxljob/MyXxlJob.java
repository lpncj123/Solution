package cn.lp.xxljob;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.xxljob
 * @Author: lp
 * @CreateTime: 2024-07-08  16:58
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
public class MyXxlJob {
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    // SEGMENT 分段数
    private static final int SEGMENT =200;

    public void process(){}
    public void segmentation(){

    }
//    模拟根据产品类型查询响应数据 threadNum 线程数
    public List<Map<String,String>> scanInitMessages(int threadNum){
        log.info("扫描初始化消息线程数:{}",threadNum);
        List<Map<String,String>> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Map<String,String> map = new HashMap<>(16);
            map.put("id", String.valueOf(i+1));
            map.put("name","lp"+i);
            map.put("age","18");
            //帮我写一个如果i大于0小于250，那么就put1001，否则put2001
            if(i>0&&i<250){
                map.put("id","1001");
            }else if(i>=250&&i<500){
                map.put("id","2001");
            }else if(i>=500&&i<750){
                map.put("id","3001");
            }else if(i>=750&&i<1000){
                map.put("id","4001");
            }
            list.add(map);
        }
        log.info("分片后集合:{}",list.stream().filter(map -> map.get("id").charAt(0)==String.valueOf(threadNum+1).charAt(0)).collect(Collectors.toList()));
        return list.stream().filter(map -> map.get("id").charAt(0)==String.valueOf(threadNum+1).charAt(0)).collect(Collectors.toList());
    }
    @XxlJob("MySelfJob")
    public void mySelfJob() {
        for (int i = 0; i < threadPoolExecutor.getMaximumPoolSize(); i++) {
            List<Map<String, String>> maps = this.scanInitMessages(i);
            threadPoolExecutor.execute(() -> {
                for (Map<String, String> map : maps) {
                    log.info("线程:{}执行:{}",Thread.currentThread().getName(),map.get("id"));
                }
            });
        }
        log.info("xxl-job执行成功");
    }
    @XxlJob("MySelfJobFenPian")
    public void mySelfJobFenPian() {
        int index = XxlJobHelper.getShardIndex();
        log.info("xxl-job分片执行第:{}片",index);
    }
}
