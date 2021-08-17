package com.demon.mapper;

import com.demon.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    //获取全部题目
    List<Question> queryQuestion();

    //根据题目ID获取题目
    Question queryQuestionById(int q_id);

    List<Question> queryQuestionByIdList(List<Integer> q_id_List);

    //根据学科获取题目
    List<Question> queryQuestionBySubject(String subject);

    //获取题目分类的单元（全部单元数字）
    List<Integer> queryQuestionUnit();

    //获取题目学科
    List<String> queryQuestionSubject();

}
