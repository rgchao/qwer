package com.jf.exam.pojo.vo;

public class StudentExamResultVO implements Comparable<StudentExamResultVO>{

    private String id;
    private String name;
    private int point;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public int compareTo(StudentExamResultVO o) {
        //先根据分数进行排序
        int pointCompare = Integer.compare(o.getPoint(), this.point);
        if (pointCompare == 0) {
            //根据id排序
            return o.getId().compareTo(this.id);
        }
        return pointCompare;
    }

}
