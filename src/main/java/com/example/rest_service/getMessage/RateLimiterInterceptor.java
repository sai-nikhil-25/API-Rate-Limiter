package com.example.rest_service.getMessage;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

    private static Logger logger = Logger.getLogger(RateLimiterInterceptor.class.getName());

    // Storage for IP address and number of requests count.
    private final Map<String,Integer> requestCounts = new ConcurrentHashMap<>();

    // constant LIMIT = 5-- 5 PER REQUEST
    private static final int LIMIT = 5;

    @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String id = request.getRemoteAddr();
        int count = requestCounts.getOrDefault(id,0)+1;
        requestCounts.put(id,count);

            if(count > LIMIT) {
                response.setStatus(429);
                response.getWriter().write("Too many requests");
                response.setContentType("text/plain");
                return false;
            }
        return true;
    }
}
