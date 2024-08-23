package cn.lp.springproxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.springproxy
 * @Author: lp
 * @CreateTime: 2024-08-01  14:35
 * @Description: TODO  cglib代理
 * @Version: 1.0
 */
public class MyServiceInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before operation");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("After operation");
        return result;
    }
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyGglibService.class);
        enhancer.setCallback(new MyServiceInterceptor());
        MyGglibService proxy = (MyGglibService) enhancer.create();
        proxy.performOperation();
    }
}
