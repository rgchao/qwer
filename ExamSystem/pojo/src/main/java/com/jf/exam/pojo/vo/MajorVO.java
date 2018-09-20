package com.jf.exam.pojo.vo;

import java.io.Serializable;

/** 
 * <br/>
 * Created by chao on 2018/07/19
 */
public class MajorVO extends Query implements Serializable {
    private static final long serialVersionUID = -6643687029079314583L;

    private Integer id;

    private String name;

    private Integer delFlag;

	public MajorVO() {
	}

	public Integer getId() {
		return id;
	}
    public void setId(Integer id) {
		this.id = id;
	}
    public String getName() {
		return name;
	}
    public void setName(String name) {
		this.name = name;
	}
    public Integer getDelFlag() {
		return delFlag;
	}
    public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
