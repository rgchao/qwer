package com.jf.exam.pojo.vo;

import com.jf.exam.pojo.data.ClassDO;
import com.jf.exam.pojo.data.GradeDO;
import com.jf.exam.pojo.data.MajorDO;

import java.io.Serializable;

/**
 * <br/>
 * Created by weidong on 2018/07/18
 */
public class StudentListVO implements Serializable {
    private static final long serialVersionUID = -6837270121265009270L;
    private String id;
    private String name;

    /**
     * 学号
     */
    private String sno;

    ClassDO clazz;
    GradeDO grade;
    MajorDO major;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassDO getClazz() {
        return clazz;
    }

    public void setClazz(ClassDO clazz) {
        this.clazz = clazz;
    }

    public GradeDO getGrade() {
        return grade;
    }

    public void setGrade(GradeDO grade) {
        this.grade = grade;
    }

    public MajorDO getMajor() {
        return major;
    }

    public void setMajor(MajorDO major) {
        this.major = major;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
}
