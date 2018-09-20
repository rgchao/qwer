package com.jf.exam.controller.common;

import com.jf.exam.mapper.MajorMapper;
import com.jf.exam.pojo.data.MajorDO;
import com.jf.exam.pojo.vo.GradeVO;
import com.jf.exam.service.MajorService;
import com.jf.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

@RequestMapping("/major")
public class MajorController {
    /**
     * 请求专业信息接口
     * 1. 添加班级时，需要获取所有专业信息
     * 2. 添加学生时，需要根据年级id来查询对应的专业
     */
    @Autowired
    MajorService majorService;
    @RequestMapping("/ajax")
    @ResponseBody
    public Result<List<MajorDO>> ajax(Integer grade){
        //根据年级id查询专业信息
        GradeVO gradeVO =new GradeVO();
        gradeVO.setId(grade);
        Result result = majorService.listMajorByGrade(gradeVO);
        return result;
    }
}
