package com.jf.exam.service;

import com.jf.exam.pojo.data.StudentDO;
import com.jf.exam.pojo.vo.StudentListVO;
import com.jf.exam.pojo.vo.StudentVO;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;

/** 
 * <br/>
 * Created by chao on 2018/07/20
 */
public interface StudentService {

	Result addStudent(StudentVO studentVO) throws Exception;

	Result updateStudent(StudentVO studentVO) throws Exception;
	
	Result findDetailStudent(StudentVO studentVO) throws Exception;
	
	PageBean<StudentListVO> listStudent(StudentVO studentVO) throws Exception;

    Result listStudentPage(StudentVO studentVO) throws Exception;
	
	Result countStudent(StudentVO studentVO) throws Exception;
	
	Result deleteStudent(StudentVO studentVO) throws Exception;
	StudentDO getStudentByName(String username);
	public boolean findStudentByCno(StudentVO studentVO);
	Result isExist(StudentVO student);
}