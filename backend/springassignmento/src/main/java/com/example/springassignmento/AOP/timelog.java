package com.example.springassignmento.AOP;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.example.springassignmento.Repository.*;

@Aspect
@Component
public class timelog
{
    @Around("within(com.example.springassignmento..*)")
    public Object calculatetime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long initial =  System.currentTimeMillis();
        Object obj = proceedingJoinPoint.proceed();
        long end =  System.currentTimeMillis();
        System.out.println("total time taken :- "+ (end - initial));
        return obj;
    }
}
