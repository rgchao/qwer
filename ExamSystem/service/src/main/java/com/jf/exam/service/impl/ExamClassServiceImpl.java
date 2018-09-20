package com.jf.exam.service.impl;

import com.jf.exam.mapper.ExamClassMapper;
import com.jf.exam.pojo.vo.ExamClassVO;
import com.jf.exam.service.ExamClassService;
import com.jf.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
 * <br/>
 * Created by chao on 2018/07/24
 */
@Service("examClassService")
public class ExamClassServiceImpl implements ExamClassService {

	private final static Logger LOG = LoggerFactory.getLogger(ExamClassServiceImpl.class);

	@Resource
	private ExamClassMapper examClassMapper;

	@Override
	public Result addExamClass(ExamClassVO examClassVO) throws Exception {
		return null;
	}

	@Override
	public Result updateExamClass(ExamClassVO examClassVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailExamClass(ExamClassVO examClassVO) throws Exception{
		return null;
	}

	@Override
	public Result listExamClass(ExamClassVO examClassVO) throws Exception{
		return null;
	}
	
	@Override
	public Result listExamClassPage(ExamClassVO examClassVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countExamClass(ExamClassVO examClassVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteExamClass(ExamClassVO examClassVO) throws Exception{
		return null;
	}

}