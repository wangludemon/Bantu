package com.demon.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //登录成功后要有用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");

        if(loginUser == null){
            request.setAttribute("login_msg","请先登录！");
            //转发
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }else{
            return true;
        }




    }

}
