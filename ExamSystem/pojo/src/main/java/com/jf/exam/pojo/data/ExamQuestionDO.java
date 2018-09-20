package com.jf.exam.pojo.data;

import java.io.Serializable;

/** 
 * <br/>
 * Created by chao on 2018/07/25
 */
public class ExamQuestionDO implements Serializable {
    private static final long serialVersionUID = -6203704051799309495L;

    private Integer id;

    private Integer fkExam;

    private Integer fkQuestion;

    /**
	 * 题目类型
	 */
	private Integer fkQtype;

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
    public Integer getFkQuestion() {
		return fkQuestion;
	}
    public void setFkQuestion(Integer fkQuestion) {
		this.fkQuestion = fkQuestion;
	}
    public Integer getFkQtype() {
		return fkQtype;
	}
    public void setFkQtype(Integer fkQtype) {
		this.fkQtype = fkQtype;
	}
    public Integer getDelFlag() {
		return delFlag;
	}
    public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
