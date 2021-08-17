package com.demon.service;

import com.demon.mapper.ExamPaperMapper;
import com.demon.mapper.QuestionMapper;
import com.demon.pojo.ExamPaper;
import com.demon.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.desktop.QuitEvent;
import java.util.*;


@Service
public class ExamService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    ExamPaperMapper examPaperMapper;

    //获得对应科目的题组
    public List<Question> getAllQuestionBySubject(String subject){
        return questionMapper.queryQuestionBySubject(subject);
    }

    public List<Question> getQuestionByIdList(List<Integer> q_id_List){
        return questionMapper.queryQuestionByIdList(q_id_List);
    }

    //获得对应科目，组别的题组
    public List<Question> getGroupQuestion(String subject, int unit){
        List<Question> temp = questionMapper.queryQuestionBySubject(subject);
        List<Question> res = new ArrayList<>();
        for(Question q: temp){
            if(q.getUnit() == unit){
                res.add(q);
            }
        }
        return res;
    }

    //获得题组中的单选题
    public List<Question> singleQuestion(List<Question> temp){
        List<Question> single = new ArrayList<>();
        for(Question q:temp){
            if(q.getQ_E() == null){
                single.add(q);
            }
        }
        return single;
    }

    public List<Question> multiQuestion(List<Question> temp){
        List<Question> multi = new ArrayList<>();
        for(Question q:temp){
            if(q.getQ_E() != null){
                multi.add(q);
            }
        }
        return multi;
    }

    //获取题目分组数（暂时不用）
    public List<Integer> getGroup(){
        List<Question> temp = questionMapper.queryQuestion();
        int[] record = new int[51];

        for (Question q: temp){
            record[q.getUnit()] = 1;
        }
        List<Integer> res = new ArrayList<>();

        for(int i=1;i<=50;i++){
            if(record[i] == 1){
                res.add(i);
            }else
                break;
        }
        return res;
    }

    //单元字符串数组，提供给前端展示
    public List<String> getUnitList(){
        List<Integer> unit = questionMapper.queryQuestionUnit();
        Collections.sort(unit);

        List<String> res = new ArrayList<>();
        for (Integer i: unit){
            String temp = "单元" + i.toString();
            res.add(temp);
        }
        return res;
    }

    //获得学科名
    public List<String> getSubjectList(){
        return questionMapper.queryQuestionSubject();
    }


    /**
     * 序列号
     * p_id=1, create_time=Mon Aug 16 01:28:10 CST 2021, content='1,2,4,5'
     * 202108150001
     * 处理试卷序列号
     * */

    public int transformNumber(String numStr){
        //解析序列号
        String subStr = numStr.substring(numStr.length() - 4 );
        System.out.println(subStr);
        String newStr = subStr.replaceAll("^0*","");
        System.out.println(newStr);
        return Integer.parseInt(newStr);
    }



    //根据试卷序列号（试卷Id）获取一套试卷的题目
    public List<Question> getExamQuestions(String p_id){
        ExamPaper examPaper = examPaperMapper.queryExamPaperById(p_id);
        String content = examPaper.getContent();
        String temp[];
        temp=content.split(",");
        List<Integer> idList = new ArrayList<>();
        for (String s : temp) {
            idList.add(Integer.parseInt(s));
        }

        List<Question> q = questionMapper.queryQuestionByIdList(idList);
        for(Question question: q){
            System.out.println(question);
        }

        return q;

    }

    //添加试卷
    public void addExamPaper(ExamPaper examPaper){
        examPaperMapper.addExamPaper(examPaper);
    }

}
