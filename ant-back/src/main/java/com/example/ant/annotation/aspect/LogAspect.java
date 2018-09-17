package com.example.ant.annotation.aspect;

import com.alibaba.fastjson.JSON;
import com.example.ant.annotation.ControllerLog;
import com.example.ant.annotation.RestLog;
import com.example.ant.context.SessionContext;
import com.example.ant.data.system.model.SystemUserModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 描述：切面类，暂时不将日志写进数据库，只做log打印
 * User: 曾远征
 * Date: 2018-09-17
 * Time: 16:34
 */

@Aspect
@Component
@Slf4j
public class LogAspect {

    //Rest层切点
    @Pointcut("@annotation(com.example.ant.annotation.RestLog)")
    public void RestAspect() {
    }

    //Controller层切点
    @Pointcut("@annotation(com.example.ant.annotation.ControllerLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        SystemUserModel user = (SystemUserModel) session.getAttribute(SessionContext.LOGINUSER);
        //请求的IP
        String ip = request.getRemoteAddr();
        try {
            //LOG日志输出
            log.info("=====前置通知开始=====");

            log.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.info("方法描述:" + getControllerMethodDescription(joinPoint));
            log.info("请求人:" + user.getAccount());
            log.info("请求IP:" + ip);
            log.info("=====前置通知结束=====");
        } catch (Exception e) {
            //记录本地异常日志
            log.error("=====前置通知异常=====");
            log.error("异常信息:{}", e.getMessage());
            log.info("=====前置通知结束=====");
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ControllerLog.class).description();
                    break;
                }
            }
        }
        return description;
    }

    /**
     * 异常通知 用于拦截Rest层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "RestAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        //读取session中的用户
        SystemUserModel user = (SystemUserModel) session.getAttribute(SessionContext.LOGINUSER);
        //获取请求ip
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
            }
        }
        try {
            log.info("=====异常通知开始=====");
            log.info("异常代码:" + e.getClass().getName());
            log.info("异常信息:" + e.getMessage());
            log.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            log.info("方法描述:" + getServiceMthodDescription(joinPoint));
            log.info("请求人:" + user.getAccount());
            log.info("请求IP:" + ip);
            log.info("请求参数:" + params);
            log.info("=====异常通知结束=====");
        } catch (Exception ex) {
            //记录本地异常日志
            log.error("=====异常通知异常=====");
            log.error("异常信息:{}", ex.getMessage());
            log.error("=====异常通知结束=====");
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Rest层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(RestLog.class).description();
                    break;
                }
            }
        }
        return description;
    }


}
