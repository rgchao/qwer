package com.jf.exam.service.impl;

import com.jf.exam.mapper.QuestionJudgeMapper;
import com.jf.exam.pojo.vo.QuestionJudgeVO;
import com.jf.exam.service.QuestionJudgeService;
import com.jf.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
@Service("questionJudgeService")
public class QuestionJudgeServiceImpl implements QuestionJudgeService {

	private final static Logger LOG = LoggerFactory.getLogger(QuestionJudgeServiceImpl.class);

	@Resource
	private QuestionJudgeMapper questionJudgeMapper;




	
	@Override
	public Result findDetailQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception{
		return null;
	}

	@Override
	public Result listQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception{
		return null;
	}
	
	@Override
	public Result listQuestionJudgePage(QuestionJudgeVO questionJudgeVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception{
		return null;
	}

}