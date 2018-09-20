package com.jf.exam.pojo.vo;

import java.io.Serializable;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
public class QuestionVO implements Serializable {
    private static final long serialVersionUID = -7592800663452338196L;

    private Integer id;

    private String title;

    private String optiona;

    private String optionb;

    private String optionc;

    private String optiond;

    private Integer point;

    private String type;

    private String answer;

    private String fkTeacher;

    private Integer delFlag;

    public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
	}
    public String getTitle() {
		return title;
	}
    public void setTitle(String title) {
		this.title = title;
	}
    public String getOptiona() {
		return optiona;
	}
    public void setOptiona(String optiona) {
		this.optiona = optiona;
	}
    public String getOptionb() {
		return optionb;
	}
    public void setOptionb(String optionb) {
		this.optionb = optionb;
	}
    public String getOptionc() {
		return optionc;
	}
    public void setOptionc(String optionc) {
		this.optionc = optionc;
	}
    public String getOptiond() {
		return optiond;
	}
    public void setOptiond(String optiond) {
		this.optiond = optiond;
	}
    public Integer getPoint() {
		return point;
	}
    public void setPoint(Integer point) {
		this.point = point;
	}
    public String getType() {
		return type;
	}
    public void setType(String type) {
		this.type = type;
	}
    public String getAnswer() {
		return answer;
	}
    public void setAnswer(String answer) {
		this.answer = answer;
	}
    public String getFkTeacher() {
		return fkTeacher;
	}
    public void setFkTeacher(String fkTeacher) {
		this.fkTeacher = fkTeacher;
	}
    public Integer getDelFlag() {
		return delFlag;
	}
    public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
