//package com.example.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * @author HP
// * 解决跨域问题
// */
//@Configuration
////启动时自动加载
//
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowCredentials(true);
//        //开启验证
//        ;
//    }
//}
