package com.demon.controller;
import com.demon.mapper.ExamPaperMapper;
import com.demon.mapper.MCQsMapper;
import com.demon.mapper.QuestionMapper;
import com.demon.mapper.UserMapper;
import com.demon.pojo.ExamPaper;
import com.demon.pojo.MCQs;
import com.demon.pojo.Question;
import com.demon.pojo.User;
import com.demon.service.ExamService;
import com.demon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

//    @Autowired
 //   UserMapper userMapper;

//    @Autowired
//    UserService userService;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    ExamService examService;

    @Autowired
    MCQsMapper mcQsMapper;

    @Autowired
    ExamPaperMapper examPaperMapper;

    @RequestMapping("/test")
    public String test(HttpServletRequest request){

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(3);
//        list.add(5);
//
//        List<Question> q = questionMapper.queryQuestionByIdList(list);
//        for(Question question: q){
//            System.out.println(question);
//        }
        examService.startExam("202108150001");
        return "hello";
    }

}
