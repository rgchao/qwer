package com.jf.exam.mapper;

import com.jf.exam.pojo.data.QuestionTypeDO;
import com.jf.exam.pojo.vo.QuestionTypeVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
@Repository
public interface QuestionTypeMapper {

    Integer addQuestionType(QuestionTypeVO questionTypeVO);

    Integer updateQuestionType(QuestionTypeVO questionTypeVO);

    QuestionTypeDO findDetailQuestionType(QuestionTypeVO questionTypeVO);

    List<QuestionTypeDO> listQuestionType(QuestionTypeVO questionTypeVO);

    List<QuestionTypeDO> listQuestionTypePage(QuestionTypeVO questionTypeVO);

    Integer countQuestionType(QuestionTypeVO questionTypeVO);

    Integer deleteQuestionType(QuestionTypeVO questionTypeVO);

}
