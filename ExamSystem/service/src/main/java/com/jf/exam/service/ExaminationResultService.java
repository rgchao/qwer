package com.jf.exam.service;

import com.github.pagehelper.Page;
import com.jf.exam.pojo.StatisticsData;
import com.jf.exam.pojo.data.ExaminationResultDO;
import com.jf.exam.pojo.vo.ExamResultViewVO;
import com.jf.exam.pojo.vo.ExaminationResultVO;
import com.jf.exam.pojo.vo.StudentReportVO;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;

import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/28
 */
public interface ExaminationResultService {

	Result addExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;

	Result updateExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;
	
	Result findDetailExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;
	
	PageBean<ExaminationResultVO> listExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;

    Result listExaminationResultPage(ExaminationResultVO examinationResultVO) throws Exception;
	
	Result countExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;
	
	Result deleteExaminationResult(ExaminationResultVO examinationResultVO) throws Exception;

	ExamResultViewVO getERViewById(String eid, String sid);

    StatisticsData getStatisticsData(Integer eid);

	List<StudentReportVO> getReportData(Integer eid);
}