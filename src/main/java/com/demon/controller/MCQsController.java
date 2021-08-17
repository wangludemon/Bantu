package com.demon.controller;

import com.demon.pojo.Question;
import com.demon.service.MCQsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MCQsController {
    /**错题库**/

    @Autowired
    MCQsService mcQsService;


    @RequestMapping("/MCQsLibrary.html")
    public String wrongQuestionLibrary(HttpServletRequest request, Model model){
        String username = request.getSession().getAttribute("loginUser").toString();
        List<Question> questions = mcQsService.getWrongQuestion(username);
        model.addAttribute("questions",questions);
        return "StudentPage/MCQs/WrongQuestionLibrary.html";
    }

    //
    @RequestMapping("/redoMCQs.html")
    public String redoWrongQuestion(Model model, HttpServletRequest request,
                                    @RequestParam("q_id") int q_id){
        Question question = mcQsService.getQuestionById(q_id);
        model.addAttribute("q",question);

        return "StudentPage/MCQs/RedoWrongQuestion.html";
    }

    //错题重做
    @RequestMapping("/redoResult.html")
    public String redoResult(HttpServletRequest request, Model model){
        String q_id = request.getParameter("q_id");
        System.out.println("问题id是");
        System.out.println(q_id);
        Question question = mcQsService.getQuestionById(Integer.parseInt(q_id));

        String answer = "";
        if(question.getQ_E() == null){
            answer = request.getParameter(q_id);
        }else{
            String[] temp = request.getParameterValues(q_id);
            String a = "";
            for(int i=0;i<temp.length;i++){
                a= a+temp[i];
            }
            answer = a;
        }

        model.addAttribute("answer",answer);

        model.addAttribute("q",question);
        return "StudentPage/MCQs/RedoResult.html";
    }

    //删除错题
    @RequestMapping("/deleteMCQs.html")
    public String deleteMCQs(HttpServletRequest request){

        String res = request.getParameter("choice");

        if(res.equals("Yes")){
            System.out.println("delete");
            String username = request.getSession().getAttribute("loginUser").toString();
            String q_id = request.getParameter("q_id");

            mcQsService.deleteMCQs(username,Integer.parseInt(q_id));
        }


        return "redirect:/MCQsLibrary.html";
    }

}
