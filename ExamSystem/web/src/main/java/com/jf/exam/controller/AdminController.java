package com.jf.exam.controller;

import com.jf.exam.pojo.Manager;
import com.jf.exam.service.ManageService;
import com.jf.exam.utils.Result;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ManageService manageService;

    @RequestMapping("/password")
    public String password() {
        return "admin/password";
    }
    @RequestMapping("/password/check")
    @ResponseBody
    public Result check(String password, HttpSession session){
        Manager manager = (Manager) session.getAttribute("manager");
        Md5Hash md5Hash =new Md5Hash(password,manager.getName());
        if(manager.getPassword().equals(md5Hash.toString())){
            return  new Result( Result.CODE_SUCCESS ,"校验密码成功");
        }
        return new Result(Result.CODE_FAILURE,"校验密码错误");
    }
    @RequestMapping("/password/modify")

    public String  modify(String oldPassword, String newPassword , HttpSession session, Model model){
        Manager manager = (Manager) session.getAttribute("manager");
        Md5Hash md5Hash =new Md5Hash(oldPassword,manager.getName());
        if(!manager.getPassword().equals(md5Hash.toString())){
            return "error";
        }
        manager.setModified(true);
        manager.setPassword(new Md5Hash(newPassword,manager.getName()).toString());
        manageService.updatePassword(manager);
        model.addAttribute("message","修改成功");
        model.addAttribute("url","login");
        return "success";
    }
    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("function",1);
        return "admin/index";
    }
}
