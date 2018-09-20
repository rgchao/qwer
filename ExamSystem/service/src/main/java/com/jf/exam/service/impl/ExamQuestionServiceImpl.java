package com.jf.exam.service.impl;

import com.jf.exam.mapper.ExamQuestionMapper;
import com.jf.exam.pojo.vo.ExamQuestionVO;
import com.jf.exam.service.ExamQuestionService;
import com.jf.exam.service.ExamService;
import com.jf.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/** 
 * <br/>
 * Created by chao on 2018/07/25
 */
@Service("examQuestionService")
public class ExamQuestionServiceImpl implements ExamQuestionService {

	private final static Logger LOG = LoggerFactory.getLogger(ExamQuestionServiceImpl.class);

	@Resource
	private ExamQuestionMapper examQuestionMapper;
	@Autowired
	ExamService examService;


	@Override
	public Result addExamQuestion(ExamQuestionVO examQuestionVO, Integer examId, int qScore) throws Exception {
		Integer count = examQuestionMapper.addExamQuestion(examQuestionVO);
		if(count>0){
			//更新试卷表
			Integer updateExamCount = examService.updateExamScore(examId, qScore, examQuestionVO.getFkQtype());
			if(updateExamCount>0){
				return Result.getSuccess("添加成功");
			}
		}
		return null;
	}

	@Override
	public Result updateExamQuestion(ExamQuestionVO examQuestionVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailExamQuestion(ExamQuestionVO examQuestionVO) throws Exception{
		return null;
	}

	@Override
	public Result listExamQuestion(ExamQuestionVO examQuestionVO) throws Exception{
		return null;
	}
	
	@Override
	public Result listExamQuestionPage(ExamQuestionVO examQuestionVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countExamQuestion(ExamQuestionVO examQuestionVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteExamQuestion(ExamQuestionVO examQuestionVO) throws Exception{
		return null;
	}

}