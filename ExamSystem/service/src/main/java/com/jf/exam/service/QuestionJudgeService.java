package com.jf.exam.service;

import com.jf.exam.pojo.vo.QuestionJudgeVO;
import com.jf.exam.utils.Result;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
public interface QuestionJudgeService {




	
	Result findDetailQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception;
	
	Result listQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception;

    Result listQuestionJudgePage(QuestionJudgeVO questionJudgeVO) throws Exception;
	
	Result countQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception;
	
	Result deleteQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception;
}