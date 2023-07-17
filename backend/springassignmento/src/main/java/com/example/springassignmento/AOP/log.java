//package com.example.springassignmento.AOP;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class log
//{
//    @Pointcut()
//    Logger logger = LoggerFactory.getLogger(log.class);
//    @Around("@annotation(org.springframework.stereotype.Component.Repository *)")
//    public Object trackTime(ProceedingJoinPoint pjp) throws Throwable{
//        long startTime = System.currentTimeMillis();
//        Object obj = pjp.proceed();
//        long endTime = System.currentTimeMillis();
//        logger.info("\nMethod name: "+pjp.getSignature()+"\nTime taken to execute :"+(endTime-startTime));
//        return obj;
//    }
//}