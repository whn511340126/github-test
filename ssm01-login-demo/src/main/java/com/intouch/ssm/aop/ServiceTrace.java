package com.intouch.ssm.aop;
import com.intouch.ssm.util.PropertiesUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  定义一个POJO类,封装环绕通知
 */
@Component
@Aspect
public class ServiceTrace {
    //定义时间格式常量
    private static final DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    //定义切入点
    @Pointcut("within(com.intouch.ssm.service.*)")
    private void servicePointcut(){}//该方法针对切入点注解起到一个支撑的作用

    //定义环绕通知
    @Around(value = "servicePointcut()")
    public Object traceLog(ProceedingJoinPoint pjoint) throws Throwable {
        //获得目标对象执行方法的说明信息
        String methodInfo=getTargetMethodInfo(pjoint);
        //记录业务方法执行的起始时间点
        long start=System.currentTimeMillis();
        System.out.println("AOP环绕通知:"+dateFormat.format(new Date(start))+ methodInfo+"方法开始执行...." );

        //通过ProceedingJoinPoint对象将当前的执行权移交给下一个链式对象(连接点)
        Object obj=pjoint.proceed();

        //记录业务方法执行结束的时间点
        long end=System.currentTimeMillis();
        System.out.println("AOP环绕通知:"+dateFormat.format(new Date(end))+
                methodInfo+"方法执行结束,耗时:"+(end-start)+"毫秒!" );
        return obj;
    }

    //定义获得目标对象执行方法的信息
    private String getTargetMethodInfo(ProceedingJoinPoint pjoint){
        //(1)获得目标对象的类名
        String className=pjoint.getTarget().getClass().getName();
        //(2)获得目标对象执行方法的名称
        String methodName=pjoint.getSignature().getName();
        //(3)拼接Properties文件的属性名称
        String key=className+"."+methodName;
        //(4)获得属性值
        String value = PropertiesUtil.getPropertyValue(key);
        return value;
    }
}
