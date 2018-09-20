package com.jf.exam.service;

import com.jf.exam.pojo.data.ClassDO;
import com.jf.exam.pojo.vo.ClassListVO;
import com.jf.exam.pojo.vo.ClassVO;
import com.jf.exam.pojo.vo.ExamClassVO;
import com.jf.exam.pojo.vo.ExamVO;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;

import java.util.List;


/** 
 * <br/>
 * Created by chao on 2018/07/20
 */
public interface ClassService {

	Result addClass(ClassVO classVO) throws Exception;

	Result updateClass(ClassVO classVO) throws Exception;
	
	Result findDetailClass(ClassVO classVO) throws Exception;

    PageBean<ClassListVO> listClass(ClassVO classVO) throws Exception;

    Result listClassPage(ClassVO classVO) throws Exception;
	
	Result countClass(ClassVO classVO) throws Exception;


	Result deleteClass(ClassVO classVO) throws Exception;
	List<ClassDO> listClassByGradeAndMajor(ClassVO classVO);
	/**
	 * 设置班级和试卷关系
	 * 添加时需要清空之前的记录
	 */
	public Result addExamClass(String examId, List<ExamClassVO> vos);
	//根据试卷id查询班级信息
	Result listByExam(ExamVO teacherVO);
}