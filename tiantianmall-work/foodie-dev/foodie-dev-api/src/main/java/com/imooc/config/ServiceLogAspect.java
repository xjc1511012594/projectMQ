package com.imooc.config;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author xulei
 * @date 2020-1-7 21:04
 */
/*@Aspect
@Component*/
@Slf4j
public class ServiceLogAspect {


        @Around("execution(* com.imooc.service.impl..*.*(..))")
        public Object recordTimeLog(ProceedingJoinPoint proceedingJoinPointding) throws Throwable {
            log.info("===开始执行{}.{}===",proceedingJoinPointding.getTarget().getClass(),proceedingJoinPointding.getSignature().getName());
            //开始时间
            long start = System.currentTimeMillis();
            Object result = proceedingJoinPointding.proceed();
            //结束时间
            long end = System.currentTimeMillis();
            long time = end - start;
            if(time>3000){
                log.error("执行时间为 {} 毫秒",time);
            }else if(time>1000){
                log.info("执行时间为 {} 毫秒",time);
            }else {
                log.info("执行时间为 {} 毫秒",time);
            }
            return result;
        }
        
}
