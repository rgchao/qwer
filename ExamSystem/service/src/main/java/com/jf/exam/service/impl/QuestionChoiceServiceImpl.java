package com.jf.exam.service.impl;

import com.jf.exam.mapper.QuestionChoiceMapper;
import com.jf.exam.pojo.vo.QuestionChoiceVO;
import com.jf.exam.service.QuestionChoiceService;
import com.jf.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
@Service("questionChoiceService")
public class QuestionChoiceServiceImpl implements QuestionChoiceService {

	private final static Logger LOG = LoggerFactory.getLogger(QuestionChoiceServiceImpl.class);

	@Resource
	private QuestionChoiceMapper questionChoiceMapper;

	@Override
	public Result addQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception {
		return null;
	}

	@Override
	public Result updateQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception{
		return null;
	}

	@Override
	public Result listQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception{
		return null;
	}
	
	@Override
	public Result listQuestionChoicePage(QuestionChoiceVO questionChoiceVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception{
		return null;
	}

}