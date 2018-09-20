package com.jf.exam.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentIndexController {
    /**
     * 学生主页
     *
     */
    @RequestMapping("/index")
        public String intdex(){
        return "student/index";
    }
}
