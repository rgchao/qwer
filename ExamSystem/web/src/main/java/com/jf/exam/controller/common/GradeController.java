package com.jf.exam.controller.common;

import com.jf.exam.pojo.data.GradeDO;
import com.jf.exam.service.GradeService;
import com.jf.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    GradeService gradeService;
    /**
     * 返回所有的年级
     *
     */
    @RequestMapping("/ajax")
    @ResponseBody
    public Result ajax(){
        List<GradeDO> grades = gradeService.listAll();
        return new Result(Result.CODE_SUCCESS,grades);
    }
}
