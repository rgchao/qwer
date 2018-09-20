package com.jf.exam.mapper;

import com.jf.exam.pojo.data.ExamDO;
import com.jf.exam.pojo.vo.ClassVO;
import com.jf.exam.pojo.vo.ExamVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/23
 */
@Repository
public interface ExamMapper {

    Integer addExam(ExamVO examVO);

    Integer updateExam(ExamVO examVO);

    ExamDO findDetailExam(ExamVO examVO);

    List<ExamDO> listExam(ExamVO examVO);

    List<ExamDO> listExamPage(ExamVO examVO);

    Integer countExam(ExamVO examVO);

    Integer deleteExam(ExamVO examVO);
    //根据学生的班级id查询学生的试卷列表，并排除初始化状态的试卷
    List<ExamDO> listExamByClassId(ClassVO query);
}
