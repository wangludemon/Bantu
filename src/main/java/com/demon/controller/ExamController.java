package com.demon.controller;

import com.demon.pojo.ExamResult;
import com.demon.pojo.MCQs;
import com.demon.pojo.Question;
import com.demon.service.ExamService;
import com.demon.service.MCQsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @Autowired
    MCQsService mcQsService;

    private List<Question> single;
    private List<Question> multi;

    private ExamResult examResult;

    @RequestMapping("/exam.html")
    public String startExam(@RequestParam("exam_number") String num,
                            HttpServletRequest request,
                            Model model){
        List<Question> questions = examService.startExam(num);
        single = examService.singleQuestion(questions);
        multi = examService.multiQuestion(questions);

        examResult = new ExamResult();
        examResult.setUsername(request.getSession().getAttribute("loginUser").toString());
        examResult.setP_id(examService.transformNumber(num));
        examResult.setStart_time(new Date());

        model.addAttribute("singleQuestion", single);
        model.addAttribute("multiQuestion", multi);
        return "StudentPage/Exam/examPage.html";
    }

    @RequestMapping("/examResult.html")
    public String examResult(HttpServletRequest request, Model model) {
//记录正确题数
        model.addAttribute("singleQuestion", single);
        model.addAttribute("multiQuestion", multi);

        int correct = 0;

        //单选
        List<String> singleAnswer = new ArrayList<>();

        for (int i = 0; i < single.size(); i++) {
            int id = single.get(i).getQ_id();
            //获取题号为（id）的题目的对应答案
            String answer = request.getParameter(String.valueOf(id));
            singleAnswer.add(answer);

            if (answer.equals(single.get(i).getAnswer()))
                correct++;
            else {
                //加入错题库
                String username = request.getSession().getAttribute("loginUser").toString();
                mcQsService.addMCQs(new MCQs(username, id, answer));
            }
        }

        //多选
        List<String> multiAnswer = new ArrayList<>();

        for (int i = 0; i < multi.size(); i++) {
            int id = multi.get(i).getQ_id();

            String[] answer = request.getParameterValues(String.valueOf(id));
            String a = "";
            for (int j = 0; j < answer.length; j++) {
                a = a + answer[j];
            }
            multiAnswer.add(a);

            if (a.equals(multi.get(i).getAnswer()))
                correct++;
            else {
                String username = request.getSession().getAttribute("loginUser").toString();
                mcQsService.addMCQs(new MCQs(username, id, a));
            }

        }


        model.addAttribute("singleAnswer", singleAnswer);
        model.addAttribute("multiAnswer", multiAnswer);

        model.addAttribute("result", String.valueOf(correct));
        int total = single.size() + multi.size();

        model.addAttribute("total",String.valueOf(total));
        return "StudentPage/Exam/examResult.html";
    }

}
