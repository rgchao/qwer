package com.jf.exam.mapper;

import com.jf.exam.pojo.data.ClassDO;
import com.jf.exam.pojo.vo.ClassListVO;
import com.jf.exam.pojo.vo.ClassVO;
import com.jf.exam.pojo.vo.ExamVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/20
 */
@Repository
public interface ClassMapper {

    Integer addClass(ClassVO classVO);

    Integer updateClass(ClassVO classVO);

    ClassDO findDetailClass(ClassVO classVO);

    List<ClassDO> listClass(ClassVO classVO);

    List<ClassDO> listClassPage(ClassVO classVO);

    Integer countClass(ClassVO classVO);

    Integer deleteClass(ClassVO classVO);

    List<ClassListVO> listClazzAssociation(ClassVO classVO);
    //根据试卷id查询班级信息
    List<ClassDO> listByExam(ExamVO teacherVO);

}
