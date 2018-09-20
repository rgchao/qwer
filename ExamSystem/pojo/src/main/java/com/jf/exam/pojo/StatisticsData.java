package com.jf.exam.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 存储统计的信息
 */
public class StatisticsData {

    //所统计的试卷的题目
    private String title;

    //试卷总分
    private int examPoints;

    //参加的人数
    private int personCount;


    //在60% 80% 90%节点上具体是多少分，注意此处用了int而不是double
    private int sixtyPoint;
    private int eighttyPoint;
    private int ninetyPoint;


    //最高分学生的姓名及分数
    private List<String> highestNames = new ArrayList<String>();
    private int highestPoint;

    //最低分学生的姓名以及分数
    private List<String> lowestNames = new ArrayList<>();
    private int lowestPoint;

    //总分60%以下(不含60%)
    private List<Integer> underSixty = new ArrayList<>();
    //[60%-80%)
    private List<Integer> sixtyAndEighty = new ArrayList<>();
    //[80%-90%)
    private List<Integer> eightyAndNinety = new ArrayList<>();
    //[90%-100%]
    private List<Integer> aboveNinety = new ArrayList<>();

    private String url;//图片的url

    /**
     * 添加分数最高的学生姓名
     */
    public void addHightestName(String names) {
        this.highestNames.add(names);
    }

    /**
     * 添加分数最低的学生姓名
     */
    public void addLowestNames(String name) {
        this.lowestNames.add(name);
    }

    /**
     * 添加一个低于60%的分数
     */
    public void addUnderSixty(int point) {
        this.underSixty.add(point);
    }

    /**
     * 添加一个[60%-80%)之间的分数
     *
     * @param point
     */
    public void addSixtyAndEighty(int point) {
        this.sixtyAndEighty.add(point);
    }

    /**
     * 添加一个[80%-90%)之间的分数
     *
     * @param point
     */
    public void addEightyAndNinety(int point) {
        this.eightyAndNinety.add(point);
    }

    /**
     * 添加一个[90%-100%]间的分数
     *
     * @param point
     */
    public void addAboveNinety(int point) {
        this.aboveNinety.add(point);
    }


    public List<Integer> getUnderSixty() {
        return Collections.unmodifiableList(underSixty);
    }

    public List<Integer> getSixtyAndEighty() {
        return Collections.unmodifiableList(sixtyAndEighty);
    }

    public List<Integer> getEightyAndNinety() {
        return Collections.unmodifiableList(eightyAndNinety);
    }

    public List<Integer> getAboveNinety() {
        return Collections.unmodifiableList(aboveNinety);
    }



    //--------------------------------------------------------------------------

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getExamPoints() {
        return examPoints;
    }

    public void setExamPoints(int examPoints) {
        this.examPoints = examPoints;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getSixtyPoint() {
        return sixtyPoint;
    }

    public void setSixtyPoint(int sixtyPoint) {
        this.sixtyPoint = sixtyPoint;
    }

    public int getEighttyPoint() {
        return eighttyPoint;
    }

    public void setEighttyPoint(int eighttyPoint) {
        this.eighttyPoint = eighttyPoint;
    }

    public int getNinetyPoint() {
        return ninetyPoint;
    }

    public void setNinetyPoint(int ninetyPoint) {
        this.ninetyPoint = ninetyPoint;
    }

    public List<String> getHighestNames() {
        return highestNames;
    }

    public void setHighestNames(List<String> highestNames) {
        this.highestNames = highestNames;
    }

    public int getHighestPoint() {
        return highestPoint;
    }

    public void setHighestPoint(int highestPoint) {
        this.highestPoint = highestPoint;
    }

    public List<String> getLowestNames() {
        return lowestNames;
    }

    public void setLowestNames(List<String> lowestNames) {
        this.lowestNames = lowestNames;
    }

    public int getLowestPoint() {
        return lowestPoint;
    }

    public void setLowestPoint(int lowestPoint) {
        this.lowestPoint = lowestPoint;
    }

    public void setUnderSixty(List<Integer> underSixty) {
        this.underSixty = underSixty;
    }

    public void setSixtyAndEighty(List<Integer> sixtyAndEighty) {
        this.sixtyAndEighty = sixtyAndEighty;
    }

    public void setEightyAndNinety(List<Integer> eightyAndNinety) {
        this.eightyAndNinety = eightyAndNinety;
    }

    public void setAboveNinety(List<Integer> aboveNinety) {
        this.aboveNinety = aboveNinety;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
