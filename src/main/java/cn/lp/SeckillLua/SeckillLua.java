package cn.lp.SeckillLua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp
 * @Author: lp
 * @CreateTime: 2024-07-09  14:51
 * @Description: TODO
 * @Version: 1.0
 */

//秒杀
@Component
public class SeckillLua {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final RedisScript<Long> stockScript;

    public SeckillLua() {
        this.stockScript = new DefaultRedisScript<>(
            "local stock = redis.call('GET', KEYS[1])\n" +
                "if tonumber(stock) > 0 then\n" +
                "    redis.call('DECR', KEYS[1])\n" +
                "    return tonumber(stock) - 1\n" +
                "else\n" +
                "    return -1\n" +
                "end", Long.class);
    }

    public boolean purchase(String productId, int userId) {
        Long stock = redisTemplate.execute(stockScript, Collections.singletonList("product:stock:" + productId));
        if (stock != null && stock >= 0) {
            // 库存扣减成功，保存订单信息
            saveOrder(productId, userId);
            return true;
        } else {
            // 库存不足，秒杀失败
            return false;
        }
    }

    private void saveOrder(String productId, int userId) {
        // 保存订单信息到数据库或其他存储系统
    }
    public void initializeStock(String productId, int stock) {
        redisTemplate.opsForValue().set("product:stock:" + productId, stock);
    }






}
