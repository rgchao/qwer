package com.jf.exam.controller.manage;

import com.jf.exam.pojo.data.TeacherDO;
import com.jf.exam.pojo.vo.StudentVO;
import com.jf.exam.pojo.vo.TeacherVO;
import com.jf.exam.service.TeacherService;
import com.jf.exam.utils.DataUtils;
import com.jf.exam.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/teacher")
public class ManagerTeacherController {
    @Autowired
    TeacherService teacherService;
    @Value("#{properties['teacher.pageSize']}")
    int pageSize=5;
    @Value("#{properties['teacher.pageNumber']}")
    int pageNumber=5;
    @RequestMapping("/list")
    public String list(TeacherVO teacherVO, Model model) throws Exception {
        model.addAttribute("function",6);
        teacherVO.setPageSize(pageSize);
        teacherVO.setSize(pageNumber);
        teacherVO.setPageCode(DataUtils.getPageCode(teacherVO.getPageCode()+""));
        PageBean<TeacherDO> pageBean =teacherService.listTeacher(teacherVO);
        model.addAttribute("pageBean",pageBean);
        return "admin/teacher_list";
    }
}
