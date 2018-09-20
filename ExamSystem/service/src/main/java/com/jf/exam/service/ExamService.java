package com.jf.exam.service;


import com.jf.exam.pojo.vo.BeginExamVO;
import com.jf.exam.pojo.vo.ClassVO;
import com.jf.exam.pojo.vo.ExamAnswerVO;
import com.jf.exam.pojo.vo.ExamVO;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;
/** 
 * <br/>
 * Created by chao on 2018/07/23
 */
public interface ExamService {

	Result addExam(ExamVO examVO) throws Exception;

	Result updateExam(ExamVO examVO) throws Exception;
	
	Result findDetailExam(ExamVO examVO) throws Exception;
	
	PageBean<ExamVO> listExam(ExamVO examVO) throws Exception;

    Result listExamPage(ExamVO examVO) throws Exception;
	
	Result countExam(ExamVO examVO) throws Exception;
	
	Result deleteExam(ExamVO examVO) throws Exception;

	/**
	 * 更新试卷的分数
	 * @param examId
	 * @param qScore
	 * @param fkQtype
	 */
    Integer updateExamScore(Integer examId, int qScore, Integer fkQtype);

    //根据学生的班级的查询 学生的试卷列表，并排除初始化状态的试卷
	PageBean<ExamVO> listExamByClassId(ClassVO classVO);

	boolean hasJoined(Integer eid,String id);

	BeginExamVO joined(Integer eid);

	Result marking(ExamAnswerVO examAnswerVO, String sid);

}