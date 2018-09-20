package com.jf.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.jf.exam.mapper.*;
import com.jf.exam.pojo.StatisticsData;
import com.jf.exam.pojo.data.*;
import com.jf.exam.pojo.vo.*;
import com.jf.exam.service.ExaminationResultService;
import com.jf.exam.utils.AnswerUtils;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.QuestionType;
import com.jf.exam.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/28
 */
@Service("examinationResultService")
public class ExaminationResultServiceImpl implements ExaminationResultService {

	private final static Logger LOG = LoggerFactory.getLogger(ExaminationResultServiceImpl.class);

	@Resource
	private ExaminationResultMapper examinationResultMapper;

	@Autowired
    ExamMapper examMapper;

	@Autowired
    ExaminationResulquestionMapper examinationResulquestionMapper;

	@Autowired
    QuestionJudgeMapper questionJudgeMapper;

	@Autowired
    QuestionChoiceMapper questionChoiceMapper;
	@Override
	public Result addExaminationResult(ExaminationResultVO examinationResultVO) throws Exception {
		return null;
	}

	@Override
	public Result updateExaminationResult(ExaminationResultVO examinationResultVO) throws Exception {
		return null;
	}
	
	@Override
	public Result findDetailExaminationResult(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}

	@Override
	public PageBean<ExaminationResultVO> listExaminationResult(ExaminationResultVO query) throws Exception{
		//防止代码越界
		Integer count =examinationResultMapper.countExaminationResult(query);
		int max =(count +query.getSize()-1)/query.getPageSize();
		if(query.getPageCode()>max){
			query.setPageSize(max);
		}
		PageHelper.startPage(query.getPageCode(),query.getPageSize());
		List<ExaminationResultDO> examinationResultDOS =examinationResultMapper.listExaminationResult(query);

		ArrayList<ExaminationResultVO> resultVOS =new ArrayList<>();
		for (ExaminationResultDO examinationResultDO:examinationResultDOS) {
			ExaminationResultVO vo =new ExaminationResultVO();
			BeanUtils.copyProperties(examinationResultDO,vo);
			resultVOS.add(vo);
		}
		PageBean<ExaminationResultVO> pageBean =new PageBean<>(resultVOS,query.getPageSize(),query.getPageCode(),count,query.getSize());
		return pageBean;
	}
	
	@Override
	public Result listExaminationResultPage(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countExaminationResult(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteExaminationResult(ExaminationResultVO examinationResultVO) throws Exception{
		return null;
	}

    @Override
    public ExamResultViewVO getERViewById(String eid, String sid) {
	    //查询学生的考试信息
        ExaminationResultVO resultVO =new ExaminationResultVO();
        resultVO.setId(Integer.parseInt(eid));
       // resultVO.setFkStudent(sid);
        ExaminationResultDO examinationResultDO = examinationResultMapper.findDetailExaminationResult(resultVO);

        if(examinationResultDO==null){
            return null;
        }
       // ExaminationResultDO examinationResultDO =examinationResult.get(0);

        //考试的详细信息，给页面用
        ExamResultViewVO erViewVO =new ExamResultViewVO();
        erViewVO.setDate(examinationResultDO.getTime());
        erViewVO.setPoint(examinationResultDO.getPoint());

        //查询试卷信息，获取题目的分数
        ExamDO detailExam = examMapper.findDetailExam(ExamVO.getExamVOById(examinationResultDO.getFkExam()));

        erViewVO.setJudgePoints(detailExam.getJudgepoints());
        erViewVO.setSinglePoints(detailExam.getSinglepoints());
        erViewVO.setMultiPoints(detailExam.getMultipoints());

        //根据考试结果id获取答题信息
        ExaminationResulquestionVO resultquestionVO =new ExaminationResulquestionVO();
        resultquestionVO.setFkExaminationResult(examinationResultDO.getId());
        List<ExaminationResulquestionDO> erqDOS =examinationResulquestionMapper.listExaminationResulquestion(resultquestionVO);

        //遍历答题结果
        for(ExaminationResulquestionDO erqDO: erqDOS){//获取每个答题详情

            //封装答题详情
            ExamResultViewVO.ExamResultQuestionVO resultQuestionVO =new ExamResultViewVO.ExamResultQuestionVO();

            //判断题目类型
            if(erqDO.getFkQtype()== QuestionType.JUDGE){
                //判断题
                //根据判断题的id查询判断题的题目信息
                QuestionJudgeVO judgeVO =new QuestionJudgeVO();
                judgeVO.setId(erqDO.getFkQuestion());
                QuestionJudgeDO detailQuestionJudge =questionJudgeMapper.findDetailQuestionJudge(judgeVO);

                //设置题目的基本信息
                resultQuestionVO.setId(detailQuestionJudge.getId());
                resultQuestionVO.setTitle(detailQuestionJudge.getQuestion());
                resultQuestionVO.setAnswer(detailQuestionJudge.getAnswer());
                resultQuestionVO.setPoint(detailQuestionJudge.getScore().intValue());
                resultQuestionVO.setType(QuestionType.JUDGE+"");

                //判断此题是否回答正确
                boolean right =erqDO.getIsRight()==0;//【0正确】
                resultQuestionVO.setRight(right);//设置回答是否正确
                if(!right){
                    //设置错误的答案信息
                    resultQuestionVO.setWrongAnswer(erqDO.getWrongAnswer());
                    resultQuestionVO.setWrongAnswerFacade(AnswerUtils.getAnswerFacade(3,erqDO.getWrongAnswer()));
                }
                erViewVO.addJudgeQuestion(resultQuestionVO);
            }else {
                //根据选择题的id查询选择题的信息
                QuestionChoiceVO choiceVO =new QuestionChoiceVO();
                choiceVO.setId(erqDO.getFkQuestion());
                QuestionChoiceDO detailQuestionChoice = questionChoiceMapper.findDetailQuestionChoice(choiceVO);

                //设置题目的基本信息
                resultQuestionVO.setId(detailQuestionChoice.getId());
                resultQuestionVO.setTitle(detailQuestionChoice.getQuestion());
                resultQuestionVO.setAnswer(detailQuestionChoice.getAnswer());
                resultQuestionVO.setPoint(detailQuestionChoice.getScore().intValue());
                resultQuestionVO.setType(erqDO.getFkQtype()+"");

                //判断此题是否回答正确
                boolean right =erqDO.getIsRight()==0;
                resultQuestionVO.setRight(right);
                if(!right){
                    resultQuestionVO.setWrongAnswer(erqDO.getWrongAnswer());
                    resultQuestionVO.setWrongAnswerFacade(AnswerUtils.getAnswerFacade(erqDO.getFkQtype(),erqDO.getWrongAnswer()));
                }
                if(erqDO.getFkQtype()==QuestionType.SINGLE){
                    //添加到单选题的集合
                    erViewVO.addSingleQuestion(resultQuestionVO);
                }else {
                    erViewVO.addMultiQuestion(resultQuestionVO);
                }
            }
        }
        return erViewVO;
    }

    @Override
    public StatisticsData getStatisticsData(Integer eid) {
	    ExaminationResultVO resultVO =new ExaminationResultVO();
	    resultVO.setFkExam(eid);

	    //获取考试结果时，一并获取学生的信息
        List<StudentExamResultVO> resultDOS = examinationResulquestionMapper.listExaminationResultByExamId(resultVO);

        if(!resultDOS.isEmpty()){
            //获取试卷信息
            ExamDO examDO =examMapper.findDetailExam(ExamVO.getExamVOById(eid));
            int points =examDO.getPoints();
            //封装统计的信息
            StatisticsData data =new StatisticsData();
            //设置试卷信息
            data.setTitle(examDO.getTitle());
            data.setExamPoints(points);
            //参加人数
            data.setPersonCount(resultDOS.size());

            //各个分段的成绩
            //six
            int sp =(int) (points*0.6);
            //eight
            int ep =(int)(points*0.8);
            //nine
            int np =(int)(points*0.9);
            data.setSixtyPoint(sp);
            data.setEighttyPoint(ep);
            data.setNinetyPoint(np);
            //最高分和最低分姓名
            Collections.sort(resultDOS);


            data.addHightestName(resultDOS.get(0).getName());
            data.setHighestPoint(resultDOS.get(0).getPoint());
            data.addLowestNames(resultDOS.get(resultDOS.size()-1).getName());
            data.setLowestPoint(resultDOS.get(resultDOS.size()-1).getPoint());

            //设置分数段
            for(StudentExamResultVO resultDO :resultDOS){
                int point =resultDO.getPoint();
                if(point<sp){
                    data.addUnderSixty(point);
                }else if(point<ep){
                    data.addSixtyAndEighty(point);
                }else if(point<np){
                    data.addEightyAndNinety(point);
                }else {
                    data.addAboveNinety(point);
                }
            }
            return  data;
        }
        return null;
    }

    @Override
    public List<StudentReportVO> getReportData(Integer eid) {
	    ExamDO examDO =examMapper.findDetailExam(ExamVO.getExamVOById(eid));
	    ExaminationResultVO vo =new ExaminationResultVO();
	    vo.setFkExam(eid);
	    List<StudentReportVO> reportVOS =examinationResultMapper.listStudentReportByExamId(vo);
        for (StudentReportVO reportVO : reportVOS) {
            reportVO.setTitle(examDO.getTitle());
        }
	    return reportVOS;
    }

}