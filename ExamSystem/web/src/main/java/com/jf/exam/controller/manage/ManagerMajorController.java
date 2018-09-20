package com.jf.exam.controller.manage;

import com.jf.exam.pojo.data.MajorDO;
import com.jf.exam.pojo.vo.MajorVO;
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

@Controller
@RequestMapping("/admin/major")
public class ManagerMajorController {
    @Autowired
    MajorService majorService;
    @Value("#{properties['major.pageSize']}")
    int pageSize=5;
    @Value("#{properties['major.size']}")
    int size=5;
    @RequestMapping("/list")
    public String list(MajorVO majorVO, Model model) throws Exception {
        //majorVO.setPageCode(DataUtils.getPageCode(majorVO.getPageCode()+toString()));
        model.addAttribute("function",3);
        majorVO.setPageSize(pageSize);
        majorVO.setSize(size);
        majorVO.setPageCode(DataUtils.getPageCode(majorVO.getPageCode()+""));
        PageBean<MajorDO> pageBean = majorService.listMajor(majorVO);
        model.addAttribute("pageBean",pageBean);
        model.addAttribute("majorVO",majorVO);
        return "admin/major_list";
    }
   @RequestMapping("/add")
   @ResponseBody
    public Result add(MajorVO majorVO) throws Exception {
        return majorService.addMajor(majorVO);
   }
   @RequestMapping("/delete/{mid}")
   @ResponseBody
    public Result delete(@PathVariable(value = "mid") Integer mid) throws Exception {
        MajorVO majorVO =new MajorVO();
        majorVO.setId(mid);
        return majorService.deleteMajor(majorVO);
   }
   @RequestMapping("/edit")
   @ResponseBody
    public Result edit(MajorVO majorVO) throws Exception {
        return majorService.updateMajor(majorVO);
   }
}
