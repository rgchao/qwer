package com.jf.exam.service;

import com.jf.exam.pojo.vo.QuestionTypeVO;
import com.jf.exam.utils.Result;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
public interface QuestionTypeService {

	Result addQuestionType(QuestionTypeVO questionTypeVO) throws Exception;

	Result updateQuestionType(QuestionTypeVO questionTypeVO) throws Exception;
	
	Result findDetailQuestionType(QuestionTypeVO questionTypeVO) throws Exception;
	
	Result listQuestionType(QuestionTypeVO questionTypeVO) throws Exception;

    Result listQuestionTypePage(QuestionTypeVO questionTypeVO) throws Exception;
	
	Result countQuestionType(QuestionTypeVO questionTypeVO) throws Exception;
	
	Result deleteQuestionType(QuestionTypeVO questionTypeVO) throws Exception;
}