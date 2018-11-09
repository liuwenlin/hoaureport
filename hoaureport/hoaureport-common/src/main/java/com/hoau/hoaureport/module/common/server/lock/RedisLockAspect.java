package com.hoau.hoaureport.module.common.server.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author：高佳
 * @create：2015年6月25日 上午10:14:31
 * @description：通过redis实现对于分布式的环境下的并发管理（通过redis锁实现并发处理） 
 */
@Aspect
@Component
public class RedisLockAspect {
	
    /**
     * 创建一个redis锁 
     * @param proceed 实际处理对象 
     * @param redisLock RedisLock注解对象 
     * @author 高佳
     * @throws Throwable 
     * @date 2015年6月25日
     * @update 
     */
    @Around("@annotation(redisLock)")  
    public void acquireLock(final ProceedingJoinPoint proceed, final RedisLock redisLock){  
    	/*try {
    		System.out.println("添加锁");
    		Object[] args = proceed.getArgs();
    		Annotation[][] pas=((MethodSignature)proceed.getSignature()).getMethod().getParameterAnnotations();
    		for (int i = 0; i < args.length; i++) {
    			for(Annotation an:pas[i]) {  
                    if(an instanceof DeptConvert && args[i] instanceof String) {  
                    	String s = (String)args[i];
                    	args[i] = deptService.queryDeptCodeByLogistCode(s);
                    }
                }  
    			convert(args[i]);
			}
			proceed.proceed(args);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally{
			System.out.println("释放所");
		}
    	return null;*/
    }  
    
    
	public void convert(Object obj){
    	/*if(obj == null){
    		return;
    	}
    	if(obj instanceof LogisCode){
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				Annotation an = field.getAnnotation(DeptConvert.class);
				ReflectionUtils.makeAccessible(field);
				if(an != null && field.getGenericType().toString().equals(
					      "class java.lang.String")){
					String logistCode = (String)(ReflectionUtils.getField(field, obj));
					ReflectionUtils.setField(field, obj, deptService.queryDeptCodeByLogistCode(logistCode));
				}
				convert(ReflectionUtils.getField(field, obj));
			}
			return;
		}
    	//数组
    	if(obj.getClass().isArray()){
    		Object[] array = (Object[]) obj;
    		if(array.length > 0 && array[0] instanceof LogisCode){
    			for (Object object : array) {
        			convert(object);
    			}
    		}
    		return;
    	}
    	//集合
    	if(obj instanceof List){
    		List<?> list = (List<?>)obj;
    		if(! list.isEmpty() && list.get(0) instanceof LogisCode){
    			for (Object object : list) {
        			convert(object);
    			}
    		}
    		return;
    	}*/
    }
}
