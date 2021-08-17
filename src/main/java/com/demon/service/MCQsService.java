package com.demon.service;

import com.demon.mapper.MCQsMapper;
import com.demon.mapper.QuestionMapper;
import com.demon.pojo.MCQs;
import com.demon.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MCQsService {

    @Autowired
    MCQsMapper mcQsMapper;

    @Autowired
    QuestionMapper questionMapper;

    //返回用户名为username的错题MCQs（错题编号，用户名，用户错误答案）
    public List<MCQs> getMCQsListById(String username){
        return mcQsMapper.queryMCQsById(username);
    }

    //返回单个题目
    public Question getQuestionById(int q_id){
        return questionMapper.queryQuestionById(q_id);
    }

    //返回用户名为username的错题Question的List
    public List<Question> getWrongQuestion(String username){
        List<MCQs> mcQs = mcQsMapper.queryMCQsById(username);
        List<Question> res = new ArrayList<>();
        for (MCQs qs:mcQs){
            res.add(questionMapper.queryQuestionById(qs.getQ_id()));
        }
        return res;
    }

    //添加错题
    public void addMCQs(MCQs mcQs){
        MCQs temp = mcQsMapper.queryMCQsByPrimaryKey(mcQs.getUsername(),mcQs.getQ_id());
        if(temp == null){
            mcQsMapper.insertMCQs(mcQs);
        }else
        {
            mcQsMapper.updateMCQs(mcQs);
        }
    }

    //删除错题
    public void deleteMCQs(String username, int q_id){
        mcQsMapper.deleteMCQs(username,q_id);
    }

}
