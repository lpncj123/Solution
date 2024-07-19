package cn.lp.controller;

import cn.lp.SeckillLua.SeckillLua;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.controller
 * @Author: lp
 * @CreateTime: 2024-07-09  15:05
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("redisLuaMiaoShaController")
public class RedisLuaMiaoShaController {
    @Autowired
    private SeckillLua seckillLua;
    @PutMapping("redisLuaMiaoSha")
    public void redisLuaMiaoSha() {
        boolean purchase = seckillLua.purchase("1009", 1);
        if (purchase) {
            log.info("秒杀成功");
        }else{
            log.info("库存不足");
        }
    }

    @PutMapping("initShppingNum")
    public void initShppingNum() {
        seckillLua.initializeStock("1009", 100000);
    }
}
