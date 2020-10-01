package com.mmzcg.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnClass(WebMvcConfigurer.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOrigins("*")
                //设置允许的方法
                .allowedMethods("*")
                //设置允许的头信息
                .allowedHeaders("*")
                //是否允许证书 不再默认开启
                .allowCredentials(Boolean.TRUE);
    }
}
