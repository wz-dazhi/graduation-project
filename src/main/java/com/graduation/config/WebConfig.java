package com.graduation.config;

import com.graduation.interceptor.UserInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: graduation-project
 * @package: com.graduation.config
 * @className: WebConfig
 * @description:
 * @author: yue
 * @date: 2022/6/22
 * @version: 1.0
 */
@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry r) {
        r.addInterceptor(userInterceptor)
                .excludePathPatterns("/login.html", "/api/user/login", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html/**");
    }

}
