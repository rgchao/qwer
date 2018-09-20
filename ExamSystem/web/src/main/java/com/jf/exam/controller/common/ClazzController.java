package com.jf.exam.controller.common;

import com.jf.exam.pojo.data.ClassDO;
import com.jf.exam.pojo.vo.ClassVO;
import com.jf.exam.service.ClassService;
import com.jf.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 通过ajax方式获取班级信息
 * 因为整个项目都会用到次ajax请求，
 * 所以将该方法提取到单独的Controller中
 */
@Controller
@RequestMapping("/clazz")
public class ClazzController {
    @Autowired
    ClassService classService;
    /**
     * 根据年级id和专业id获取班级信息
     *
     * @param grade 年级id
     * @param major 专业id
     * @return
     */
    @RequestMapping("/ajax")
    @ResponseBody
    public Result<List<ClassDO>> ajax(Integer grade,Integer major){
        ClassVO classVO =new ClassVO();
        classVO.setFkMajor(major);
        classVO.setFkGrade(grade);
        List<ClassDO> classDOS = classService.listClassByGradeAndMajor(classVO);
        return new Result<>(Result.CODE_SUCCESS,classDOS);
    }
}
