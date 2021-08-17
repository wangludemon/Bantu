package com.demon.controller;

import com.demon.mapper.ExamResultMapper;
import com.demon.pojo.ExamPaper;
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
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ExamController {

    @Autowired
    ExamService examService;

    @Autowired
    MCQsService mcQsService;

    @Autowired
    ExamResultMapper examResultMapper;



    /**
     * 智能组卷  --- 选择科目
     * 将请求时间作为生成的试卷的编号
     * @param model
     * @return
     */
    @RequestMapping("/selectSubject.html")
    public String exerciseOption(Model model) {
        model.addAttribute("subject",examService.getSubjectList());
        return "StudentPage/Exam/selectSubject.html";
    }

    //模拟考试、、智能组卷
    @RequestMapping("/MockExam.html")
    public String mockExam(@RequestParam("subject") String subject,
                           Model model,
                           HttpSession session){
        //获取相应科目题目
        List<Question> questions = examService.getAllQuestionBySubject(subject);

        /**智能组卷方法（未实现）**/

        String content = "";
        for (Question q:questions){
            content += String.valueOf(q.getQ_id());
            content += ",";
        }
        List<Question> singleQuestion = examService.singleQuestion(questions);
        List<Question> multiQuestion = examService.multiQuestion(questions);

        //用户此时请求的时间作为题目编号加入会话，页面之间传数据
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");//设置日期格式

        LocalDateTime localDateTime = LocalDateTime.now();
        String nowTime = localDateTime.format(fmt);
        /** 生成试卷编号p_id**/
        //将当前时间作为试卷编号
        model.addAttribute("p_id",nowTime);
        //获取系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        //时区的日期和时间
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        //获取时刻
        Date date = Date.from(zonedDateTime.toInstant());
        //添加试卷
        ExamPaper examPaper = new ExamPaper(nowTime,date,content);
        System.out.println(examPaper);
        examService.addExamPaper(examPaper);

        //请求访问时间作为session名
        String single = "single"+ nowTime;
        String multi = "multi"+ nowTime;

        session.setAttribute(single, singleQuestion);
        session.setAttribute(multi,multiQuestion);
        model.addAttribute("nowTime",nowTime);

        model.addAttribute("singleQuestion", singleQuestion);
        model.addAttribute("multiQuestion", multiQuestion);
        return "StudentPage/Exam/examPage.html";
    }



    /**
     * 根据试卷编号开始考试
     * @param num
     * @param request
     * @param model
     * @return 开始考试界面
     */
    @RequestMapping("/exam.html")
    public String startExam(@RequestParam("exam_number") String num,
                            HttpServletRequest request,
                            Model model,
                            HttpSession session){
        //根据试卷编号获取题目
        List<Question> questions = examService.getExamQuestions(num);

        List<Question> singleQuestion = examService.singleQuestion(questions);
        List<Question> multiQuestion = examService.multiQuestion(questions);

        //用户此时请求的时间作为题目编号加入会话，页面之间传数据
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");//设置日期格式
        String nowTime = LocalDateTime.now().format(fmt);
        String single = "single"+ nowTime;
        String multi = "multi"+ nowTime;

        //本次试题加入session，页面间传送数据
        session.setAttribute(single, singleQuestion);
        session.setAttribute(multi,multiQuestion);


        model.addAttribute("nowTime",nowTime);
        model.addAttribute("p_id",num);
        model.addAttribute("singleQuestion", singleQuestion);
        model.addAttribute("multiQuestion", multiQuestion);
        return "StudentPage/Exam/examPage.html";
    }

    /**
     * 考试结果界面
     * @param request
     * @param model
     * @return 考试结果界面
     */
    @RequestMapping("/examResult.html")
    public String examResult(HttpServletRequest request, Model model) {

        String p_id = request.getParameter("p_id");
        String nowTime = request.getParameter("nowTime");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        LocalDateTime localDateTime = LocalDateTime.parse(nowTime,fmt);

//获取系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
//时区的日期和时间
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
//获取时刻
        Date date = Date.from(zonedDateTime.toInstant());

        String single = "single"+nowTime;
        String multi = "multi"+nowTime;

        List<Question> singleQuestion = (List<Question>) request.getSession().getAttribute(single);
        List<Question> multiQuestion = (List<Question>) request.getSession().getAttribute(multi);
        model.addAttribute("singleQuestion", singleQuestion);
        model.addAttribute("multiQuestion", multiQuestion);

        String mcqs_id = "";
        int correct = 0;

        //单选
        List<String> singleAnswer = new ArrayList<>();

        for (int i = 0; i < singleQuestion.size(); i++) {
            int id = singleQuestion.get(i).getQ_id();
            //获取题号为（id）的题目的对应答案
            String answer = request.getParameter(String.valueOf(id));
            singleAnswer.add(answer);

            if (answer.equals(singleQuestion.get(i).getAnswer()))
                correct++;
            else {
                //加入错题库

                mcqs_id += (String.valueOf(id)+",");

                String username = request.getSession().getAttribute("loginUser").toString();
                mcQsService.addMCQs(new MCQs(username, id, answer));
            }
        }

        //多选
        List<String> multiAnswer = new ArrayList<>();

        for (int i = 0; i < multiQuestion.size(); i++) {
            int id = multiQuestion.get(i).getQ_id();

            String[] answer = request.getParameterValues(String.valueOf(id));
            String a = "";
            for (int j = 0; j < answer.length; j++) {
                a = a + answer[j];
            }
            multiAnswer.add(a);

            if (a.equals(multiQuestion.get(i).getAnswer()))
                correct++;
            else {
                mcqs_id += (String.valueOf(id)+",");
                String username = request.getSession().getAttribute("loginUser").toString();
                mcQsService.addMCQs(new MCQs(username, id, a));
            }

        }


        model.addAttribute("singleAnswer", singleAnswer);
        model.addAttribute("multiAnswer", multiAnswer);

        model.addAttribute("result", String.valueOf(correct));

        int total = singleQuestion.size() + multiQuestion.size();

        /**记录考试结果**/
        ExamResult examResult = new ExamResult();
        examResult.setUsername(request.getSession().getAttribute("loginUser").toString());
        examResult.setP_id(p_id);
        examResult.setGrade(correct);
        examResult.setMcqs_id(mcqs_id);
        examResult.setStart_time(date);
        examResult.setEnd_time(new Date());
        examResultMapper.addExamResult(examResult);

        model.addAttribute("total",String.valueOf(total));
        return "StudentPage/Exam/examResult.html";
    }

}
