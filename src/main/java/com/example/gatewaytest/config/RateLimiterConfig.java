package com.example.gatewaytest.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @Author: yangdingshan
 * @Date: 2022/7/26 15:41
 * @Description:
 */
@Configuration
public class RateLimiterConfig {

    @Primary
    @Bean(value = "pathKeyResolver")
    public KeyResolver pathKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getPath()
                        .toString()
        );
    }

    @Bean(value = "ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("X-Forwarded-For")
        );
    }

    @Bean(value = "userIdKeyResolver")
    public KeyResolver userIdKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("userId")
        );
    }
}
