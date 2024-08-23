package cn.lp.iocseriano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.iocseriano
 * @Author: lp
 * @CreateTime: 2024-08-21  17:02
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class PaymentContext {

    private final Map<String, PaymentStrategy> paymentStrategyMap;

    @Autowired
    public PaymentContext(Map<String, PaymentStrategy> paymentStrategyMap) {
        this.paymentStrategyMap = paymentStrategyMap;
    }

    public void pay(String paymentType, int amount) {
        PaymentStrategy strategy = paymentStrategyMap.get(paymentType);
        if (strategy == null) {
            throw new IllegalArgumentException("Invalid payment type: " + paymentType);
        }
        strategy.pay(amount);
    }
}
