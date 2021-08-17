package com.demon.mapper;

import com.demon.pojo.ExamPaper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ExamPaperMapper {
    List<ExamPaper> queryExamPaper();

    ExamPaper queryExamPaperById(int p_id);
}
