package com.juandavyc.accounts.logging;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class loggingAspect {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *) || within(@org.springframework.stereotype.Service *)")
    public void applicationPointcut() {}

    @Before("applicationPointcut()")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        log.info("Entering: {}.{} with arguments: {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                methodName,
                args);
    }

    @AfterReturning(pointcut = "applicationPointcut()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        String methodName = MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName();
        log.info("Exiting: {}.{} with result: {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                methodName,
                result);
    }

    @AfterThrowing(pointcut = "applicationPointcut()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Throwable ex) {
        String methodName = MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName();
        log.error("Exception in: {}.{} -> {}",
                joinPoint.getTarget().getClass().getSimpleName(),
                methodName,
                ex.getMessage(),
                ex);
    }


}
