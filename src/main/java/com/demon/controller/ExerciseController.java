package com.demon.controller;

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
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExerciseController {

/** 科目和单元不对应 **/

    @Autowired
    ExamService examService;
    @Autowired
    MCQsService mcQsService;


    @RequestMapping("/selectUnit.html")
    public String exerciseOption(Model model) {
        model.addAttribute("subject",examService.getSubjectList());
        model.addAttribute("unit",examService.getUnitList());
        return "StudentPage/Exercise/selectUnit.html";
    }

    @RequestMapping("/exercise.html")
    public String startExercise(@RequestParam("subject") String subject,
                                @RequestParam("unit") String unit,
                                Model model, HttpSession session){
        int temp = Integer.parseInt( unit.substring(2));
        List<Question> questions = examService.getGroupQuestion(subject,temp);

        List<Question> singleQuestion = examService.singleQuestion(questions);
        List<Question> multiQuestion = examService.multiQuestion(questions);


        //用户此时请求的时间作为题目编号加入会话，页面之间传数据
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");//设置日期格式
        String nowTime = LocalDateTime.now().format(fmt);
        String single = "single"+ nowTime;
        String multi = "multi"+ nowTime;

        session.setAttribute(single, singleQuestion);
        session.setAttribute(multi,multiQuestion);


        model.addAttribute("nowTime",nowTime);
        model.addAttribute("singleQuestion", singleQuestion);
        model.addAttribute("multiQuestion", multiQuestion);

        return "StudentPage/Exercise/exercisePage.html";
    }

    @RequestMapping("exerciseResult.html")
    public String result(HttpServletRequest request, Model model){
        //记录正确题数

        String nowTime = request.getParameter("nowTime");

        String single = "single"+nowTime;
        String multi = "multi"+nowTime;

        List<Question> singleQuestion = (List<Question>) request.getSession().getAttribute(single);
        List<Question> multiQuestion = (List<Question>) request.getSession().getAttribute(multi);
        model.addAttribute("singleQuestion", singleQuestion);
        model.addAttribute("multiQuestion", multiQuestion);
        int correct = 0;

        //单选
        List<String> singleAnswer = new ArrayList<>();

        for(int i=0;i<singleQuestion.size();i++)
        {
            int id = singleQuestion.get(i).getQ_id();
            //获取题号为（id）的题目的对应答案
            String answer = request.getParameter(String.valueOf(id));
            singleAnswer.add(answer);

            if(answer.equals(singleQuestion.get(i).getAnswer()))
                correct++;
            else{
                //加入错题库
                String username = request.getSession().getAttribute("loginUser").toString();
                mcQsService.addMCQs(new MCQs(username,id,answer));
            }
        }

        //多选
        List<String> multiAnswer = new ArrayList<>();

        for(int i=0;i<multiQuestion.size();i++)
        {
            int id = multiQuestion.get(i).getQ_id();

            String[] answer = request.getParameterValues(String.valueOf(id));
            String a = "";
            for(int j=0;j<answer.length;j++)
            {
                a=a+answer[j];
            }
            multiAnswer.add(a);

            if(a.equals(multiQuestion.get(i).getAnswer()))
                correct++;
            else{
                String username = request.getSession().getAttribute("loginUser").toString();
                mcQsService.addMCQs(new MCQs(username,id,a));
            }

        }


        model.addAttribute("singleAnswer",singleAnswer);
        model.addAttribute("multiAnswer",multiAnswer);

        model.addAttribute("result",String.valueOf(correct));
        return "StudentPage/Exercise/resultPage.html";
    }
}
