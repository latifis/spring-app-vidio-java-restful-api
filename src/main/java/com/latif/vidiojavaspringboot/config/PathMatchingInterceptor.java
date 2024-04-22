package com.latif.vidiojavaspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PathMatchingInterceptor implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Autowired
    public PathMatchingInterceptor(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .excludePathPatterns(
                        "/v1/api/auth/login",
                        "/v1/api/auth/register"
                );
    }

}
