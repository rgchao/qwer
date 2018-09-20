package com.jf.exam.controller.teacher;

import com.jf.exam.pojo.vo.ExamClassVO;
import com.jf.exam.pojo.vo.ExamVO;
import com.jf.exam.service.ClassService;
import com.jf.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/teacher/clazz")
@ResponseBody
public class TeacherClazzController {
    @Autowired
    ClassService classService;
    @RequestMapping("/list")
    public Result list(String examId){
        //根据试卷id查询适用班级
        return  classService.listByExam(ExamVO.getExamVOById(Integer.parseInt(examId)));

    }
    @RequestMapping("/reset")
    @ResponseBody
    public Result reset(String examId, String clazzIds){
        //[{clazzIds:111},]
        //将试卷和班级进行关联
        ArrayList<ExamClassVO> vos=new ArrayList<>();
        if(clazzIds!=null&&!clazzIds.equals("")){//没有班级数据
            for (String id: clazzIds.split(",")) {
                ExamClassVO vo =new ExamClassVO();
                vo.setFkExam(Integer.parseInt(examId));
                vo.setFkClass(Integer.parseInt(id));
                vos.add(vo);
            }
        }
        return classService.addExamClass(examId,vos);
    }
}
