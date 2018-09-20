package com.jf.exam.controller;

import com.jf.exam.pojo.Manager;
import com.jf.exam.pojo.Student;
import com.jf.exam.pojo.Teacher;
import com.jf.exam.pojo.data.StudentDO;
import com.jf.exam.pojo.data.TeacherDO;
import com.jf.exam.pojo.vo.StudentVO;
import com.jf.exam.pojo.vo.TeacherVO;
import com.jf.exam.service.ManageService;
import com.jf.exam.shiro.UsernamePasswordTypeToken;
import com.jf.exam.utils.DataUtils;
import com.jf.exam.utils.Result;
import com.jf.exam.utils.WYYTest;
import com.jf.exam.utils.randomUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.cs.Surrogate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    ManageService manageService;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/login/do")
    public String doLogin(String username, String password, String verify, int role, Model model, HttpSession session){
        Subject subject =SecurityUtils.getSubject();
        try {
            switch (role){
                case 1:
                    subject.login(new UsernamePasswordTypeToken(username, password,UsernamePasswordTypeToken.SUTDENT));
                   StudentDO student = (StudentDO) subject.getPrincipal();
                    session.setAttribute("student",student);
                    return "student/index";

                case 2:
                    subject.login(new UsernamePasswordTypeToken(username,password,UsernamePasswordTypeToken.TEACHER));
                    TeacherDO teacher= (TeacherDO) subject.getPrincipal();
                    session.setAttribute("teacher",teacher);
                    return "teacher/index";

                case 3:
                    UsernamePasswordTypeToken token =new UsernamePasswordTypeToken(username,password,UsernamePasswordTypeToken.ADMIN);
                    subject.login(token);
                   //获取登陆的信息，存入session域
                    Manager manager = (Manager) subject.getPrincipal();
                    session.setAttribute("manager",manager);
                    return "admin/index";
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            model.addAttribute("error","用户名或密码错误");
            return "login";
        }
        return "login";
    }
    /**
     * 校验验证码
     * 返回json格式的数据
     * {code:200,msg:验证成功}
     * @param verify
     * @param session
     */
    @RequestMapping("/login/verify")
    @ResponseBody
    public Result verify(String verify, HttpSession session){
        return DataUtils.checkVerify(verify,session);
    }


    @RequestMapping(value = "/duanxin", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String sendCode(ModelMap model,String phone,HttpSession session){
        String result="";
//        Map<String, Object> condMap = new HashMap<String, Object>();
//        condMap.put("phone",phone);
        randomUtil util = new randomUtil();
        String appKey = "vphLX4J74uIcfa6chfFdSrUz3vThIb";
        String appSecret = "LTAIp3txqPYOT8qs";

        String nonce = randomUtil.math(6);
        String str2 = WYYTest.sendMsg(phone, appKey, appSecret, nonce);
        System.out.println(str2);
        result = nonce;
        session.setAttribute("res", nonce);
        return result;
    }


}

