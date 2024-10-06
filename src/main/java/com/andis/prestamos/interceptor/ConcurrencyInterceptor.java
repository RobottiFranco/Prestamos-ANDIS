package com.andis.prestamos.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.andis.prestamos.limiter.ConcurrencyLimiter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ConcurrencyInterceptor implements HandlerInterceptor {

    @Autowired
    private ConcurrencyLimiter concurrencyLimiter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        concurrencyLimiter.acquire();
        System.out.println("permitido");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        concurrencyLimiter.release();
        System.out.println("liberado");
    }
}
