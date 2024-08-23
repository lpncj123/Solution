package cn.lp.iocseriano;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.iocseriano
 * @Author: lp
 * @CreateTime: 2024-08-20  18:23
 * @Description: TODO
 * @Version: 1.0
 */
public interface PaymentStrategy {
    void pay(int amount);
}
