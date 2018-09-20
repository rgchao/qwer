package com.jf.exam.pojo.vo;

import java.io.Serializable;
import java.util.Date;

/** 
 * <br/>
 * Created by chao on 2018/07/23
 */
public class ExamVO extends Query implements Serializable {
    private static final long serialVersionUID = -5627858693140378513L;

    private Integer id;

    private String title;

    private Integer timelimit;

    private Date endtime;
	private String statusFacade;

	public String getStatusFacade() {
		return statusFacade;
	}

	public void setStatusFacade(String statusFacade) {
		this.statusFacade = statusFacade;
	}

	private Integer fkStatus;

    private Integer points;

    private Integer singlepoints;

    private Integer multipoints;

    private Integer judgepoints;

    private String fkTeacher;

    private Integer delFlag;
	public static ExamVO getExamVOById(Integer id){
		ExamVO examVO = new ExamVO();
		examVO.setId(id);
		return examVO;
	}
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
    public Integer getTimelimit() {
		return timelimit;
	}
    public void setTimelimit(Integer timelimit) {
		this.timelimit = timelimit;
	}
    public Date getEndtime() {
		return endtime;
	}
    public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
    public Integer getFkStatus() {
		return fkStatus;
	}
    public void setFkStatus(Integer fkStatus) {
		this.fkStatus = fkStatus;
	}
    public Integer getPoints() {
		return points;
	}
    public void setPoints(Integer points) {
		this.points = points;
	}
    public Integer getSinglepoints() {
		return singlepoints;
	}
    public void setSinglepoints(Integer singlepoints) {
		this.singlepoints = singlepoints;
	}
    public Integer getMultipoints() {
		return multipoints;
	}
    public void setMultipoints(Integer multipoints) {
		this.multipoints = multipoints;
	}
    public Integer getJudgepoints() {
		return judgepoints;
	}
    public void setJudgepoints(Integer judgepoints) {
		this.judgepoints = judgepoints;
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
