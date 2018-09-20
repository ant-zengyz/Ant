package com.example.ant.annotation.aspect;

import com.alibaba.fastjson.JSON;
import com.example.ant.annotation.ControllerLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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

    //Controller层切点
    @Pointcut("@annotation(com.example.ant.annotation.ControllerLog)")
    public void controllerAspect() {
    }

    /*@Before("controllerAspect()")
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
            //获取所有的参数


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
    }*/

    /**
     * 环绕通知
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        Object[] args = joinPoint.getArgs();
        String params = "";
        //获取请求参数集合并进行遍历拼接
        if (args.length > 0) {
            if ("POST".equals(method)) {
                Object object = args[0];
                Map map = getKeyAndValue(object);
                params = JSON.toJSONString(map);
            } else if ("GET".equals(method)) {
                params = queryString;
            }
        }
        log.info("=====环绕通知开始=====");
        log.info("请求开始地址:" + url);log.info("请求开始地址（URI）:" + uri);
        log.info("请求开始类型:" + method);log.info("请求开始参数:" + params);
        log.info("方法描述:" + getControllerMethodDescription(joinPoint));
        Object result = joinPoint.proceed();
        log.info("请求结束返回值:" + JSON.toJSONString(result));
        log.info("=====环绕通知结束=====");
        return result;
    }

    public static Map<String, Object> getKeyAndValue(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Class userCla = (Class) obj.getClass();//得到类对象
        Field[] fs = userCla.getDeclaredFields();//得到类中的所有属性集合
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true);
            Object val = new Object();
            try {
                val = f.get(obj);
                map.put(f.getName(), val);// 设置键值
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static String getControllerMethodDescription(ProceedingJoinPoint joinPoint) throws Exception {
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

}
