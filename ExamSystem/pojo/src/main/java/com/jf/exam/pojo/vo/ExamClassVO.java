package com.jf.exam.pojo.vo;

import java.io.Serializable;

/** 
 * <br/>
 * Created by chao on 2018/07/24
 */
public class ExamClassVO implements Serializable {
    private static final long serialVersionUID = -8174503694125454368L;

    private Integer id;

    private Integer fkExam;

    private Integer fkClass;

    private Integer delFlag;

    public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
	}
    public Integer getFkExam() {
		return fkExam;
	}
    public void setFkExam(Integer fkExam) {
		this.fkExam = fkExam;
	}
    public Integer getFkClass() {
		return fkClass;
	}
    public void setFkClass(Integer fkClass) {
		this.fkClass = fkClass;
	}
    public Integer getDelFlag() {
		return delFlag;
	}
    public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
