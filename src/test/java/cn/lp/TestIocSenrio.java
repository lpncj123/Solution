package cn.lp;

import cn.lp.iocseriano.PaymentContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp
 * @Author: lp
 * @CreateTime: 2024-08-21  17:36
 * @Description: TODO
 * @Version: 1.0
 */
public class TestIocSenrio extends TestJunit{
    @Autowired
    private PaymentContext paymentContext;

    @Test
    public void testCreditCardPayment() {
        paymentContext.pay("bitcoinPayment", 100);
        // 你可以通过日志或者其他方式验证输出结果。
        // 在实际应用中，可以使用更复杂的断言或模拟来验证行为。
    }
}
