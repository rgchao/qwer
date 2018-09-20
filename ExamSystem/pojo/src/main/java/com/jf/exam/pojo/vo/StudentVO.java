package com.jf.exam.pojo.vo;

import java.io.Serializable;

/** 
 * <br/>
 * Created by chao on 2018/07/20
 */
public class StudentVO extends Query implements Serializable {
    private static final long serialVersionUID = -5490089079648567042L;

    private String id;

    /**
	 * 学号
	 */
	private String sno;

    private String name;

    private String password;

    private Integer fkClass;

    private Integer modified;

    private String delFlag;

	public StudentVO() {
	}

	public StudentVO(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
    public void setId(String id) {
		this.id = id;
	}
    public String getSno() {
		return sno;
	}
    public void setSno(String sno) {
		this.sno = sno;
	}
    public String getName() {
		return name;
	}
    public void setName(String name) {
		this.name = name;
	}
    public String getPassword() {
		return password;
	}
    public void setPassword(String password) {
		this.password = password;
	}
    public Integer getFkClass() {
		return fkClass;
	}
    public void setFkClass(Integer fkClass) {
		this.fkClass = fkClass;
	}
    public Integer getModified() {
		return modified;
	}
    public void setModified(Integer modified) {
		this.modified = modified;
	}
    public String getDelFlag() {
		return delFlag;
	}
    public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
