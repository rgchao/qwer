package com.jf.exam.mapper;

import com.jf.exam.pojo.data.StudentDO;
import com.jf.exam.pojo.vo.StudentListVO;
import com.jf.exam.pojo.vo.StudentVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/20
 */
@Repository
public interface StudentMapper {

    Integer addStudent(StudentVO studentVO);

    Integer updateStudent(StudentVO studentVO);

    StudentDO findDetailStudent(StudentVO studentVO);

    List<StudentDO> listStudent(StudentVO studentVO);

    List<StudentDO> listStudentPage(StudentVO studentVO);

    Integer countStudent(StudentVO studentVO);

    Integer deleteStudent(StudentVO studentVO);

    List<StudentListVO> listAssociation(StudentVO studentVO);

    List<StudentDO> listStudents(StudentVO studentVO);
}
