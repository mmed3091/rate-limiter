package org.example.ratelimiter.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ratelimiter.service.RateLimiterService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final RateLimiterService limiter;

    public RateLimitFilter(RateLimiterService limiter) {
        this.limiter = limiter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        // extract api key from request header
        String apiKey = request.getHeader("X-API-KEY");

        // process the request through rate limiter
        if(limiter.tryAcquireTokens(apiKey, 1) == 0) {
            response.setStatus(429); // reject request
            response.getWriter().write("Too many requests.");
            return;
        }

        // accept request and pass it on to controller
        chain.doFilter(request, response);

    }


}
