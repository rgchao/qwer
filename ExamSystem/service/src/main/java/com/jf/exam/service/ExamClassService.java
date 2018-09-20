package com.jf.exam.service;

import com.jf.exam.pojo.vo.ExamClassVO;
import com.jf.exam.utils.Result;

/** 
 * <br/>
 * Created by chao on 2018/07/24
 */
public interface ExamClassService {

	Result addExamClass(ExamClassVO examClassVO) throws Exception;

	Result updateExamClass(ExamClassVO examClassVO) throws Exception;
	
	Result findDetailExamClass(ExamClassVO examClassVO) throws Exception;
	
	Result listExamClass(ExamClassVO examClassVO) throws Exception;

    Result listExamClassPage(ExamClassVO examClassVO) throws Exception;
	
	Result countExamClass(ExamClassVO examClassVO) throws Exception;
	
	Result deleteExamClass(ExamClassVO examClassVO) throws Exception;
}