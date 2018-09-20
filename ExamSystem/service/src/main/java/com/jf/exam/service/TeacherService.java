package com.jf.exam.service;

import com.jf.exam.pojo.data.TeacherDO;
import com.jf.exam.pojo.vo.TeacherClassVO;
import com.jf.exam.pojo.vo.TeacherVO;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;

import java.util.ArrayList;

/** 
 * <br/>
 * Created by weidong on 2018/07/18
 */
public interface TeacherService {

	Result addTeacher(TeacherVO teacherVO) throws Exception;

	Result updateTeacher(TeacherVO teacherVO) throws Exception;
	
	Result findDetailTeacher(TeacherVO teacherVO) throws Exception;

	PageBean<TeacherDO> listTeacher(TeacherVO teacherVO) throws Exception;

    Result listTeacherPage(TeacherVO teacherVO) throws Exception;
	
	Result countTeacher(TeacherVO teacherVO) throws Exception;
	
	Result deleteTeacher(TeacherVO teacherVO) throws Exception;

	Result addClass(ArrayList<TeacherClassVO> ids, String tid);

	TeacherDO getTeacherByName(String username);
}