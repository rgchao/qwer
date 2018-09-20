package com.jf.exam.mapper;

import com.jf.exam.pojo.data.QuestionChoiceDO;
import com.jf.exam.pojo.vo.QuestionChoiceVO;
import com.jf.exam.pojo.vo.QuestionListVO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
@Repository
public interface QuestionChoiceMapper {

    Integer addQuestionChoice(QuestionChoiceVO questionChoiceVO);

    Integer updateQuestionChoice(QuestionChoiceVO questionChoiceVO);

    QuestionChoiceDO findDetailQuestionChoice(QuestionChoiceVO questionChoiceVO);

    List<QuestionChoiceDO> listQuestionChoice(QuestionChoiceVO questionChoiceVO);

    List<QuestionChoiceDO> listQuestionChoicePage(QuestionChoiceVO questionChoiceVO);

    Integer countQuestionChoice(QuestionChoiceVO questionChoiceVO);

    Integer deleteQuestionChoice(QuestionChoiceVO questionChoiceVO);

    List<QuestionChoiceDO> listQuestionChoiceIdIn(ArrayList<Integer> choiceIDs);
}
