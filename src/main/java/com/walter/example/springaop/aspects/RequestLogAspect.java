package com.walter.example.springaop.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 当每一次请求的时候（ RequestMapping ） ，记录请求的内容
 */

@Aspect
@Component
@Slf4j
public class RequestLogAspect {
    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(public * *(..))")
    public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        log.info("@annotation(org.springframework.web.bind.annotation.RequestMapping) && execution(public * *(..))");

        HttpServletRequest request = null;
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
             request = servletRequestAttributes.getRequest();
        }

        Object value;

        try {
            value = proceedingJoinPoint.proceed();
        } finally {
            if (Objects.nonNull(request)) {
                log.info(
                        "{} {} from {} user: {}",
                        request.getMethod(),
                        request.getRequestURI(),
                        request.getRemoteAddr(),
                        request.getHeader("user-id"));
            }
        }

        return value;
    }
}
