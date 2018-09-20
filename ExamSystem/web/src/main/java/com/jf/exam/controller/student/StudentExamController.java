package com.jf.exam.controller.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jf.exam.pojo.data.StudentDO;
import com.jf.exam.pojo.vo.BeginExamVO;
import com.jf.exam.pojo.vo.ClassVO;
import com.jf.exam.pojo.vo.ExamAnswerVO;
import com.jf.exam.pojo.vo.ExamVO;
import com.jf.exam.service.ExamService;
import com.jf.exam.utils.DataUtils;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/student/exam")
public class StudentExamController {

    @Value("#{properties['student.exam.pageSize']}")
    private int pageSize;
    @Value("#{properties['student.exam.pageNumber']}")
    private int pageNumber;

    @Autowired
    ExamService examService;
    /**
     * 根据学生的班级id查询学生的试卷列表，并排除初始化状态的试卷
     *
     * @return
     */
    @RequestMapping("/list")
    public String listHelp(HttpSession session,Model model){
        return list(1,session,model);
    }

    @RequestMapping("/list/{pn}")
    public String list(@PathVariable Integer pn ,HttpSession session, Model model){
        StudentDO student = (StudentDO) session.getAttribute("student");
        ClassVO classVO =new ClassVO();
        classVO.setId(student.getFkClass());
        classVO.setPageCode(DataUtils.getPageCode(pn+""));
        classVO.setPageSize(pageSize);
        classVO.setSize(pageNumber);

        PageBean<ExamVO> pageBean = examService.listExamByClassId(classVO);
        model.addAttribute("pageBean",pageBean);
        return "student/exam_list";
    }
    @RequestMapping("/join/{eid}")
    public String join(@PathVariable Integer eid ,HttpSession session, Model model){
        StudentDO studentDO = (StudentDO) session.getAttribute("student");
        //判断是否参加考试
        if(examService.hasJoined(eid,studentDO.getId())){
            model.addAttribute("message","你已经参加过考试了");
            return "error";
        }
        //查询试卷的数据
        BeginExamVO beginExamVO =examService.joined(eid);
        model.addAttribute("exam",beginExamVO);
        model.addAttribute("eid",eid);
        return "student/exam_take";
    }
    @RequestMapping("/submit")
    @ResponseBody
    public Result subimt(String result,HttpSession session) throws IOException {
        StudentDO studentDO = (StudentDO) session.getAttribute("student");
        ObjectMapper mapper =new ObjectMapper();
       ExamAnswerVO examAnswerVO= mapper.readValue(result,ExamAnswerVO.class);
       return examService.marking(examAnswerVO,studentDO.getId());
    }
}
