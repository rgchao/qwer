package com.jf.exam.service;

import com.jf.exam.pojo.data.GradeDO;
import com.jf.exam.pojo.vo.GradeVO;

import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;

import java.util.List;


/** 
 * <br/>
 * Created by weidong on 2018/07/19
 */
public interface GradeService {

	Result addGrade(GradeVO gradeVO) throws Exception;

	Result updateGrade(GradeVO gradeVO) throws Exception;
	
	Result findDetailGrade(GradeVO gradeVO) throws Exception;
	
	PageBean<GradeDO> listGrade(GradeVO gradeVO) throws Exception;

    Result listGradePage(GradeVO gradeVO) throws Exception;
	
	Result countGrade(GradeVO gradeVO) throws Exception;
	
	Result deleteGrade(GradeVO gradeVO) throws Exception;
	List<GradeDO> listAll();
}