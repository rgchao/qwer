package com.jf.exam.service;

import com.jf.exam.pojo.data.QuestionChoiceDO;
import com.jf.exam.pojo.vo.*;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;

import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
public interface QuestionService {


	Result addQuestionChoice(QuestionChoiceVO questionChoiceVO,String examId) throws Exception;
	//更新选择题
	Result updateQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception;
	//更新判断题
	Result updateQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception;

	//添加判断题
	Result addQuestionJudge(QuestionJudgeVO questionJudgeVO,String examId) throws Exception;
	//查询单选和多选题的方法
	PageBean<QuestionListVO> listQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception;
	//查询判断题的方法
	PageBean<QuestionListVO> listQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception;

	PageBean<QuestionManageVO> listQuestionByExam(Integer eid, Integer pageCode, int pageSize, int pageNumber);

	//根据类型查询题目
	Result listAllByType(QuestionBankVO questionBankVO);

	//批量添加题目和试卷的关系
	Result addExamQuestion(List<ExamQuestionVO> vos, Integer examId);
}