package com.jf.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.exam.mapper.ClassMapper;
import com.jf.exam.mapper.StudentMapper;
import com.jf.exam.pojo.data.ClassDO;
import com.jf.exam.pojo.data.StudentDO;
import com.jf.exam.pojo.vo.ClassVO;
import com.jf.exam.pojo.vo.StudentListVO;
import com.jf.exam.pojo.vo.StudentVO;
import com.jf.exam.service.StudentService;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/20
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

	private final static Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Resource
	private StudentMapper studentMapper;
	@Resource
	private ClassMapper classMapper;
	@Override
	public Result addStudent(StudentVO studentVO) throws Exception {
		if(findStudentByCno(studentVO)){
			return Result.getFailure("此学生存在");
		}
		studentVO.setDelFlag("0");
		int count =studentMapper.addStudent(studentVO);
		if(count>0){
			return new Result(Result.CODE_SUCCESS,"添加成功");
		}
		throw  new RuntimeException("添加错误");
	}

	@Override
	public Result updateStudent(StudentVO studentVO) throws Exception {
		StudentDO studentDO =studentMapper.findDetailStudent(studentVO);
		if(studentDO==null){
			return Result.getSuccess("学生不存在，修改失败");
		}
		ClassDO classDO = classMapper.findDetailClass(new ClassVO(studentDO.getFkClass()));
		if(classDO==null){
			return Result.getSuccess("班级不存在,修改失败");
		}
		int count =studentMapper.updateStudent(studentVO);
		if(count>0){
			return Result.getSuccess("添加成功");
		}

		return Result.getFailure("添加失败");
	}
	
	@Override
	public Result findDetailStudent(StudentVO studentVO) throws Exception{
		return null;
	}

	@Override
	public PageBean<StudentListVO> listStudent(StudentVO studentVO) throws Exception{
		//防止页码越界
		Integer count = studentMapper.countStudent(studentVO);
		int max =(count+studentVO.getPageSize()-1)/studentVO.getPageSize();
		if(studentVO.getPageCode()>max){
			studentVO.setPageCode(max);
		}

		PageHelper.startPage(studentVO.getPageCode(),studentVO.getPageSize());
		List<StudentListVO> listVOS = studentMapper.listAssociation(studentVO);
		PageInfo<StudentListVO> info =new PageInfo<>(listVOS);
		PageBean<StudentListVO> pageBean =new PageBean<>(listVOS,info.getPageSize(),studentVO.getPageCode(),
				(int)info.getTotal(),studentVO.getPageSize());
		return pageBean;
	}

	
	@Override
	public Result listStudentPage(StudentVO studentVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countStudent(StudentVO studentVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteStudent(StudentVO studentVO) throws Exception{
		List<StudentDO> studentDOS = studentMapper.listStudent(studentVO);
		if (studentDOS == null || studentDOS.isEmpty()) {
			return Result.getFailure("此学生不存在");
		}

		int count =studentMapper.deleteStudent(studentVO);
		if(count>0){
			return new Result(Result.CODE_SUCCESS,"删除成功");
		}
		throw new RuntimeException("删除错误");
	}
	/**
	 * 通过学生名称获取学生信息
	 */
	@Override
	public StudentDO getStudentByName(String username) {
		List<StudentDO> studentDOS =studentMapper.listStudent(new StudentVO(username));
		if(studentDOS!=null && !studentDOS.isEmpty()){
			return studentDOS.get(0);
		}
		return null;
	}


	@Override
	public boolean findStudentByCno(StudentVO studentVO) {
		//StudentVO studentVO = new StudentVO();
		//studentVO.setSno(sno);
		List<StudentDO> studentDOS = studentMapper.listStudents(studentVO);
		if (studentDOS != null && !studentDOS.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public Result isExist(StudentVO student) {
		List<StudentDO> studentDOS = studentMapper.listStudent(student);
		if (studentDOS != null && !studentDOS.isEmpty()) {
			return Result.getFailure("学生存在");
		}
		return Result.getSuccess("学生不存在");
	}
}