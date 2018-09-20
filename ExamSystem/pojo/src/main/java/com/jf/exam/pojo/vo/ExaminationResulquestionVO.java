package com.jf.exam.pojo.vo;

import java.io.Serializable;

/**
 * <br/>
 * Created by weidong on 2018/07/27
 */
public class ExaminationResulquestionVO extends Query implements Serializable {
    private static final long serialVersionUID = -8118544675774284419L;

    private Integer id;

    /**
     * 是否正确
     */
    private Integer isRight;

    /**
     * 错误答案
     */
    private String wrongAnswer;

    /**
     * 考试结果id
     */
    private Integer fkExaminationResult;

    /**
     * 题目id
     */
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

    public Integer getIsRight() {
        return isRight;
    }

    public void setIsRight(Integer isRight) {
        this.isRight = isRight;
    }

    public String getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(String wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public Integer getFkExaminationResult() {
        return fkExaminationResult;
    }

    public void setFkExaminationResult(Integer fkExaminationResult) {
        this.fkExaminationResult = fkExaminationResult;
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
