/**
 * Copyright 2018 aTool.org
 */
package com.jf.exam.pojo.vo;

import java.util.List;

public class ExamAnswerVO {

    private String eid;
    private List<ExamAnswerQuestions> questions;

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEid() {
        return eid;
    }

    public void setQuestions(List<ExamAnswerQuestions> questions) {
        this.questions = questions;
    }

    public List<ExamAnswerQuestions> getQuestions() {
        return questions;
    }

}