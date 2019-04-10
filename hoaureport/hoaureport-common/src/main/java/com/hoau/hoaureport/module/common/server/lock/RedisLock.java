package com.hoau.hoaureport.module.common.server.lock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author：高佳
 * @create：2015年6月25日 上午10:09:44
 * @description：使用注解方式实现redis分布式锁操作的设计
 */
@Target(ElementType.METHOD)  
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface RedisLock {
	//最长等待时间  
    int maxWait() default 0;  
  
    //键的过期时间  
    int expiredTime() default 60;
}
