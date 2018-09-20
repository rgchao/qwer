package com.jf.exam.pojo.vo;

import java.io.Serializable;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
public class QuestionTypeVO implements Serializable {
    private static final long serialVersionUID = -7918181822479404184L;

    private Integer id;

    /**
	 * 题目类型
	 */
	private String type;

    public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
	}
    public String getType() {
		return type;
	}
    public void setType(String type) {
		this.type = type;
	}
}
