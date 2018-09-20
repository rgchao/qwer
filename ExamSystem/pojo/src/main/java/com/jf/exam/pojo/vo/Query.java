package com.jf.exam.pojo.vo;

import java.util.HashMap;
import java.util.Map;

public class Query {
    private int pageSize;
    private int size;
    //排序字段，默认按照id进行排序
    public Map sortByParams = new HashMap();
    {
        sortByParams.put("id","ASC");
    }
    private int pageCode;//当前的页码

    private int startRecord;//用户分页数据

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getPageCode() {
        return pageCode;
    }

    public void setPageCode(int pageCode) {
        this.pageCode = pageCode;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public Map getSortByParams() {
        return sortByParams;
    }

    public void setSortByParams(Map sortByParams) {
        this.sortByParams = sortByParams;
    }
}
