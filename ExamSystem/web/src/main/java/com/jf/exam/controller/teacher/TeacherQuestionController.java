package com.jf.exam.controller.teacher;

import com.jf.exam.pojo.data.TeacherDO;
import com.jf.exam.pojo.vo.*;
import com.jf.exam.service.QuestionService;
import com.jf.exam.utils.DataUtils;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.QuestionType;
import com.jf.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;

@Controller
@RequestMapping("/teacher/question")
public class TeacherQuestionController {
    @Autowired
    QuestionService questionService;
    @Value("#{properties['question.pageSize']}")
    private int pageSize;
    @Value("#{properties['question.pageNumber']}")
    private int pageNumber;
    @RequestMapping("/singles")
    public String singlesHelper(QuestionChoiceVO questionChoiceVO, Model model, HttpSession session) throws Exception {
        model.addAttribute("function",4);
        return singles(1,questionChoiceVO,model,session);
    }

    @RequestMapping("/singles/{pn}")
    public String singles(@PathVariable Integer pn,QuestionChoiceVO questionChoiceVO, Model model, HttpSession session) throws Exception {
        questionChoiceVO.setPageCode(pn);
        questionChoiceVO.setType(QuestionType.SINGLE);

        model.addAttribute("type",QuestionType.SINLE_EN);
        return questionChoice(questionChoiceVO,model,session);
    }

    @RequestMapping("/multis")
    public String multisHelper(QuestionChoiceVO questionChoiceVO, Model model, HttpSession session) throws Exception {
        model.addAttribute("function",5);
        return multis(1,questionChoiceVO,model,session);
    }

    @RequestMapping("/multis/{pn}")
    public String multis(@PathVariable Integer pn,QuestionChoiceVO questionChoiceVO, Model model, HttpSession session) throws Exception {
        questionChoiceVO.setPageCode(pn);
        questionChoiceVO.setType(QuestionType.MULTI);
        model.addAttribute("type",QuestionType.MULTI_EN);
        return questionChoice(questionChoiceVO,model,session);
    }
    //抽取
    public String questionChoice(QuestionChoiceVO questionChoiceVO, Model model,HttpSession session) throws Exception {
        questionChoiceVO.setPageSize(pageSize);
        questionChoiceVO.setSize(pageNumber);
        questionChoiceVO.setPageCode(DataUtils.getPageCode(questionChoiceVO.getPageCode()+""));
        TeacherDO teacher = (TeacherDO) session.getAttribute("teacher");
        questionChoiceVO.setFkTeacher(teacher.getId());
        PageBean<QuestionListVO> pageBean = questionService.listQuestionChoice(questionChoiceVO);
        model.addAttribute("pageBean",pageBean);
        return "teacher/question_list";
    }

    @RequestMapping("/judges")
    public String judgesHelper(QuestionJudgeVO questionJudgeVO, Model model,HttpSession session) throws Exception {
        return judges(1,questionJudgeVO,model,session);
    }
    @RequestMapping("/judges/{pn}")
    public String judges(@PathVariable Integer pn, QuestionJudgeVO questionJudgeVO, Model model,HttpSession session) throws Exception {
        model.addAttribute("function",6);
        questionJudgeVO.setPageSize(pageSize);
        questionJudgeVO.setSize(pageNumber);
        questionJudgeVO.setPageCode(DataUtils.getPageCode(questionJudgeVO.getPageCode()+""));
        questionJudgeVO.setPageCode(pn);
        questionJudgeVO.setType(QuestionType.JUDGE);
        PageBean<QuestionListVO> pageBean = questionService.listQuestionJudge(questionJudgeVO);
        TeacherDO teacher = (TeacherDO) session.getAttribute("teacher");
        questionJudgeVO.setFkTeacher(teacher.getId());
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("type",QuestionType.SINLE_EN);
        model.addAttribute("");
        return "teacher/question_list";
    }
    /**
     * 添加、更新题目
     * 如果id为-1 表示是添加
     * 如果id不是-1 表示是更新操作
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(QuestAddVO questionAddVO,String examId,  HttpSession session) throws Exception {
        //添加题目需要记录是哪个教师添加的
        TeacherDO teacherDO = (TeacherDO) session.getAttribute("teacher");
        //判断题
        if (questionAddVO.getType().equals(QuestionType.JUDGE_EN)) {
            QuestionJudgeVO vo = new QuestionJudgeVO();
            vo.setId(null);
            vo.setFkTeacher(teacherDO.getId());
            vo.setAnswer(questionAddVO.getAnswer());
            vo.setScore(new BigDecimal(questionAddVO.getPoint()));
            vo.setQuestion(questionAddVO.getTitle());
            vo.setType(QuestionType.JUDGE);
            vo.setDelFalg(0);
            if (questionAddVO.getId() == -1 || questionAddVO.getId() == 0) {
                //添加的是判断题
                return questionService.addQuestionJudge(vo,examId);
            } else {
                //新添加的题目，需要记录是哪个教师添加的
                vo.setId(questionAddVO.getId());
                //更新
                return questionService.updateQuestionJudge(vo);
            }
        }else {
            QuestionChoiceVO vo = new QuestionChoiceVO();
            vo.setQuestion(questionAddVO.getTitle());
            vo.setOptiona(questionAddVO.getOptionA());
            vo.setOptionb(questionAddVO.getOptionB());
            vo.setOptionc(questionAddVO.getOptionC());
            vo.setOptiond(questionAddVO.getOptionD());
            vo.setAnswer(questionAddVO.getAnswer());
            vo.setScore(new BigDecimal(questionAddVO.getPoint()));
            vo.setDelFalg(0);
            //判断题目的类型
            if(questionAddVO.getType().equals(QuestionType.SINLE_EN)){
                vo.setType(QuestionType.SINGLE);//单选题
            }else {
                vo.setType(QuestionType.MULTI);//多选题
            }
            //新添加的是选择题
            if(questionAddVO.getId()==-1 ||questionAddVO.getId()==0){
                vo.setFkTeacher(teacherDO.getId());
                return questionService.addQuestionChoice(vo,examId);
            }else {
                //新添加的题目，需要记录是哪个教师添加的
                vo.setId(questionAddVO.getId());
                //更新
                return questionService.updateQuestionChoice(vo);
            }
        }
    }
    @RequestMapping("/repository")
    @ResponseBody
    public Result repository(@RequestBody QuestionBankVO questionBankVO) throws Exception {
        return questionService.listAllByType(questionBankVO);
    }

    @RequestMapping("/addExamQuestion")
    @ResponseBody
    public Result addExamQuestion(@RequestBody QuestionBankVO questionBankVO) throws Exception {
        //{"types":[{"id":"1","point":"10"},{"id":"21","point":"1"}],"examId":"1","type":"1"}
        System.out.println(questionBankVO);
        //构造试卷和题目关系的对象
        ArrayList<ExamQuestionVO> examQuestionVOS =new ArrayList<>();
        for (TypesVO typesVO: questionBankVO.getTypes()
             ) {
            ExamQuestionVO examQuestionVO =new ExamQuestionVO();
            //题目类型，再更新试卷信息时用到
            examQuestionVO.setFkQtype(Integer.parseInt(questionBankVO.getType()));
            examQuestionVO.setFkExam(Integer.parseInt(questionBankVO.getExamId()));
            examQuestionVO.setFkQuestion(Integer.parseInt(typesVO.getId()));

            //题目分数，在更新题目试卷时用到
            examQuestionVO.setScore(Integer.parseInt(typesVO.getPoint()));
            examQuestionVOS.add(examQuestionVO);
        }
        return questionService.addExamQuestion(examQuestionVOS,Integer.parseInt(questionBankVO.getExamId()));
    }
}
