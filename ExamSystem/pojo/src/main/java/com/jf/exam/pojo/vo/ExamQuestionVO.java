package com.jf.exam.pojo.vo;

import java.io.Serializable;

/** 
 * <br/>
 * Created by chao on 2018/07/25
 */
public class ExamQuestionVO extends  Query implements Serializable {
    private static final long serialVersionUID = -7875169443278917704L;

    private Integer id;

    private Integer fkExam;

    private Integer fkQuestion;

    /**
	 * 题目类型
	 */
	private Integer fkQtype;

    private Integer delFlag;
	//用于更新试卷的分数
	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

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
