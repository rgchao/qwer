package com.jf.exam.service;

import com.jf.exam.pojo.vo.QuestionChoiceVO;
import com.jf.exam.utils.Result;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
public interface QuestionChoiceService {

	Result addQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception;

	Result updateQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception;
	
	Result findDetailQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception;
	
	Result listQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception;

    Result listQuestionChoicePage(QuestionChoiceVO questionChoiceVO) throws Exception;
	
	Result countQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception;
	
	Result deleteQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception;
}