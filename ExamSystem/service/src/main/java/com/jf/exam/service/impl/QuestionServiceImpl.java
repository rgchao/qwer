package com.jf.exam.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jf.exam.mapper.ExamQuestionMapper;
import com.jf.exam.mapper.QuestionChoiceMapper;
import com.jf.exam.mapper.QuestionJudgeMapper;
import com.jf.exam.pojo.data.ExamQuestionDO;
import com.jf.exam.pojo.data.QuestionChoiceDO;
import com.jf.exam.pojo.data.QuestionJudgeDO;
import com.jf.exam.pojo.vo.*;
import com.jf.exam.service.ExamQuestionService;
import com.jf.exam.service.ExamService;
import com.jf.exam.service.QuestionService;
import com.jf.exam.utils.*;
import com.jf.exam.utils.exception.BusinessException;
import com.jf.exam.utils.exception.PageCodeException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionChoiceMapper questionChoiceMapper;
    @Autowired
    QuestionJudgeMapper questionJudgeMapper;
    @Autowired
    ExamQuestionMapper examQuestionMapper;
    @Autowired
    ExamQuestionService examQuestionService;
    @Autowired
    ExamService examService;
    @Override
    public PageBean<QuestionListVO> listQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception {
        //防止页码越界
        Integer count =questionChoiceMapper.countQuestionChoice(questionChoiceVO);
        int max=(count+questionChoiceVO.getPageSize()-1)/questionChoiceVO.getPageSize();
        if(questionChoiceVO.getPageCode()>max){
            questionChoiceVO.setPageCode(max);
            //throw new PageCodeException("越界啦",max);
        }
        PageHelper.startPage(questionChoiceVO.getPageCode(),questionChoiceVO.getPageSize());
        List<QuestionChoiceDO> questionChoiceDOS = questionChoiceMapper.listQuestionChoice(questionChoiceVO);
        //将得到的questionChoiceDOS赋值到 vo
        ArrayList<QuestionListVO> list =new ArrayList<>();
        for(QuestionChoiceDO qdo: questionChoiceDOS){
            QuestionListVO vo =new QuestionListVO();
            BeanUtils.copyProperties(qdo,vo);
            vo.setTitle(qdo.getQuestion());
            vo.setPoint(qdo.getScore().intValue());
            vo.setAnswerFacade(AnswerUtils.getAnswerFacade(qdo.getType(),qdo.getAnswer()));
            vo.setType(qdo.getType()+"");
            list.add(vo);
        }

        //设置自己的分页信息
        PageInfo<QuestionChoiceDO> info =new PageInfo<>(questionChoiceDOS);
        PageBean<QuestionListVO> pageBean =new PageBean<>(list,info.getPageSize(),questionChoiceVO.getPageCode()
        ,(int)info.getTotal(),questionChoiceVO.getSize());
        return pageBean;
    }

    @Override
    public PageBean<QuestionListVO> listQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception {
        //防止页码越界
        Integer count =questionJudgeMapper.countQuestionJudge(questionJudgeVO);
        int max=(count+questionJudgeVO.getPageSize()-1)/questionJudgeVO.getPageSize();
        if(questionJudgeVO.getPageCode()>max){
            //questionJudgeVO.setPageCode(max);
            throw new PageCodeException("越界啦",max);
        }
        PageHelper.startPage(questionJudgeVO.getPageCode(),questionJudgeVO.getPageSize());
        List<QuestionJudgeDO> questionJudgeDOS = questionJudgeMapper.listQuestionJudge(questionJudgeVO);

        //将得到的questionChoiceDOS赋值到 vo
        ArrayList<QuestionListVO> list =new ArrayList<>();
        for(QuestionJudgeDO qdo: questionJudgeDOS){
            QuestionListVO vo =new QuestionListVO();
            BeanUtils.copyProperties(qdo,vo);
            vo.setTitle(qdo.getQuestion());
            vo.setPoint(qdo.getScore().intValue());
            vo.setAnswerFacade(AnswerUtils.getAnswerFacade(qdo.getType(),qdo.getAnswer()));
            vo.setType(qdo.getType()+"");
            list.add(vo);
        }

        //设置自己的分页信息
        PageInfo<QuestionJudgeDO> info =new PageInfo<>(questionJudgeDOS);
        PageBean<QuestionListVO> pageBean =new PageBean<>(list,info.getPageSize(),questionJudgeVO.getPageCode()
                ,(int)info.getTotal(),questionJudgeVO.getSize());
        return pageBean;

    }

    @Override
    public PageBean<QuestionManageVO> listQuestionByExam(Integer eid, Integer pageCode, int pageSize, int pageNumber) {
        //1.根据试卷id查询所有的题目
        ExamQuestionVO examQuestionVO =new ExamQuestionVO();
        examQuestionVO.setFkExam(eid);
        List<ExamQuestionDO> examQuestionDOS =examQuestionMapper.listExamQuestion(examQuestionVO);

        if(examQuestionDOS==null ||examQuestionDOS.isEmpty()){
            return null;
        }
        ArrayList<Integer> choiceIDs =new ArrayList<>();
        ArrayList<Integer> jubgeIDs =new ArrayList<>();

        //2.根据类型筛选id
        for(ExamQuestionDO examQuestionDO:examQuestionDOS){
            if(examQuestionDO.getFkQtype()==QuestionType.JUDGE){
                jubgeIDs.add(examQuestionDO.getFkQuestion());
            }else {
                //选择题
                choiceIDs.add(examQuestionDO.getFkQuestion());
            }

        }
        //因为题目的分成两个表来存储的，所以id会重复，所以需要根据各自的id查询各自的表

        //构造查询条件
        QuestionManageQuery query =new QuestionManageQuery();
        //id
        query.setChoiceIDs(choiceIDs);
        query.setJudgeIDs(jubgeIDs);
        //分页条件
        //int start =(pageCode-1)*pageSize;
        int start =(DataUtils.getPageCode(pageCode+"")-1)*pageSize;
        query.setStartRecord(start);
        query.setPageSize(pageSize);
        query.setPageCode(pageCode);
        query.setSize(pageNumber);
        List<QuestionManageVO> questionManageVOS = examQuestionMapper.listExamQuestionByExam(query);

        for (QuestionManageVO manageVO:questionManageVOS
             ) {
            manageVO.setAnswerFacade(QuestionType.getTypeByNum(Integer.parseInt(manageVO.getType())));
        }

        //总个数是id集合的大小 choiceIDs.size() +judgeIDs.size()
        PageBean<QuestionManageVO> pageBean =new PageBean<>(questionManageVOS,query.getPageSize(),query.getPageCode(),examQuestionDOS.size(),query.getSize());

        return pageBean;
    }

    /**
     * 返回的数据和列表一样
     * @param questionBankVO
     * @return
     */
    @Override
    public Result listAllByType(QuestionBankVO questionBankVO) {
        //存储所有的题目数据(数据库)
        ArrayList<QuestionListVO> qlvs =new ArrayList<>();
        if(Integer.parseInt(questionBankVO.getType())==QuestionType.JUDGE){
            //查询判断题
            QuestionJudgeVO judgeVO =new QuestionJudgeVO();
            List<QuestionJudgeDO> judgeDOS =questionJudgeMapper.listQuestionJudge(judgeVO);
            //将数据转换
           parsingJudegeQuestion(judgeDOS,qlvs);
        }else {
            //查询选择题
            QuestionChoiceVO choiceVO =new QuestionChoiceVO();
            //单选和多选进行区分
            if(Integer.parseInt(questionBankVO.getType())==QuestionType.SINGLE){
                choiceVO.setType(QuestionType.SINGLE);
            }else {
                choiceVO.setType(QuestionType.MULTI);
            }
            List<QuestionChoiceDO> choiceDOS =questionChoiceMapper.listQuestionChoice(choiceVO);
            //将数据进行转换
            parsingChoiceQuestion(choiceDOS,qlvs);
        }

        //去除重复的数据
        //去除试卷已有的题目数据
        for (int i=0;i<questionBankVO.getTypes().size();i++){
            TypesVO typeVO =questionBankVO.getTypes().get(i);
            for(int j=0;j<qlvs.size();j++){
                QuestionListVO q =qlvs.get(j);
                if(typeVO.getType().equals(q.getType())&&Integer.parseInt(typeVO.getId())==q.getId()){
                    qlvs.remove(j);
                }
            }
        }
        System.out.println(qlvs);
        return new Result(Result.CODE_SUCCESS,qlvs);
    }

    @Override
    public Result addExamQuestion(List<ExamQuestionVO> vos, Integer examId) {
        //使用循环添加试卷和题目的关系
        for(ExamQuestionVO vo : vos){
            int count =examQuestionMapper.addExamQuestion(vo);
            if(count>0){
                if(examId!=null && examId!=0){
                    //更新试卷信息
                    Integer updateExamScore = examService.updateExamScore(examId, vo.getScore(), vo.getFkQtype());
                }
            }
        }
        return Result.getSuccess("添加成功");
    }


    @Override
    public Result addQuestionChoice(QuestionChoiceVO questionChoiceVO,String examId) throws Exception {
        Integer count = questionChoiceMapper.addQuestionChoice(questionChoiceVO);
        if(count>0){
            if(examId !=null){
                ExamQuestionVO examQuestionVO =new ExamQuestionVO();
                examQuestionVO.setFkExam(Integer.parseInt(examId));
                examQuestionVO.setFkQtype(1);
                examQuestionVO.setFkQuestion(questionChoiceVO.getId());
                return examQuestionService.addExamQuestion(examQuestionVO,Integer.parseInt(examId),questionChoiceVO.getScore().intValue());
            }
            return new Result(Result.CODE_SUCCESS,"添加成功");
        }
        throw new BusinessException("添加错误");
    }

    @Override
    public Result addQuestionJudge(QuestionJudgeVO questionJudgeVO,String examId) throws Exception {

        Integer count = questionJudgeMapper.addQuestionJudge(questionJudgeVO);
        if(count>0){
            if(examId !=null){
                //在试卷管理页面添加的题目，将试卷和题目进行封装
                //试卷id和 题目id
                ExamQuestionVO examQuestionVO =new ExamQuestionVO();
                examQuestionVO.setFkExam(Integer.parseInt(examId));
                examQuestionVO.setFkQuestion(questionJudgeVO.getId());
                examQuestionVO.setFkQtype(3);
                return examQuestionService.addExamQuestion(examQuestionVO,Integer.parseInt(examId),questionJudgeVO.getScore().intValue());
            }
            return Result.getSuccess("添加成功");
        }
        throw new BusinessException("添加错误");
    }
    @Override
    public Result updateQuestionChoice(QuestionChoiceVO questionChoiceVO) throws Exception {
        Integer count = questionChoiceMapper.updateQuestionChoice(questionChoiceVO);
        if(count>0){
            return new Result(Result.CODE_SUCCESS,"更新成功");
        }
        throw new BusinessException("更新错误");
    }

    @Override
    public Result updateQuestionJudge(QuestionJudgeVO questionJudgeVO) throws Exception {
        Integer count = questionJudgeMapper.updateQuestionJudge(questionJudgeVO);
        if(count>0){
            return new Result(Result.CODE_SUCCESS,"更新成功");
        }
        throw new BusinessException("更新错误");

    }
/**
 * 解析判断题
 *
 */
    public void parsingJudegeQuestion(List<QuestionJudgeDO> judgeDOS,ArrayList<QuestionListVO> qlvs){
        for(QuestionJudgeDO judgeDO :judgeDOS){
            QuestionListVO vo =new QuestionListVO();
            BeanUtils.copyProperties(judgeDO,vo);
            vo.setTitle(judgeDO.getQuestion());
            vo.setType(judgeDO.getType()+"");
            vo.setPoint(judgeDO.getScore().intValue());
            qlvs.add(vo);
        }
    }
    /**
     * 解析选择题
     */
    public void parsingChoiceQuestion(List<QuestionChoiceDO> choiceDOS,ArrayList<QuestionListVO> qlvs){
        for(QuestionChoiceDO choiceDO:choiceDOS){
            QuestionListVO vo =new QuestionListVO();
            BeanUtils.copyProperties(choiceDO,vo);
            vo.setTitle(choiceDO.getQuestion());
            vo.setPoint(choiceDO.getScore().intValue());
            vo.setType(choiceDO.getType()+"");
            qlvs.add(vo);
        }
    }
}
