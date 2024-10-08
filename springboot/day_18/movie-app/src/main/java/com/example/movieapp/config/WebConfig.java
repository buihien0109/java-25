package com.example.movieapp.config;

import com.example.movieapp.config.interceptor.AuthenticationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns(
                        "/api/reviews", "/api/reviews/**", "/api/favorites",
                        "/api/favorites/**", "/api/users", "/api/users/**",
                        "/thong-tin-ca-nhan", "/phim-yeu-thich", "/lich-su-xem-phim"
                );
    }
}
