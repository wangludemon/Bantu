package com.demon.mapper;

import com.demon.pojo.MCQs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MCQsMapper {

    MCQs queryMCQsByPrimaryKey(String username, int q_id);

    List<MCQs> queryMCQsById(String username);
    //List<MCQs> queryMCQsByQuestionId(int q_id);

    int updateMCQs(MCQs mcQs);

    int insertMCQs(MCQs mcQs);

    int deleteMCQs(String username, int q_id);
}
