package com.juandavyc.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class GatewayRoutesConfig {


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/temu/accounts/**")
                        .filters(f -> f
                                .rewritePath("/temu/accounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                        )
                        .uri("lb://accounts"))
                .route(p -> p
                        .path("/temu/orders/**")
                        .filters(f -> f
                                .rewritePath("/temu/orders/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                        )
                        .uri("lb://orders"))
                .route(p -> p
                        .path("/temu/products/**")
                        .filters(f -> f
                                .rewritePath("/temu/products/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                        )
                        .uri("lb://products"))
                .build();
    }
}
