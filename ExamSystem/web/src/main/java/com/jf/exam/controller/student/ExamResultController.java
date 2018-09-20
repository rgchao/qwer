package com.jf.exam.controller.student;

import com.jf.exam.pojo.data.StudentDO;
import com.jf.exam.pojo.vo.ExamResultViewVO;
import com.jf.exam.pojo.vo.ExaminationResultVO;
import com.jf.exam.service.ExaminationResultService;
import com.jf.exam.utils.DataUtils;
import com.jf.exam.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/student/notes")
public class ExamResultController {
    @Autowired
    ExaminationResultService examinationResultService;
     @Value("#{properties['student.examinationResult.pageSize']}")
    private int pageSize;
     @Value("#{properties['student.examinationResult.pageNumber']}")
    private int  pageNumber;
     @RequestMapping
    public String notes(HttpSession session, Model model) throws Exception {
         return notes("1",session,model);
     }
     @RequestMapping("/{pn}")
     public String notes(@PathVariable String pn,HttpSession session,Model model) throws Exception {
         StudentDO studentDO = (StudentDO) session.getAttribute("student");
         int pageCode =DataUtils.getPageCode(pn);
         ExaminationResultVO  vo =new ExaminationResultVO();
         vo.setFkStudent(studentDO.getId());
         vo.setPageCode(pageCode);
         vo.setPageSize(pageSize);
         vo.setSize(pageNumber);
         PageBean<ExaminationResultVO> pageBean =examinationResultService.listExaminationResult(vo);
         model.addAttribute("pageBean",pageBean);
         return "student/examinationResult_list";
     }
     @RequestMapping("/view/{eid}")
    public String view(@PathVariable String eid, HttpSession session,Model model){
         StudentDO student = (StudentDO) session.getAttribute("student");

         ExamResultViewVO viewVO =examinationResultService.getERViewById(eid,student.getId());

         model.addAttribute("view",viewVO);

         return "student/examinationResult_view";
     }
}
