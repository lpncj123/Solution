package cn.lp.springproxy;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.springproxy
 * @Author: lp
 * @CreateTime: 2024-08-01  14:30
 * @Description: TODO
 * @Version: 1.0
 */
public class MyServiceImpl implements MyService{
    @Override
    public void performOperation() {
        System.out.println("Performing operation");
    }
}
