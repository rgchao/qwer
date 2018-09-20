package com.jf.exam.mapper;

import com.jf.exam.pojo.data.ExaminationResulquestionDO;
import com.jf.exam.pojo.vo.ExaminationResulquestionVO;
import com.jf.exam.pojo.vo.ExaminationResultVO;
import com.jf.exam.pojo.vo.StudentExamResultVO;
import com.jf.exam.pojo.vo.StudentReportVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br/>
 * Created by weidong on 2018/07/27
 */
@Repository
public interface ExaminationResulquestionMapper {

    Integer addExaminationResulquestion(ExaminationResulquestionVO examinationResultquestionVO);

    Integer updateExaminationResulquestion(ExaminationResulquestionVO examinationResultquestionVO);

    ExaminationResulquestionDO findDetailExaminationResulquestion(ExaminationResulquestionVO examinationResultquestionVO);

    List<ExaminationResulquestionDO> listExaminationResulquestion(ExaminationResulquestionVO examinationResultquestionVO);

    List<ExaminationResulquestionDO> listExaminationResulquestionPage(ExaminationResulquestionVO examinationResultquestionVO);

    Integer countExaminationResulquestion(ExaminationResulquestionVO examinationResultquestionVO);

    Integer deleteExaminationResulquestion(ExaminationResulquestionVO examinationResultquestionVO);

    List<StudentExamResultVO> listExaminationResultByExamId(ExaminationResultVO resultVO);

    List<StudentReportVO> listStudentReportByExamId(ExaminationResultVO vo);
}
