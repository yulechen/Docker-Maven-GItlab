package cn.ersoft.sexam.common.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法级别日志
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodLog {

    /**
     * 是否记录请求参数
     */
    boolean inputLog() default true;

    /**
     * 是否记录方法返回值
     */
    boolean outputLog() default true;

    /**
     * 是否记录执行时间
     */
    boolean processTime() default true;

}
