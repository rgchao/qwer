package com.jf.exam.pojo.vo;

import java.util.ArrayList;

public class QuestionBankVO {
    String type;
    ArrayList<TypesVO> types;
    //针对添加的操作，需要试卷id
    String examId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<TypesVO> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<TypesVO> types) {
        this.types = types;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }
}
