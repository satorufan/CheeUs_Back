package com.cheeus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//        	.exposedHeaders("Set-Cookie")
            .allowedOrigins("http://localhost:3000")
            .allowedHeaders("*") // 허용할 HTTP 헤더
            .allowCredentials(true) // 인증 정보를 포함할지 여부
            .allowedMethods(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name());
    }
	
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 정적 리소스 처리
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//
//        // 모든 경로를 리액트의 index.html로 리다이렉트
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/")
//                .resourceChain(false);
//    }
	
	public void addViewControllers(ViewControllerRegistry registry) {
        // 모든 요청을 index.html로 포워딩
        registry.addViewController("/{[path:[^\\.]*}").setViewName("forward:/index.html");
    }

}
