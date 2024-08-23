package cn.lp.iocseriano;

import org.springframework.stereotype.Component;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.iocseriano
 * @Author: lp
 * @CreateTime: 2024-08-21  17:01
 * @Description: TODO
 * @Version: 1.0
 */
@Component("paypalPayment")
public class PaypalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}
