package com.demon.mapper;

import com.demon.pojo.ExamResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ExamResultMapper {

    //添加考试结果
    public void addExamResult(ExamResult examResult);

}
