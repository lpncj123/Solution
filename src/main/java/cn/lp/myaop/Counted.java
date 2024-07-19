package cn.lp.myaop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @BelongsProject: interviewexamples
 * @BelongsPackage: cn.lp.myaop
 * @Author: lp
 * @CreateTime: 2024-07-09  13:57
 * @Description: TODO
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Counted {
    String value() default "";
}
