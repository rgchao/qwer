package com.jf.exam.service.impl;

import com.jf.exam.mapper.QuestionTypeMapper;
import com.jf.exam.pojo.vo.QuestionTypeVO;
import com.jf.exam.service.QuestionTypeService;
import com.jf.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
@Service("questionTypeService")
public class QuestionTypeServiceImpl implements QuestionTypeService {

	private final static Logger LOG = LoggerFactory.getLogger(QuestionTypeServiceImpl.class);

	@Resource
	private QuestionTypeMapper questionTypeMapper;

	@Override
	public Result addQuestionType(QuestionTypeVO questionTypeVO) throws Exception {
		return null;
	}

	@Override
	public Result updateQuestionType(QuestionTypeVO questionTypeVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailQuestionType(QuestionTypeVO questionTypeVO) throws Exception{
		return null;
	}

	@Override
	public Result listQuestionType(QuestionTypeVO questionTypeVO) throws Exception{
		return null;
	}
	
	@Override
	public Result listQuestionTypePage(QuestionTypeVO questionTypeVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countQuestionType(QuestionTypeVO questionTypeVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteQuestionType(QuestionTypeVO questionTypeVO) throws Exception{
		return null;
	}

}