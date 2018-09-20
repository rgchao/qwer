package com.jf.exam.pojo.data;

import java.io.Serializable;

/** 
 * <br/>
 * Created by chao on 2018/07/21
 */
public class QuestionTypeDO implements Serializable {
    private static final long serialVersionUID = -6241571706607838959L;

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
