package aop;


import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class OpertateAspect {
    /**
     * ①定义切入点
     * ②横切逻辑
     * ③织入
     */

    @Pointcut("@annotation(aop.RecordOperate)")
    public void pointcut(){}



    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            1,1,1,
            TimeUnit.SECONDS,new LinkedBlockingDeque<>(100));

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        threadPoolExecutor.execute(()->{
            try {
            //拿到方法签名
            MethodSignature signature = (MethodSignature)proceedingJoinPoint.getSignature();
            RecordOperate annotation = signature.getMethod().getAnnotation(RecordOperate.class);

            Class<? extends Convert> convert = annotation.convert();
                Convert logConvert = convert.newInstance();
                System.out.println("proceedingJoinPoint.getArgs()"+proceedingJoinPoint.getArgs()[0]);
                logConvert.convert(proceedingJoinPoint.getArgs()[0]);
                OperateLogD0 operateLogD0 = new OperateLogD0();
                //描述可以通过注解拿到
                operateLogD0.setDesc(annotation.desc());
                operateLogD0.setResult(result.toString());
                System.out.println("operateLogD0:"+ JSON.toJSONString(operateLogD0));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return  result;
    }
}
