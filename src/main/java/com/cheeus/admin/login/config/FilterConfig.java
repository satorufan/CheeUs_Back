package com.cheeus.admin.login.config;

import com.cheeus.admin.login.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 URL 패턴에 필터 적용
        return registrationBean;
    }
}
