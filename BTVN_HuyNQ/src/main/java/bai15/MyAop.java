package bai15;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAop {
    private boolean isNumber = false;

    @Before("execution(* bai15.MySum.sum(..))")
    public void beforeSum(JoinPoint joinPoint) {
        String str1 = (String) joinPoint.getArgs()[0];
        String str2 = (String) joinPoint.getArgs()[1];
        this.isNumber = str1.matches("-?\\d+") && str2.matches("-?\\d+");
    }

    @Around("execution(* bai15.MySum.sum(..))")
    public String beforeReturn(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = joinPoint.proceed();
        if (this.isNumber)
            return obj.toString();
        return "N/A";
    }
}