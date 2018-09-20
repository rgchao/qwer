package com.jf.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.exam.mapper.*;
import com.jf.exam.pojo.data.*;
import com.jf.exam.pojo.vo.*;
import com.jf.exam.service.ExamService;
import com.jf.exam.utils.ExamUtils;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.QuestionType;
import com.jf.exam.utils.Result;
import com.jf.exam.utils.exception.BusinessException;
import com.jf.exam.utils.exception.PageCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * <br/>
 * Created by chao on 2018/07/23
 */
@Service("examService")
public class ExamServiceImpl implements ExamService {

	private final static Logger LOG = LoggerFactory.getLogger(ExamServiceImpl.class);

	@Resource
	private ExamMapper examMapper;
    @Autowired
    ExamQuestionMapper questionMapper;
    @Autowired
	ExamClassMapper examClassMapper;
    @Autowired
    ExaminationResultMapper examinationResultMapper;
    @Autowired
    ExamQuestionMapper examQuestionMapper;
    @Autowired
    QuestionChoiceMapper questionChoiceMapper;
    @Autowired
    QuestionJudgeMapper questionJudgeMapper;
    @Autowired
    ExaminationResulquestionMapper examinationResulquestionMapper;
	@Override
	public Result addExam(ExamVO examVO) throws Exception {
	    int count =examMapper.addExam(examVO);
	    if(count>0){
	        return Result.getSuccess("添加成功");
        }
		throw new BusinessException("添加错误");
	}

	@Override
	public Result updateExam(ExamVO examVO) throws Exception {
        int count = examMapper.updateExam(examVO);
        if (count > 0) {
            return Result.getSuccess("更新成功");
        }
        throw new BusinessException("更新失败");
	}
	
	@Override
	public Result findDetailExam(ExamVO examVO) throws Exception{
	    //获取试卷详情
        ExamDO detailExam = examMapper.findDetailExam(examVO);

        //设置试卷基本信息
        ExamDetailsVO examDetailsVO =new ExamDetailsVO();
        examDetailsVO.setExamId(examVO.getId()+"");
        examDetailsVO.setExamTitle(detailExam.getTitle());
        examDetailsVO.setExamTime(detailExam.getTimelimit()+"");
        examDetailsVO.setPoint(detailExam.getPoints()+"");

        ExamQuestionVO questionVO =new ExamQuestionVO();
        questionVO.setFkExam(examVO.getId());

        //查询单选题个数
        questionVO.setFkQtype(1);
        Integer singleCount = questionMapper.countExamQuestion(questionVO);

        //查询多选题个数
        questionVO.setFkQtype(2);
        Integer mulitCount = questionMapper.countExamQuestion(questionVO);

        //查询判断题个数
        questionVO.setFkQtype(3);
        Integer jugdeCount = questionMapper.countExamQuestion(questionVO);

        //设置基本信息
        examDetailsVO.setSingleSize(singleCount+"");
        examDetailsVO.setJugdeSize(jugdeCount+"");
        examDetailsVO.setMultiSize(mulitCount+"");
        return new Result(Result.CODE_SUCCESS,examDetailsVO);
	}

	@Override
	public PageBean<ExamVO> listExam(ExamVO examVO) throws Exception{
		Integer countExam = examMapper.countExam(examVO);
		//防止页码越界
		int max=(countExam+examVO.getPageSize()-1)/examVO.getPageSize();
		if(examVO.getPageCode()>max){
			//questionJudgeVO.setPageCode(max);
			throw new PageCodeException("越界啦",max);
		}
		//分页
		PageHelper.startPage(examVO.getPageCode(),examVO.getPageSize());
		List<ExamDO> examDOS = examMapper.listExam(examVO);
		ArrayList examVOS =new ArrayList();
		//门面值得转换
		for (ExamDO exam:examDOS
			 ) {
            ExamVO examVOs = new ExamVO();
			BeanUtils.copyProperties(exam,examVOs);
            examVOs.setStatusFacade(ExamUtils.getStatus(examVOs.getFkStatus()));
			examVOS.add(examVOs);
		}
		PageInfo<ExamVO>  info =new PageInfo<>(examVOS);
		PageBean<ExamVO> pageBean =new PageBean<>(examVOS,info.getPageSize(),examVO.getPageCode(),(int)info.getTotal(),examVO.getSize());
		return pageBean;
	}
	
	@Override
	public Result listExamPage(ExamVO examVO) throws Exception{
		return null;
	}
	
	@Override
	public Result countExam(ExamVO examVO) throws Exception{
		return null;
	}
	
	@Override
	public Result deleteExam(ExamVO examVO) throws Exception{
        int count =examMapper.deleteExam(examVO);
        if(count>0){
            return Result.getSuccess("删除成功");
        }
        throw new BusinessException("删除错误");
	}

	@Override
	public Integer updateExamScore(Integer examId, int qScore, Integer fkQtype) {
		//先查询试卷的信息
		ExamDO detailExam = examMapper.findDetailExam(ExamVO.getExamVOById(examId));
		ExamVO examVO=new ExamVO();
		examVO.setId(detailExam.getId());
		examVO.setPoints(detailExam.getPoints()+qScore);
		examVO.setFkStatus(2);
		if(fkQtype==QuestionType.SINGLE){
			examVO.setSinglepoints(detailExam.getSinglepoints()+qScore);
		}else if(fkQtype==QuestionType.MULTI){
			examVO.setMultipoints(detailExam.getMultipoints()+qScore);
		}else if(fkQtype==QuestionType.JUDGE){
			examVO.setJudgepoints(detailExam.getJudgepoints()+qScore);
		}
		return examMapper.updateExam(examVO);

	}

	@Override
	public PageBean<ExamVO> listExamByClassId(ClassVO classVO) {
		//防止页码越界
		ExamClassVO examClassVO =new ExamClassVO();
		examClassVO.setFkClass(classVO.getId());
		Integer count =examClassMapper.countExamClass(examClassVO);
		int max =(count + classVO.getPageSize()-1)/classVO.getPageSize();
		if(classVO.getPageCode()>max){
			throw new PageCodeException("越界啦",max);
		}
		PageHelper.startPage(classVO.getPageCode(),classVO.getPageSize());

		List<ExamDO> examDOS =examMapper.listExamByClassId(classVO);

		ArrayList<ExamVO> vos =new ArrayList<>();
		for(ExamDO examDO:examDOS){
		    ExamVO examVO =new ExamVO();
		    BeanUtils.copyProperties(examDO,examVO);
		    vos.add(examVO);
        }

        //根据插件提供的分页信息，构造自己的分页信息
        PageBean<ExamVO> pageBean =new PageBean<>(vos,classVO.getPageSize(),classVO.getPageCode(),count,classVO.getSize());

		return pageBean;
	}

    @Override
    public boolean hasJoined(Integer eid, String sid) {
	    ExaminationResultVO vo =new ExaminationResultVO();
	    vo.setFkExam(eid);
	    vo.setFkStudent(sid);
	    List<ExaminationResultDO> resultDOS =examinationResultMapper.listExaminationResult(vo);
        if(resultDOS !=null && !resultDOS.isEmpty()){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public BeginExamVO joined(Integer eid) {
	    //先查询试卷基本信息
        ExamDO detailExam = examMapper.findDetailExam(ExamVO.getExamVOById(eid));
        BeginExamVO beginExamVO =new BeginExamVO();
        //设置基本信息
        beginExamVO.setTitle(detailExam.getTitle());
        beginExamVO.setLimit(detailExam.getTimelimit());
        beginExamVO.setSinglePoints(detailExam.getSinglepoints());
        beginExamVO.setMultiPoints(detailExam.getMultipoints());
        beginExamVO.setJudgePoints(detailExam.getJudgepoints());
        beginExamVO.setPoints(detailExam.getPoints()+"");

        //获得题目的信息，根据试卷id获取所有题目的id
        ArrayList<Integer> choiceIDs =new ArrayList<>();
        ArrayList<Integer> judgeIDs =new ArrayList<>();
        getQuestionIds(eid+"",choiceIDs,judgeIDs);
        if(choiceIDs.isEmpty()&&judgeIDs.isEmpty()){
            return null;
        }
        //根据题目id查询题目数据
        List<QuestionChoiceDO> choiceDOS = questionChoiceMapper.listQuestionChoiceIdIn(choiceIDs);

        for(QuestionChoiceDO choiceDO:choiceDOS){
            QuestionListVO vo =new QuestionListVO();
            vo.setId(choiceDO.getId());
            vo.setPoint(choiceDO.getScore().intValue());
            vo.setTitle(choiceDO.getQuestion());
            vo.setOptiona(choiceDO.getOptiona());
            vo.setOptionb(choiceDO.getOptionb());
            vo.setOptionc(choiceDO.getOptionc());
            vo.setOptiond(choiceDO.getOptiond());
            if(choiceDO.getType()==QuestionType.SINGLE){
                vo.setType(QuestionType.SINLE_EN);
                beginExamVO.addSingleQuestion(vo);
            }else {
                vo.setType(QuestionType.MULTI_EN);
                beginExamVO.addMultiQuestion(vo);
            }


        }
        List<QuestionJudgeDO> judgeDOS =  questionJudgeMapper.listQuestionJudgeIdIn(judgeIDs);
        for (QuestionJudgeDO judgeDO: judgeDOS) {
            QuestionListVO vo =new QuestionListVO();
            vo.setId(judgeDO.getId());
            vo.setPoint(judgeDO.getScore().intValue());
            vo.setTitle(judgeDO.getQuestion());
            vo.setType(judgeDO.getType()+"");
            beginExamVO.addJudgeQuestion(vo);
        }
        return beginExamVO;
    }

    @Override
    public Result marking(ExamAnswerVO examAnswerVO, String sid) {
	    //先查询试卷的基本信息
        ExamDO detailExam = examMapper.findDetailExam(ExamVO.getExamVOById(Integer.parseInt(examAnswerVO.getEid())));
        //获取试卷的题目信息，进行比对

        //获取题目的信息，根据试卷id获取所有的题目id
        ArrayList<Integer> choiceIDs =new ArrayList<>();
        ArrayList<Integer> judgeIDs =new ArrayList<>();

        getQuestionIds(examAnswerVO.getEid(),choiceIDs,judgeIDs);

        int points =0;//记录考试的分数

        ArrayList<ExaminationResulquestionVO> erqvs = new ArrayList<>();

        //处理选择题
        List<QuestionChoiceDO> choiceDOS =questionChoiceMapper.listQuestionChoiceIdIn(choiceIDs);
        for(QuestionChoiceDO choiceDO:choiceDOS){//数据库的题目数据
            for(ExamAnswerQuestions eaq:examAnswerVO.getQuestions()){
                //判断题目的类型和id是否匹配
                if(choiceDO.getId()==Integer.parseInt(eaq.getId())&&choiceDO.getType()==eaq.getType()){
                    //添加答题的详情
                    ExaminationResulquestionVO erqv =new ExaminationResulquestionVO();
                    //考试结果表的id和试卷id是同一个
                    //erqv.setFkExaminationResult(detailExam.getId());
                    //设置题目id
                    erqv.setFkQuestion(choiceDO.getId());
                    //题目类型
                    erqv.setFkQtype(choiceDO.getType());

                    //获取学生的答案，进行比对
                    String answer =eaq.getAnswer();
                    if(answer.equals(choiceDO.getAnswer())){
                        //正确了
                        erqv.setIsRight(0);
                        points+=choiceDO.getScore().intValue();
                    }else {
                        //错误了
                        erqv.setIsRight(1);
                        //设置错误的答案
                        erqv.setWrongAnswer(answer);
                    }
                    //examinationResulquestionMapper.addExaminationResulquestion(erqv);
                    erqvs.add(erqv);
                }
            }
        }
        //处理判断题
        List<QuestionJudgeDO> judgeDOS =questionJudgeMapper.listQuestionJudgeIdIn(judgeIDs);
        for(QuestionJudgeDO judgeDO:judgeDOS){//数据库
            for(ExamAnswerQuestions eaq:examAnswerVO.getQuestions()){
                if(judgeDO.getId()==Integer.parseInt(eaq.getId())&&judgeDO.getType()==eaq.getType()){
                    //添加答题的详情
                    ExaminationResulquestionVO erqv =new ExaminationResulquestionVO();
                    //题目类型
                    erqv.setFkQtype(judgeDO.getType());
                    //考试结果表的id和试卷id是同一个
                    erqv.setFkExaminationResult(detailExam.getId());
                    //设置题目id
                    erqv.setFkQuestion(judgeDO.getId());
                    //获取学生的答案，进行对比
                    String answer =eaq.getAnswer();
                    if(answer.equals(judgeDO.getAnswer())){
                        //正确了
                        erqv.setIsRight(0);
                        points +=judgeDO.getScore().intValue();
                    }else {
                        //错误了
                        erqv.setIsRight(1);
                        //设置了错误的答案
                        erqv.setWrongAnswer(answer);
                    }
                    //examinationResulquestionMapper.addExaminationResulquestion(erqv);
                    erqvs.add(erqv);
                }
            }
        }
        //插入考试数据
        ExaminationResultVO resultVO =new ExaminationResultVO();
        //试卷id
        resultVO.setTime(new Date());
        resultVO.setFkExam(detailExam.getId());
        resultVO.setFkStudent(sid);
        resultVO.setExamTitle(detailExam.getTitle());
        resultVO.setPoint(points);//得分

        examinationResultMapper.addExaminationResult(resultVO);
        for(ExaminationResulquestionVO erqv :erqvs){
            erqv.setFkExaminationResult(resultVO.getId());
            examinationResulquestionMapper.addExaminationResulquestion(erqv);
        }
        return new Result(Result.CODE_SUCCESS,resultVO);
   
    }

    public void getQuestionIds(String eid,ArrayList<Integer> choiceIDs ,ArrayList<Integer>judgeIDs){
	    //1.根据试卷id查询所有的题目
        ExamQuestionVO  examQuestionVO =new ExamQuestionVO();
        examQuestionVO.setFkExam(Integer.parseInt(eid));
        List<ExamQuestionDO> examQuestionDOS =examQuestionMapper.listExamQuestion(examQuestionVO);
        //2.根据类型筛选id
        for(ExamQuestionDO examQuestionDO:examQuestionDOS){
            if(examQuestionDO.getFkQtype()==QuestionType.JUDGE){//判断题
                judgeIDs.add(examQuestionDO.getFkQuestion());

            }else {
                //选择题
                choiceIDs.add(examQuestionDO.getFkQuestion());
            }
        }
    }
}