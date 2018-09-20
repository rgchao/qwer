package com.jf.exam.controller.manage;

import com.jf.exam.pojo.data.GradeDO;
import com.jf.exam.pojo.vo.GradeVO;
import com.jf.exam.service.GradeService;
import com.jf.exam.utils.DataUtils;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/grade")
public class ManagerGradeController {
    @Autowired
    GradeService gradeService;
    @Value("#{properties['grade.pageSize']}")
    int pageSize=5;
    @Value("#{properties['grade.size']}")
    int size=5;

    @RequestMapping("/list")
    public String list(@Validated GradeVO query, Model model) throws Exception {
        model.addAttribute("function",2);
        query.setPageSize(pageSize);
        query.setSize(size);
        query.setPageCode(DataUtils.getPageCode(query.getPageCode()+""));
        PageBean<GradeDO> pageBean = gradeService.listGrade(query);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("query",query);
        return "admin/grade_list";
    }

    @RequestMapping("/add/{grade}")
    @ResponseBody
    public Result add(@PathVariable(value = "grade")Integer grade) throws Exception {
        //保存到数据库
        GradeVO gradeVO =new GradeVO();
        gradeVO.setGrade(grade);
        return gradeService.addGrade(gradeVO);
    }
    @RequestMapping("/delete/{gid}")
    @ResponseBody
    public Result delete(@PathVariable(value = "gid")Integer gid) throws Exception {

            //修改数据库的
            GradeVO gradeVO =new GradeVO();
            gradeVO.setId(gid);
            return gradeService.deleteGrade(gradeVO);
    }

}
