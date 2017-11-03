package com.pursue.corner.integrated.rw;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.pursue.corner.integrated.rw.annotation.DataSource;


/**
 * 支持数据库读写分离的切面
 */
@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
public class RwDataSourceAspect {

    private static final Logger LOG = LoggerFactory.getLogger(RwDataSourceAspect.class);

    /**
     * 拦截DAO层所有的方法
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("@annotation(com.pursue.corner.integrated.rw.annotation.DataSource)")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws Throwable {
        String dataSource = dataSource(joinPoint);
        try {
            DataSourceRwContext.put(dataSource);
            return joinPoint.proceed();
        } finally {
            DataSourceRwContext.remove();
        }
    }

    /**
     * 从注解上解析出数据名
     * @param joinPoint
     * @return
     */
    protected String dataSource(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            try {
                MethodSignature methodSignature = (MethodSignature) signature;
                Object target = joinPoint.getTarget();
                Method method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
                if (method.isAnnotationPresent(DataSource.class)) {
                    String dataSource = method.getAnnotation(DataSource.class).value();
                    LOG.debug("{} route to {} dataSource", method.getName(), dataSource);
                    return dataSource;
                }
            } catch (Exception e) {
                LOG.error("parse dataSource name from @DataSource error.", e);
            }
        }
        return null;
    }
}
