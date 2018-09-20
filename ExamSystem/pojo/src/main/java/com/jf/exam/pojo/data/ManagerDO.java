package com.jf.exam.pojo.data;

import java.io.Serializable;

/** 
 * <br/>
 * Created by chao on 2018/07/19
 */
public class ManagerDO implements Serializable {
    private static final long serialVersionUID = -5838263490340147222L;

    private Integer id;

    private String name;

    private String password;

    private Integer modified;

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
    public String getPassword() {
		return password;
	}
    public void setPassword(String password) {
		this.password = password;
	}
    public Integer getModified() {
		return modified;
	}
    public void setModified(Integer modified) {
		this.modified = modified;
	}
}
