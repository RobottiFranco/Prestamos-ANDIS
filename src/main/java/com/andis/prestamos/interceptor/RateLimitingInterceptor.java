package com.andis.prestamos.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {

    @Autowired
    private CacheManager cacheManager;

    private static final int LIMIT = 3; // solicitudes concurrentes maximas
    private static final long TIME_WINDOW = 30000; // tiempo de ventana (30 segundos)

    @SuppressWarnings("unchecked")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Rate limiting interceptor triggered for: " + request.getRemoteAddr());
        String clientIp = request.getRemoteAddr();
        Cache cache = cacheManager.getCache("rateLimitCache");
        List<Long> requestTimes = cache.get(clientIp, List.class);

        if (requestTimes == null) {
            requestTimes = new ArrayList<>();
        }

        long now = System.currentTimeMillis();
        requestTimes.add(now);

        requestTimes.removeIf(time -> now - time > TIME_WINDOW);

        if (requestTimes.size() > LIMIT) {
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Rate limit exceeded. Please try again later.");
            return false;
        }

        cache.put(clientIp, requestTimes);
        return true;
    }
}
