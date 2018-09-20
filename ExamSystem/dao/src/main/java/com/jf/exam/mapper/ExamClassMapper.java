package com.jf.exam.mapper;

import com.jf.exam.pojo.data.ExamClassDO;
import com.jf.exam.pojo.vo.ExamClassVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/24
 */
@Repository
public interface ExamClassMapper {

    Integer addExamClass(ExamClassVO examClassVO);

    Integer updateExamClass(ExamClassVO examClassVO);

    ExamClassDO findDetailExamClass(ExamClassVO examClassVO);

    List<ExamClassDO> listExamClass(ExamClassVO examClassVO);

    List<ExamClassDO> listExamClassPage(ExamClassVO examClassVO);

    Integer countExamClass(ExamClassVO examClassVO);

    Integer deleteExamClass(ExamClassVO examClassVO);
    Integer deleteExamClassByExamId(String examId);
}
