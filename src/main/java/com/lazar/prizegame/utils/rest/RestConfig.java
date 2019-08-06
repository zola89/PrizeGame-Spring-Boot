package com.lazar.prizegame.utils.rest;

import com.lazar.prizegame.utils.headers.Headers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;



@Configuration
public class RestConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedHeader(Headers.SUCCESS_MESSAGE);
        config.addExposedHeader(Headers.SUCCESS_MESSAGE);
        config.addAllowedHeader(Headers.WARNING_MESSAGE);
        config.addExposedHeader(Headers.WARNING_MESSAGE);
        config.addAllowedHeader(Headers.ERROR_MESSAGE);
        config.addExposedHeader(Headers.ERROR_MESSAGE);
        config.addAllowedHeader(Headers.INFO_MESSAGE);
        config.addExposedHeader(Headers.INFO_MESSAGE);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
