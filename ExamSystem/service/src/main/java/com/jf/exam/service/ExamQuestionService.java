package com.jf.exam.service;

import com.jf.exam.pojo.vo.ExamQuestionVO;
import com.jf.exam.utils.Result;

/** 
 * <br/>
 * Created by chao on 2018/07/25
 */
public interface  ExamQuestionService {

	Result addExamQuestion(ExamQuestionVO examQuestionVO,Integer examId,int qScore) throws Exception;

	Result updateExamQuestion(ExamQuestionVO examQuestionVO) throws Exception;
	
	Result findDetailExamQuestion(ExamQuestionVO examQuestionVO) throws Exception;
	
	Result listExamQuestion(ExamQuestionVO examQuestionVO) throws Exception;

    Result listExamQuestionPage(ExamQuestionVO examQuestionVO) throws Exception;
	
	Result countExamQuestion(ExamQuestionVO examQuestionVO) throws Exception;
	
	Result deleteExamQuestion(ExamQuestionVO examQuestionVO) throws Exception;
}