package com.demon.controller;

import com.demon.pojo.User;
import com.demon.service.ExamService;
import com.demon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ExamService examService;

    @RequestMapping("/userIndex")
    public String login(@RequestParam("Username") String username,
                        @RequestParam("Password") String password,
                        Model model, HttpSession session){
        int result = userService.loginJudge(username,password);
        System.out.println(result);
        if(result == 0){
            model.addAttribute("login_msg","用户名或密码错误！");
            return "loginpage";
        } else if(result == 1)
        {
            //跳转到教师界面
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }
        else
        {
            //跳转到学生界面
            session.setAttribute("loginUser",username);
//            model.addAttribute("subject",examService.getSubjectList());
//            model.addAttribute("unit",examService.getUnitList());
            return "redirect:/main.html";
        }
    }

    @RequestMapping("/quit")
    public String quit(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    @RequestMapping("/UserRegister")
    public String register(@RequestParam("RegisterUsername") String username,
                           @RequestParam("RegisterPassword")String password,
                           @RequestParam("ConfirmPassword") String confirm_password,
                           @RequestParam("RegisterEmail") String email,
                           @RequestParam("identity") String identity,
                           Model model)
    {
//        if(username.length()<8||username.length()>20){
//            System.out.println("1");
//            model.addAttribute("register_username","用户名不合法！");
//            return "registerpage";
//        }else if(password.length()<6||password.length()>15){
//            System.out.println("2");
//            model.addAttribute("register_password","密码不合法！");
//            return "registerpage";
//        }else if(!password.equals(confirm_password)){
//            System.out.println("3");
//            model.addAttribute("register_confirm","确认密码不相同！");
//            return "registerpage";
//        }else
        {
            System.out.println("result");
            boolean result;
            if(identity.equals("student"))
                result = userService.register(new User(username,password,email,0));
            else
                result = userService.register(new User(username,password,email,1));
            if(result)
                return "redirect:/login.html";
            else
            {
                model.addAttribute("register_username","该用户名已存在！");
                return "registerpage";
            }
        }


    }
}
