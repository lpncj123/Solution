package cn.lp.springproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.springproxy
 * @Author: lp
 * @CreateTime: 2024-08-01  14:29
 * @Description: TODO  JDK动态代理
 * @Version: 1.0
 */
public class MyServiceProxy implements InvocationHandler {
//    目标代理对象
    private MyService target;
    public MyServiceProxy(MyService target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before operation");
        Object result = method.invoke(target, args);
        System.out.println("After operation");
        return result;
    }

    public static void main(String[] args) {
        MyService target = new MyServiceImpl();
        MyService proxy = (MyService) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            new Class<?>[]{MyService.class},
            new MyServiceProxy(target)
        );
        proxy.performOperation();
    }
}
