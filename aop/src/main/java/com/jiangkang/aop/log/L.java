package com.jiangkang.aop.log;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;



/**
 * Created by jiangkang on 2017/10/24.
 */

@Aspect
public class L {


    @Pointcut("execution(@com.jiangkang.annotations.log.DebugLog * *(..))")
    public void method(){}


    @Around("method()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        enterMethod(joinPoint);
        Object result = joinPoint.proceed();
        exitMethod(joinPoint);
        return result;
    }

    private void exitMethod(ProceedingJoinPoint joinPoint) {
        Log.d("L", "exitMethod: ");
    }

    private void enterMethod(ProceedingJoinPoint joinPoint) {
        Log.d("L", "enterMethod: ");
    }

}
