package com.jf.exam.controller.manage;

import com.jf.exam.pojo.vo.StudentListVO;
import com.jf.exam.pojo.vo.StudentVO;
import com.jf.exam.service.StudentService;
import com.jf.exam.utils.DataUtils;
import com.jf.exam.utils.PageBean;
import com.jf.exam.utils.Result;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/admin/student")
public class ManagerStudentController {
    @Autowired
    StudentService studentService;
    @Value("#{properties['student.pageSize']}")
    int pageSize=5;
    @Value("#{properties['student.pageNumber']}")
    int pageNumber=5;
    @RequestMapping("/list")
    public String list(StudentVO studentVO,Model model) throws Exception {
        model.addAttribute("function",5);
        studentVO.setPageSize(pageSize);
        studentVO.setSize(pageNumber);
        studentVO.setPageCode(DataUtils.getPageCode(studentVO.getPageCode()+""));
        PageBean<StudentListVO> pageBean = studentService.listStudent(studentVO);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("studentVO",studentVO);
        return "admin/student_list";
    }
    /**
     * 检查学生是否存在
     *
     * @param student 学生id
     * @return
     */
    @RequestMapping("/check")
    @ResponseBody
    public Result check(StudentVO student) {
        return studentService.isExist(student);
    }
    @RequestMapping("/add")
    @ResponseBody
    public Result add(StudentVO studentVO,Integer clazz) throws Exception {
        studentVO.setId(UUID.randomUUID().toString());
        studentVO.setModified(0);
        studentVO.setPassword(new Md5Hash("admin", studentVO.getName()).toString());
        studentVO.setFkClass(clazz);
       return  studentService.addStudent(studentVO);
    }
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(StudentVO studentVO,Integer clazz) throws Exception {
        studentVO.setFkClass(clazz);
        return studentService.updateStudent(studentVO);
    }
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id) throws Exception {
        StudentVO studentVO =new StudentVO();
        studentVO.setId(id);
        return studentService.deleteStudent(studentVO);
    }
}
