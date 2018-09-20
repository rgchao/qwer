package com.jf.exam.controller.manage;

import com.jf.exam.pojo.data.ClassDO;
import com.jf.exam.pojo.data.GradeDO;
import com.jf.exam.pojo.data.MajorDO;
import com.jf.exam.pojo.vo.ClassListVO;
import com.jf.exam.pojo.vo.ClassVO;
import com.jf.exam.service.ClassService;
import com.jf.exam.service.GradeService;
import com.jf.exam.service.MajorService;
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

import java.util.List;

@Controller
@RequestMapping("/admin/clazz")
public class ManagerClassController {
   @Autowired
    ClassService classService;
   @Autowired
    GradeService gradeService;
   @Autowired
    MajorService majorService;
    @Value("#{properties['clazz.pageSize']}")
    int pageSize=5;
    @Value("#{properties['clazz.pageNumber']}")
    int pageNumber=5;

    @RequestMapping("/list")
   public String list(ClassVO classVO, Model model) throws Exception {
        //model.addAttribute("function",4);
       classVO.setPageSize(pageSize);
       classVO.setSize(pageNumber);
       classVO.setPageCode(DataUtils.getPageCode(classVO.getPageCode()+""));
       PageBean<ClassListVO> pageBean = classService.listClass(classVO);

       //查询年级列表
        List<GradeDO> grades =gradeService.listAll();

        //查询专业列表
        List<MajorDO> majors =majorService.listAll();

       model.addAttribute("pageBean",pageBean);
        //将查询封装的数据自己返回，用于查询回显
       model.addAttribute("classVO",classVO);
       model.addAttribute("grades",grades);
       model.addAttribute("majors",majors);
       return "admin/clazz_list";
   }
   @RequestMapping("/add")
   @ResponseBody
   public Result add(Model model,Integer grade, Integer major, Integer clazz) throws Exception {
        ClassVO classVO =new ClassVO();
        classVO.setFkGrade(grade);
        classVO.setFkMajor(major);
        classVO.setCno(clazz);
        return classService.addClass(classVO);
   }
   @RequestMapping("/delete/{cid}")
   @ResponseBody
    public Result delete(@PathVariable Integer cid) throws Exception {
       ClassVO classVO =new ClassVO();
       classVO.setId(cid);
       return classService.deleteClass(classVO);
   }
}
