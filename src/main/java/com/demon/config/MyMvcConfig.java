package com.demon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        //配置根目录下的
        registry.addViewController("/").setViewName("index.html");
        registry.addViewController("/index.html").setViewName("index.html");
        registry.addViewController("/login.html").setViewName("loginpage.html");
        registry.addViewController("/register.html").setViewName("registerpage.html");

       // registry.addViewController("/main.html").setViewName("hello.html");

        registry.addViewController("/main.html").setViewName("StudentPage/userpage.html");
        //registry.addViewController("/selectUnit.html").setViewName("StudentPage/Exercise/selectUnit.html");
        registry.addViewController("/enterExamNumber.html").setViewName("StudentPage/Exam/startExam.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/login.html","/bootstrap/**","/userIndex"
                ,"css/**","js/**","/register.html","/UserRegister","/test");

    }
}
