package com.jf.exam.mapper;

import com.jf.exam.pojo.data.ExaminationResultDO;
import com.jf.exam.pojo.vo.ExaminationResultVO;
import com.jf.exam.pojo.vo.StudentReportVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/28
 */
@Repository
public interface ExaminationResultMapper {

    Integer addExaminationResult(ExaminationResultVO examinationResultVO);

    Integer updateExaminationResult(ExaminationResultVO examinationResultVO);

    ExaminationResultDO findDetailExaminationResult(ExaminationResultVO examinationResultVO);

    List<ExaminationResultDO> listExaminationResult(ExaminationResultVO examinationResultVO);

    List<ExaminationResultDO> listExaminationResultPage(ExaminationResultVO examinationResultVO);

    Integer countExaminationResult(ExaminationResultVO examinationResultVO);

    Integer deleteExaminationResult(ExaminationResultVO examinationResultVO);

    List<StudentReportVO> listStudentReportByExamId(ExaminationResultVO vo);
}
